import java.io.IOException;
import java.util.*;

public class Task {

    protected String name;
    protected boolean status;

    private static int numberOfTasks = 0;

    public Task (String name) {
        this.name = name;
        this.status = false;
        numberOfTasks++;
    }

    public Task (String name, boolean status) {
        this.name = name;
        this.status = status;
        numberOfTasks++;
    }

    public static void removeTask(ArrayList<Task>taskList, int index) {
        taskList.remove(index);
        numberOfTasks--;
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

    static void searchKeyword(ArrayList<Task>taskList, String[] msgArray) throws DukeException {
        String[] matchedKeyword;
        System.out.println(" Here are the matching tasks in your list:");
        try {
            if (msgArray.length != 2) throw new DukeException("Please specify only one keyword!");
            int i = 1;
            for (Task x : taskList) {
                String[] singleWord = x.name.split(" ");
                for (String y : singleWord) {
                    if (y.equals(msgArray[1])) {
                        System.out.print(" " + i + ".");
                        System.out.println(x.toString());
                        i++;
                        break;
                    }
                }

            }

        } catch (DukeException e) {
            System.out.println(" ☹ OOPS!!! " + e.getMessage());
        }

    }

    static void setDelete(ArrayList<Task>taskList, String[] msgArray, CreateFile cf)  throws IOException {
        try {
            if (msgArray.length == 1) throw new DukeException("The index of the done item must be stated.");
            else if (!msgArray[1].matches("\\d+")) throw new DukeException("The index must be of numerical value.");
            else if (Integer.parseInt(msgArray[1]) -1 >= taskList.size())
                throw new DukeException("The index cannot be found! Try a smaller index!");
            else if (taskList.size() == 0) throw new DukeException("No task yet! Insert new task to get started!");
            int index = Integer.parseInt(msgArray[1]) - 1;
            System.out.println(" Noted. I've removed this task: ");
            System.out.println("   " + taskList.get(index).toString());
            removeTask(taskList, index);
            System.out.println(" Now you have " + getNumberOfTasks() + " tasks in the list.");
            cf.deleteFile(index + 1);
        } catch (DukeException ex) {
            System.out.println(" ☹ OOPS!!! " + ex.getMessage());
        }
    }

    static void setDone(ArrayList<Task>taskList, String[] msgArray, CreateFile cf) throws IOException {
        try {
            if (msgArray.length == 1) throw new DukeException("The index of the done item must be stated.");
            else if (!msgArray[1].matches("\\d+")) throw new DukeException("The index must be of numerical value.");
            else if (Integer.parseInt(msgArray[1]) -1 >= taskList.size())
                throw new DukeException("The index cannot be found! Try a smaller index!");
            else if (taskList.size() == 0) throw new DukeException("No task yet! Insert new task to get started!");
            int index = Integer.parseInt(msgArray[1]) - 1;
            taskList.get(index).setStatusDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.print("   " + taskList.get(index).getStatus() + " ");
            System.out.println(taskList.get(index).name);
            cf.updateFile(index + 1);
        } catch (DukeException ex) {
            System.out.println(" ☹ OOPS!!! " + ex.getMessage());
        }
    }

}
