package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.utils.TaskList;

import java.time.LocalDateTime;
import java.util.List;

public class Message {


    public String listMessage(TaskList tasks) {
        return "Here are the tasks in your List:\n"+ tasks;
    }

    public String markMessage(TaskList tasks, int taskIndex) throws DukeException {
        Task task = tasks.markTaskDone(taskIndex);
        return "OK, I've marked this task as not done yet:\n" + tasks;
    }

    public String unmarkMessage(TaskList tasks, int taskIndex) throws DukeException {
        Task task = tasks.unmarkTask(taskIndex);
        return "OK, I've marked this task as not done yet:\n" + tasks;
    }

    public String todoMessage(TaskList tasks, String description) throws DukeException {
        Task todo = tasks.addTodoTask(description);
        return "Got it. I've added this task:\n "
                + todo + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    public String deadlineMessage(TaskList tasks, String description, LocalDateTime dateTime) throws DukeException {
        Task deadline = tasks.addDeadlineTask(description, dateTime);
        return "Got it. I've added this task:\n "
                + deadline + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    public String eventMessage(TaskList tasks, String description, LocalDateTime from, LocalDateTime to) throws DukeException {
        Task event = tasks.addEventTask(description, from, to);
        return "Got it. I've added this task:\n "
                + event + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    public String deleteMessage(TaskList tasks, int taskIndex) throws DukeException {
        Task task = tasks.deleteTask(taskIndex);
        return "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    public String findMessage(TaskList tasks, String keyword) {
        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return "Here are the matching tasks in your list:\n" + matchingTasks;
    }

    public String farewellMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
