public class ExitCommand extends Command {

    /**
     * Creates the ExitCommand object.
     * @param name the name of the exit command.
     */
    public ExitCommand(String name) {
        super(name);
    }
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints the closing message.
     * @param taskList the list of tasks object.
     * @param ui the class that creates the user interface.
     * @param storage the container that stores all the tasks objects.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.closingMessage();
    }

}
