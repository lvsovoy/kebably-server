package me.lesovoy.kebably.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.lesovoy.kebably.model.enumeration.*;

import javax.persistence.Embeddable;
import java.util.List;

@Data
@Embeddable
public class Item {

    @JsonProperty("type")
    private ItemType type;

    @JsonProperty("size")
    private Size size;

    @JsonProperty("contents")
    private Contents contents;

    @JsonProperty("spiciness")
    private Spiciness spiciness;

    @JsonProperty("sauce")
    private Sauce sauce;

    public Item() {
    }

    public Item(ItemType type, Size size, Contents contents, Spiciness spiciness, Sauce sauce) {
        this.type = type;
        this.size = size;
        this.contents = contents;
        this.spiciness = spiciness;
        this.sauce = sauce;
    }
}
