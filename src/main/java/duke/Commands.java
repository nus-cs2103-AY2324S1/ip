package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.List;

public class Commands {
    private Ui ui;
    private TaskList task_List;
    private Storage storage;


    public Commands(Ui ui, Storage storage, TaskList task_List) {
        this.ui = ui;
        this.task_List = task_List;
        this.storage = storage;
    }
    public void addTodoTask(String input) {
        Task task = new Todo(input);
        generalAddTasks(task);
    }

    public void addDeadlineTask(String input, String by) {
        Task task = new Deadline(input, by);
        generalAddTasks(task);
    }

    public void addEventTask(String input, String from, String by) {
        Task task = new Event(input, from, by);
        generalAddTasks(task);
    }

    public void generalAddTasks(Task task) {
        task_List.addTask(task);
        storage.saveTasksToFile(task_List);
        ui.printAddTask(task, task_List.getTask_Count());
    }

    public void listTasks(TaskList task_List){
        List<Task> tasks = task_List.getTask_List();
        int taskCount = task_List.getTask_Count();
        ui.printTaskList(tasks, taskCount);
    }

    public void markTasks(int task_number) {
        try {
            if (task_number > 0 && task_List.getTask(task_number) != null) {
                Task taskTobeMarked = task_List.getTask(task_number);
                taskTobeMarked.taskDone();
                storage.saveTasksToFile(task_List);
                ui.printMarkTask(taskTobeMarked);
            } else {
                throw new MYBotExceptions.NoSuchTaskException();
            }
        } catch (MYBotExceptions e) {
            ui.printException(e);
        }
    }


    public void unmarkTasks(int task_number){
        try {
            if (task_number > 0 && task_List.getTask(task_number) != null) {
                Task taskTobeMarked = task_List.getTask(task_number);
                taskTobeMarked.undoTask();
                storage.saveTasksToFile(task_List);
                ui.printUnmarkTask(taskTobeMarked);
            } else {
                throw new MYBotExceptions.NoSuchTaskException();
            }
        } catch (MYBotExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeTasks(int task_number) {

        try {
            if (task_number >= 0 && task_number <= task_List.getTask_Count()) {
                Task taskToBeRemoved = task_List.getTask(task_number);
                task_List.removeTask(task_number);
                storage.saveTasksToFile(task_List);
                ui.printRemoveTasks(taskToBeRemoved, task_List);
            } else {
                throw new MYBotExceptions.InvalidTaskException();
            }
        } catch (MYBotExceptions e) {
            ui.printException(e);
        }
    }

    /**
     * Find and prints tasks that match the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public void findTasks(String keyword) {
        List<Task> matchingTasks = task_List.findMatchingTasks(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
}
