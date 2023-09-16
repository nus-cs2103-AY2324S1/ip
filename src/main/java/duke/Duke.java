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
    /**
     * Constructs a new Duke object.
     * This constructor initializes a new instance of the Duke class. The Duke object is typically used to
     * manage and interact with tasks in a task management application.
     */
    public Duke() {
        this.message = new Message();
        this.storage = new Storage();
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

        if (userCommand.equals("LIST")) {
            message.listMessage(tasks);
        } else if (userCommand.equals("MARK")) {
            message.markMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("UNMARK")) {
            message.unmarkMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("TODO")) {
            message.todoMessage(tasks, parser.getTodoDescription());
        } else if (userCommand.equals("DEADLINE")) {
            String[] deadlineDetails = parser.getDeadlineDescription();
            message.deadlineMessage(tasks, deadlineDetails[0], parser.getDateTime(deadlineDetails[1]));
        } else if (userCommand.equals("EVENT")) {
            String[] eventDetails = parser.getEventDescription();
            message.eventMessage(tasks, eventDetails[0], parser.getDateTime(eventDetails[1]),
                    parser.getDateTime(eventDetails[2]));
        } else if (userCommand.equals("DELETE")) {
            message.deleteMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("FIND")) {
            message.findMessage(tasks, parser.getStringKeyword());
        } else if (userCommand.equals("BYE")) {
            message.farewellMessage();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        this.storage.saveTasksToFile(this.tasks.getTasks());

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
