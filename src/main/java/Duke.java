import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String bracket = "____________________________________________________________\n";
        String intro = " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println(bracket + intro + bracket);

        Scanner myObj = new Scanner(System.in);
        String inputMessage = myObj.nextLine();
        ArrayList<String> tasks = new ArrayList<String>();

        while (!inputMessage.equals("bye")) {
            if (inputMessage.equals("list")) {
                System.out.print(bracket);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i+1) + ". " + tasks.get(i));
                }
                System.out.print(bracket);
            } else {
                System.out.print(bracket + " added: " + inputMessage + "\n"  + bracket);
                tasks.add(inputMessage);
            }
            inputMessage = myObj.nextLine();
        }
        System.out.println(bracket + " " + "Bye. Hope to see you again soon!" + "\n" + bracket);
    }
}
