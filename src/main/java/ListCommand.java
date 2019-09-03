public class ListCommand extends Command{

    public ListCommand(String name) {
        super(name);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listPrint(TaskList.taskList);
    }

}
