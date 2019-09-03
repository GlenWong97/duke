public class ExitCommand extends Command {

    public ExitCommand(String name) {
        super(name);
    }
    @Override
    public boolean isExit() {
        return true;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.closingMessage();
    }

}
