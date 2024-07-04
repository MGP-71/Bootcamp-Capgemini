package com.gildedrose;

import javax.naming.InvalidNameException;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) throws InvalidNameException {
		if (items == null)
			throw new IllegalArgumentException("Item list is null");
		this.items = items;
		for (Item item : items) {
			if (item.name == null)
				throw new InvalidNameException("Nombre del item nulo");
			if (item.name.trim().isBlank())
				throw new InvalidNameException("Nombre en blanco no válido");
			if (item.quality < 0)
				throw new IllegalArgumentException("Quality under 0");
			if (item.quality > 50 && !item.name.equals("Sulfuras, Hand of Ragnaros"))
				throw new IllegalArgumentException("Sell in above 50");
		}
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			if (!items[i].name.equals("Aged Brie")
					&& !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
				if (items[i].quality > 0) {
					if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
						items[i].quality = items[i].quality - 1;
						if (items[i].quality > 0 && items[i].name.contains("Conjured")) { ///APORTACION
							items[i].quality = items[i].quality - 1;
						}
					}
				}
			} else {
				if (items[i].quality < 50) {
					items[i].quality = items[i].quality + 1;

					if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].sellIn < 11) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}

						if (items[i].sellIn < 6) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}
					}
				}
			}

			if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
				items[i].sellIn = items[i].sellIn - 1;
			}

			if (items[i].sellIn < 0) {
				if (!items[i].name.equals("Aged Brie")) {
					if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].quality > 0) {
							if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
								items[i].quality = items[i].quality - 1;
								if (items[i].quality > 0 && items[i].name.contains("Conjured")) { ///APORTACION
									items[i].quality = items[i].quality - 1;
								}
							}
						}
					} else {
						items[i].quality = items[i].quality - items[i].quality;
					}
				} else {
					if (items[i].quality < 50) {
						items[i].quality = items[i].quality + 1;
					}
				}
			}
		}
	}
}
