package duke;

import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import javafx.fxml.FXML;

/**
 * Creates a Duke object.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Message message;
    private Exit exit;
    /**
     * Constructs a new Duke object.
     * This constructor initializes a new instance of the Duke class. The Duke object is typically used to
     * manage and interact with tasks in a task management application.
     */
    public Duke() {
        this.message = new Message();
        this.storage = new Storage();
        this.exit = new Exit();
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public String run(String userInput) throws DukeException {
        Parser parser = new Parser(userInput);
        String userCommand = parser.inputCommand();

        if (userCommand.equals("LIST") || userCommand.equals("L")) {
            message.listMessage(tasks);
        } else if (userCommand.equals("MARK") || userCommand.equals("M")) {
            message.markMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("UNMARK") || userCommand.equals("U")) {
            message.unmarkMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("TODO") || userCommand.equals("T")) {
            message.todoMessage(tasks, parser.getTodoDescription());
        } else if (userCommand.equals("DEADLINE") || userCommand.equals("D")) {
            String[] deadlineDetails = parser.getDeadlineDescription();
            message.deadlineMessage(tasks, deadlineDetails[0], parser.getDateTime(deadlineDetails[1]));
        } else if (userCommand.equals("EVENT") || userCommand.equals("E")) {
            String[] eventDetails = parser.getEventDescription();
            message.eventMessage(tasks, eventDetails[0], parser.getDateTime(eventDetails[1]),
                    parser.getDateTime(eventDetails[2]));
        } else if (userCommand.equals("DELETE") || userCommand.equals("DEL")) {
            message.deleteMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("FIND") || userCommand.equals("F")) {
            message.findMessage(tasks, parser.getStringKeyword());
        } else if (userCommand.equals("BYE") || userCommand.equals("B")) {
            message.farewellMessage();
            exit.start();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        this.storage.saveTasksToFile(this.tasks.getTasks());
        assert message != null: "message should not be empty.";

        return message.getMessage();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    String getResponse(String input) throws DukeException {
        return run(input);
    }
}
