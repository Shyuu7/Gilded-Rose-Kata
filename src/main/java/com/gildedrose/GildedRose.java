package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Princípio Aberto-Fechado: sim, é possível estender o sistema sem modificar o código existente.
 * Para adicionar um novo item, apenas é necessário:
 *  - Criar uma nova classe referente ao item desejado que implemente ItemUpdater e
 *  - Adicionar uma entrada no mapa de updaters no metodo initializeUpdaters()
 * Não é preciso modificar a lógica principal em updateQuality(); inclusive é desencorajado fazer isso.

 * 2. Princípio da Responsabilidade Única: sim, cada itemUpdater tem uma única responsabilidade
 * Cada implementação (AgedBrieUpdater, SulfurasUpdater, etc.) é responsável apenas
 * pelas regras de atualização de seu respectivo tipo de item.
 * GildedRose é responsável apenas pela coordenação dos updaters.

 * 3. Princípio de Substituição de Liskov: respeitado até o momento.
 * As implementações de ItemUpdater podem ser substituídas sem alterar o comportamento esperado do sistema,
 * desde que sigam o contrato definido pela interface ItemUpdater.
 * Comportamento padrão evita que itens não especificados quebrem o sistema.
 */

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
