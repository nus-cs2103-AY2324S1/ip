package duke;

/**
 * This class deals with making sense of the user command
 */
public class Parser {
    /**
     * This function takes in the user input and carries out the next steps accordingly
     *
     * @param userInput what the user types in to the console
     * @param tasks the duke.TaskList
     * @param storage the duke.Storage
     */
    public static String parse(String userInput, TaskList tasks, Storage storage) {
        String output;
        if (userInput.equals("list")) {
            output = tasks.displayList();
        } else if (userInput.equals("bye")) {
            output = "Thanks for coming!";
        } else if (userInput.contains("mark")) {
            try {
                output = tasks.markDescription(userInput);
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else if (userInput.contains("todo")) {
            if (userInput.length() <= 5) {
                //throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                output = "OOPS!!! The description of a todo cannot be empty.";
            } else {
                try {
                    output = tasks.addTask("T", userInput.substring(5));
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
        } else if (userInput.contains("deadline")) {
            if (userInput.length() <= 9) {
                //throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                output = "OOPS!!! The description of a deadline cannot be empty.";
            } else {
                try {
                    output = tasks.addTask("D", userInput.substring(9));
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
        } else if (userInput.contains("event")) {
            if (userInput.length() <= 6) {
                //throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                output = "OOPS!!! The description of an event cannot be empty.";
            } else {
                try {
                    output = tasks.addTask("E", userInput.substring(6));
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
        } else if (userInput.contains("delete")) {
            try {
                output = tasks.deleteTask(userInput);
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else if (userInput.contains("find")) {
            output = tasks.displayMatchingList(userInput.substring(5));
        } else {
            output = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        storage.updateFile(tasks);
        return output;
    }
}
