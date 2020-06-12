package models.xml;

import org.simpleframework.xml.*;

import java.util.List;


@Root(name = "suite")
public class Suite {
    @Attribute
    private String name;
    @Attribute
    private String verbose;
    @Attribute
    private String parallel;
    @Attribute(name = "thread-count")
    private String threadCount;
    @Path("listeners")
    @ElementList(inline=true)
    List<Listener> listeners;
    @Element
    Parameter parameter;
    @ElementList(inline=true)
    List<Test> tests;

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }

    public String getParallel() {
        return parallel;
    }

    public void setParallel(String parallel) {
        this.parallel = parallel;
    }

    public String getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(String threadCount) {
        this.threadCount = threadCount;
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
