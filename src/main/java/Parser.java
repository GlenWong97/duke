public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] msgArray = command.split("\\s+");
        switch (msgArray[0]) {
            case("bye"):
                return new ExitCommand(msgArray[0]);
            case ("list"):
                return new ListCommand(msgArray[0]);
            case ("find"):
                if (msgArray.length != 2) throw new DukeException("Please specify only one keyword!");
                return new FindCommand(msgArray[0], msgArray[1]);
            case ("done"):
                if (msgArray.length == 1) throw new DukeException("The index of the done item must be stated.");
                else if (!msgArray[1].matches("\\d+")) throw new DukeException("The index must be of numerical value.");
                else if (Integer.parseInt(msgArray[1]) -1 >= TaskList.taskList.size())
                    throw new DukeException("The index cannot be found! Try a smaller index!");
                else if (TaskList.taskList.size() == 0) throw new DukeException("No task yet! Insert new task to get started!");
                return new DoneCommand(msgArray[0], msgArray[1]);
            case ("delete"):
                if (msgArray.length == 1) throw new DukeException("The index of the deleted item must be stated.");
                else if (!msgArray[1].matches("\\d+")) throw new DukeException("The index must be of numerical value.");
                else if (Integer.parseInt(msgArray[1]) -1 >= TaskList.taskList.size())
                    throw new DukeException("The index cannot be found! Try a smaller index!");
                else if (TaskList.taskList.size() == 0) throw new DukeException("No task yet! Insert new task to get started!");
                return new DeleteCommand(msgArray[0], msgArray[1]);
            case ("todo"):
            case ("deadline"):
            case ("event"):
                if (msgArray.length == 1) throw new DukeException("The description of a todo cannot be empty.");
                return new AddCommand(msgArray[0], command);
            default: throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
