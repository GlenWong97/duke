public class Task {

    protected String name;
    protected boolean status;

    public Task (String name) {
        this.name = name;
        this.status = false;
    }

    public String getStatus() {
        return (status ? "[✓]" : "[✗]");
    }
    public void setStatusDone() {
        status = true;
    }

}
