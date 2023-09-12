package duke;

import duke.task.TaskList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents an intelligent chat robot that helps a person to keep track of various things with encouraging quotes.
 */
public class Duke{
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            //TODO: create a new file when saving
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    public String getResponse(String input) {
        try {
            String output= parser.parse(input);
            if (!parser.isRunning()) {
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return showSavingError();
                }
                return "8888";
                //TODO: exit
            }
            return output;
        } catch (DukeException e) {
            return handleException(e);
        }
    }

//    public void showLoadingError() {
//        System.out.println("Generating new chat session...");
//    }

    private String showSavingError() {
        return "⚠ Oops! Something wrong when closing:(";
    }

    private String handleException(DukeException e) {
        String message = e.getMessage();
        String warning;
        switch (message) {
        case "todo error":
            warning = "⚠ Oops! Need description for the todo:(";
            break;
        case "deadline error":
            warning = "⚠ Oops! Need description and formatted by date for the deadline:(";
            break;
        case "event error":
            warning = "⚠ Oops! Need description, from and to date for the event:(";
            break;
        case "task not found":
            warning = "⚠ Oops! Cannot find task:(";
            break;
        case "undefined":
            warning = "⚠ Sorry! I am not able to understand you. Try another language:D";
            break;
        default:
            warning = "⚠ Oops! Something went wrong:(";
            break;
        }
        return warning;
    }

}

