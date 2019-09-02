import java.io.IOException;
import java.util.*;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        Messenger.openingMessage();
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        CreateFile cf = new CreateFile();
        CreateFile.loadSave(taskList);
        String inputMessage = myObj.nextLine();

        while (!inputMessage.equals("bye")) {
            try {
                Messenger.printBracket();
                String[] msgArray = inputMessage.split("\\s+");
                switch (msgArray[0]) {
                    case ("list"):
                        Messenger.listPrint(taskList);
                        break;
                    case ("find"):
                        Task.searchKeyword(taskList, msgArray);
                        break;
                    case ("done"):
                        Task.setDone(taskList, msgArray, cf);
                        break;
                    case ("todo"):
                        String[] todoArray = inputMessage.split(" ", 2);
                        Todo.setTodo(taskList, todoArray, cf);
                        break;
                    case ("deadline"):
                    case ("event"):
                        Deadline.createDlEvent(taskList, inputMessage, cf);
                        break;
                    default: throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex){
                System.out.println(" ☹ OOPS!!! " + ex.getMessage());
            }
            Messenger.printBracket();
            inputMessage = myObj.nextLine();
        }
        Messenger.closingMessage();
    }
}
