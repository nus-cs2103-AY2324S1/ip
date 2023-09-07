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
        String[] words = input.split(" ");
        Command command = Parser.decideCommand(input);
        String response;

        try {
            switch (command) {
            case BYE:
                response = Ui.getExitMessage();
                // exit app after 1 second
                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(Platform::exit);
                break;
            case LIST:
                response = tasks.getList();
                break;
            case MARK:
                response = tasks.markTask(words[1]);
                break;
            case UNMARK:
                response = tasks.unmarkTask(words[1]);
                break;
            case DELETE:
                response = tasks.deleteTask(words[1]);
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
                response = tasks.find(input);
                break;
            default:
                // invalid input
                throw new DukeException("Bro, I'm sorry but I don't know what that means :-(");
            }

            storage.writeToFile();

        } catch (DukeException e) {
            return e.toString();
        }
        return response;
    }


}
