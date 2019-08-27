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
            /*br = new BufferedReader(new FileReader("./data/temp.txt"));
            bw = new BufferedWriter(new FileWriter("./data/duke.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
            }
            br.close();*/
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

}
