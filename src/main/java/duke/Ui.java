package duke;

import java.util.Arrays;

/**
 * Starts the UI
 */
public class Ui {
    private static String line = "\t____________________________________________________________\n";
    private static String exitMessage = line + "\tBye. Hope to see you again\n" + line;
    protected TaskList tasks;

    /**
     * Constructor to create a new UI
     * @param tasks lists of tasks
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }


    /**
     * Starts the UI interaction with the user.
     */
    public String startUi(String echo) {
        try {
            String[] words = echo.split(" ");
            if (echo.equalsIgnoreCase("list")) {
                return this.tasks.printListContent();
            } else if (words[0].equalsIgnoreCase("mark")) {
                char lastCharacter = echo.charAt(echo.length() - 1);
                return this.tasks.markTaskAsDone(lastCharacter);
            } else if (words[0].equalsIgnoreCase("unmark")) {
                char lastCharacter = echo.charAt(echo.length() - 1);
                return this.tasks.unmarkTask(lastCharacter);
            } else if (words[0].equalsIgnoreCase("delete")) {
                char lastCharacter = echo.charAt(echo.length() - 1);
                return this.tasks.deleteTask(lastCharacter);
            } else if (words[0].equalsIgnoreCase("todo")) {
                String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                return tasks.toDoHandler(description, false, false);
            } else if (words[0].equalsIgnoreCase("deadline")) {
                String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                return tasks.deadlineHandler(description, false, false);
            } else if (words[0].equalsIgnoreCase("event")) {
                String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                return tasks.eventHandler(description, false, false);
            } else if (words[0].equalsIgnoreCase("find")) {
                String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                return tasks.findTask(description);
            } else if (words[0].equalsIgnoreCase("update")) {
                String completeDescription = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                String description = String.join(" ", Arrays.copyOfRange(words, 2, words.length));
                char number = completeDescription.charAt(0);
                return tasks.updateTime(description, number);
            } else if (words[0].equalsIgnoreCase("help")) {
                return tasks.help();
            } else {
                throw new InvalidCommandException();
            }
        } catch (EmptyDescriptionException | InvalidCommandException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        } catch (NotANumberException e) {
            return "That is not a number!";
        } catch (InvalidInputException e) {
            return "That is an invalid input";
        }
    }
}
