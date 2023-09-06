package duke;

/**
 * The `Parser` class is responsible for parsing user input and extracting relevant information
 * for processing in the Duke application.
 */
public class Parser {

    /**
     * Parses a user input string to extract the task number to mark as done.
     *
     * @param userInput The user's input command.
     * @param list      The task list to validate the task number against.
     * @return The task number to mark as done.
     * @throws ParserException if parsing or validation fails.
     */
    public int parseMark(String userInput, TaskList list) throws ParserException {
        int taskNo;
        try {
            // Extract the task number from the user input.
            taskNo = Integer.parseInt(userInput.substring(5));

            // Check if the task number is within valid bounds.
            if (taskNo > list.getSize() | taskNo < 1) {
                throw new InvalidTaskNumberException("Please enter valid Task No. to mark!");
            }
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to mark in the format: 'mark 4'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No. to mark!");
        }
        return taskNo;
    }

    /**
     * Parses a user input string to extract the task number to unmark as not done.
     *
     * @param userInput The user's input command.
     * @param list      The task list to validate the task number against.
     * @return The task number to unmark as not done.
     * @throws ParserException if parsing or validation fails.
     */
    public int parseUnmark(String userInput, TaskList list) throws ParserException {
        int taskNo;
        try {
            // Extract the task number from the user input.
            taskNo = Integer.parseInt(userInput.substring(7));

            // Check if the task number is within valid bounds.
            if (taskNo > list.getSize() | taskNo < 1) {
                throw new InvalidTaskNumberException("Please enter valid Task No. to unmark!");
            }
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to unmark in the format: 'unmark 4'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No. to unmark!");
        }
        return taskNo;
    }

    /**
     * Parses a user input string to extract the task number to delete.
     *
     * @param userInput The user's input command.
     * @param list      The task list to validate the task number against.
     * @return The task number to delete.
     * @throws ParserException if parsing or validation fails.
     */
    public int parseDelete(String userInput, TaskList list) throws ParserException {
        int taskNo;
        try {
            // Extract the task number from the user input.
            taskNo = Integer.parseInt(userInput.substring(7));

            // Check if the task number is within valid bounds.
            if (taskNo > list.getSize() | taskNo < 1) {
                throw new InvalidTaskNumberException("Please enter valid Task No. to delete!");
            }
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to delete in the format: 'delete 4'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No. to delete!");
        }
        return taskNo;
    }

    /**
     * Parses a user input string to search for a specific task.
     *
     * @param userInput The user's input command.
     * @param list      The task list to validate the task number against.
     * @return The query string to search for.a
     * @throws ParserException if parsing or validation fails.
     */
    public String parseFind(String userInput, TaskList list) throws ParserException {
        String queryString;
        try {
            queryString = userInput.substring(5);
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to delete in the format: 'delete 4'");
        }
        return queryString;
    }


}
