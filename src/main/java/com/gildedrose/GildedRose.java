package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    Item[] items;
    private final Map<String, ItemUpdater> updaters;

    public GildedRose(Item[] items) {
        this.items = items;
        this.updaters = new HashMap<>();
        initializeUpdaters();
    }

    //Delegando cada updater para seu respectivo item
    private void initializeUpdaters() {
        updaters.put("Sulfuras, Hand of Ragnaros", new SulfurasUpdater());
        updaters.put("Aged Brie", new AgedBrieUpdater());
        updaters.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdater());
        updaters.put("Conjured Mana Cake", new ConjuredItemUpdater());
    }

    public void updateQuality() {
        for (Item item : items) {
            //Comportamento padrão para itens não especiais
            ItemUpdater updater = updaters.getOrDefault(item.name, new DefaultUpdater());
            updater.updateItem(item);
        }
    }
}
