package duke;

import duke.exception.DeadlineCommandUseException;
import duke.exception.EventCommandUseException;
import duke.exception.InvalidInputException;
import duke.exception.ToDoCommandUseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     * Parses user input and executes various instructions to manage tasks.
     *
     * @param str    The user input string.
     * @param tasks  The task list to which tasks are added or manipulated.
     */
    public void chat(String str, TaskList tasks) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Ui ui = new Ui();
        try {
            if (!str.equals("list")) {
                if (str.startsWith("mark ")) {
                    String num = str.substring(5);
                    int number = Integer.valueOf(num);
                    if (number <= 0 || number > tasks.getSize()) {
                        throw new InvalidInputException(str);
                    }
                    int index = number - 1; //index for task list
                    tasks.markDone(index);
                    Task done = tasks.getTask(index);
                    ui.printDone(done);
                } else if (str.startsWith("unmark ")) {
                    String num = str.substring(7);
                    int number = Integer.valueOf(num);
                    if (number <= 0 || number > tasks.getSize()) {
                        throw new InvalidInputException(str);
                    }
                    int index = number - 1; //index for task list
                    tasks.markNotDone(index);
                    Task notDone = tasks.getTask(index);
                    ui.printNotDone(notDone);

                } else if (str.startsWith("delete ")) {
                    String num = str.substring(7);
                    int number = Integer.valueOf(num);
                    if (number <= 0 || number > tasks.getSize()) {
                        throw new InvalidInputException(str);
                    }
                    int index = number - 1;
                    Task toBeDeleted = tasks.removeTask(index);
                    ui.printDelete(toBeDeleted, tasks);

                } else if (str.startsWith("find ")) {
                    String keyword = str.substring(5);
                    TaskList matchingTasks = new TaskList();
                    for (int i = 0; i < tasks.getSize(); i++) {
                        if (tasks.getTask(i).getTask().contains(keyword)) {
                            matchingTasks.addTask(tasks.getTask(i));
                        }
                    }
                    ui.printMatchingTasks(matchingTasks);
                } else {
                    if (str.startsWith("todo")) {
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
                        ui.printAddTask(task, tasks);
                    } else if (str.startsWith("deadline")) {
                        if (!str.contains("/by ")) {
                            throw new DeadlineCommandUseException(str); //needs to check for /by
                        } else {
                            String byWhen = "/by ";
                            int index = str.indexOf(byWhen);
                            String deadline = str.substring(index + 4); //remove /by from the substring
                            if (deadline.trim().isEmpty()) { //needs to check whether there is anything after /by
                                throw new DeadlineCommandUseException(str);
                            }
                            String workToDo = str.substring(9, index - 1);
                            Task task = new Deadline(workToDo, LocalDateTime.parse(deadline, formatter));
                            tasks.addTask(task);
                            ui.printAddTask(task, tasks);
                        }
                    } else if (str.startsWith("event")) {
                        if (!str.contains("/from")) {
                            throw new EventCommandUseException(str);
                        } else {
                            String fromMarker = "/from "; //mark the /from index of the string
                            int firstIndex = str.indexOf(fromMarker);
                            int secondIndex;
                            String fromWhen;
                            String toWhen;
                            String workToDo = str.substring(6, firstIndex - 1);
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
                                ui.printAddTask(task, tasks);
                            }
                        }
                    } else {
                        throw new InvalidInputException(str);
                    }
                }
            } else {
                ui.listTasks(tasks);
            }
        } catch (java.time.format.DateTimeParseException e) {
            //detect inputs that don't follow the yyyy-MM-dd HHmm format
            ui.printException();
        } catch (InvalidInputException | EventCommandUseException |
                 DeadlineCommandUseException | ToDoCommandUseException e) {
            ui.printException(e.getMessage());
        }
    }
}
