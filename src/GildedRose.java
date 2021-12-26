import java.util.List;

public class GildedRose {
    public void updateQuality(List<Item> items) {
        for (Item item : items) {
            if (isNotSulfuras(item)) {
                if (isAgedBrie(item) || isBackstagePass(item)) {
                    checkAndIncreaseQuality(item);
                } else {
                    checkConjuredAndDecreaseQuality(item);
                }

                item.setSellIn(item.getSellIn() - 1);

                if (item.getSellIn() < 0) {
                    if (isBackstagePass(item)) {
                        item.setQuality(0);
                    } else if (isAgedBrie(item)) {
                        if (isQualityLessThan50(item)) {
                            increaseQuality(item);
                        }
                    } else if (isQualityMoreThanZero(item)) {
                        decreaseQuality(item);
                    }
                }
            }
        }
    }

    private void checkConjuredAndDecreaseQuality(Item item) {
        if (isConjured(item) && isQualityMoreThanZero(item)) {
            decreaseQuality(item);
        }
        if (isQualityMoreThanZero(item)) {
            decreaseQuality(item);

        }
    }

    private void checkAndIncreaseQuality(Item item) {
        if (isQualityLessThan50(item)) {
            increaseQuality(item);
            if (isBackstagePass(item)) {
                increaseQualityDependingsByDays(item);
            }
        }
    }

    private boolean isConjured(Item item) {
        return "Conjured Mana Cake".equals(item.getName());
    }

    private boolean isQualityMoreThanZero(Item item) {
        return item.getQuality() > 0;
    }

    private void increaseQualityDependingsByDays(Item item) {
        if (item.getSellIn() < 11 && isQualityLessThan50(item)) {
            increaseQuality(item);
        }
        if (item.getSellIn() < 6 && isQualityLessThan50(item)) {
            increaseQuality(item);
        }
    }

    private boolean isBackstagePass(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.getName());
    }

    private boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.getName());
    }

    private boolean isNotSulfuras(Item item) {
        return !"Sulfuras, Hand of Ragnaros".equals(item.getName());
    }

    private boolean isQualityLessThan50(Item item) {
        return item.getQuality() < 50;
    }

    private void increaseQuality(Item item) {
        item.setQuality(item.getQuality() + 1);
    }

    private void decreaseQuality(Item item) {
        item.setQuality(item.getQuality() - 1);
    }
}
