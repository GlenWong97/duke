import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String bracket = "____________________________________________________________\n";
        String listMessage = " Here are the tasks in your list:\n";
        openingMessage();

        Scanner myObj = new Scanner(System.in);
        String inputMessage = myObj.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!inputMessage.equals("bye")) {
            System.out.print(bracket);
            String[] msgArray = inputMessage.split("\\s+");
            if (inputMessage.equals("list")) {
                System.out.print(listMessage);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print(" " + (i + 1) + ".");
                    System.out.print(taskList.get(i).toString() + " \n");
                }
            } else if (msgArray[0].equals("done")) {
                int index = Integer.parseInt(msgArray[1]) - 1;
                taskList.get(index).setStatusDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.print("   " + taskList.get(index).getStatus() + " ");
                System.out.println(taskList.get(index).name);
            } else if (msgArray[0].equals("todo")) {
                String[] todoArray = inputMessage.split(" ", 2);
                Todo item = new Todo(todoArray[1]);
                taskList.add(item);
                System.out.print(item.printText());
            } else if (msgArray[0].equals("deadline")) {
                String[] dlArray = inputMessage.split(" ");
                String bufferItem = "";
                String bufferDeadline = "";
                boolean mark = false;
                for (int x = 1; x < dlArray.length; x++) {
                    if (!mark && dlArray[x].charAt(0) != '/') {
                        bufferItem += (dlArray[x] + " ");
                    } else if (mark){
                        bufferDeadline += (dlArray[x] + " ");
                    } else {
                        mark = true;
                    }
                }
                bufferItem = bufferItem.substring(0, bufferItem.length() -1);
                bufferDeadline = bufferDeadline.substring(0, bufferDeadline.length() -1);
                Deadline dl = new Deadline(bufferItem, bufferDeadline);
                taskList.add(dl);
                System.out.print(dl.printText());
            } else if (msgArray[0].equals("event")) {
                String[] evArray = inputMessage.split(" ");
                String bufferItem = "";
                String bufferEvent = "";
                boolean mark = false;
                for (int x = 1; x < evArray.length; x++) {
                    if (!mark && evArray[x].charAt(0) != '/') {
                        bufferItem += (evArray[x] + " ");
                    } else if (mark){
                        bufferEvent += (evArray[x] + " ");
                    } else {
                        mark = true;
                    }
                }
                bufferItem = bufferItem.substring(0, bufferItem.length() -1);
                bufferEvent = bufferEvent.substring(0, bufferEvent.length() -1);
                Event ev = new Event(bufferItem, bufferEvent);
                taskList.add(ev);
                System.out.print(ev.printText());
            } else {
                Task t = new Task(inputMessage);
                System.out.print(" added: " + inputMessage + "\n");
                taskList.add(t);
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
        String outro = " " + "Bye. Hope to see you again soon!" + "\n";
        System.out.println(bracket + outro + bracket);
    }
}
