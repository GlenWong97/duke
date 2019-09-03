import java.io.IOException;

public class AddCommand extends Command {

    public String msgArray;
    public AddCommand(String name, String msgArray) {
        super(name);
        this.msgArray = msgArray;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (name.equals("todo")) {
            String[] todoArray = msgArray.split(" ", 2);
            Todo.setTodo(TaskList.taskList, todoArray, storage);
        } else if (name.equals("deadline") || name.equals("event")) {
            Deadline.createDlEvent(TaskList.taskList, msgArray, storage);
        }
    }
}
