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
    /*public void process(String inputMessage) {
        String[] dlArray = inputMessage.split(" ");
        String bufferItem = "";
        String bufferDeadline = "";
        boolean mark = false;
        for (int x = 1; x < dlArray.length; x++) {
            if (!mark && dlArray[x].charAt(0) != '/') {
                bufferItem += (dlArray[x] + " ");
            } else if (mark){
                bufferDeadline += (dlArray[x] + " ");
            } else {
                mark = true;
            }
        }
        bufferItem = bufferItem.substring(0, bufferItem.length() -1);
        bufferDeadline = bufferDeadline.substring(0, bufferDeadline.length() -1);
        Deadline dl = new Deadline(bufferItem, bufferDeadline);
    }*/

}