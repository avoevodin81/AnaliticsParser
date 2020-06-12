package models.json;

public class AnaliticsModel {

    private String name;
    private Object params;
    private String status;
    private String reason;
    private KnownBugsModel knownBugs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public KnownBugsModel getKnownBugs() {
        return knownBugs;
    }

    public void setKnownBugs(KnownBugsModel knownBugs) {
        this.knownBugs = knownBugs;
    }

}
