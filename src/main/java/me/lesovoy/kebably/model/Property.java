package me.lesovoy.kebably.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Property {

    @JsonProperty("key")
    private String key;

    @JsonProperty("value")
    private String value;

    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
