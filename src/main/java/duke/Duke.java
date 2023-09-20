package duke;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

/**
 * Contains the chatbot Brobot. It allows users to add and delete different types of tasks and mark them
 * as complete or incomplete
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;


    /**
     * Constructor for an instance of Duke chatbot.
     *
     * @param filePath the filePath to save the tasks to and load tasks from.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }


    /**
     * Returns a string response based on the user input.
     *
     * @param input User string input.
     * @return Response by Brobot in string format.
     */
    public String getResponse(String input) {
        Command command = Parser.decideCommand(input);
        String response;

        try {
            switch (command) {
            case BYE:
                response = Ui.getExitMessage();
                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(Platform::exit);
                break;
            case LIST:
                response = tasks.getList();
                break;
            case MARK:
                response = tasks.markTask(input);
                break;
            case UNMARK:
                response = tasks.unmarkTask(input);
                break;
            case DELETE:
                response = tasks.deleteTask(input);
                break;
            case TODO:
                response = tasks.addTodo(input);
                break;
            case EVENT:
                response = tasks.addEvent(input);
                break;
            case DEADLINE:
                response = tasks.addDeadline(input);
                break;
            case FIND:
                response = tasks.handleFind(input);
                break;
            case SORT:
                response = tasks.handleSort(input);
                break;
            default:
                throw new DukeException("Bro, I'm sorry but I don't know what that means :-(");
            }

            storage.writeToFile();

        } catch (DukeException e) {
            return e.toString();
        }
        return response;
    }


}
