package me.lesovoy.kebably.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Item {

    @JsonProperty("name")
    private String name;

    @JsonProperty("properties")
    private List<Property> properties;

    //TODO: Price

    public Item(String name) {
        this.name = name;
    }

    public void addProperty(Property property){
        this.properties.add(property);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
