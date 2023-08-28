package teho.main;

import teho.exceptions.EmptyDeadlineDescriptionException;
import teho.exceptions.EmptyEventDescriptionException;
import teho.exceptions.EmptyToDoDescriptionException;
import teho.exceptions.InvalidCommandException;

import java.util.Scanner;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;

/**
 * Main class that represents a Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 */
public class TehO {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a TehO instance with string representation of a file path
     * to save and load tasks into and from.
     *
     * @param filePath Path of file to save and load tasks into and from.
     */
    public TehO(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Runs TehO to understand user's commands and carry out actions accordingly.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.generateHelloMessage();
        storage.loadTasks();
        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                ui.generateGoodbyeMessage();
                storage.saveTasks(taskList);
                break;
            } else if (userCommand.equals("list")) {
                ui.generateList(taskList);
            } else if (userCommand.startsWith("mark")) {
                markTask(userCommand);
            } else if (userCommand.startsWith("unmark")) {
                unmarkTask(userCommand);
            } else if (userCommand.startsWith("todo")) {
                addToDo(userCommand);
            } else if (userCommand.startsWith("deadline")) {
                addDeadline(userCommand);
            } else if (userCommand.startsWith("event")) {
                addEvent(userCommand);
            } else if (userCommand.startsWith("delete")) {
                delete(userCommand);
            } else {
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }
            }
        }
        sc.close();
    }

    /**
     * Represents the main method to start TehO.
     *
     * @param args Stores Java command-line arguments and is an array of type java.lang.String class.
     */
    public static void main(String[] args) {
        new TehO("/Users/loomeilinzann/ip/text-ui-test/data/teho.txt").run();
    }

    /**
     * Represents the marking of task according to user's command.
     *
     * @param userCommand User's command containing the task to be marked.
     */
    public void markTask(String userCommand) {
        //note that split returns a String[]
        //parseInt returns the integer value which is represented by the argument
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = this.taskList.getTask(taskNumber);
        task.markAsDone(taskNumber);
        ui.generateMarkTaskMessage(task);
    }

    /**
     * Represents the unmarking of task according to user's command.
     *
     * @param userCommand User's command containing the task to be unmarked.
     */
    public void unmarkTask(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = this.taskList.getTask(taskNumber);
        task.markAsNotDone(taskNumber);
        ui.generateUnmarkTaskMessage(task);
    }

    /**
     * Represents the adding of new ToDo task into taskList according to user's command.
     *
     * @param userCommand User's command containing the new ToDo task to be added into taskList.
     */
    public void addToDo(String userCommand) {
        try {
            if (userCommand.length() < 5) {
                throw new EmptyToDoDescriptionException();
            }
            String command = userCommand.substring(5); //"todo " 5 index
            Task task = new ToDo(command);
            this.taskList.add(task);
            ui.generateAddToDoMessage(task, taskList);
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Represents the adding of new Deadline task into taskList according to user's command.
     *
     * @param userCommand User's command containing the new Deadline task to be added into taskList.
     */
    public  void addDeadline(String userCommand) {
        try {
            if (userCommand.length() < 9) {
                throw new EmptyDeadlineDescriptionException();
            }
            String commandWithDate = userCommand.substring(9); //"deadline " 9 index
            String cDeadline = commandWithDate.split(" /by ")[0]; //just description
            LocalDate byDate = LocalDate.parse(commandWithDate.split(" /by ")[1]); //just byDate
            Task task = new Deadline(cDeadline, byDate);
            this.taskList.add(task);
            ui.generateAddDeadlineMessage(task, taskList);
        } catch (EmptyDeadlineDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Represents the addition of new Event task into taskList according to user's command.
     *
     * @param userCommand User's command containing the new Event task to be added into taskList.
     */
    public void addEvent(String userCommand) {
        try {
            if (userCommand.length() < 6) {
                throw new EmptyEventDescriptionException();
            }
            String commandWithDate = userCommand.substring(6); //"todo " 6 index
            String cEvent = commandWithDate.split(" /from ")[0]; //just description
            String dates = commandWithDate.split(" /from ")[1]; //bothDates
            LocalDate fromDate = LocalDate.parse(dates.split(" /to ")[0]); //just fromDate
            LocalDate toDate = LocalDate.parse(dates.split(" /to ")[1]); //just toDate
            Task task = new Event(cEvent, fromDate, toDate);
            this.taskList.add(task);
            ui.generateAddEventMessage(task, taskList);
        } catch (EmptyEventDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Represents the deletion of task from taskList according to user's command.
     *
     * @param userCommand User's command containing the task to be deleted from taskList.
     */
    public  void delete(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = this.taskList.getTask(taskNumber);
        this.taskList.remove(taskNumber);
        ui.generateDeleteMessage(task, taskList);
    }
}

