package models.xml;

import org.simpleframework.xml.Attribute;

public class Listener {
    @Attribute(name = "class-name")
    String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
