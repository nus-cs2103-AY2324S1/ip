package duke.ui;

/**
 * Represents Ui class
 */
public class Ui {
    /**
     * Display starting text with example commands
     *
     * @return String greeting message
     */
    public static String greetingText() {
        String ret = "";
        ret += "Welcome to Samantha's chatBot!\n";
        ret += "You may add tasks in the following format:\n\n";
        ret += "Todo {description}\n";
        ret += "Deadline {description} /by {DD/MM/YYYY HHMM}\n";
        ret += "Event {description} /from {DD/MM/YYYY HHMM} /to {DD/MM/YYYY HHMM}\n";
        return ret;
    }

    /**
     * Ending Message
     *
     * @return String exit message
     */
    public static String endingText() {
        return "See you soon";
    }
}
