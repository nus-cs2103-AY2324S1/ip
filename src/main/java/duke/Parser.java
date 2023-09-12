package duke;

import java.io.IOException;

/**
 * This class deals with making sense of the user command
 */
public class Parser {
    /**
     * Processes the user input and carries out the next steps accordingly
     *
     * @param userInput what the user types in to the console
     * @param tasks the duke.TaskList
     * @param storage the duke.Storage
     */
    public static String parse(String userInput, TaskList tasks, Storage storage) {
        String output;
        boolean shouldUpdateUndoTxtFile = false;
        if (userInput.equals("list")) {
            output = "Here are the tasks in your list:\n" + tasks.displayList();
        } else if (userInput.equals("bye")) {
            output = "Thanks for coming!";
        } else if (userInput.contains("mark")) {
            try {
                output = tasks.markDescription(userInput);
                shouldUpdateUndoTxtFile = true;
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else if (userInput.contains("todo")) {
            if (userInput.length() <= 5) {
                output = "OOPS!!! The description of a todo cannot be empty.";
            } else {
                try {
                    output = tasks.addTask("T", userInput.substring(5));
                    shouldUpdateUndoTxtFile = true;
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
        } else if (userInput.contains("deadline")) {
            if (userInput.length() <= 9) {
                output = "OOPS!!! The description of a deadline cannot be empty.";
            } else {
                try {
                    output = tasks.addTask("D", userInput.substring(9));
                    shouldUpdateUndoTxtFile = true;
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
        } else if (userInput.contains("event")) {
            if (userInput.length() <= 6) {
                output = "OOPS!!! The description of an event cannot be empty.";
            } else {
                try {
                    output = tasks.addTask("E", userInput.substring(6));
                    shouldUpdateUndoTxtFile = true;
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
        } else if (userInput.contains("delete")) {
            try {
                output = tasks.deleteTask(userInput);
                shouldUpdateUndoTxtFile = true;
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else if (userInput.contains("find")) {
            output = tasks.displayMatchingList(userInput.substring(5));
        } else if (userInput.contains("undo")) {
            try {
                tasks.undo(storage);
            } catch (IOException | ClassNotFoundException e) {
                return e.getMessage();
            }
            output = "Most recent move undone, here is the updated list :)\n" + tasks.displayList();
        } else if (userInput.equals("help")) {
            output = tasks.displayUserGuide();
        } else {
            output = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        storage.updateFile(tasks, shouldUpdateUndoTxtFile);
        return output;
    }
}
