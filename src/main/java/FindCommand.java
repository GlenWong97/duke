public class FindCommand extends Command {

    public String keyword;

    public FindCommand(String name, String keyword) {
        super(name);
        this.keyword = keyword;
    }
    @Override
    public boolean isExit() {
        return false;
    }
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
