package duke;

import javafx.util.Pair;

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
    public String[] parseFind(String userInput, TaskList list) throws ParserException {
        String argsString = userInput.substring(5).trim();
        return argsString.split("\\s+");
    }

    /**
     * Parses a user input string to extract the task number to add tags to.
     *
     * @param userInput The user's input command.
     * @param list      The task list to validate the task number against.
     * @return A pair containing the task number and an array of tags to add.
     * @throws ParserException if parsing or validation fails.
     */
    public Pair<Integer, String[]> parseTag(String userInput, TaskList list) throws ParserException {
        int taskNo;
        String[] args = userInput.substring(4).split(" ");
        try {
            // Extract the task number from the user input.
            taskNo = Integer.parseInt(args[0]);

            // Check if the task number is within valid bounds.
            if (taskNo > list.getSize() | taskNo < 1) {
                throw new InvalidTaskNumberException("Please enter valid Task No. to tag!");
            }

        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to tag in the format: 'tag 4 fun sport'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No. to tag!");
        }
        int newLength = args.length - 1;
        String[] tagsToOutput = new String[newLength];
        System.arraycopy(args, 1, tagsToOutput, 0, newLength);
        return new Pair<>(taskNo, tagsToOutput);

    }

    /**
     * Parses a user input string to extract the task numbers for setting a task as dependent on another task.
     *
     * @param userInput The user's input command.
     * @param list      The task list to validate the task numbers against.
     * @return A pair containing the child task number and the parent task number.
     * @throws ParserException if parsing or validation fails.
     */
    public Pair<Integer, Integer> parseDoAfter(String userInput, TaskList list) throws ParserException {
        int childTaskNo;
        int parentTaskNo;
        String[] args = userInput.substring(8).split(" ");
        try {
            // Extract the task numbers from the user input.
            childTaskNo = Integer.parseInt(args[0]);
            parentTaskNo = Integer.parseInt(args[1]);
            // Check if the task numbers are within valid bounds.
            if ((childTaskNo > list.getSize() | childTaskNo < 1) ||
                    (parentTaskNo > list.getSize() | parentTaskNo < 1) ||
                    args.length > 2) {
                throw new InvalidTaskNumberException("Please enter valid Task No.!");
            }
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to doAfter in the format: 'doafter 4 5'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No.!");
        } catch (ArrayIndexOutOfBoundsException a) {
            throw new ParserException("Please enter valid Task No. args : like 'doafter 4 5'");
        }
        return new Pair<>(childTaskNo, parentTaskNo);
    }


}
