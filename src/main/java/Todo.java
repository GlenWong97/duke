import java.io.IOException;
import java.util.ArrayList;

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

    static void setTodo(ArrayList<Task> taskList, String[] todoArray, CreateFile cf)  throws IOException {
        try {
            if (todoArray.length == 1) throw new DukeException("The description of a todo cannot be empty.");
            Todo item = new Todo(todoArray[1]);
            taskList.add(item);
            cf.saveTodo("T", "0", todoArray[1]);
            System.out.print(item.printText());
        } catch (DukeException ex) {
            System.out.println(" ☹ OOPS!!! " + ex.getMessage());
        }
    }

}
