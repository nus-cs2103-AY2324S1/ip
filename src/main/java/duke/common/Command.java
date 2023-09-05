package duke.common;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command.
 */
public class Command {
    public String handleFarewell() {
        return Message.FAREWELL;
    }
    public String handleList(TaskList tasks) {
        return Message.LIST_TASKS + tasks;
    }
    public String handleUnmarkTask(TaskList tasks, int taskNumber) throws DukeException {
        Task task = tasks.unmarkTask(taskNumber);
        return Message.UNMARK_TASK + task;
    }
    public String handleMarkTask(TaskList tasks, int taskNumber) throws DukeException {
        Task task = tasks.markTask(taskNumber);
        return Message.MARK_TASK + task;
    }
    public String handleAddTodo(TaskList tasks, String description) {
        Todo todo = tasks.addTodo(description);
        return Message.ADD_TASK + todo + String.format(Message.TASK_STATUS, tasks.getSize());
    }
    public String handleAddDeadline(TaskList tasks, String description, LocalDateTime dateTime) {
        Deadline deadline = tasks.addDeadline(description, dateTime);
        return Message.ADD_TASK + deadline + String.format(Message.TASK_STATUS, tasks.getSize());
    }
    public String handleAddEvent(TaskList tasks, String description, LocalDateTime start, LocalDateTime end) {
        Event event = tasks.addEvent(description, start, end);
        return Message.ADD_TASK + event + String.format(Message.TASK_STATUS, tasks.getSize());
    }
    public String handleDeleteTask(TaskList tasks, int taskNumber) throws DukeException {
        Task task = tasks.deleteTask(taskNumber);
        return Message.DELETE_TASK + task + String.format(Message.TASK_STATUS, tasks.getSize());
    }
    public String handleFindTask(TaskList tasks, String keyword) {
        TaskList matchingTasks = tasks.filterTasks(keyword);
        return Message.FIND_TASKS + matchingTasks;
    }
}
