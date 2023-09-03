package duke;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidNumberException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.Scanner;

/**
 * Represents Duke, a Personal Assistant Chatbot that helps a person to keep track of
 * various things. The name Duke was chosen as a placeholder name, in honor of Duke,
 * the Java Mascot. The current name of the Chatbot is John.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private int itemsAdded;


    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        taskList = new TaskList(storage.retrieveData());
        itemsAdded = taskList.getSize();
    }

    public void run() {
        storage.createFile();
        ui.greetUser();

        Scanner sc = new Scanner(System.in);

        while (true) {
            ui.showReply();
            String command = sc.nextLine();

            if (command.equals("bye")) {
                ui.showGoodbyeMessage();
                storage.save(taskList.getTasks());
                break;
            } else {
                try {
                    if (command.equals("list")) {
                        printList(taskList);
                    } else if (command.startsWith("mark ")) { // space behind is needed!, number index = 5
                        printMark(command, taskList);
                    } else if (command.startsWith("unmark ")) { // number index = 7
                        printUnmark(command, taskList);
                    } else if (command.startsWith("todo ")) { // description starting index = 5
                        addToDo(command, taskList);
                        itemsAdded++; // increment number of items
                    } else if (command.startsWith("deadline ")) { // description starting index = 9
                        addDeadline(command, taskList);
                        itemsAdded++; // increment number of items
                    } else if (command.startsWith("event ")) { // description starting index = 6
                        addEvent(command, taskList);
                        itemsAdded++; // increment number of items
                    } else if (command.startsWith("delete ")) { // number index = 7
                        deleteTask(command, taskList);
                        itemsAdded--; //decrement number of items
                    } else {
                        if (command.startsWith("todo")) {
                            throw new InvalidDescriptionException("todo");
                        } else if (command.startsWith("deadline")) {
                            throw new InvalidDescriptionException("deadline");
                        } else if (command.startsWith("event")) {
                            throw new InvalidDescriptionException("event");
                        } else {
                            throw new InvalidCommandException();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + ". " + taskList.getTask(i - 1).toString());
            // adding toString() to use the overridden one in duke.task.Task, etc.
        }
    }

    public void printMark(String command, TaskList taskList) throws Exception {
        int taskPos = Integer.parseInt(command.substring(5)) - 1;
        // convert substring to int, -1 for index

        // only numbers >= 0 and < total number are valid
        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            taskList.getTask(taskPos).markAsDone();
            ui.showTaskMarkedAsDone(taskList.getTask(taskPos));
        } else {
            throw new InvalidNumberException();
        }
    }

    public void printUnmark(String command, TaskList taskList) throws Exception {
        int taskPos = Integer.parseInt(command.substring(7)) - 1;
        // convert substring to int, -1 for index


        // only numbers >= 0 and < total number are valid
        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            taskList.getTask(taskPos).unmark();
            ui.showTaskMarkedAsNotDone(taskList.getTask(taskPos));
        } else {
            throw new InvalidNumberException();
        }
    }

    public void addToDo(String command, TaskList taskList) throws Exception {
        String description = command.substring(5);

        if (description.isEmpty()) {
            throw new InvalidDescriptionException("todo");
        }

        Task newTask = new ToDo(description);
        taskList.addTask(newTask); // add new command

        ui.showTaskAdded(newTask, taskList.getSize());

    }

    public void addDeadline(String command, TaskList taskList) throws Exception {
        // indexOf: searches for the substring and returns the index of the first character
        if (command.contains(" /by ")) {
            String description = command.substring(9, command.indexOf(" /by "));
            String by = command.substring(command.indexOf(" /by ") + 5);
            // from " " to the specified date is 5

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("deadline");
            }

            if (by.isEmpty()) {
                throw new InvalidDeadlineException();
            }
            Task newTask = new Deadline(description, by);
            taskList.addTask(newTask); // add new command

            ui.showTaskAdded(newTask, taskList.getSize());

        } else {
            throw new InvalidDeadlineException();
        }
    }

    public void addEvent(String command, TaskList taskList) throws Exception {
        if (command.contains(" /from ") && command.contains(" /to ")) {
            String description = command.substring(6, command.indexOf(" /from "));
            // from " " to 'from' date is 7
            String from = command.substring(command.indexOf(" /from ") + 7, command.indexOf(" /to "));
            // from " " to 'to' date is 5
            String to = command.substring(command.indexOf(" /to ") + 5);

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("event");
            }

            if (from.isEmpty() || to.isEmpty()) {
                throw new InvalidEventException();
            }

            Task newTask = new Event(description, from, to);
            taskList.addTask(newTask); // add new command

            ui.showTaskAdded(newTask, taskList.getSize());

        } else {
            throw new InvalidEventException();
        }
    }

    public void deleteTask(String command, TaskList taskList) throws Exception {
        int taskPos = Integer.parseInt(command.substring(7)) - 1;
        // convert substring to int, -1 for index
        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            Task deleted = taskList.getTask(taskPos);
            taskList.removeTask(taskPos);

            ui.showTaskDeleted(deleted, taskList.getSize());

        } else {
            throw new InvalidNumberException();
        }

    }

}