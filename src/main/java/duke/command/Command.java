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
     * @return Task object based on the input command.
     * @throws NoSuchCommandException When an invalid command is inputted.
     * @throws InvalidIndexException If an invalid task index is inputted.
     * @throws EmptyDescriptionException If a task description is not inputted.
     * @throws UnmatchedArgumentException If unmatched arguments are inputted.
     */
    public String execute(TaskList tasks, Storage storage) throws NoSuchCommandException, InvalidIndexException,
            UnmatchedArgumentException, EmptyDescriptionException {

        String result = "";
        if (command.equals("list")) {
            result = tasks.listAllTask();
        } else if (command.equals("mark")) {

            if (!isValidDetail) {
                throw new InvalidIndexException();
            }
            int index = Integer.parseInt(details);
            if (index > 0 && index <= tasks.size()) {
                result = mark(index, tasks, storage);
            } else {
                throw new InvalidIndexException();
            }
        } else if (command.equals("delete")) {

            if (!isValidDetail) {
                throw new InvalidIndexException();
            }
            int index = Integer.parseInt(details);
            if (index > 0 && index <= tasks.size()) {
                result = delete(index, tasks, storage);
            } else {
                throw new InvalidIndexException();
            }
        } else if (command.equals("unmark")) {

            if (!isValidDetail) {
                throw new InvalidIndexException();
            }
            int index = Integer.parseInt(details);
            if (index > 0 && index <= tasks.size()) {
                result = unmark(index, tasks, storage);
            } else {
                throw new InvalidIndexException();
            }
        } else if (command.equals("todo")) {
            result = addTodo(details, tasks, storage);
        } else if (command.equals("deadline")) {
            result = addDeadline(details, tasks, storage);
        } else if (command.equals("event")) {
            result = addEvent(details, tasks, storage);
        } else if (command.equals("due")) {
            result = checkTaskDue(details, tasks);
        } else if (command.equals("find")) {
            result = find(details, tasks);
        } else if (command.equals("bye")) {
            isExit();
        } else {
            throw new NoSuchCommandException();
        }
        return result;
    }

    /**
     * Marks a task as done and updates it into the storage (file).
     *
     * @param i The index of the task to be marked as done in the list.
     * @param tasks The task list containing the tasks.
     * @param storage The storage object that contains the file for updating.
     * @return The response of the bot after mark the task as done.
     */
    public String mark(int i, TaskList tasks, Storage storage) {

        String result = "";
        tasks.get(i - 1).mark();
        result += Ui.showLine() + "\n" + " Nice! I've marked this task as done:" + "\n" + " " + tasks.get(i - 1).toString();
        result += "\n";
        result += Ui.showLine();
        storage.writeInto(tasks);
        return result;
    }

    /**
     * Unmarks a task and updates it into the storage (file).
     *
     * @param i The index of the task to be unmarked in the list.
     * @param tasks The task list containing the tasks.
     * @param storage The storage object that contains the file for updating.
     * @return The response of the bot after mark the task as undone.
     */
    public String unmark(int i, TaskList tasks, Storage storage) {

        String result = "";
        tasks.get(i - 1).unmark();
        result += Ui.showLine() + "\n" + " Ok! I've marked this task as not done yet:" + "\n" + " " + tasks.get(i - 1).toString();
        result += "\n";
        result += Ui.showLine();
        storage.writeInto(tasks);
        return result;
    }

    /**
     * Adds a new task of type Todo into the task list and updates the storage (file).
     *
     * @param message The description of the task.
     * @param tasks The task list for the addition of task.
     * @param storage The storage object that contains the file for updating.
     * @return The response of the bot after adding a todo task.
     * @throws UnmatchedArgumentException If the description of the task is empty.
     */
    public String addTodo(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException {

        if (message.isBlank()) {
            throw new UnmatchedArgumentException(0, 1);
        }
        Task todo = new Todo(message, false);
        tasks.add(todo);
        storage.writeInto(tasks);
        String result = Ui.showLine() + "\n" + "Got it. I've added this task: " + "\n";
        result += " " + todo + "\n" + "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }

    /**
     * Adds a new task of type Deadline to the task list and updates the storage (file).
     *
     * @param message The description and deadline of the task.
     * @param tasks The task list for the addition of the task.
     * @param storage The storage object that contains the file for updating.
     * @return The response of the bot after adding a deadline.
     * @throws UnmatchedArgumentException If the arguments passed in are insufficient.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public String addDeadline(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException,
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
        storage.writeInto(tasks);
        String result = Ui.showLine() + "\n" + "Got it. I've added this task: " + "\n" + " " + dl + "\n";
        result += "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }

    /**
     * Adds a task of type Event to the task list and updates the storage (file).
     *
     * @param message The description and the event duration of the task.
     * @param tasks The task list for the addition of the task.
     * @param storage The storage object for that contains the file for updating.
     * @return The response of the bot after adding an event.
     * @throws UnmatchedArgumentException If the arguments passed in are insufficient.
     * @throws EmptyDescriptionException If the description of the task is empty.
     * @throws DateTimeException If the start or end time of the event is invalid.
     */
    public String addEvent(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException, EmptyDescriptionException {

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
        storage.writeInto(tasks);
        String result =  Ui.showLine() + "\n" + "Got it. I've added this task: " + "\n" + " " + e + "\n";
        result += "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }

    /**
     * Deletes a task from the task list based on the index provided and updates the storage (file).
     *
     * @param index The task with the index to be deleted.
     * @param tasks The task list for the deletion of task.
     * @param storage The storage object that contains the file for updating.
     * @return The response of the bot when the task is deleted.
     */
    public String delete(int index, TaskList tasks, Storage storage) {

        tasks.remove(index - 1);
        storage.writeInto(tasks);
        String result = Ui.showLine() + "\n" + "Noted. I've removed this task:" + "\n  " + tasks.get(index - 1);
        result += "\nNow that you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }

    /**
     * Checks for the task that has the contains the same date as provided in the task list.
     *
     * @param dueDate The task with the date in the task list.
     * @param tasks The task list to search for based on the provided date.
     * @return The list of tasks that match the due date inputted.
     */
    public String checkTaskDue(String dueDate, TaskList tasks) {

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
        String result = "\tHere " + (isGreaterThan1 ? "are" : "is") +" the " +
                (isGreaterThan1 ? "tasks that contain" : "task that contains") + " the date:\n";
        for (Task t : dueDateList) {
            result += t + "\n";
        }
        result += Ui.showLine();
        result += "\n";
        return result;
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
     * @return The response of the bot that match the task inputted.
     */
    public String find(String toFind, TaskList tasks) {

        ArrayList<Task> contain = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(toFind)) {
                contain.add(tasks.get(i));
            }
        }
        boolean isGreaterThan1 = contain.size() > 1;
        String result = "Here " + (isGreaterThan1 ? "are" : "is") + " are the matching " +
                (isGreaterThan1 ? "tasks" : "task") + " in your list:\n";
        for (int i = 0; i < contain.size(); i++) {
            result += (i + 1) + ". " + contain.get(i) + "\n";
        }
        result += Ui.showLine();
        result += "\n";
        return result;
    }
}
