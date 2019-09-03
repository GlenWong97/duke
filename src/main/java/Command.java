import java.io.IOException;

public abstract class Command {

    protected String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

}

