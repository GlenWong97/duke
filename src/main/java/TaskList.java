import java.util.ArrayList;
import java.util.List;

public class TaskList{

    public static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }
    public void add(Task t) {
        taskList.add(t);
    }
}
