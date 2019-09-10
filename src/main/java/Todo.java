import java.io.IOException;
import java.util.ArrayList;

public class Todo extends Task{

    /**
     * Instantiate a Todo object.
     * @param name the name of the todo object.
     */
    public Todo(String name) {
        super(name);
    }

    /*public Todo (String name, boolean status) {
        super(name, status);
    }*/

    /**
     * Returns a string containing a tag, name of Todo task.
     * @return string containing the todo name and location.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Prints confirmation message when a todo has been created.
     * Prints the total number of tasks.
     * @return a string containing confirmation message, the name of todo, and
     * total number of tasks.
     */
    public String printText() {
        String text1 = " Got it. I've added this task:\n";
        String text2 = "   " + toString() + "\n";
        String text3 = " Now you have " + Integer.toString(Task.getNumberOfTasks()) +" tasks in the list.\n";
        return (text1 + text2 + text3);
    }


    static void setTodo(ArrayList<Task> taskList, String[] todoArray, Storage cf)  throws IOException {
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
