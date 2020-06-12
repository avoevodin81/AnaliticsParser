package models.xml;

import org.simpleframework.xml.*;

import java.util.List;

@Root(name = "test")
public class Test {
    @Attribute
    String name;
    @Attribute(name = "preserve-order", required=false)
    String preserveOrder;

    @Path("classes")
    @ElementList(inline = true, required = false)
    List<TestClass> testClasses;

    @Path("classes")
    @Element(required = false)
    TestClass testClass;

    public List<TestClass> getTestClasses() {
        return testClasses;
    }

    public void setTestClasses(List<TestClass> testClasses) {
        this.testClasses = testClasses;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public void setTestClass(TestClass testClass) {
        this.testClass = testClass;
    }

    public String getPreserveOrder() {
        return preserveOrder;
    }

    public void setPreserveOrder(String preserveOrder) {
        this.preserveOrder = preserveOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
