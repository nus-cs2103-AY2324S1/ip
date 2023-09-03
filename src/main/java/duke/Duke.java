package duke;

import duke.exception.UnknownCommandException;
import duke.exception.ChattyException;
import duke.exception.DetailsUnknownException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {

    private Ui ui;
    private Messages messages;
    private TaskList tasklist;
    private Storage storage;

    /**
     * An empty constructor for Duke
     */
    public Duke() {
        this.messages = new Messages();
        this.ui = new Ui();
        this.tasklist = new TaskList();
    }

    /**
     * Constructor for Duke. Takes in the path of the file as argument. Load the file if the file exists.
     *
     * @param filePath the path to the file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasklist = new TaskList();
            storage.loadTask();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasklist = new TaskList();
        } catch (DetailsUnknownException e) {
            e.getMessage();
        }
    }

    /**
     * invoke the startChatting() method for the chatbot to start running
     *
     * @param args
     */
    public static void main(String[] args) {

        new Duke("Level7File/tasks.txt").startChatting();
    }

    /**
     * Run the chatbot depending on what command the user entered
     */
    public void startChatting() {
        Messages.Greet();

        String exitC = "bye";
        String listC = "list";
        String deleteC = "delete";
        String doneC = "mark";
        String undoneC = "unmark";
        String deadlineC = "deadline";
        String eventC = "event";
        String todoC = "todo";

        String userInput;
        do {
            userInput = ui.getInput();
            Command command = new Command(tasklist, ui);

            if (!userInput.equalsIgnoreCase(exitC)) {
                try {
                    if (userInput.equalsIgnoreCase(listC)) {
                        command.handleList();
                    } else if (userInput.startsWith(deleteC)) {
                        command.handleDelete(userInput, deleteC);
                    } else if (userInput.startsWith(doneC)) {
                        command.handleDone(userInput, doneC);
                    } else if (userInput.startsWith(undoneC)) {
                        command.handleUndone(userInput, undoneC);
                    } else if (userInput.startsWith(deadlineC)) {
                        command.handleDeadline(userInput, deadlineC);
                    } else if (userInput.startsWith(eventC)) {
                        command.handleEvent(userInput, eventC);
                    } else if (userInput.startsWith(todoC)) {
                        command.handleTodo(userInput, todoC);
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.saveTaskToFile();
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing date and time: " + e.getMessage());
                } catch (DetailsUnknownException e) {
                    System.out.println(e.getMessage());
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                } catch (ChattyException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!userInput.equalsIgnoreCase(exitC));

        this.messages.Exit();
    }
}