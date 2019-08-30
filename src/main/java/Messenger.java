import java.util.ArrayList;

public class Messenger {

    static void openingMessage() {
        String bracket = "____________________________________________________________\n";
        String intro = " Hello! I'm Duke\n" +
                " What can I do for you?\n";
        String listMessage = " Here are the tasks in your list:\n";
        System.out.println(bracket + intro + bracket);
    }

    static void closingMessage() {
        String bracket = "____________________________________________________________\n";
        String out = " " + "Bye. Hope to see you again soon!" + "\n";
        System.out.println(bracket + out + bracket);
    }

    static void printBracket() {
        String bracket = "____________________________________________________________\n";
        System.out.print(bracket);
    }

    static void listPrint(ArrayList<Task> taskList) {
        String listMessage = " Here are the tasks in your list:\n";
        System.out.print(listMessage);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(" " + (i + 1) + ".");
            System.out.print(taskList.get(i).toString() + " \n");
        }
    }

}
