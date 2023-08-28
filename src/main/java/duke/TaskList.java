package duke;

import duke.exception.TaskNotFoundException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks :(");
            return;
        }
        int numTask = 1;
        for (Task task : taskList) {
            System.out.printf("%d.%s\n", numTask, task);
            numTask += 1;
        }
    }

    public void markTask(int taskId) throws TaskNotFoundException {
        if (taskId >= taskList.size()) throw new TaskNotFoundException();
        Task doneTask = taskList.get(taskId);
        doneTask.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);
    }

    public void unmarkTask(int taskId) throws TaskNotFoundException {
        if (taskId >= taskList.size()) throw new TaskNotFoundException();
        Task unmarkTask = taskList.get(taskId);
        unmarkTask.unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkTask);
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    public void deleteTask(int taskId) throws TaskNotFoundException {
        if (taskId >= taskList.size()) throw new TaskNotFoundException();
        Task removedTask = taskList.remove(taskId);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    public void write() {
        Storage.writeToFile(taskList);
    }
}
