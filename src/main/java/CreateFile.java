import java.io.*;
import java.util.*;
import java.nio.Buffer;

public class CreateFile {

    public String filepath = "./data/duke.txt";
    public String newfilepath = "./data/temp.txt";
    public static void main(String[] args) throws IOException {

    }

    public void saveDlEvent(String typeOf, String isDone, String nameTask, String date) throws IOException {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(typeOf + " | " + isDone + " | " + nameTask + " | " + date);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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
    public void transferFile() throws IOException {
        try {
            FileWriter fw = new FileWriter("./data/temp.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            File file = new File(filepath);
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
            File dump = new File(filepath);
            tempFile.renameTo(dump);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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
            File dump = new File(filepath);
            tempFile.renameTo(dump);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void loadSave(ArrayList<Task> taskList) throws FileNotFoundException, DukeException{
        try {
            File file = new File("./data/duke.txt");
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
            System.out.println(e.getMessage());
        }
    }

}
