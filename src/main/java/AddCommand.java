import java.io.IOException;



public class AddCommand extends Command {

    public String msgArray;
    /**
     * Returns an AddCommand object.
     * @param name the name of the AddCommand object.
     * @param msgArray the entire input command.
     */
    public AddCommand(String name, String msgArray) {
        super(name);
        this.msgArray = msgArray;
    }


    @Override
    public boolean isExit() { return false; }

    /**
     * Creates a Todo or DlEvent object depending on the command.
     * @param tasks the list of tasks object.
     * @param ui the class that creates the user inteerface.
     * @param storage the container that stores all the tasks objects.
     * @throws IOException If the saved file - duke.txt does not exist.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (name.equals("todo")) {
            String[] todoArray = msgArray.split(" ", 2);
            Todo.setTodo(TaskList.taskList, todoArray, storage);
        } else if (name.equals("deadline") || name.equals("event")) {
            Deadline.createDlEvent(TaskList.taskList, msgArray, storage);
        }
    }
}
