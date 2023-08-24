
import Task.Task;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    final private List<Task> tasks = new ArrayList<>();
    public InputHandler() {}
    public void HandleInput(String input) {
        String[] inputComponents = input.trim().split(" ", 2);
        String command = inputComponents[0];
        String content = "";
        if(inputComponents.length > 1)
            content = inputComponents[1];
        try {
            switch (command) {
                case "bye":
                    Message.OnExit().Print();
                    Duke.Exit();
                    break;
                case "todo":
                    Task task = Task.Of(content, Task.TaskType.TODO);
                    tasks.add(task);
                    Message.OnTaskAdd((task)).Print();
                    break;
                case "deadline":
                    task = Task.Of(content, Task.TaskType.DEADLINE);
                    tasks.add(task);
                    Message.OnTaskAdd((task)).Print();
                    break;
                case "event":
                    task = Task.Of(content, Task.TaskType.EVENT);
                    tasks.add(task);
                    Message.OnTaskAdd((task)).Print();
                    break;
                case "list":
                    Message.ChainList(Message.ConvertTasks(tasks), "\n").Print();
                    break;
                case "mark":
                    int taskIndex;
                    try {
                        taskIndex = Integer.parseInt(content) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException.InvalidTaskIndexException(content);
                    }
                    try {
                        task = tasks.get(taskIndex);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException.TaskIndexOutOfRangeException();
                    }
                    task.SetCompleted();
                    Message.OnTaskComplete(task).Print();
                    break;
                case "unmark":
                    try {
                        taskIndex = Integer.parseInt(content) - 1;
                    } catch (NumberFormatException e) {
                        Message.OnInvalidTaskNo(content).Print();
                        break;
                    }
                    task = tasks.get(taskIndex);
                    task.SetUncompleted();
                    Message.OnTaskUncomplete(task).Print();
                    break;
                default:
                    throw new DukeException.NoCommandFoundException();
            }
        } catch (DukeException.EmptyTaskDescException e) {
            Message.EmptyTaskName().Print();
        } catch (DukeException.NoCommandFoundException e) {
            Message.NoCommandFound().Print();
        } catch (DukeException.InvalidTaskIndexException e) {
            Message.OnInvalidTaskNo(e.getMessage()).Print();
        } catch (DukeException.TaskIndexOutOfRangeException e) {
            Message.OnTaskIndexOutOfRange().Print();
        }
    }
}
