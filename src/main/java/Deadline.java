import java.io.IOException;
import java.util.ArrayList;

public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    public String printText() {
        String text1 = " Got it. I've added this task:\n";
        String text2 = "   " + toString() + "\n";
        String text3 = " Now you have " + Integer.toString(Task.getNumberOfTasks()) +" tasks in the list.\n";
        return (text1 + text2 + text3);
    }
    static void createDlEvent(ArrayList<Task> taskList, String inputMessage, CreateFile cf) throws IOException {
        String[] dlArray = inputMessage.split(" ");
        String bufferItem = "";
        String bufferDeadline = "";
        boolean mark = false;
        for (int x = 1; x < dlArray.length; x++) {
            if (!mark && dlArray[x].charAt(0) != '/') {
                bufferItem += (dlArray[x] + " ");
            } else if (mark) {
                bufferDeadline += (dlArray[x] + " ");
            } else {
                mark = true;
            }
        }
        bufferItem = bufferItem.substring(0, bufferItem.length() - 1);
        bufferDeadline = bufferDeadline.substring(0, bufferDeadline.length() - 1);
        String[] smartDeadline = bufferDeadline.split("\\s+");
        String buffernewDeadline = "";
        String[] smartDate;
        String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        if (smartDeadline[0].contains("/")) {
            smartDate = smartDeadline[0].split("\\/");
            if (smartDate.length == 3) {
                if (smartDate[0].charAt(smartDate[0].length() - 1) == '1') smartDate[0] += "st";
                else if (smartDate[0].charAt(smartDate[0].length() - 1) == '2') smartDate[0] += "nd";
                else if (smartDate[0].charAt(smartDate[0].length() - 1) == '3') smartDate[0] += "rd";
                else smartDate[0] += "th";
                smartDate[1] = months[Integer.parseInt(smartDate[1]) -1];
                buffernewDeadline += smartDate[0];
                buffernewDeadline += " of ";
                buffernewDeadline += smartDate[1] + " ";
                buffernewDeadline += smartDate[2] + ", ";

            }
        }

        if (smartDeadline.length == 2) {
            double intTime = Double.parseDouble(smartDeadline[1]);
            if (intTime > 1200) {
                intTime /= 100;
                intTime -= 12;
                buffernewDeadline += String.format("%.2f", intTime) + "pm";
            } else {
                intTime /= 100;
                buffernewDeadline += String.format("%.2f", intTime) + "am";
            }
        }

        if (dlArray[0].equals("deadline")) {
            Deadline dl = new Deadline(bufferItem, buffernewDeadline);
            taskList.add(dl);
            cf.saveDlEvent("D", "0", bufferItem, buffernewDeadline);
            System.out.print(dl.printText());
        } else if (dlArray[0].equals("event")) {
            Event ev = new Event(bufferItem, buffernewDeadline);
            taskList.add(ev);
            cf.saveDlEvent("E", "0", bufferItem, buffernewDeadline);
            System.out.print(ev.printText());
        }
    }

}