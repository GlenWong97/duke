import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String bracket = "____________________________________________________________\n";
        String intro = " Hello! I'm Duke\n" +
                " What can I do for you?\n";
        String listMessage = " Here are the tasks in your list:\n";
        System.out.println(bracket + intro + bracket);

        Scanner myObj = new Scanner(System.in);
        String inputMessage = myObj.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!inputMessage.equals("bye")) {
            String[] msgArray = inputMessage.split("\\s+");
            if (inputMessage.equals("list")) {
                System.out.print(bracket + listMessage);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print(" " + (i + 1) + ".");
                    System.out.print(taskList.get(i).getStatus() + " ");
                    System.out.println(taskList.get(i).name);
                }
                System.out.print(bracket);
            } else if (msgArray[0].equals("done")) {
                int index = Integer.parseInt(msgArray[1]) - 1;
                taskList.get(index).setStatusDone();
                System.out.print(bracket);
                System.out.println(" Nice! I've marked this task as done:");
                System.out.print("   " + taskList.get(index).getStatus() + " ");
                System.out.println(taskList.get(index).name);
                System.out.println(bracket);
            } else {
                Task t = new Task(inputMessage);
                System.out.print(bracket + " added: " + inputMessage + "\n"  + bracket);
                taskList.add(t);
            }
            inputMessage = myObj.nextLine();
        }
        System.out.println(bracket + " " + "Bye. Hope to see you again soon!" + "\n" + bracket);
    }
}
