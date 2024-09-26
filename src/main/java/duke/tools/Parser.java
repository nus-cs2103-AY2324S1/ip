package duke.tools;

public class Parser {

    /**
     * Parses the text in the .txt document.
     * It splits the text by "|",
     * giving the task type, status, description and date/ time
     *
     * @param task The formatted String task
     * @return the array of Strings
     */
    public String[] parseTask(String task) {
        String[] parts = task.split("\\|");
        return parts;
    }

    /**
     * Parses the Contact task.
     * It splits the text by /@ and /for.
     *
     * @param task The input task
     * @return String array containing task description, mode of contact and reason for contacting.
     */
    public String[] parseContact(String task) {
        assert task != null : "The task to contact someone to be parsed cannot be null";
        String[] parts = task. split(" /@ | /for ");
        return parts;
    }

    /**
     * Parses the Deadline task.
     * It splits the text by /by.
     *
     * @param task The input task
     * @return String array containing task description and deadline.
     */
    public String[] parseDeadline(String task) {
        assert task != null : "The Deadline to be parsed cannot be null";
        String[] parts = task. split(" /by ");
        return parts;
    }

    /**
     * Parses the Event task.
     * It splits the text by /from and /to.
     *
     * @param task The input task
     * @return String array containing task description, start and end time.
     */
    public String[] parseEvent(String task) {
        assert task != null : "The Event to be parsed cannot be null";
        String[] parts = task. split(" /from | /to ");
        return parts;
    }

}