public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }
    public Todo (String name, boolean status) {
        super(name, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String printText() {
        String text1 = " Got it. I've added this task:\n";
        String text2 = "   " + toString() + "\n";
        String text3 = " Now you have " + Integer.toString(Task.getNumberOfTasks()) +" tasks in the list.\n";
        return (text1 + text2 + text3);
    }

}
