import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException {

        String bracket = "____________________________________________________________\n";
        openingMessage();
        Scanner myObj = new Scanner(System.in);
        String inputMessage = myObj.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!inputMessage.equals("bye")) {
            try {
                System.out.print(bracket);
                String[] msgArray = inputMessage.split("\\s+");
                if (inputMessage.equals("list")) {
                    listPrint(taskList);
                } else if (msgArray[0].equals("done")) {
                    setDone(taskList, msgArray);
                } else if (msgArray[0].equals("todo")) {
                    String[] todoArray = inputMessage.split(" ", 2);
                    setTodo(taskList, todoArray);
                } else if (msgArray[0].equals("deadline")) {
                    createDlEvent(taskList, inputMessage);
                } else if (msgArray[0].equals("event")) {
                    createDlEvent(taskList, inputMessage);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex){
                System.out.println(" ☹ OOPS!!! " + ex.getMessage());
            }
            System.out.println(bracket);
            inputMessage = myObj.nextLine();
        }
        closingMessage();
    }

    private static void openingMessage() {
        String bracket = "____________________________________________________________\n";
        String intro = " Hello! I'm Duke\n" +
                " What can I do for you?\n";
        String listMessage = " Here are the tasks in your list:\n";
        System.out.println(bracket + intro + bracket);
    }
    private static void closingMessage() {
        String bracket = "____________________________________________________________\n";
        String out = " " + "Bye. Hope to see you again soon!" + "\n";
        System.out.println(bracket + out + bracket);
    }
    private static void listPrint(ArrayList<Task>taskList) {
        String listMessage = " Here are the tasks in your list:\n";
        System.out.print(listMessage);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(" " + (i + 1) + ".");
            System.out.print(taskList.get(i).toString() + " \n");
        }
    }
    private static void setDone(ArrayList<Task>taskList, String[] msgArray) {
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
        } catch (DukeException ex) {
            System.out.println(" ☹ OOPS!!! " + ex.getMessage());
        }
    }
    private static void setTodo(ArrayList<Task> taskList, String[] todoArray) {
        try {
            if (todoArray.length == 1) throw new DukeException("The description of a todo cannot be empty.");
            Todo item = new Todo(todoArray[1]);
            taskList.add(item);
            System.out.print(item.printText());
        } catch (DukeException ex) {
            System.out.println(" ☹ OOPS!!! " + ex.getMessage());
        }
    }
    private static void createDlEvent(ArrayList<Task>taskList, String inputMessage) {
        String[] dlArray = inputMessage.split(" ");
        String bufferItem = "";
        String bufferDeadline = "";
        boolean mark = false;
        for (int x = 1; x < dlArray.length; x++) {
            if (!mark && dlArray[x].charAt(0) != '/') {
                bufferItem += (dlArray[x] + " ");
            } else if (mark) {
                bufferDeadline += (dlArray[x] + " ");
            } else {
                mark = true;
            }
        }
        bufferItem = bufferItem.substring(0, bufferItem.length() - 1);
        bufferDeadline = bufferDeadline.substring(0, bufferDeadline.length() - 1);
        if (dlArray[0].equals("deadline")) {
            Deadline dl = new Deadline(bufferItem, bufferDeadline);
            taskList.add(dl);
            System.out.print(dl.printText());
        } else if (dlArray[0].equals("event")) {
            Event ev = new Event(bufferItem, bufferDeadline);
            taskList.add(ev);
            System.out.print(ev.printText());
        }
    }
}
