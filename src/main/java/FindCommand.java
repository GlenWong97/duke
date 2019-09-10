public class FindCommand extends Command {

    public String keyword;

    /**
     * Creates a FindCommand object.
     * @param name the name of the command.
     * @param keyword the name of the keyword given by the user.
     */
    public FindCommand(String name, String keyword) {
        super(name);
        this.keyword = keyword;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    /**
     * Loops through all the individual string in the name
     * of the tasks in taskList and prints out the tasks
     * that contains the keyword.
     *
     * @param taskList the list of tasks object.
     * @param ui the class that creates the user interface.
     * @param storage the container that stores all the tasks objects.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.searchResult();
        int i = 1;
        for (Task x : TaskList.taskList) {
            String[] singleWord = x.name.split(" ");
            for (String y : singleWord) {
                if (y.equals(keyword)) {
                    System.out.print(" " + i + ".");
                    System.out.println(x.toString());
                    i++;
                    break;
                }
            }
        }
    }


}
