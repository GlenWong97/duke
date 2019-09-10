import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main constructor in the command line interface.
     * Instantiates ui, storage and taskList object.
     * Loads save file based on the filepath.
     * Returns DukeException if the file does not exist.
     * @param filePath the path towards the duke.txt file containing all the saved tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Takes in command until the command "bye" is given.
     * Greets the user when program starts and ends.
     * Execute the command by sending it through the parser class.
     * @throws IOException If the saved file - duke.txt does not exist.
     */
    public void run() throws IOException {

        ui.openingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(" ☹ OOPS!!! " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
