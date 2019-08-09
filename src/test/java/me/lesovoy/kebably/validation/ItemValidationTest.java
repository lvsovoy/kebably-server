package me.lesovoy.kebably.validation;

import me.lesovoy.kebably.TestBase;
import me.lesovoy.kebably.model.Item;
import me.lesovoy.kebably.model.construction.ItemBuilder;
import me.lesovoy.kebably.model.enumeration.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ItemValidationTest extends TestBase {

    @Test
    public void testSerialization() throws Exception {
        String itemJson = FileUtils.readFileToString(new File(TEST_RESOURCES + "schema/item.json"), "UTF8");
        Item item = new ItemBuilder(ItemType.KEBAB)
                .withSize(Size.MEDIUM)
                .withContents(Contents.CHICKEN)
                .withSpiciness(Spiciness.NOT_SPICY)
                .withSauce(Sauce.GARLIC)
                .build();
        String json = OBJECT_MAPPER.writeValueAsString(item);
        JSONAssert.assertEquals(itemJson, json, JSONCompareMode.LENIENT);
    }

    @Test
    public void testDeserialization() throws Exception {
        Item item = OBJECT_MAPPER.readValue(new File(TEST_RESOURCES + "schema/item.json"), Item.class);
        assertEquals(ItemType.KEBAB, item.getType());
        assertEquals(Size.MEDIUM, item.getSize());
        assertEquals(Contents.CHICKEN, item.getContents());
        assertEquals(Spiciness.NOT_SPICY, item.getSpiciness());
        assertEquals(Sauce.GARLIC, item.getSauce());
    }
}
