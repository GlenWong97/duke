public class ListCommand extends Command{

    public ListCommand(String name) {
        super(name);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out all the tasks formatted based on ui class that is contained
     * in the taskList class.
     * @param taskList the list of tasks object.
     * @param ui the class that creates the user interface.
     * @param storage the container that stores all the tasks objects.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listPrint(TaskList.taskList);
    }

}
