package com.gildedrose;

public class BackstagePassUpdater implements ItemUpdater {
    @Override
    public void updateItem(Item item) {
        decreaseSellIn(item);
        if (item.sellIn < 0) {
            item.quality = 0;
        } else {
            increaseQuality(item);
            if (item.sellIn < 10) {
                increaseQuality(item);
            }
            if (item.sellIn < 5) {
                increaseQuality(item);
            }
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
