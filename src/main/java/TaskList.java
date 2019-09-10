import java.util.ArrayList;
import java.util.List;

public class TaskList{

    public static ArrayList<Task> taskList;

    /**
     * Creates a taskList object that contains an ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * instantiate the TaskList object based on taskList object.
     * @param taskList the list of tasks object.
     */
    public TaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }
    /*public void add(Task t) {
        taskList.add(t);
    }*/
}
