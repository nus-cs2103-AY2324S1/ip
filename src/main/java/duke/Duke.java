package duke;

import java.util.Scanner;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;
import duke.util.TaskList;
import duke.exceptions.DukeException;


/*
 * Duke is a personal assistant chatbot that helps a person to keep track of various things.
 */

public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.tasks = new TaskList(storage.readTasks());
            this.parser = new Parser();

        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            CommandType commandType = parser.parseCommandType(input);
            return handleCommand(commandType, input);
            

        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
        return "Duke heard: " + input;
        
        // return "Duke heard: " + input;
    }

    private void run() {
        ui.printWelcomeMessage();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    break;
                }
                CommandType commandType = parser.parseCommandType(input);
                handleCommand(commandType, input);
            }
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        } catch (Exception e) {
            ui.printErrorMessage(new DukeException("An unexpected error occurred: " + e.getMessage()));
        }
        ui.printFarewellMessage();
    }

    /*
     * Main entry point of the Duke application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        
        Duke ekud = new Duke();
        ekud.run();
    }

    

    // private void handleCommand(CommandType commandType, String command) throws DukeException {
    //     switch (commandType) {
    //     case LIST:
    //         ui.printList(tasks.getTasks());
    //         break;
    //     case MARK:
    //         markTask(command);
    //         break;
    //     case DELETE:
    //         deleteTask(command);
    //         break;
    //     case TODO:
    //     case DEADLINE:
    //     case EVENT:
    //         addTask(command);
    //         break;
    //     case FIND:
    //         handleFind(command);
    //         break;
    //     case UNKNOWN:
    //         ui.printErrorMessage(new DukeException("I'm sorry, but I don't know what that means :-("));
    //         break;
    //     }
    // }

    private String handleCommand(CommandType commandType, String command) throws DukeException {
        Task task;
        switch (commandType) {
        case LIST:
            ui.printList(tasks.getTasks());
            return ui.displayList(tasks.getTasks());

        case MARK:
            task = markTask(command);
            return ui.displayMarkedTaskConfirmation(task);

        case DELETE:
            task = deleteTask(command);
            return ui.displayDeletedTaskConfirmation(task, tasks);

        case TODO:
        case DEADLINE:
        case EVENT:
            task = addTask(command);
            return ui.displayAddedTaskConfirmation(task, tasks);

        case FIND:
            TaskList filteredList = handleFind(command);
            return ui.displayList(filteredList.getTasks());

        case UNKNOWN:
            ui.printErrorMessage(new DukeException("I'm sorry, but I don't know what that means :-("));
            return ui.displayErrorMessage(new DukeException("I'm sorry, but I don't know what that means :-("));

        case EXIT:
            ui.printFarewellMessage();
            return ui.displayFarewellMessage();
        }
        

        return "";
    }

    private TaskList handleFind(String command) {
        ui.printFindMessage();
        String keyword = command.split(" ")[1];
        TaskList filtered = tasks.filter(keyword);
        ui.printList(filtered.getTasks());
        return filtered;
    }

    private Task addTask(String task) {
        try {
            Task newTask = null;
            if (task.startsWith("todo")) {
                newTask = ToDo.createToDoFromCommand(task);
            } else if (task.startsWith("deadline")) {
                newTask = Deadline.createDeadlineFromCommand(task);
            } else if (task.startsWith("event")) {
                newTask = Event.createEventFromCommand(task);
            }

            if (newTask != null) {
                tasks.add(newTask);
                storage.write(newTask);
                ui.printAddedTaskConfirmation(newTask, tasks);
            } 
            return newTask;
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
        return null;
    }


    private Task markTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            storage.write(tasks.getTasks());
            ui.printMarkedTaskConfirmation(task);
            return task;            
        } catch (NumberFormatException e) {
            ui.printErrorMessage(new DukeException("Invalid command format"));
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
        return null;
    }

    private Task deleteTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.remove(index);
            storage.write(tasks.getTasks());
            ui.printDeletedTaskConfirmation(task, tasks);  
            return task;  
        } catch (NumberFormatException e) {
            ui.printErrorMessage(new DukeException("Invalid command format"));
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        } 
        return null;
    }


}
