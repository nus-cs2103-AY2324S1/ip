package duke;

import duke.task.Task;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;

/**
 * Creates a Duke object.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke() {
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
        String message = "";
        //Load tasks from file

        //Read user input
        Parser parser = new Parser(userInput);
        String userCommand = parser.inputCommand();
        System.out.println(userCommand);
        if (userCommand.equals("LIST")) {
            message = "Here are the tasks in your List:\n" + tasks.toString();
        } else if (userCommand.equals("MARK")) {
            Task task = this.tasks.markTaskDone(parser.getTaskNumber());
            message = "Nice! I've marked this task as done:\n" + task;
        } else if (userCommand.equals("UNMARK")) {
            Task task = this.tasks.unmarkTask(parser.getTaskNumber());
            message = "OK, I've marked this task as not done yet:\n" + task;
        } else if (userCommand.equals("TODO")) {
            Task task = this.tasks.addTodoTask(parser.getTodoDescription());
            message = "Got it. I've added this task:\n " + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
        } else if (userCommand.equals("DEADLINE")) {
            String[] descriptionAndDateTime = parser.getDeadlineDescription();
            Task task = this.tasks.addDeadlineTask(descriptionAndDateTime[0], parser.getDateTime(descriptionAndDateTime[1]));
            message = "Got it. I've added this task:\n " + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
        } else if (userCommand.equals("EVENT")) {
            String[] deadlineDescription = parser.getEventDescription();
            Task task = this.tasks.addEventTask(deadlineDescription[0], parser.getDateTime(deadlineDescription[1]),
                parser.getDateTime(deadlineDescription[2]));
            message = "Got it. I've added this task:\n " + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
        } else if (userCommand.equals("DELETE")) {
            Task task = this.tasks.deleteTask(parser.getTaskNumber());
            message = "Now you have " + tasks.getSize() + " tasks in the list.";
        } else if (userCommand.equals("FIND")) {
            List<Task> matchingTasks = this.tasks.findTasksByKeyword(parser.getStringKeyword());
            message = "Here are the matching tasks in your list:\n" + matchingTasks;
        } else if (userCommand.equals("BYE")) {
            message = "Bye. Hope to see you again soon!";
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        this.storage.saveTasksToFile(this.tasks.getTasks());

        return message;
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