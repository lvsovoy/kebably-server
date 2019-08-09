package me.lesovoy.kebably.model.construction;

import me.lesovoy.kebably.model.Item;
import me.lesovoy.kebably.model.enumeration.*;

public class ItemBuilder {
    ItemType type;
    Size size;
    Contents contents;
    Spiciness spiciness;
    Sauce sauce;

    public ItemBuilder(ItemType type) {
        this.type = type;
    }

    public ItemBuilder withSize(Size size) {
        this.size = size;
        return this;
    }

    public ItemBuilder withContents(Contents contents) {
        this.contents = contents;
        return this;
    }

    public ItemBuilder withSpiciness(Spiciness spiciness) {
        this.spiciness = spiciness;
        return this;
    }

    public ItemBuilder withSauce(Sauce sauce) {
        this.sauce = sauce;
        return this;
    }

    public Item build() {
        Item item = new Item();
        item.setType(this.type);
        item.setSize(this.size);
        item.setContents(this.contents);
        item.setSpiciness(this.spiciness);
        item.setSauce(this.sauce);
        return item;
    }
}
