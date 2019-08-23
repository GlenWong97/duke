import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        
        String bracket = "____________________________________________________________\n";
        String intro = " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println(bracket + intro + bracket);

        Scanner myObj = new Scanner(System.in);
        String inputMessage = myObj.nextLine();

        while (!inputMessage.equals("bye")) {
            System.out.println(bracket + " " + inputMessage + "\n" + bracket);
            inputMessage = myObj.nextLine();
        }

        System.out.println(bracket + " " + "Bye. Hope to see you again soon!" + "\n" + bracket);
    }
}
