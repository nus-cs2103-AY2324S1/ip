package teho.main;

import teho.exceptions.*;

import java.util.Scanner;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;

/**
 * Main class that represents a Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 */
public class TehO  {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    static final int TODO_SIZE = 4;
    static final int DEADLINE_SIZE = 8;
    static final int EVENT_SIZE = 5;
    static final int FIND_SIZE = 4;

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

    public String getResponse(String userCommand) {
        String returnMessage = null;
            if (userCommand.equals("bye")) {
                storage.saveTasks(taskList);
                returnMessage = ui.generateGoodbyeMessage();
            } else if (userCommand.equals("list")) {
                returnMessage = ui.generateList(taskList);
            } else if (userCommand.startsWith("mark")) {
                returnMessage = markTask(userCommand);
            } else if (userCommand.startsWith("unmark")) {
                returnMessage = unmarkTask(userCommand);
            } else if (userCommand.startsWith("todo")) {
                returnMessage = addToDo(userCommand);
            } else if (userCommand.startsWith("deadline")) {
                returnMessage =  addDeadline(userCommand);
            } else if (userCommand.startsWith("event")) {
                returnMessage = addEvent(userCommand);
            } else if (userCommand.startsWith("delete")) {
                returnMessage = delete(userCommand);
            } else if (userCommand.startsWith("find")){
                returnMessage = find(userCommand);
            } else if (userCommand.startsWith("sortAlphabetically")){
                returnMessage = ui.sortListAlphabetically(taskList);
            } else {
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    return e.toString();
                }
            }
            return returnMessage;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.generateHelloMessage();
        storage.loadTasks();
        while (true) {
            String userCommand = sc.nextLine();
            String response = getResponse(userCommand);
            System.out.println(response);
            if (userCommand.equals("bye")) {
                break;
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
    public String markTask(String userCommand) {
        //note that split returns a String[]
        //parseInt returns the integer value which is represented by the argument
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        assert taskNumber >= 0 : "taskNumber should not be negative";
        Task task = this.taskList.getTask(taskNumber);
        task.markAsDone(taskNumber);
        return ui.generateMarkTaskMessage(task);
    }

    /**
     * Represents the unmarking of task according to user's command.
     *
     * @param userCommand User's command containing the task to be unmarked.
     */
    public String unmarkTask(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        assert taskNumber >= 0 : "taskNumber should not be negative";
        Task task = this.taskList.getTask(taskNumber);
        task.markAsNotDone(taskNumber);
        return ui.generateUnmarkTaskMessage(task);
    }

    /**
     * Represents the adding of new ToDo task into taskList according to user's command.
     *
     * @param userCommand User's command containing the new ToDo task to be added into taskList.
     */
    public String addToDo(String userCommand) {
        try {
            if (userCommand.length() <= TODO_SIZE) {
                throw new EmptyToDoDescriptionException();
            }
            String command = userCommand.substring(TODO_SIZE + 1); //"todo "
            Task task = new ToDo(command);
            this.taskList.add(task);
            return ui.generateAddToDoMessage(task, taskList);
        } catch (EmptyToDoDescriptionException e) {
            return e.toString();
        }
    }

    /**
     * Represents the adding of new Deadline task into taskList according to user's command.
     *
     * @param userCommand User's command containing the new Deadline task to be added into taskList.
     */
    public  String addDeadline(String userCommand) {
        try {
            if (userCommand.length() <= DEADLINE_SIZE) {
                throw new EmptyDeadlineDescriptionException();
            }
            String commandWithDate = userCommand.substring(DEADLINE_SIZE + 1); //"deadline "
            String description = commandWithDate.split(" /by ")[0];
            LocalDate byDate = LocalDate.parse(commandWithDate.split(" /by ")[1]);
            Task task = new Deadline(description, byDate);
            this.taskList.add(task);
            return ui.generateAddDeadlineMessage(task, taskList);
        } catch (EmptyDeadlineDescriptionException e) {
            return e.toString();
        }
    }

    /**
     * Represents the addition of new Event task into taskList according to user's command.
     *
     * @param userCommand User's command containing the new Event task to be added into taskList.
     */
    public String addEvent(String userCommand) {
        try {
            if (userCommand.length() <= EVENT_SIZE) {
                throw new EmptyEventDescriptionException();
            }
            String commandWithDate = userCommand.substring(EVENT_SIZE + 1); //"event "
            String description = commandWithDate.split(" /from ")[0];
            String dates = commandWithDate.split(" /from ")[1]; //fromDate and toDate
            LocalDate fromDate = LocalDate.parse(dates.split(" /to ")[0]);
            LocalDate toDate = LocalDate.parse(dates.split(" /to ")[1]);
            Task task = new Event(description, fromDate, toDate);
            this.taskList.add(task);
            return ui.generateAddEventMessage(task, taskList);
        } catch (EmptyEventDescriptionException e) {
            return e.toString();
        } catch (IllegalArgumentException e) {
            return "The from date cannot be after the to date!";
        }
    }

    /**
     * Represents the deletion of task from taskList according to user's command.
     *
     * @param userCommand User's command containing the task to be deleted from taskList.
     */
    public String delete(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        assert taskNumber >= 0 : "taskNumber should not be negative";
        Task task = this.taskList.getTask(taskNumber);
        this.taskList.remove(taskNumber);
        return ui.generateDeleteMessage(task, taskList);
    }

    public String find(String userCommand) {
        try {
            if (userCommand.length() <= FIND_SIZE) {
                throw new EmptyFindDescriptionException();
            }
            String toMatch = userCommand.substring(FIND_SIZE + 1);
            return ui.generateFindMessage(toMatch, taskList);
        } catch (EmptyFindDescriptionException e) {
            return e.toString();
        }
    }
}

