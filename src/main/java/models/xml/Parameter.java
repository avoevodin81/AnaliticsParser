package models.xml;

import org.simpleframework.xml.Attribute;

public class Parameter {
    @Attribute
    String name;
    @Attribute
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
