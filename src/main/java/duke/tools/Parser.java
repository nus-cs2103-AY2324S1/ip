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

}