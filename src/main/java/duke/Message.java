package duke;

import duke.task.Task;
import duke.utils.TaskList;

import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private String message = "";

    public void listMessage(TaskList tasks) {
        message = "Here are the tasks in your List:\n"+ tasks;
    }

    public void markMessage(TaskList tasks, int taskIndex) throws DukeException {
        tasks.markTaskDone(taskIndex);
        message = "OK, I've marked this task as done:\n" + tasks;
    }

    public void unmarkMessage(TaskList tasks, int taskIndex) throws DukeException {
        tasks.unmarkTask(taskIndex);
        message = "OK, I've marked this task as not done yet:\n" + tasks;
    }

    public void todoMessage(TaskList tasks, String description) throws DukeException {
        Task todo = tasks.addTodoTask(description);
        message = "Got it. I've added this task:\n "
                + todo + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    public void deadlineMessage(TaskList tasks, String description, LocalDateTime dateTime) throws DukeException {
        Task deadline = tasks.addDeadlineTask(description, dateTime);
        message = "Got it. I've added this task:\n "
                + deadline + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    public void eventMessage(TaskList tasks, String description, LocalDateTime from, LocalDateTime to) throws DukeException {
        Task event = tasks.addEventTask(description, from, to);
        message = "Got it. I've added this task:\n "
                + event + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    public void deleteMessage(TaskList tasks, int taskIndex) throws DukeException {
        Task task = tasks.deleteTask(taskIndex);
        message = "Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    public void findMessage(TaskList tasks, String keyword) {
        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        message = "Here are the matching tasks in your list:\n" + matchingTasks;
    }

    public void farewellMessage() {
        message = "Bye. Hope to see you again soon!";
    }

    public String getMessage() {
        return message;
    }
}
