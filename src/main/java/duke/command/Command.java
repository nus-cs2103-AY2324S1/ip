package duke.command;

import duke.Ui;
import duke.Storage;
import duke.Parser;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.NoSuchCommandException;
import duke.exception.UnmatchedArgumentException;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Command {

    /*
        The keyword for which command method to call.
     */
    private String command;
    /*
        The details of the command.
     */
    private String details;
    /*
        True if there is a detail for the command, False otherwise.
     */
    private boolean isValidDetail;

    /**
     * Creates a new Command object with the command and details passed in.
     *
     * @param command The command from user input.
     * @param details The details of user input.
     */
    public Command(String command, String details) {

        this.command = command;
        if (details.equals("")) {
            this.isValidDetail = false;
        } else {
            this.isValidDetail = true;
        }
        this.details = details;
    }

    /**
     * Executes the function based on the command and details provided.
     *
     * @param tasks The task list for addition of task.
     * @param storage The storage object for reading or writing tasks into a specific file.
     * @throws NoSuchCommandException When an invalid command is inputted.
     * @throws InvalidIndexException If an invalid task index is inputted.
     * @throws EmptyDescriptionException If a task description is not inputted.
     * @throws UnmatchedArgumentException If unmatched arguments are inputted.
     */
    public void execute(TaskList tasks, Storage storage) throws NoSuchCommandException, InvalidIndexException {

        try {
            if (command.equals("list")) {
                tasks.listAllTask();
            } else if (command.equals("mark")) {

                if (!isValidDetail) {
                    throw new InvalidIndexException();
                }
                int index = Integer.parseInt(details);
                if (index > 0 && index <= tasks.size()) {
                    mark(index, tasks, storage);
                } else {
                    throw new InvalidIndexException();
                }
            } else if (command.equals("delete")) {

                if (!isValidDetail) {
                    throw new InvalidIndexException();
                }
                int index = Integer.parseInt(details);
                if (index > 0 && index <= tasks.size()) {
                    delete(index, tasks, storage);
                } else {
                    throw new InvalidIndexException();
                }
            } else if (command.equals("unmark")) {

                if (!isValidDetail) {
                    throw new InvalidIndexException();
                }
                int index = Integer.parseInt(details);
                if (index > 0 && index <= tasks.size()) {
                    unmark(index, tasks, storage);
                } else {
                    throw new InvalidIndexException();
                }
            } else if (command.equals("todo")) {
                addTodo(details, tasks, storage);
            } else if (command.equals("deadline")) {
                addDeadline(details, tasks, storage);
            } else if (command.equals("event")) {
                addEvent(details, tasks, storage);
            } else if (command.equals("due")) {
                checkTaskDue(details, tasks);
            } else if (command.equals("find")) {
                find(details, tasks);
            } else if (command.equals("bye")) {
                isExit();
            } else {
                throw new NoSuchCommandException();
            }
        } catch (NoSuchCommandException e) {
            System.out.println(e);
        } catch (UnmatchedArgumentException e) {
            System.out.println(e);
        } catch (InvalidIndexException e) {
            System.out.println(e);
        } catch (EmptyDescriptionException e) {
            System.out.println(e);
        } catch (NumberFormatException | StringIndexOutOfBoundsException | DateTimeException e) {

            System.out.println(Ui.showLine());
            System.out.println("\tPlease enter a proper date.");
            System.out.println("\t" + e.getMessage());
            System.out.println();
            System.out.println(Ui.showLine());
        }
    }

    /**
     * Marks a task as done and updates it into the storage (file).
     *
     * @param i The index of the task to be marked as done in the list.
     * @param tasks The task list containing the tasks.
     * @param storage The storage object that contains the file for updating.
     */
    public static void mark(int i, TaskList tasks, Storage storage) {

        tasks.get(i - 1).mark();
        System.out.println(Ui.showLine());
        System.out.println(" \tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.get(i - 1).toString());
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    /**
     * Unmarks a task and updates it into the storage (file).
     *
     * @param i The index of the task to be unmarked in the list.
     * @param tasks The task list containing the tasks.
     * @param storage The storage object that contains the file for updating.
     */
    public static void unmark(int i, TaskList tasks, Storage storage) {

        tasks.get(i - 1).unmark();
        System.out.println(Ui.showLine());
        System.out.println(" \tOk! I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.get(i - 1).toString());
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    /**
     * Adds a new task of type Todo into the task list and updates the storage (file).
     *
     * @param message The description of the task.
     * @param tasks The task list for the addition of task.
     * @param storage The storage object that contains the file for updating.
     * @throws UnmatchedArgumentException If the description of the task is empty.
     */
    public static void addTodo(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException {

        if (message.isBlank()) {
            throw new UnmatchedArgumentException(0, 1);
        }
        System.out.println(Ui.showLine());
        System.out.println("\tGot it. I've added this task: ");
        Task todo = new Todo(message, false);
        tasks.add(todo);
        System.out.println("\t  " + todo);
        System.out.println("\tNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    /**
     * Adds a new task of type Deadline to the task list and updates the storage (file).
     *
     * @param message The description and deadline of the task.
     * @param tasks The task list for the addition of the task.
     * @param storage The storage object that contains the file for updating.
     * @throws UnmatchedArgumentException If the arguments passed in are insufficient.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static void addDeadline(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException,
            EmptyDescriptionException {

        String[] arr = message.split(" /");
        Parser p = new Parser();
        if (arr.length < 2) {
            throw new UnmatchedArgumentException(arr.length, 2);
        }
        if (arr[0].isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        String dateAndTime = arr[1].substring(3).replace(" ", "/");
        Deadline dl = new Deadline(arr[0], false, p.checkDateAndTime(dateAndTime));
        tasks.add(dl);
        System.out.println(Ui.showLine());
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + dl);
        System.out.println("\tNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    /**
     * Adds a task of type Event to the task list and updates the storage (file).
     *
     * @param message The description and the event duration of the task.
     * @param tasks The task list for the addition of the task.
     * @param storage The storage object for that contains the file for updating.
     * @throws UnmatchedArgumentException If the arguments passed in are insufficient.
     * @throws EmptyDescriptionException If the description of the task is empty.
     * @throws DateTimeException If the start or end time of the event is invalid.
     */
    public static void addEvent(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException, EmptyDescriptionException {

        String[] arr = message.split(" /");
        Parser p = new Parser();
        if (arr.length < 3) {
            throw new UnmatchedArgumentException(arr.length, 3);
        }
        if (arr[0].isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        LocalDateTime start = p.checkDateAndTime(arr[1].substring(5).replace(" ", "/"));
        LocalDateTime end = p.checkDateAndTime(arr[2].substring(3).replace(" ", "/"));
        if (end.isBefore(start) || end.isEqual(start)) {
            throw new DateTimeException("The end is date should not be earlier or the same as the start date.");
        }
        Event e = new Event(arr[0], false, start, end);
        tasks.add(e);
        System.out.println(Ui.showLine());
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + e);
        System.out.println("\tNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    /**
     * Deletes a task from the task list based on the index provided and updates the storage (file).
     *
     * @param index The task with the index to be deleted.
     * @param tasks The task list for the deletion of task.
     * @param storage The storage object that contains the file for updating.
     */
    public static void delete(int index, TaskList tasks, Storage storage) {

        System.out.println(Ui.showLine());
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("\tNow that you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    /**
     * Checks for the task that has the contains the same date as provided in the task list.
     *
     * @param dueDate The task with the date in the task list.
     * @param tasks The task list to search for based on the provided date.
     */
    public static void checkTaskDue(String dueDate, TaskList tasks) {

        ArrayList<Task> dueDateList = new ArrayList<>();
        Parser p = new Parser();
        LocalDateTime date = p.checkDateAndTime(dueDate.replace(" ", "/"));

        for (Task t : tasks.getTasks()) {
            if (t instanceof Deadline &&
                    ((Deadline) t).getDueDate().isEqual(date)){
                dueDateList.add(t);
            } else if (t instanceof Event &&
                    (((Event) t).getStart().isEqual(date) ||
                            ((Event) t).getEnd().isEqual(date))) {
                dueDateList.add(t);
            }
        }
        boolean isGreaterThan1 = dueDateList.size() > 1;
        System.out.println("\tHere " + (isGreaterThan1 ? "are" : "is") +" the " +
                (isGreaterThan1 ? "tasks that contain" : "task that contains") + " the date:\n");
        for (Task t : dueDateList) {
            System.out.println("\t\t" + t);
        }
        System.out.println(Ui.showLine());
        System.out.println();
    }

    /**
     * Checks if the program should terminate from the command.
     *
     * @return true if the command is "bye", false otherwise.
     */
    public boolean isExit() {
        return (command.equals("bye"));
    }

    /**
     * Checks for the task that has the contains the provided message in the task list.
     *
     * @param toFind The task with the message in the task list.
     * @param tasks The task list to search for based on the provided message.
     */
    public void find(String toFind, TaskList tasks) {

        ArrayList<Task> contain = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(toFind)) {
                contain.add(tasks.get(i));
            }
        }
        boolean isGreaterThan1 = contain.size() > 1;
        System.out.println("\tHere " + (isGreaterThan1 ? "are" : "is") + " are the matching " +
                (isGreaterThan1 ? "tasks" : "task") + " in your list:\n");
        for (int i = 0; i < contain.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + contain.get(i));
        }
        System.out.println(Ui.showLine());
        System.out.println();
    }
}
