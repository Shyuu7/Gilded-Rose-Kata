package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GildedRoseTest {

    private GildedRose app;
    private Item[] items;

    @BeforeEach
    void setUp() {
        items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };
        app = new GildedRose(items);
    }

    @Test
    void testUpdateQualityAfterOneDay() {
        app.updateQuality();
        assertEquals(9, items[0].sellIn); // +5 Dexterity Vest
        assertEquals(19, items[0].quality);

        assertEquals(1, items[1].sellIn); // Aged Brie
        assertEquals(1, items[1].quality);

        assertEquals(4, items[2].sellIn); // Elixir of the Mongoose
        assertEquals(6, items[2].quality);

        // Sulfuras nunca muda
        assertEquals(0, items[3].sellIn);
        assertEquals(80, items[3].quality);

        //Backstage passes prazo normal
        assertEquals(14 ,items[5].sellIn);
        assertEquals(21, items[5].quality);

        //Backstage passes prazo <= 10
        assertEquals(9 ,items[6].sellIn);
        assertEquals(50, items[6].quality);

        //Backstage passes prazo <= 5
        assertEquals(4 ,items[7].sellIn);
        assertEquals(50, items[7].quality);

        // Conjured item
        assertEquals(2, items[8].sellIn);
        assertEquals(4, items[8].quality);
    }

    @Test
    void testUpdateQualityAfterMultipleDays() {
        for (int i = 0; i < 21; i++) {
            app.updateQuality();
        }
        //Qualidade não deve ser negativa nunca
        assertTrue(items[0].quality >= 0);
        assertTrue(items[1].quality >= 0);
    }

    @Test
    void testBackstagePasses() {
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        GildedRose singleItemApp = new GildedRose(new Item[]{backstagePass});

        singleItemApp.updateQuality();

        assertEquals(10, backstagePass.sellIn);
        assertEquals(22, backstagePass.quality); // +2 quando sellIn <= 10

       for (int i = 1; i < 6; i++) {
            singleItemApp.updateQuality();
       }
        assertEquals(5, backstagePass.sellIn);
        assertEquals(33, backstagePass.quality); // +3 quando sellIn <= 5

        for (int i = 1; i < 10; i++) {
            singleItemApp.updateQuality();
        }
        assertEquals(-4, backstagePass.sellIn);
        assertEquals(0, backstagePass.quality); // Qualidade zera após data do concerto
    }

    @Test
    void testConjuredItemDegradation() {
        Item conjuredItem = new Item("Conjured Mana Cake", 2, 20);
        GildedRose singleItemApp = new GildedRose(new Item[]{conjuredItem});

        singleItemApp.updateQuality();
        assertEquals(1, conjuredItem.sellIn);
        assertEquals(18, conjuredItem.quality); // Degradação de 2

        singleItemApp.updateQuality();
        assertEquals(0, conjuredItem.sellIn);
        assertEquals(16, conjuredItem.quality); // Degradação de 2

        singleItemApp.updateQuality();
        assertEquals(-1, conjuredItem.sellIn);
        assertEquals(12, conjuredItem.quality); // Degradação 2x mais rápida após sellIn < 0
    }
}
