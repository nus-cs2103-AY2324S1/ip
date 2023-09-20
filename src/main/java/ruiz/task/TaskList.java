package ruiz.task;

import java.io.IOException;
import java.util.ArrayList;

import ruiz.exception.BotException;
import ruiz.ui.Ui;
import ruiz.utils.Parser;

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
        assert this.taskList != null : "taskList should not be null";
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
    public String markTask(String input) throws BotException {
        int taskIndex = this.parser.getTaskNumber(input);
        if (taskIndex >= 0 && this.taskList.size() > taskIndex) {
            Task task = this.taskList.get(taskIndex);
            assert task != null : "task should not be null";
            task.mark();
            return ui.markTask(task);
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
    public String unmarkTask(String input) throws BotException {
        int taskIndex = this.parser.getTaskNumber(input);
        if (taskIndex >= 0 && this.taskList.size() > taskIndex) {
            Task task = this.taskList.get(taskIndex);
            assert task != null : "task should not be null";
            task.unmark();
            return ui.unmarkTask(task);
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
    public String deleteTask(String input) throws BotException {
        int taskIndex = this.parser.getTaskNumber(input);
        if (taskIndex >= 0 && this.taskList.size() > taskIndex) {
            Task task = this.taskList.get(taskIndex);
            assert task != null : "task should not be null";
            this.taskList.remove(taskIndex);
            return ui.deletedTask(task, this.getTaskListSize());
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
    public String addTodo(String input) throws BotException {
        String content = parser.getTodoDescription(input);
        String location = parser.getLocation(input);
        assert content != null : "toDo should not be null";
        Task temp = new ToDo(content, location);
        this.taskList.add(temp);
        return ui.addedNewTaskMsg(temp, this.getTaskListSize());
    }

    /**
     * This method creates a deadline and adds it to the list of tasks.
     *
     * @param input contains the keyWord "deadline" and the description of the deadline
     *              with the time it needs to be completed by.
     * @throws BotException if the input is not in the valid format of a deadline.
     */
    public String addDeadline(String input) throws BotException, IOException {
        String description = parser.getDeadlineDescription(input);
        String location = parser.getLocation(input);
        assert description != null : "description should not be null";
        String by = parser.getBy(input);
        Task temp = new Deadline(description, by, location);
        this.taskList.add(temp);
        return ui.addedNewTaskMsg(temp, this.getTaskListSize());
    }

    /**
     * This method creates an event and adds it to the list of tasks.
     *
     * @param input contains the command "event" and the description of the event
     *              with the time it takes place from and ends by.
     * @throws BotException if the input is not in the form a valid event.
     */
    public String addEvent(String input) throws BotException, IOException {
        String description = parser.getEventDescription(input);
        String beginning = parser.getEventBeginning(input);
        String end = parser.getEventEnd(input);
        String location = parser.getLocation(input);
        Task temp = new Event(description, beginning, end, location);

        this.taskList.add(temp);
        return ui.addedNewTaskMsg(temp, this.getTaskListSize());
    }

    /**
     * This method finds tasks that contain the keyword passed in.
     *
     * @param input contains the command "find" and the "keyword"
     * @throws BotException if the input is not in the form a valid "find".
     */
    public String findTasksWithKeyword(String input) throws BotException {
        String keyword = parser.getKeyword(input);
        ArrayList<Task> tasksWithKeyword = this.taskList.stream()
                .filter(task -> task.containsKeyword(keyword))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return new Ui().getTasks(tasksWithKeyword);
    }
}
