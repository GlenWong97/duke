import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    Scanner myObj = new Scanner(System.in);
    public void showLoadingError() {
        System.out.println("No duke.txt file found, creating one now!");
    }

    /**
     * Prints the welcome message when user enters.
     */
    public void openingMessage() {
        String bracket = "____________________________________________________________\n";
        String intro = " Hello! I'm Duke\n" +
                " What can I do for you?\n";
        String listMessage = " Here are the tasks in your list:\n";
        System.out.println(bracket + intro + bracket);
    }

    /**
     * Prints the closing message when user ends the program.
     * Only prints this when user keys in "bye".
     */
    public void closingMessage() {
        String closeMsg = " " + "Bye. Hope to see you again soon!";
        System.out.println(closeMsg);
    }

    /**
     * Prints a string of underscores that is used to create bracket in output message.
     */
    public void showLine() {
        String bracket = "____________________________________________________________\n";
        System.out.print(bracket);
    }

    /**
     * Prints a string containing first line when user finds a task.
     */
    public void searchResult() {
        System.out.println(" Here are the matching tasks in your list:");
    }

    /**
     * Prints a success message when user deletes a task.
     */
    public void successDelete(int index) {
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("   " + TaskList.taskList.get(index).toString());
    }

    /**
     * Prints the list of task that is formatted.
     * @param taskList the list of tasks object.
     */
    public static void listPrint(ArrayList<Task> taskList) {
        String listMessage = " Here are the tasks in your list:\n";
        System.out.print(listMessage);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(" " + (i + 1) + ".");
            System.out.print(taskList.get(i).toString() + " \n");
        }
    }

    /**
     * Takes in input from user.
     * @return user input read in using scanner object.
     * @throws DukeException if there is no message.
     */
    public String readCommand() throws DukeException {
        try {
            return myObj.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
