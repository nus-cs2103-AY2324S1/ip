package duke.Tasks;

import java.time.LocalDate;
import java.util.regex.Pattern;

import duke.Commands;
import duke.FormatterDate;
import duke.Exceptions.EmptyDetailsOfTaskError;
import duke.Exceptions.UnknownCommandException;

/**
 * SuperClass of task to be implemented such as todo, deadLine and event
 */
public class Task {
    protected String description;
    public boolean isDone;

    /**
     * Constructor of the task object
     * @param description is the details of the task given
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done
     * @return a boolean, true if it is done and false if it is not done
     */
    public boolean isItDone() {
        return isDone;
    }

    /**
     * Gets the status icons that is recorded in the taskList when the
     * task is done
     * @return a String which is "x" if it is done and " " if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    /**
     * Changes a task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes a task to be not done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * To create a task without letting classes at higher level know the type of
     * task to be created, basically making use of polymorphism.
     * @param description takes in a string which is the details of the task
     * @return a Task
     * @throws EmptyDetailsOfTaskError is thrown when there is no description
     * for the task
     * @throws UnknownCommandException is thrown when the command is not
     * recognised by the user
     */
    public static Task createTask(String description) throws EmptyDetailsOfTaskError, UnknownCommandException {
        String[] splittedDescription = description.split(" ");
        String commandName = splittedDescription[0];
        if (description.startsWith(Commands.todo.name()) && splittedDescription.length != 1) {
            String task = description.replace("todo ", "");
            Task currentTask = new Todo(task);
            return currentTask;
        } else if (description.startsWith(Commands.deadline.name()) && splittedDescription.length != 1) {
            String task = description.replace("deadline ", "");
            String[] splittedTask = task.split(" /by ");
            if (splittedTask.length == 1) {
                throw new EmptyDetailsOfTaskError("The end of a deadline cannot be empty.");
            }
            String taskName = splittedTask[0];
            String end = splittedTask[1];
            Task currentTask = new Deadlines(taskName, end);
            return currentTask;
        } else if (description.startsWith(Commands.event.name()) && splittedDescription.length != 1) {
            String task = description.replace("event ", "");
            String[] splitStart = task.split(" /from ");
            if (splitStart.length == 1) {
                throw new EmptyDetailsOfTaskError("The start of a event cannot be empty.");
            }
            String[] splitEnd = splitStart[1].split(" /to ");
            if (splitEnd.length == 1) {
                throw new EmptyDetailsOfTaskError("The end of a event cannot be empty.");
            }
            String taskName = splitStart[0];
            String start = splitEnd[0];
            String end = splitEnd[1];
            Task currentTask = new Events(taskName, start, end);
            return currentTask;
        } else if (splittedDescription.length == 1 &&
                (commandName.equals(Commands.todo.name()) ||
                        commandName.equals(Commands.deadline.name()) ||
                        commandName.equals(Commands.event.name()))) {
            throw new EmptyDetailsOfTaskError("The description of a " + commandName + " cannot be empty.");
        } else {
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-C");
        }
    }

    /**
     * Takes in the string of the task that is saved in the previously
     * saved file and returns a task to be added back into the taskList
     * @param data is the string of the task
     * @return a Task to be added into the taskList
     */
    public static Task createTaskFromSavedData(String data) {
        String taskType = data.substring(2, 3);
        String taskStatus = data.substring(6, 7);
        String taskDescription = data.substring(10);
        Task currentTask = null;
        if (taskType.equals("T")) {
            currentTask = new Todo(taskDescription);
        } else if (taskType.equals("D")) {
            String[] splittedData = taskDescription.split(Pattern.quote(" (by: "));
            String taskEnd = splittedData[1].replace(")", "");
            currentTask = new Deadlines(splittedData[0], taskEnd);
        } else if (taskType.equals("E")) {
            String[] splittedData = taskDescription.split(Pattern.quote(" (from: "));
            String[] splittedDetails = splittedData[1].split(Pattern.quote(" to: "));
            String taskStart = splittedDetails[0];
            String taskEnd = splittedDetails[1].replace(")", "");
            currentTask = new Events(splittedData[0], taskStart, taskEnd);
        }
        if (taskStatus.equals("x")) {
            currentTask.markAsDone();
        }
        return currentTask;
    }

    /**
     * Converts the date in the string format to a date in
     * the localDate format
     * @param sDate is the string of the date
     * @return a localDate object
     */
    public static LocalDate convertDatePlease(String sDate) {
        //@@author-zhanyang01-reused
        //Reused from m1oojv DateTimeParser.java, LocalDateTime method
        //reuse with minor modifications
        for (FormatterDate formatterDate : FormatterDate.values()) {
            try {
                return LocalDate.parse(sDate, formatterDate.formatter);
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println("Why is it invalid??!!??!");
        return null;
        //@@author
    }

    /**
     * toString of a task, basically the string of the task object
     * @return a string of the task object
     */
    @Override
    public String toString() {
        return String.format(" %s | %s", getStatusIcon(), description);
    }
}