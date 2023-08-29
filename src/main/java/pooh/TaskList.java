package pooh;

import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int task) {
        this.taskList.remove(task);
    }

    public Task getTask(int index) throws InvalidTaskException {
        try {
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskException();
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public static void addTask(TaskList taskList, String userAction, String cmd) throws
            EmptyTaskDescriptorsException {
        if (cmd.split(" ", 2).length == 1) {
            throw new EmptyTaskDescriptorsException();
        }
        String userArgs = cmd.split(" ", 2)[1];
        Task task;
        if (userAction.equalsIgnoreCase("todo")) {
            task = new Todo(userArgs);
        } else if (userAction.equalsIgnoreCase("deadline")) {
            String[] specificArgs = userArgs.split(" /by ", 2);
            String description = specificArgs[0];
            String deadlineTime = specificArgs[1];
            task = new Deadline(description, deadlineTime);
        } else {
            String[] specificArgs = userArgs.split(" /from ", 2);
            String description = specificArgs[0];
            String[] eventTimes = specificArgs[1].split(" /to ");
            String eventStartTime = eventTimes[0];
            String eventEndTime = eventTimes[1];
            task = new Event(description, eventStartTime, eventEndTime);
        }
        taskList.add(task);
        String addTaskMessage = String.format("      Got it. I've added this task:\n          %s\n      Now you have " +
                "%d tasks in the list", task, taskList.getSize());
        Ui.respond(addTaskMessage);
    }

    public static void deleteTask(TaskList taskList, int index) throws InvalidTaskException {
        Task task = taskList.getTask(index);
        taskList.remove(index);
        String delTaskMessage = String.format("      Noted. I've removed this task:\n          %s\n      Now you have" +
                " %d tasks in the list", task, taskList.getSize());
        Ui.respond(delTaskMessage);
    }
}
