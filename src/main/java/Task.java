public class Task {

    protected String name;
    protected boolean status;

    private static int numberOfTasks = 0;

    public Task (String name) {
        this.name = name;
        this.status = false;
        numberOfTasks++;
    }

    public String getStatus() {
        return (status ? "[✓]" : "[✗]");
    }
    public void setStatusDone() {
        status = true;
    }
    public String toString() {
        return getStatus() + " " + name;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

}
