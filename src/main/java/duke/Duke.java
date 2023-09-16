package duke;

import java.util.List;

import duke.Message;
import duke.task.Task;
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
        String msg = "";
        Parser parser = new Parser(userInput);
        String userCommand = parser.inputCommand();

        if (userCommand.equals("LIST")) {
//            message = "Here are the tasks in your List:\n" + tasks.toString();
            message.listMessage(tasks);
        } else if (userCommand.equals("MARK")) {
//            Task task = this.tasks.markTaskDone(parser.getTaskNumber());
//            message = "Nice! I've marked this task as done:\n" + task;
            message.markMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("UNMARK")) {
//            Task task = this.tasks.unmarkTask(parser.getTaskNumber());
//            message = "OK, I've marked this task as not done yet:\n" + task;
            message.unmarkMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("TODO")) {
//            Task task = this.tasks.addTodoTask(parser.getTodoDescription());
//            message = "Got it. I've added this task:\n "
//                    + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            message.todoMessage(tasks, parser.getTodoDescription());
        } else if (userCommand.equals("DEADLINE")) {
            String[] deadlineDetails = parser.getDeadlineDescription();
//            Task task = this.tasks.addDeadlineTask(descriptionAndDateTime[0],
//                    parser.getDateTime(descriptionAndDateTime[1]));
//            message = "Got it. I've added this task:\n "
//                    + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            message.deadlineMessage(tasks, deadlineDetails[0], parser.getDateTime(deadlineDetails[1]));
        } else if (userCommand.equals("EVENT")) {
            String[] eventDetails = parser.getEventDescription();
//            Task task = this.tasks.addEventTask(deadlineDescription[0], parser.getDateTime(deadlineDescription[1]),
//                parser.getDateTime(deadlineDescription[2]));
//            message = "Got it. I've added this task:\n "
//                    + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            message.eventMessage(tasks, eventDetails[0], parser.getDateTime(eventDetails[1]),
                    parser.getDateTime(eventDetails[2]));
        } else if (userCommand.equals("DELETE")) {
//            Task task = this.tasks.deleteTask(parser.getTaskNumber());
//            message = "Now you have " + tasks.getSize() + " tasks in the list.";
            message.deleteMessage(tasks, parser.getTaskNumber());
        } else if (userCommand.equals("FIND")) {
//            List<Task> matchingTasks = this.tasks.findTasksByKeyword(parser.getStringKeyword());
//            message = "Here are the matching tasks in your list:\n" + matchingTasks;
            message.findMessage(tasks, parser.getStringKeyword());
        } else if (userCommand.equals("BYE")) {
            message.farewellMessage();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        this.storage.saveTasksToFile(this.tasks.getTasks());

        return msg;
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
