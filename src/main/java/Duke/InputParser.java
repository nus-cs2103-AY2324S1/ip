package Duke;
import DukeException.*;
import Task.Task;
import Task.TaskList;

public class InputParser {

    private TaskList taskList;
    public InputParser(TaskList taskList) {
        this.taskList = taskList;
    }
    public Message HandleInput(String input, Duke duke) {
        String[] inputComponents = input.trim().split(" ", 2);
        String command = inputComponents[0];
        String content = "";
        if(inputComponents.length > 1)
            content = inputComponents[1];
        try {
            switch (command) {
                case "bye":
                    duke.Exit();
                    return Message.OnExit();
                case "todo":
                    Task task = Task.Of(content, Task.TaskType.TODO);
                    taskList.AddTask(task);
                    return Message.OnTaskAdd((task));
                case "deadline":
                    task = Task.Of(content, Task.TaskType.DEADLINE);
                    taskList.AddTask(task);
                    return Message.OnTaskAdd((task));
                case "event":
                    task = Task.Of(content, Task.TaskType.EVENT);
                    taskList.AddTask(task);
                    return Message.OnTaskAdd((task));
                case "list":
                    return Message.ChainList(Message.ConvertTasks(taskList), "\n").ChainTo(
                    Message.NumberOfTasks(taskList), "\n");
                case "mark":
                    int taskIndex;
                    try {
                        taskIndex = Integer.parseInt(content) - 1;
                    } catch (NumberFormatException e) {
                        throw new InvalidTaskIndexException(content);
                    }
                    try {
                        task = taskList.GetTask(taskIndex);
                    } catch (IndexOutOfBoundsException e) {
                        throw new TaskIndexOutOfRangeException(taskIndex + " is not a valid index");
                    }
                    task.SetCompleted();
                    return Message.OnTaskComplete(task);
                case "unmark":
                    try {
                        taskIndex = Integer.parseInt(content) - 1;
                    } catch (NumberFormatException e) {
                        return Message.OnInvalidTaskNo(content);
                    }
                    try {
                        task = taskList.GetTask(taskIndex);
                    } catch (IndexOutOfBoundsException e) {
                        throw new TaskIndexOutOfRangeException(taskIndex + " is not a valid index");
                    }
                    task.SetUncompleted();
                    return Message.OnTaskUncomplete(task);
                case "delete":
                    try {
                        taskIndex = Integer.parseInt(content) - 1;
                    } catch (NumberFormatException e) {
                        return Message.OnInvalidTaskNo(content);
                    }
                    try {
                        task = taskList.GetTask(taskIndex);
                    } catch (IndexOutOfBoundsException e) {
                        throw new TaskIndexOutOfRangeException(taskIndex + " is not a valid index");
                    }
                    taskList.RemoveTask((task));
                    return Message.OnTaskDelete(task);
                default:
                    throw new NoCommandFoundException(command + " is not a valid command.");
            }
        } catch (EmptyTaskDescException e) {
            return Message.EmptyTaskName();
        } catch (InvalidTaskFormatException e) {
            return Message.OnInvalidTaskFormat();
        } catch (NoCommandFoundException e) {
            return Message.NoCommandFound();
        } catch (InvalidTaskIndexException e) {
            return Message.OnInvalidTaskNo(e.getMessage());
        } catch (TaskIndexOutOfRangeException e) {
            return Message.OnTaskIndexOutOfRange();
        }
    }
}
