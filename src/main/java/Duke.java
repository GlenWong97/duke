import java.io.IOException;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        String bracket = "____________________________________________________________\n";
        openingMessage();

        Scanner myObj = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<Task>();
        loadSave(taskList);

        String inputMessage = myObj.nextLine();
        CreateFile cf = new CreateFile();

        while (!inputMessage.equals("bye")) {
            try {
                System.out.print(bracket);
                String[] msgArray = inputMessage.split("\\s+");
                if (inputMessage.equals("list")) {
                    listPrint(taskList);
                } else if (msgArray[0].equals("done")) {
                    setDone(taskList, msgArray, cf);
                } else if (msgArray[0].equals("todo")) {
                    String[] todoArray = inputMessage.split(" ", 2);
                    setTodo(taskList, todoArray, cf);
                } else if (msgArray[0].equals("deadline")) {
                    createDlEvent(taskList, inputMessage, cf);
                } else if (msgArray[0].equals("event")) {
                    createDlEvent(taskList, inputMessage, cf);
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
    private static void setDone(ArrayList<Task>taskList, String[] msgArray, CreateFile cf) throws IOException{
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
    private static void setTodo(ArrayList<Task> taskList, String[] todoArray, CreateFile cf)  throws IOException{
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
    private static void createDlEvent(ArrayList<Task>taskList, String inputMessage, CreateFile cf) throws IOException {
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
            cf.saveDlEvent("D", "0", bufferItem, bufferDeadline);
            System.out.print(dl.printText());
        } else if (dlArray[0].equals("event")) {
            Event ev = new Event(bufferItem, bufferDeadline);
            taskList.add(ev);
            cf.saveDlEvent("E", "0", bufferItem, bufferDeadline);
            System.out.print(ev.printText());
        }
    }

    private static void loadSave(ArrayList<Task> taskList) throws FileNotFoundException, DukeException{
        try {
            File file = new File("./data/duke.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] strArray = scanner.nextLine().split("\\s+\\|\\s+");
                if (strArray[0].equals("T")) {
                    if (strArray[1].equals("0")) {
                        Todo item = new Todo(strArray[2]);
                        taskList.add(item);
                    } else {
                        Todo item = new Todo(strArray[2]);
                        item.setStatusDone();
                        taskList.add(item);
                    }
                } else if (strArray[0].equals("E")) {
                    if (strArray[1].equals("0")) {
                        Event ev = new Event(strArray[2], strArray[3]);
                        taskList.add(ev);
                    } else {
                        Event ev = new Event(strArray[2], strArray[3]);
                        ev.setStatusDone();
                        taskList.add(ev);
                    }
                } else if (strArray[0].equals("D")) {
                    if (strArray[1].equals("0")) {
                        Deadline dl = new Deadline(strArray[2], strArray[3]);
                        taskList.add(dl);
                    } else {
                        Deadline dl = new Deadline(strArray[2], strArray[3]);
                        dl.setStatusDone();
                        taskList.add(dl);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


}
