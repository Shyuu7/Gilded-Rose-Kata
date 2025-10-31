package com.gildedrose;

public class DefaultUpdater implements ItemUpdater {
    @Override
    public void updateItem(Item item) {
        decreaseSellIn(item);
        decreaseQuality(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }
}
