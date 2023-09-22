package tasks;

import helpers.ClashDetection;

import java.util.ArrayList;

/**
 * This class encapsulates a TaskList that stores all the Task objects
 * the user has created, and handles updating of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(0);
    }

    /**
     * Constructs a TaskList object that creates Task objects and
     * stores them in the task list.
     *
     * @param content A String containing information of the Task objects to be added.
     */
    public TaskList(String content) {
        this.tasks = new ArrayList<>(0);

        addTextToTask(content);
    }

    /**
     * Converts the String to Task objects to be added to the task list.
     *
     * @param content A String containing information of the Task objects to be added.
     */
    public void addTextToTask(String content) {
        String[] lines = content.split("\n");

        for (String line: lines) {
            String[] parts = line.split(" ", 3);
            boolean isDone = parts[1].equals("true");

            if (parts[0].equals("T")) {
                addTodoTask(parts[2].trim(), isDone);

            } else if (parts[0].equals("D")) {
                String[] arr = parts[2].split("/");
                addDeadlineTask(arr[0].trim(), arr[1].trim(), isDone);

            } else {
                String[] arr = parts[2].split("/");
                addEventTask(arr[0].trim(), arr[1].trim(), arr[2].trim(), isDone);

            }
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Prints onto the console all tasks on the current task list.
     *
     * @return The message to be displayed upon completion.
     */
    public String listTasks() {

        String message = "";
        message += "Here are the tasks in your list:\n";
        assert (tasks.size() >= 0);
        if (tasks.size() == 0) {
            message += "There's nothing in your list";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                message += ((i + 1) + ". " + tasks.get(i) + "\n");
            }
        }
        return message;
    }


    /**
     * Sets a particular task to 'Done' status.
     *
     * @param taskNumber The task to be set to 'Done' based on the order of the task list. Starts at 1.
     * @return The message to be displayed upon completion.
     * @throws IndexOutOfBoundsException When the taskNumber does not exist.
     */
    public String markTaskDone(int taskNumber) throws IndexOutOfBoundsException {

        String message = "";

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        tasks.get(taskNumber - 1).setIsDone();

        message += "That's the hustle ye. You is good on this task:\n"
                + tasks.get(taskNumber - 1);

        return message;
    }

    /**
     * Sets a particular task to 'Not Done' status.
     *
     * @param taskNumber The task to be set to 'Not Done' based on the order of the task list. Starts at 1.
     * @return The message to be displayed upon completion.
     * @throws IndexOutOfBoundsException When the taskNumber does not exist.
     */
    public String unmarkTaskDone(int taskNumber) throws IndexOutOfBoundsException {

        String message = "";

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        tasks.get(taskNumber - 1).setIsNotDone();

        message += "Ok... Guess you're not actually done with this:\n" + tasks.get(taskNumber - 1);

        return message;
    }

    /**
     * Deletes a Task from the task list.
     *
     * @param taskNumber The task to be set to deleted based on the order of the task list. Starts at 1.
     * @return The message to be displayed upon completion.
     * @throws IndexOutOfBoundsException When the taskNumber does not exist.
     */
    public String deleteTask(int taskNumber) throws IndexOutOfBoundsException {

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        Task removedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        String message = "";
        message += "banished this task to the shadow realm:\n" + removedTask + "\n" + getNumTasks();
        return message;
    }

    /**
     * Adds a ToDo task object to the task list.
     *
     * @param description Description of the ToDo object.
     * @return The message to be displayed upon completion.
     */
    public String addTodoTask(String description) {

        ToDo todo = new ToDo(description);

        this.tasks.add(todo);

        String message = "";
        message += "added new task:\n" + todo + "\n" + getNumTasks();


        return message;
    }

    /**
     * Overloaded method that adds a ToDo task object to the task list
     * that specifies completion status.
     *
     * @param description Description of the ToDo object.
     * @param isDone Completion status.
     * @return The message to be displayed upon completion.
     */
    public String addTodoTask(String description, boolean isDone) {

        ToDo todo = new ToDo(description);
        todo.setDoneStatus(isDone);
        this.tasks.add(todo);


        String message = "";
        message += "added new task:\n" + todo + "\n" + getNumTasks();


        return message;
    }

    /**
     * Adds a Deadline task object to the task list.
     *
     * @param description Description of the Deadline object.
     * @param end end Date/Time of the Deadline object.
     * @return The message to be displayed upon completion.
     */
    public String addDeadlineTask(String description, String end) {

        Deadline deadline = new Deadline(description, end);
        this.tasks.add(deadline);

        String message = "";
        message += "added new task:\n" + deadline + "\n" + getNumTasks();

        return message;
    }

    /**
     * Overloaded method that adds a Deadline task object to the task list
     * that specifies completion status.
     *
     * @param description Description of the Deadline object.
     * @param end end Date/Time of the Deadline object.
     * @param isDone Completion status.
     * @return The message to be displayed upon completion.
     */
    public String addDeadlineTask(String description, String end, boolean isDone) {

        Deadline deadline = new Deadline(description, end);
        deadline.setDoneStatus(isDone);
        this.tasks.add(deadline);

        String message = "";
        message += "added new task:\n" + deadline + "\n" + getNumTasks();

        return message;
    }

    /**
     * Adds an Event task object to the task list.
     *
     * @param description Description of the Event object.
     * @param start start Date/Time of the Event object.
     * @param end end Date/Time of the Event object.
     * @return The message to be displayed upon completion.
     */
    public String addEventTask(String description, String start, String end) {

        String message = "";

        Event event = new Event(description, start, end);
        ClashDetection clashDetector = new ClashDetection(tasks, event);
        if (clashDetector.detectClash()) {
            message += clashDetector.constructClashMessage();
        }
        this.tasks.add(event);

        message += "added new task:\n" + event + "\n" + getNumTasks();

        return message;
    }

    public String addEventTask(String description, String start, String end, boolean isDone) {

        String message = "";

        Event event = new Event(description, start, end);
        event.setDoneStatus(isDone);
        ClashDetection clashDetector = new ClashDetection(tasks, event);
        if (clashDetector.detectClash()) {
            message += clashDetector.constructClashMessage();
        }
        this.tasks.add(event);

        message += "added new task:\n" + event + "\n" + getNumTasks();

        return message;
    }

    /**
     * Searches the Task List by the task description, according to the keyword given.
     *
     * @param keyword The keyword to search by.
     * @return The filtered task list containing only tasks with their descriptions matching the keyword.
     */
    public ArrayList<Task> findByKeyword(String keyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();

        for (Task task : tasks) {
            String taskDescription = task.description;
            if (taskDescription.contains(keyword)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * Prints onto the console the number of tasks currently in the task list.
     *
     * @return The message to be displayed upon completion.
     */
    public String getNumTasks() {
        assert tasks.size() >= 0 : "task list is empty or not empty";
        return "you now have " + tasks.size() + " tasks in your list.";
    }

    public void setTaskDoneStatus(String input, Task task) {
        if (input.equals("true")) {
            task.setIsDone();
        } else {
            task.setIsNotDone();
        }
    }
}
