import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    Scanner myObj = new Scanner(System.in);
    public void showLoadingError() {
        System.out.println("No duke.txt file found, creating one now!");
    }

    public void openingMessage() {
        String bracket = "____________________________________________________________\n";
        String intro = " Hello! I'm Duke\n" +
                " What can I do for you?\n";
        String listMessage = " Here are the tasks in your list:\n";
        System.out.println(bracket + intro + bracket);
    }

    public void closingMessage() {
        String closeMsg = " " + "Bye. Hope to see you again soon!";
        System.out.println(closeMsg);
    }

    public void showLine() {
        String bracket = "____________________________________________________________\n";
        System.out.print(bracket);
    }

    public void searchResult() {
        System.out.println(" Here are the matching tasks in your list:");
    }

    public void successDelete(int index) {
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("   " + TaskList.taskList.get(index).toString());
    }

    public static void listPrint(ArrayList<Task> taskList) {
        String listMessage = " Here are the tasks in your list:\n";
        System.out.print(listMessage);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(" " + (i + 1) + ".");
            System.out.print(taskList.get(i).toString() + " \n");
        }
    }

    public String readCommand() throws DukeException {
        try {
            return myObj.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
