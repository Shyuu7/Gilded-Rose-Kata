package com.gildedrose;

public class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void updateItem(Item item) {
        decreaseSellIn(item);
        increaseQuality(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }
}
