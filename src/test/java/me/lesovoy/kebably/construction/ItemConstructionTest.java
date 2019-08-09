package me.lesovoy.kebably.construction;

import me.lesovoy.kebably.model.Item;
import me.lesovoy.kebably.model.construction.ItemBuilder;
import me.lesovoy.kebably.model.enumeration.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemConstructionTest {
    @Test
    public void testConstruction() {
      Item item = new ItemBuilder(ItemType.KEBAB)
              .withSize(Size.MEDIUM)
              .withContents(Contents.CHICKEN)
              .withSpiciness(Spiciness.NOT_SPICY)
              .withSauce(Sauce.GARLIC)
              .build();

        assertEquals(ItemType.KEBAB, item.getType());
        assertEquals(Size.MEDIUM, item.getSize());
        assertEquals(Contents.CHICKEN, item.getContents());
        assertEquals(Spiciness.NOT_SPICY, item.getSpiciness());
        assertEquals(Sauce.GARLIC, item.getSauce());
    }
}
