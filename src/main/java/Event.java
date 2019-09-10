public class Event extends Task {

    protected String at;

    /**
     * Creates an event object.
     * @param name the name of the event object.
     * @param at the location in which the event is held.
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * Returns a string containing a tag, name of event and the location of the event.
     * @return string containing the event name and location.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Prints confirmation message when an event has been created.
     * Prints the total number of tasks.
     * @return a string containing confirmation message, the name of event, and
     * total number of tasks.
     */
    public String printText() {
        String text1 = " Got it. I've added this task:\n";
        String text2 = "   " + toString() + "\n";
        String text3 = " Now you have " + Integer.toString(Task.getNumberOfTasks()) +" tasks in the list.\n";
        return (text1 + text2 + text3);
    }

}
