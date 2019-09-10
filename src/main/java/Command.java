import java.io.IOException;

public abstract class Command {

    protected String name;

    /**
     * Constructor for command class.
     * @param name the name of the command class.
     */
    public Command(String name) {
        this.name = name;
    }

    public abstract boolean isExit();

    /**
     * Executing the command based on the command.
     * @param taskList the list of tasks object.
     * @param ui the class that creates the user interface.
     * @param storage the container that stores all the tasks objects.
     * @throws IOException If the saved file - duke.txt does not exist.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

}

