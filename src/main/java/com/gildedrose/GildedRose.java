package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            boolean isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");
            boolean isAgedBrie = item.name.equals("Aged Brie");
            boolean isBackstagePasses = item.name.equals("Backstage passes to a TAFKAL80ETC concert");

            if (isSulfuras) {
                updateSulfuras(item);
            } else if (isAgedBrie) {
                updateAgedBrie(item);
            } else if (isBackstagePasses) {
                updateBackstagePass(item);
            } else {
                updateNormalItem(item);
            }
        }
    }


    private void updateSulfuras(Item item) {
        //Sulfuras não perde qualidade nem precisa ser vendido
    }

    private void updateAgedBrie(Item item) {
        decreaseSellIn(item);
        increaseQuality(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private void updateBackstagePass(Item item) {
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

    private void updateNormalItem(Item item) {
        decreaseSellIn(item);
        decreaseQuality(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
