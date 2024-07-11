package duke;

import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents an intelligent chat robot that helps a person to keep track of various things with encouraging quotes.
 */
public class Duke {
    private static final String SAVING_ERROR_MSG = "⚠ Oops! Something wrong when closing:(";
    private static final String BYE_MSG = "Bye!\n\"Beware the barrenness of a busy life.\"";
    private final Parser parser;
    private final Storage storage;
    private final TaskList tasks;
    private boolean isRunning;

    /**
     * Initializes the chat robot. Establishes task list and parser.
     */
    public Duke() throws DukeException {
        this.isRunning = true;
        storage = new Storage();
        tasks = new TaskList();
        try {
            tasks.addTasks(storage.loadFile());
        } catch (DukeException e) {
            throw new DukeException("reading error");
        } catch (Exception e) {
            // no nothing, use empty taskList
        }
        parser = new Parser(tasks);
    }

    /**
     * Returns the response of the chat robot to the user input.
     *
     * @param input the user input.
     * @return the response of the chat robot.
     */
    public String getResponse(String input) {
        try {
            String output = parser.parse(input);
            this.storage.save(this.tasks);
            if (!parser.isRunning()) {
                return exit();
            }
            return output;
        } catch (DukeException e) {
            return handleException(e);
        } catch (IOException e) {
            return handleException(new DukeException("saving error"));
        }
    }

    private String exit() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return SAVING_ERROR_MSG;
        }
        return BYE_MSG;
    }

    private String handleException(DukeException e) {
        String message = e.getMessage();
        String warning;
        switch (message) {
        case "todo error":
            warning = "⚠ Oops! Need description for the todo:(";
            break;
        case "deadline error":
            warning = "⚠ Oops! Need description and valid by date for the deadline:(";
            break;
        case "event error":
            warning = "⚠ Oops! Need description, valid from and to dates for the event:(";
            break;
        case "task not found":
            warning = "⚠ Oops! Cannot find task:(";
            break;
        case "undefined":
            warning = "⚠ Sorry! I am not able to understand you. Try another language:D";
            break;
        case "saving error":
            warning = SAVING_ERROR_MSG;
            break;
        default:
            warning = "⚠ Oops! Something went wrong:(";
            break;
        }
        return warning;
    }

}

