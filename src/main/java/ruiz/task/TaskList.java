package ruiz.task;

import ruiz.Parser;
import ruiz.Ui;
import ruiz.exception.BotException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor for the taskList class that is inititalised with a pre-saved taskList
     *
     * @param taskList List of tasks pre-saved in the hard disk
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * Constructor for the taskList class
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the size of the taskList.
     *
     * @return size of taskList.
     */
    public int getTaskListSize() {
        return this.taskList.size();
    }

    /**
     * This method marks the given task in the input.
     *
     * @param input The string that consists of the keyWord "mark" and task index being input by the user.
     * @throws BotException if the input is not a valid one.
     */
    public void markTask(String input) throws BotException {
        if (input.split(" ").length < 2) {
            throw new BotException("OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BotException("OOPS!!! The index of a task has to be an integer.");
        }
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && this.taskList.size() > taskIndex) {
            Task task = this.taskList.get(taskIndex);
            task.mark();
            ui.markTask(task);
        } else {
            throw new BotException("This task does not exist!");
        }
    }

    /**
     * This method unmarks the specified task in the string input.
     *
     * @param input The string that consists of the keyWord "unmark" and task index being input by the user.
     * @throws BotException if the input is not a valid one.
     */
    public void unmarkTask(String input) throws BotException {
        if (input.split(" ").length < 2) {
            throw new BotException("OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BotException("OOPS!!! The index of a task has to be an integer.");
        }
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && this.taskList.size() > taskIndex) {
            Task task = this.taskList.get(taskIndex);
            task.unmark();
            ui.unmarkTask(task);
        } else {
            throw new BotException("This task does not exist!");
        }
    }

    /**
     * This method deletes the specified task from its index from the list of tasks.
     *
     * @param input The string that contains the keyWord "delete" and the index of the task.
     * @throws BotException if the input of the user is not a valid one.
     */
    public void deleteTask(String input) throws BotException {
        if (input.split(" ").length < 2) {
            throw new BotException("OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BotException("OOPS!!! The index of a task has to be an integer.");
        }
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && this.taskList.size() > taskIndex) {
            Task task = this.taskList.get(taskIndex);
            this.taskList.remove(taskIndex);
            ui.deletedTask(task, this.getTaskListSize());
        } else {
            throw new BotException("This task does not exist!");
        }
    }

    /**
     * This method creates a todo and adds it to the list of tasks.
     *
     * @param input contains the keyWord "todo" and the description of the todo.
     * @throws BotException if the input is not in the format of a valid todo.
     */
    public void addTodo(String input) throws BotException {
        String content = parser.getTodoDescription(input);
        Task temp = new ToDos(content);
        this.taskList.add(temp);
        ui.addedNewTaskMsg(temp, this.getTaskListSize());
    }

    /**
     * This method creates a deadline and adds it to the list of tasks.
     *
     * @param input contains the keyWord "deadline" and the description of the deadline
     *              with the time it needs to be completed by.
     * @throws BotException if the input is not in the valid format of a deadline.
     */
    public void addDeadline(String input) throws BotException, IOException {
        String description = parser.getDeadlineDescription(input);
        String by = parser.getBy(input);
        Task temp = new Deadlines(description, by);
        this.taskList.add(temp);
        ui.addedNewTaskMsg(temp, this.getTaskListSize());
    }

    /**
     * This method creates an event and adds it to the list of tasks.
     *
     * @param input contains the keyWord "event" and the description of the event
     *              with the time it takes place from and ends by.
     * @throws BotException if the input is not in the form a valid event.
     */
    public void addEvent(String input) throws BotException, IOException {
        String description = parser.getEventDescription(input);
        String from = parser.getFrom(input);
        String to = parser.getTo(input);
        Task temp = new Events(description, from, to);
        this.taskList.add(temp);
        ui.addedNewTaskMsg(temp, this.getTaskListSize());
    }

    public void findTasksWithKeyword(String input) throws BotException {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        String keyword = parser.getKeyword(input);
        for (Task task : taskList) {
            if (task.containsKeyword(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        new Ui().getTasks(tasksWithKeyword);
    }
}
