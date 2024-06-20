package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.naming.InvalidNameException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Pruebas de la clase GildedRose")
class GildedRoseTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
    @Test
    @DisplayName("Meter un Item sin nombre")
    @Disabled
    void IdVacio() {
        Item[] items = new Item[] { new Item("", 1, 1) };
        assertThrows(InvalidNameException.class, () -> new GildedRose(items));
    }
    
    @Test
    @DisplayName("Meter un Item sin nombre")
    @Disabled
    void IdNulo() {
        Item[] items = new Item[] { new Item(null, 1, 1) };
        assertThrows(InvalidNameException.class, () -> new GildedRose(items));
    }
    
    @Test
    @DisplayName("Meter un Item con Quality negativo")
    @Disabled
    void QualityNegativo() {
        Item[] items = new Item[] { new Item("Pato", 1, -1) };
        assertThrows(IllegalArgumentException.class, () -> new GildedRose(items));
    }
    
    @Test
    @DisplayName("Meter un Item con Quality de mas de 50")
    @Disabled
    void QualityMas50() {
        Item[] items = new Item[] { new Item("Pato", 1, 51) };
        assertThrows(IllegalArgumentException.class, () -> new GildedRose(items));
    }
    
    @Test
    @DisplayName("Meter varios Items con valores adecuados sin fallos")
    void ItemsNormales() {
        Item[] items = new Item[] { new Item("Pato", 12, 23),  new Item("Rana", 13, 42),
        		new Item("Paloma", 6, 13), new Item("Cocodrilo", 3, 32)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Pato", items[0].name),
        		() -> assertEquals(11, items[0].sellIn),
        		() -> assertEquals(22, items[0].quality),
        		() -> assertEquals("Rana", items[1].name),
        		() -> assertEquals(12, items[1].sellIn),
        		() -> assertEquals(41, items[1].quality),
        		() -> assertEquals("Paloma", items[2].name),
        		() -> assertEquals(5, items[2].sellIn),
        		() -> assertEquals(12, items[2].quality),
        		() -> assertEquals("Cocodrilo", items[3].name),
        		() -> assertEquals(2, items[3].sellIn),
        		() -> assertEquals(31, items[3].quality)
        		);
    }
    
    @Test
    @DisplayName("Degradación doble de la calidad con SellIn 0")
    void DegradaciónDoble() {
        Item[] items = new Item[] { new Item("Pato", 0, 23)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Pato", items[0].name),
        		() -> assertEquals(-1, items[0].sellIn),
        		() -> assertEquals(21, items[0].quality));
    }
    
    @Test
    @DisplayName("Aged brie incrementa su calidad en 1")
    void AgedBrie1() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 20)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Aged Brie", items[0].name),
        		() -> assertEquals(9, items[0].sellIn),
        		() -> assertEquals(21, items[0].quality));
    }
    
    @Test
    @DisplayName("Aged brie incrementa su calidad en 2")
    void AgedBrie2() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 20)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Aged Brie", items[0].name),
        		() -> assertEquals(-1, items[0].sellIn),
        		() -> assertEquals(22, items[0].quality));
    }
    
    @Test
    @DisplayName("Sulfuras no se modifica")
    void Sulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Sulfuras, Hand of Ragnaros", items[0].name),
        		() -> assertEquals(10, items[0].sellIn),
        		() -> assertEquals(80, items[0].quality));
    }
    
    @Test
    @DisplayName("Entrada al Backstage incrementa su valor en 1 si queda mas de 10 dias")
    void EntradaBackstage1() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name),
        		() -> assertEquals(14, items[0].sellIn),
        		() -> assertEquals(21, items[0].quality));
    }
    
    @Test
    @DisplayName("Entrada al Backstage incrementa su valor en 2 si queda 6-10 días")
    void EntradaBackstage2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name),
        		() -> assertEquals(9, items[0].sellIn),
        		() -> assertEquals(22, items[0].quality));
    }
    
    @Test
    @DisplayName("Entrada al Backstage incrementa su valor en 2 si queda 6-10 días")
    void EntradaBackstage3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name),
        		() -> assertEquals(9, items[0].sellIn),
        		() -> assertEquals(22, items[0].quality));
    }
    
    @Test
    @DisplayName("Entrada al Backstage incrementa su valor en 3 si queda 1-5 días")
    void EntradaBackstage4() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name),
        		() -> assertEquals(4, items[0].sellIn),
        		() -> assertEquals(23, items[0].quality));
    }
    
    @Test
    @DisplayName("Entrada al Backstage cae a 0 su valor si su SellIn es 0")
    void EntradaBackstage5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name),
        		() -> assertEquals(-1, items[0].sellIn),
        		() -> assertEquals(0, items[0].quality));
    }
    
    @Test
    @DisplayName("Meter varios Items Conjurados con valores adecuados sin fallos")
    void ItemsNormalesConjurados() {
        Item[] items = new Item[] { new Item("Conjured Pato", 12, 23),  new Item("Conjured Rana", 13, 42),
        		new Item("Conjured Paloma", 6, 13), new Item("Conjured Cocodrilo", 3, 32)};
        
        GildedRose gr = new GildedRose(items);
        
        gr.updateQuality();
        
        assertAll(() -> assertEquals("Conjured Pato", items[0].name),
        		() -> assertEquals(11, items[0].sellIn),
        		() -> assertEquals(21, items[0].quality),
        		() -> assertEquals("Conjured Rana", items[1].name),
        		() -> assertEquals(12, items[1].sellIn),
        		() -> assertEquals(40, items[1].quality),
        		() -> assertEquals("Conjured Paloma", items[2].name),
        		() -> assertEquals(5, items[2].sellIn),
        		() -> assertEquals(11, items[2].quality),
        		() -> assertEquals("Conjured Cocodrilo", items[3].name),
        		() -> assertEquals(2, items[3].sellIn),
        		() -> assertEquals(30, items[3].quality)
        		);
    }
}
