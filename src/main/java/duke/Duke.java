package duke;

import java.util.List;

import exceptions.ParserException;
import io.Parser;
import io.Ui;
import javafx.application.Platform;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * The Duke class is the main entrypoint for the chatbot. It contains the main logic.
 */
public class Duke {

    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a  Duke object, which is the main entrypoint for the chatbot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage(this.taskList);
        storage.loadTasks();
    }


    /**
     * Contains core logic to list all tasks
     */
    public void listTasks() {

        if (taskList.isEmpty()) {
            ui.addPrintStatement("list is empty!");
            return;
        }

        for (int i = 0; i < taskList.size(); i++) {

            String index = Integer.toString(i + 1);
            Task selectedTask = taskList.get(i);
            ui.addPrintStatement(index + " " + ui.displayTask(selectedTask));

        }
    }

    /**
     * Contains core logic to unmark a task
     */
    public void unmarkTask() {
        try {
            // set current task as un-done
            Task selectedTask = taskList.get(parser.getIndex());
            selectedTask.setUnDone();
            ui.displayAction("Marked selected task as un-done desu", selectedTask);
        } catch (IndexOutOfBoundsException ex) {
            ui.addPrintStatement("Please enter a valid index!");
        }
    }

    /**
     * Contains core logic to mark a task as done.
     */
    public void markTaskAsDone() {
        try {
            // set current task as done
            Task selectedTask = taskList.get(parser.getIndex());
            selectedTask.setDone();
            ui.displayAction("Marked selected task as done", selectedTask);
        } catch (IndexOutOfBoundsException ex) {
            ui.addPrintStatement("Please enter a valid index!");
        }
    }

    /**
     * Contains core logic to add a todo task
     */
    public void addTodo() {
        try {
            Task curentTask = new Todo(parser.getTaskName());
            taskList.add(curentTask);
            ui.addPrintStatement("added:\t" + ui.displayTask(curentTask));
        } catch (StringIndexOutOfBoundsException ex) {
            ui.addPrintStatement("Please enter a name after the todo command!");
        }
    }

    /**
     * Contains core logic to add a deadline
     */
    public void addDeadline() {

        try {
            Task curentTask = parser.parseDeadline();
            taskList.add(curentTask);
            ui.addPrintStatement("added:\t" + ui.displayTask(curentTask));
        } catch (ParserException ex) {
            ui.addPrintStatement(ex.getMessage());
        }


    }

    /**
     * Contains core logic to add an event
     */
    public void addEvent() {
        try {
            Task curentTask = parser.parseEvent();
            taskList.add(curentTask);
            ui.addPrintStatement("added:\t" + ui.displayTask(curentTask));
        } catch (ParserException ex) {
            ui.addPrintStatement(ex.getMessage());
        }
    }

    /**
     * Contains core logic to delete a task
     */
    public void deleteTask() {
        if (taskList.isEmpty()) {
            ui.addPrintStatement("The list is empty!");
            return;
        }
        try {
            // remove the current task
            Task selectedTask = taskList.get(parser.getIndex());
            taskList.remove(parser.getIndex());
            ui.displayAction("Deleting selected task!", selectedTask);
        } catch (IndexOutOfBoundsException ex) {
            ui.addPrintStatement("Please enter a valid index!");
        }
    }


    /**
     * Contains core logic to find a task
     */
    public void findTask() {
        ui.addPrintStatement("finding task!");
        String name = parser.getTaskName();
        List<Task> tasks = taskList.findTasks(name);

        ui.addPrintStatement("Found:");
        for (int i = 0; i < tasks.size(); i++) {

            String index = Integer.toString(i + 1);
            Task selectedTask = tasks.get(i);
            ui.addPrintStatement(index + " " + ui.displayTask(selectedTask));
        }
    }

    /**
     * Displays greetings text
     */
    public String start() {
        ui.displayGreetings();
        return ui.getPrintStatement();
    }

    /**
     * Main function decides what to do based on the users input.
     *
     * @param input a string that the user has entered
     */
    public void run(String input) {

        parser.update(input);
        // there is no input
        switch (parser.getCommandString()) {
        case "bye":
            ui.displayGoodbye();
            storage.saveTasks();
            Platform.exit();
            System.exit(0);
            break;
        case "list":
            listTasks();
            break;
        case "mark": {
            markTaskAsDone();
            break;
        }
        case "unmark": {
            unmarkTask();
            break;
        }
        case "todo": {
            addTodo();
            break;
        }
        case "deadline": {
            addDeadline();
            break;
        }
        case "event": {
            addEvent();
            break;
        }
        case "delete": {
            deleteTask();
            break;
        }
        case "find": {
            findTask();
            break;
        }
        default:
            ui.addPrintStatement("Please enter a suitable task!");
        }

    }

    public String getResponse(String input) {
        run(input);
        return ui.getPrintStatement();
    }
}
