import java.io.IOException;

public class DoneCommand extends Command {

    public String doneItem;

    /**
     * Creates a DeleteCommand object.
     * @param name the name of the command.
     * @param doneItem the index of the item to be set as done.
     */
    public DoneCommand(String name, String doneItem) {
        super(name);
        this.doneItem = doneItem;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    /**
     * Changes the status of the task to be done and saves it in storage class.
     * Prints out confirmation message, status of the task and the name of the task.
     *
     * @param taskList the list of tasks object.
     * @param ui the class that creates the user interface.
     * @param storage the container that stores all the tasks objects.
     * @throws IOException If the saved file - duke.txt does not exist.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int index = Integer.parseInt(doneItem) - 1;
        TaskList.taskList.get(index).setStatusDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print("   " + TaskList.taskList.get(index).getStatus() + " ");
        System.out.println(TaskList.taskList.get(index).name);
        storage.updateFile(index + 1);
    }
}
