package duke;

import exceptions.ParserException;
import io.Parser;
import io.Ui;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
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

    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage(this.taskList);
        storage.loadTasks();
    }


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

    public void addTodo() {
        try {
            Task curentTask = new Todo(parser.getTaskName());
            taskList.add(curentTask);
            ui.addPrintStatement("added:\t" + ui.displayTask(curentTask));
        } catch (StringIndexOutOfBoundsException ex) {
            ui.addPrintStatement("Please enter a name after the todo command!");
        }
    }

    public void addDeadline() {

        try {
            Task curentTask = parser.parseDeadline();
            taskList.add(curentTask);
            ui.addPrintStatement("added:\t" + ui.displayTask(curentTask));
        } catch (ParserException ex) {
            ui.addPrintStatement(ex.getMessage());
        }


    }

    public void addEvent() {
        try {
            Task curentTask = parser.parseEvent();
            taskList.add(curentTask);
            ui.addPrintStatement("added:\t" + ui.displayTask(curentTask));
        } catch (ParserException ex) {
            ui.addPrintStatement(ex.getMessage());
        }
    }

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

    public String start() {
        ui.displayGreetings();
        return ui.getPrintStatement();
    }

    public void run(String input) {

        parser.update(input);
        // there is no input
        switch (parser.getCommandString()) {
            case "bye":
                ui.displayGoodbye();
                storage.saveTasks();
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
