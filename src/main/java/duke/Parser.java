package duke;

import duke.exception.DeadlineCommandUseException;
import duke.exception.EventCommandUseException;
import duke.exception.InvalidInputException;
import duke.exception.ToDoCommandUseException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * The Parser class is responsible for parsing user input and executing commands to manage tasks.
 * It handles various command types such as adding ToDos, Deadlines, and Events, marking tasks as done,
 * deleting tasks, and listing tasks.
 */
public class Parser {

    /**
     * Default constructor to initialize the Parser class.
     */
    public Parser() {
        //empty constructor to initialize class objects
    }
    static String temp = "";
    /**
     * Updates a task list based on the provided input string.
     * @param str   The input string containing the instruction to be performed.
     * @param tasks The TaskList object representing the list of tasks to be updated.
     * @throws InvalidInputException If the input string is invalid
     */
    private static void updateList(String str, TaskList tasks) throws InvalidInputException {
        if (str.startsWith("mark ")) {
            if (str.startsWith("mark all")) {
                for (int i = 0; i < tasks.getSize(); i++) {
                    tasks.markDone(i);
                }
                temp = Ui.printAllDone(tasks);
            }
            else {
                String num = str.substring(5);
                int number = Integer.valueOf(num);
                if (number <= 0 || number > tasks.getSize()) {
                    throw new InvalidInputException(str);
                }
                int index = number - 1; //index for task list
                tasks.markDone(index);
                Task done = tasks.getTask(index);
                temp = Ui.printDone(done);
            }
        } else if (str.startsWith("unmark ")) {
            if (str.startsWith("unmark all")) {
                for (int i = 0; i < tasks.getSize(); i++) {
                    tasks.markNotDone(i);
                }
                temp = Ui.printAllNotDone(tasks);
            } else {
                String num = str.substring(7);
                int number = Integer.valueOf(num);
                if (number <= 0 || number > tasks.getSize()) {
                    throw new InvalidInputException(str);
                }
                int index = number - 1; //index for task list
                tasks.markNotDone(index);
                Task notDone = tasks.getTask(index);
                temp = Ui.printNotDone(notDone);
            }
        } else if (str.startsWith("delete ")) {
            if (str.startsWith("delete all")) {
                while (tasks.getSize() != 0) {
                    tasks.removeTask(0);
                }
                temp = Ui.printAllDeleted(tasks);
            }
            else {
                String num = str.substring(7);
                int number = Integer.valueOf(num);
                if (number <= 0 || number > tasks.getSize()) {
                    throw new InvalidInputException(str);
                }
                int index = number - 1;
                Task toBeDeleted = tasks.removeTask(index);
                temp = Ui.printDelete(toBeDeleted, tasks);
            }
        }
    }
    /**
     * Returns a list of tasks based on the input string.
     * @param str   The input string.
     * @param tasks The TaskList to process tasks.
     */
    private static void returnList(String str, TaskList tasks) {
        if (str.startsWith("find ")) {
            //return tasklist with matching keywords
            String keyword = str.substring(5);
            TaskList matchingTasks = new TaskList();
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).getTask().contains(keyword)) {
                    matchingTasks.addTask(tasks.getTask(i));
                }
            }
            temp = Ui.printMatchingTasks(matchingTasks);
        }
        else if (str.equals("list")) {
            temp = Ui.listTasks(tasks);
        }
    }
    /**
     * Adds the tasks of type ToDo into tasklist based on the input string.
     * @param str   The input string.
     * @param tasks The TaskList to process tasks.
     * @throws ToDoCommandUseException
     */
    private static void addToDo(String str, TaskList tasks) throws ToDoCommandUseException {
        String todo = str.substring(4);
        //remove any leading and trailing whitespace characters and
        //check whether there is a task after the instruction
        if (todo.trim().isEmpty()) {
            //this would mean the instruction is incomplete
            throw new ToDoCommandUseException(str);
        }
        String string = str.substring(5);
        Task task = new ToDo(string);
        tasks.addTask(task);
        temp = Ui.printAddTask(task, tasks);
    }
    /**
     * Adds the tasks of type Deadline into tasklist based on the input string.
     * @param str   The input string.
     * @param tasks The TaskList to process tasks.
     * @throws DeadlineCommandUseException
     */
    private static void addDeadline(String str, TaskList tasks) throws DeadlineCommandUseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!str.contains("/by ")) {
            throw new DeadlineCommandUseException(str); //needs to check for /by
        } else {
            String byWhen = "/by ";
            int index = str.indexOf(byWhen);
            String deadline = str.substring(index + 4); //remove /by from the substring
            if (deadline.trim().isEmpty()) { //needs to check whether there is anything after /by
                throw new DeadlineCommandUseException(str);
            }
            String workToDo = str.substring(9, index);
            workToDo = workToDo.trim();
            Task task = new Deadline(workToDo, LocalDateTime.parse(deadline, formatter));
            tasks.addTask(task);
            temp = Ui.printAddTask(task, tasks);
        }
    }
    /**
     * Adds the tasks of type Event into tasklist based on the input string.
     * @param str   The input string.
     * @param tasks The TaskList to process tasks.
     * @throws EventCommandUseException
     */
    private static void addEvent(String str, TaskList tasks) throws EventCommandUseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!str.contains("/from")) {
            throw new EventCommandUseException(str);
        } else {
            String fromMarker = "/from "; //mark the /from index of the string
            int firstIndex = str.indexOf(fromMarker);
            int secondIndex;
            String fromWhen;
            String toWhen;
            String workToDo = str.substring(6, firstIndex);
            workToDo = workToDo.trim();
            String afterFirstIndex = str.substring(firstIndex + 6);
            if (!afterFirstIndex.contains("/to ")) { //to check the input of /to after /from
                throw new EventCommandUseException(str);
            } else {
                String toMarker = "/to "; //mark the /to index of the string
                secondIndex = afterFirstIndex.indexOf(toMarker); //to make sure we get the /to after the /from
                fromWhen = afterFirstIndex.substring(0, secondIndex).trim(); //get the from timing
                toWhen = afterFirstIndex.substring(secondIndex + 3).trim(); //get the to timing
                if (fromWhen.trim().isEmpty() ||
                        toWhen.trim().isEmpty()) { //needs to check whether there is anything after /by
                    throw new EventCommandUseException(str);
                }
                Task task = new Event(workToDo, LocalDateTime.parse(fromWhen, formatter),
                        LocalDateTime.parse(toWhen, formatter));
                tasks.addTask(task);
                temp = Ui.printAddTask(task, tasks);
            }
        }
    }
    /**
     * Adds the 3 different types of tasks into tasklist based on the input string.
     * @param str   The input string.
     * @param tasks The TaskList to process tasks.
     */
    private static void addTasks(String str, TaskList tasks) {
        try {
            if (str.startsWith("todo")) {
                addToDo(str, tasks);
            } else if (str.startsWith("deadline")) {
                addDeadline(str, tasks);
            } else if (str.startsWith("event")) {
                addEvent(str, tasks);
            }
        } catch (EventCommandUseException | DeadlineCommandUseException |
                ToDoCommandUseException e) {
            temp = Ui.printException(e.getMessage());
        }
    }
    /**
     * Parses user input and executes various instructions to manage tasks.
     * @param str    The user input string.
     * @param tasks  The task list to which tasks are added or manipulated.
     */
    public static String chat(String str, TaskList tasks) {
        try {
            if (str.equals("bye")) {
                Storage.saveTasks("src/data/Duke.txt", tasks);
                return Ui.printBye();
            }
            else if (str.equals("help")) {
                Storage.saveTasks("src/data/Duke.txt", tasks);
                return Ui.printHelp();
            }
            else if (str.startsWith("mark ") || str.startsWith("unmark ")
                    || str.startsWith("delete ")) {
                updateList(str, tasks);
                Storage.saveTasks("src/data/Duke.txt", tasks);
            }
            else if (str.startsWith("todo") || str.startsWith("deadline")
                    || str.startsWith("event")) {
                addTasks(str, tasks);
                Storage.saveTasks("src/data/Duke.txt", tasks);
            }
            else if (str.equals("list") || str.startsWith("find ")) {
                returnList(str, tasks);
                Storage.saveTasks("src/data/Duke.txt", tasks);
            } else {
                throw new InvalidInputException(str);
            }
        } catch (java.time.format.DateTimeParseException e) {
            //detect inputs that don't follow the yyyy-MM-dd HHmm format
            return Ui.printException();
        } catch (InvalidInputException | FileNotFoundException e) {
            return Ui.printException(e.getMessage());
        }
        return temp;
    }
}
