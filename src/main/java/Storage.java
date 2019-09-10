import java.io.*;
import java.util.*;
import java.nio.Buffer;

public class Storage {

    public String filePath;
    public ArrayList<Task> taskList = new ArrayList<>();
    private String newfilepath = "./data/temp.txt";
    private static TaskList bufferList;

    /**
     * Instantiate a storage object based on the filepath.
     * @param filePath a string that denotes the path to the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the existing save file and depending on the task,
     * a different task object will be instantiated in the program.
     * Task object will be stored in the taskList.
     * @return an ArrayList containing the various tasks saved in the text file.
     * @throws DukeException if there is no existing save file.
     */
    public ArrayList<Task> load() throws DukeException {

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] strArray = scanner.nextLine().split("\\s+\\|\\s+");
                if (strArray[0].equals("T")) {
                    if (strArray[1].equals("0")) {
                        Todo item = new Todo(strArray[2]);
                        taskList.add(item);
                    } else {
                        Todo item = new Todo(strArray[2]);
                        item.setStatusDone();
                        taskList.add(item);
                    }
                } else if (strArray[0].equals("E")) {
                    if (strArray[1].equals("0")) {
                        Event ev = new Event(strArray[2], strArray[3]);
                        taskList.add(ev);
                    } else {
                        Event ev = new Event(strArray[2], strArray[3]);
                        ev.setStatusDone();
                        taskList.add(ev);
                    }
                } else if (strArray[0].equals("D")) {
                    if (strArray[1].equals("0")) {
                        Deadline dl = new Deadline(strArray[2], strArray[3]);
                        taskList.add(dl);
                    } else {
                        Deadline dl = new Deadline(strArray[2], strArray[3]);
                        dl.setStatusDone();
                        taskList.add(dl);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return taskList;
    }

    public static void main(String[] args) throws IOException {

    }

    /**
     * Creates a Deadline or Event object based on the save file.
     * @param typeOf String that can be either deadline or event.
     * @param isDone String that denotes if the task has been completed.
     * @param nameTask the name of the specific task.
     * @param date the deadline for deadline task or the date in which an event is happening.
     * @throws IOException if there is no existing save file.
     */
    public void saveDlEvent(String typeOf, String isDone, String nameTask, String date) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(typeOf + " | " + isDone + " | " + nameTask + " | " + date);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creates a todo object based on the save file.
     * @param typeOf String that can be either deadline or event.
     * @param isDone String that denotes if the task has been completed.
     * @param nameTask the name of the specific task.
     * @throws IOException if there is no existing save file.
     */
    public void saveTodo(String typeOf, String isDone, String nameTask) throws IOException {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(typeOf + " | " + isDone + " | " + nameTask);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Update the status of a particular task based on the index
     * in the text file.
     * @param lineNumber the index of the task that is being saved in the taskList.
     * @throws IOException if there is no existing save file.
     */
    public void updateFile(int lineNumber) throws IOException {

        try {
            File tempFile = new File("./data/temp.txt");
            FileWriter fw = new FileWriter(tempFile, false);
            PrintWriter pw = new PrintWriter(fw);
            File file = new File("./data/duke.txt");
            Scanner scanner = new Scanner(file);
            int i = 1;
            while (scanner.hasNext()) {
                if (i != lineNumber) pw.println(scanner.nextLine());
                else {
                    String[] str = scanner.nextLine().split("\\s+\\|\\s+");
                    str[1] = "1";
                    String strBuffer = "";
                    for (String x : str) strBuffer += (x + " | ");
                    strBuffer = strBuffer.substring(0, strBuffer.length()-3);
                    pw.println(strBuffer);
                }
                i++;
            }
            pw.flush();
            pw.close();
            scanner.close();
            file.delete();
            File dump = new File(filePath);
            tempFile.renameTo(dump);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Delete the particular task based on the index in the text file.
     * @param lineNumber the index of the task that is being saved in the taskList.
     * @throws IOException if there is no existing save file.
     */
    public void deleteFile(int lineNumber) throws IOException {

        try {
            File tempFile = new File("./data/temp.txt");
            FileWriter fw = new FileWriter(tempFile, false);
            PrintWriter pw = new PrintWriter(fw);
            File file = new File("./data/duke.txt");
            Scanner scanner = new Scanner(file);
            int i = 1;
            while (scanner.hasNext()) {
                if (i != lineNumber) {
                    pw.println(scanner.nextLine());
                } else {
                    scanner.nextLine();
                }
                i++;
            }
            pw.flush();
            pw.close();
            scanner.close();
            file.delete();
            File dump = new File(filePath);
            tempFile.renameTo(dump);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void transferFile() throws IOException {
        try {
            FileWriter fw = new FileWriter("./data/temp.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            File file = new File(filePath);
            Scanner scanner = new Scanner(file).useDelimiter("\\|\\s*");
            while (scanner.hasNext()) {
                pw.println(scanner.nextLine());
            }
            pw.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }



}
