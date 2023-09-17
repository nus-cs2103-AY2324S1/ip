package duke.tools;

public class Parser {

    /**
     * Method that parses the text in the .txt document.
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

    public String[] parseContact(String task) {
        assert task != null : "The task to contact someone to be parsed cannot be null";
        String[] parts = task. split(" /@ | /for ");
        return parts;
    }

    public String[] parseDeadline(String task) {
        assert task != null : "The Deadline to be parsed cannot be null";
        String[] parts = task. split(" /by ");
        return parts;
    }

    public String[] parseEvent(String task) {
        assert task != null : "The Event to be parsed cannot be null";
        String[] parts = task. split(" /from | /to ");
        return parts;
    }

}