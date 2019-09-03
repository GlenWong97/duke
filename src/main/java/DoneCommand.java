import java.io.IOException;

public class DoneCommand extends Command {

    public String doneItem;

    public DoneCommand(String name, String doneItem) {
        super(name);
        this.doneItem = doneItem;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int index = Integer.parseInt(doneItem) - 1;
        TaskList.taskList.get(index).setStatusDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print("   " + TaskList.taskList.get(index).getStatus() + " ");
        System.out.println(TaskList.taskList.get(index).name);
        storage.updateFile(index + 1);
    }
}
