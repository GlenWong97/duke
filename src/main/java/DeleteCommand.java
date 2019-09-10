import java.io.IOException;

public class DeleteCommand extends Command {

    public String deleteItem;

    /**
     * Creates a DeleteCommand object.
     * @param name the name of the command.
     * @param deleteItem the index of the item to be deleted.
     */
    public DeleteCommand(String name, String deleteItem) {
        super(name);
        this.deleteItem = deleteItem;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Delete a task from storage based on the index given in the command.
     * Prints out confirmation message if the object has been deleted successfully.
     * Prints out the total number of tasks left in storage.
     *
     * @param taskList the list of tasks object.
     * @param ui the class that creates the user interface.
     * @param storage the container that stores all the tasks objects.
     * @throws IOException If the saved file - duke.txt does not exist.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int index = Integer.parseInt(deleteItem) - 1;
        ui.successDelete(index);
        Task.removeTask(TaskList.taskList, index);
        System.out.println(" Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        storage.deleteFile(index + 1);
    }

}
