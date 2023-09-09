package glub;

import java.util.Scanner;

import glub.task.TaskList;
import glub.task.TaskType;

/**
 * Parser that handles user inputs.
 */
public class Parser {
    /** Tasklist associated to parser. */
    private TaskList taskList;
    /** Storage associated to parser. */
    private Storage storage;

    /**
     * Initialises Parser object.
     * @param taskList TaskList with all tasks.
     * @param storage Storage object which saves tasks.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Listen to user input and execute the corresponding command.
     */
    public String parse(String input) throws GlubException {
        String[] parsedCommand = input.split(" ", 2);
        String command = parsedCommand[0];
            switch (command) {
            case "bye":
                return Ui.sayGoodbye();
            case "list":
                return Ui.printListMsg(this.taskList);
            case "mark":
                return taskList.mark(Integer.parseInt(parsedCommand[1]));
            case "unmark":
                return taskList.unmark(Integer.parseInt(parsedCommand[1]));
            case "delete":
                return taskList.deleteTask(Integer.parseInt(parsedCommand[1]));
            case "todo":
                String todo = parsedCommand[1];
                taskList.addTask(todo, TaskType.TODO, false);
                storage.saveTasks(taskList.getTaskList());
                return Ui.printAddMsg(taskList.getTaskList());
            case "deadline":
                String deadline = parsedCommand[1];
                taskList.addTask(deadline, TaskType.DEADLINE, false);
                storage.saveTasks(taskList.getTaskList());
                return Ui.printAddMsg(taskList.getTaskList());
            case "event":
                String event = parsedCommand[1];
                taskList.addTask(event, TaskType.EVENT, false);
                storage.saveTasks(taskList.getTaskList());
                return Ui.printAddMsg(taskList.getTaskList());
            case "find":
                String searchString = parsedCommand[1];
                return Ui.printFindMsg(taskList, searchString);
            default:
                throw new GlubException("OOPS!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }
