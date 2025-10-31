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
    }

    @Test
    void testUpdateQualityAfterMultipleDays() {
        for (int i = 0; i < 3; i++) {
            app.updateQuality();
        }
        assertTrue(items[0].quality >= 0, "Quality should not be negative");
        assertTrue(items[1].quality >= 0, "Quality should not be negative");
    }

    @Test
    void testBackstagePassesBehavior() {
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        GildedRose singleItemApp = new GildedRose(new Item[]{backstagePass});

        singleItemApp.updateQuality();

        assertEquals(10, backstagePass.sellIn);
        assertEquals(21, backstagePass.quality); // +1 quando sellIn > 10
    }
}
