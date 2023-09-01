package Parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    /**
     * Extracts and returns a number from the given input string.
     *
     * @param input The input string to search for a number.
     * @return The extracted number, or 0 if no number is found.
     */
    public int findNum(String input) {
        // Split the input by spaces
        String[] splitInput = input.split(" ");
        int number = 0;

        // Iterate through the parts to find the number
        for (String num : splitInput) {
            try {
                number = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                // Not a number, continue searching
            }
        }
        return number;
    }

    /**
     * Extracts and returns the task name from the text file.
     *
     * @param taskinfo The task information from the text file.
     * @param prefix   The prefix used to identify the task type.
     * @return The extracted task name.
     */
    public String taskNameFromTextFile(String taskinfo, String prefix) {
        return taskinfo.split(prefix)[0].split("] ")[2].split(" \\(")[0] + " ";
    }

    /**
     * Extracts and returns the task name from the input string.
     *
     * @param input The user input strings.
     * @return The extracted task name.
     */
    public String taskName(String input) {
        // Extract the task name based on the command type
        String name;
        if (input.contains("todo ")) {
            name = input.split("todo ")[1];
        } else if (input.contains("deadline ")) {
            String temp = input.split("deadline ")[1];
            String[] splitString = temp.split("/by");
            name = splitString[0];
        } else if (input.contains("event ")) {
            String temp = input.split("event ")[1];
            String taskName = temp.split("/from")[0];
            name = taskName;
        } else {
            name = "";
        }
        return name;
    }

    /**
     * Extracts and returns the task deadline from the input string.
     *
     * @param input The user input string.
     * @return The extracted task deadline.
     */
    public String taskBy(String input) {
        String temp = input.split("deadline ")[1];
        String[] splitString = temp.split("/by");
        return splitString[1];
    }

    /**
     * Extracts and returns the task end time from the input string.
     *
     * @param input The user input string.
     * @return The extracted task end time.
     */
    public String taskTo(String input) {
        String temp = input.split("event ")[1];
        return temp.split("/to")[1];
    }

    /**
     * Extracts and returns the task start time from the input string.
     *
     * @param input The user input string.
     * @return The extracted task start time.
     */
    public String taskFrom(String input) {
        String temp = input.split("event ")[1];
        String from = temp.split(" /to ")[0].split("/from ")[1];
        return from;
    }

    /**
     * Extracts and returns the task start time from the text file.
     *
     * @param taskinfo The task information from the text file.
     * @return The extracted task start time formatted as yyyy-MM-dd.
     */
    public String taskFromFromTextFile(String taskinfo) {
        // Extract the "from" value
        String fromPrefix = "from : ";
        int fromIndex = taskinfo.indexOf(fromPrefix) + fromPrefix.length();
        int toIndex = taskinfo.indexOf(" to:");
        String taskFromInput = taskinfo.substring(fromIndex, toIndex);

        // Parse the From input string to a LocalDate object
        LocalDate from = LocalDate.parse(taskFromInput, DateTimeFormatter.ofPattern("MMM d yyyy"));

        // Format the LocalDate object to the desired output format
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Extracts and returns the task end time from the text file.
     *
     * @param taskinfo The task information from the text file.
     * @return The extracted task end time formatted as yyyy-MM-dd.
     */
    public String taskToFromTextFile(String taskinfo) {
        // Extract the "to" value
        String toPrefix = "to: ";
        int toValueIndex = taskinfo.indexOf(toPrefix) + toPrefix.length();
        String taskToInput = taskinfo.substring(toValueIndex, toValueIndex + "MMM d yyyy".length() + 1);
        String fromPrefix = "from : ";
        int fromIndex = taskinfo.indexOf(fromPrefix) + fromPrefix.length();
        int toIndex = taskinfo.indexOf(" to:");
        String taskFromInput = taskinfo.substring(fromIndex, toIndex);

        // Parse the To input string to a LocalDate object
        LocalDate to = LocalDate.parse(taskFromInput, DateTimeFormatter.ofPattern("MMM d yyyy"));

        // Format the LocalDate object to the desired output format
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Extracts and returns the task deadline from the text file.
     *
     * @param taskinfo The task information from the text file.
     * @return The extracted task deadline formatted as yyyy-MM-dd.
     */
    public String taskByFromTextFile(String taskinfo) {
        String byPrefix = "by : ";
        int byIndex = taskinfo.indexOf(byPrefix) + byPrefix.length();
        String taskByInput = taskinfo.substring(byIndex, byIndex + "MMM d yyyy".length() + 1);

        // Parse the By string to a LocalDate object
        LocalDate date = LocalDate.parse(taskByInput, DateTimeFormatter.ofPattern("MMM d yyyy"));

        // Format the LocalDate object to the desired output format
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
