import java.io.IOException;

public class DeleteCommand extends Command {

    public String deleteItem;

    public DeleteCommand(String name, String deleteItem) {
        super(name);
        this.deleteItem = deleteItem;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int index = Integer.parseInt(deleteItem) - 1;
        ui.successDelete(index);
        Task.removeTask(TaskList.taskList, index);
        System.out.println(" Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        storage.deleteFile(index + 1);
    }

}
