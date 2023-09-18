package duke.task;

import java.time.LocalDate;
import java.util.regex.Pattern;

import duke.FormatterDate;
import duke.exception.EmptyDetailsOfTaskError;
import duke.exception.UnknownCommandException;

/**
 * Represent the superClass of task to be implemented such as todo, deadLine and
 * event
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Represent the constructor of the task object
     * 
     * @param description is the details of the task given
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done
     * 
     * @return a boolean, true if it is done and false if it is not done
     */
    public boolean isItDone() {
        return isDone;
    }

    /**
     * Gets the status icons that is recorded in the taskList when the
     * task is done
     * 
     * @return a String which is "x" if it is done and " " if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    /**
     * Changes a task to be done
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Changes a task to be not done
     */
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Creates a task without letting classes at higher level know the type of
     * task to be created, basically making use of polymorphism.
     * 
     * @param description takes in a string which is the details of the task
     * @return a Task
     * @throws EmptyDetailsOfTaskError is thrown when there is no description
     *                                 for the task
     * @throws UnknownCommandException is thrown when the command is not
     *                                 recognised by the user
     */
    public static Task createTask(String description) throws EmptyDetailsOfTaskError, UnknownCommandException {
        assert description != null : "description should not be null";
        String[] splittedDescription = description.split(" ");
        String commandName = splittedDescription[0];

        boolean isTodo = commandName.equals("todo");
        boolean isDeadline = commandName.equals("deadline");
        boolean isEvent = commandName.equals("event");
        boolean isTask = isTodo || isDeadline || isEvent;
        boolean isInvalidTask = isTask && splittedDescription.length == 1;
        boolean isValidTodo = isTodo && splittedDescription.length != 1;
        boolean notEmptyDeadline = isDeadline && splittedDescription.length != 1;
        boolean notEmptyEvent = isEvent && splittedDescription.length != 1;

        if (isValidTodo) {
            return createTodoTask(description);
        } else if (notEmptyDeadline) {
            return createDeadlineTask(description);
        } else if (notEmptyEvent) {
            return createEventTask(description);
        } else if (isInvalidTask) {
            throw new EmptyDetailsOfTaskError("The description of a " + commandName + " cannot be empty.");
        } else {
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-C");
        }
    }

    /**
     * Creates a task of type Todo
     * 
     * @param data
     * @return a Task of type Todo
     */
    private static Task createTodoTask(String data) {
        assert data != null : "data should not be null";
        String taskDescription = data.replace("todo ", "");
        return new Todo(taskDescription);
    }

    /**
     * Creates a task of type Deadline
     * 
     * @param data
     * @return a Task of type Deadline
     * @throws EmptyDetailsOfTaskError
     */
    private static Task createDeadlineTask(String data) throws EmptyDetailsOfTaskError {
        String[] splittedTask = data.replace("deadline ", "").split(" /by ");
        if (splittedTask.length == 1) {
            throw new EmptyDetailsOfTaskError("The end of a deadline cannot be empty.");
        }
        String taskName = splittedTask[0];
        String end = splittedTask[1];
        return new Deadlines(taskName, end);
    }

    /**
     * Creates a task of type Event
     * 
     * @param data
     * @return a Task of type Event
     * @throws EmptyDetailsOfTaskError
     */
    private static Task createEventTask(String data) throws EmptyDetailsOfTaskError {
        String[] splittedTask = data.replace("event ", "").split(" /from ");
        if (splittedTask.length == 1) {
            throw new EmptyDetailsOfTaskError("The start of a event cannot be empty.");
        }
        String[] splittedDetails = splittedTask[1].split(" /to ");
        if (splittedDetails.length == 1) {
            throw new EmptyDetailsOfTaskError("The end of a event cannot be empty.");
        }
        String taskName = splittedTask[0];
        String start = splittedDetails[0];
        String end = splittedDetails[1];
        return new Events(taskName, start, end);
    }

    /**
     * Takes in the string of the task that is saved in the previously
     * saved file and returns a task to be added back into the taskList
     * 
     * @param data is the string of the task
     * @return a Task to be added into the taskList
     */
    public static Task createTaskFromSavedData(String data) {
        assert data != null : "data should not be null";
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
            currentTask.setAsDone();
        }
        return currentTask;
    }

    /**
     * Converts the date in the string format to a date in
     * the localDate format
     * 
     * @param sDate is the string of the date
     * @return a localDate object
     */
    public static LocalDate convertDateFromStringtoObj(String sDate) {
        // @@author-zhanyang01-reused
        // Reused from m1oojv DateTimeParser.java, LocalDateTime method
        // reuse with minor modifications
        for (FormatterDate formatterDate : FormatterDate.values()) {
            try {
                return LocalDate.parse(sDate, formatterDate.formatter);
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println("Why is it invalid??!!??!");
        return null;
        // @@author
    }

    /**
     * Represents toString of a task, basically the string of the task object
     * 
     * @return a string of the task object
     */
    @Override
    public String toString() {
        return String.format(" %s | %s", getStatusIcon(), description);
    }
}