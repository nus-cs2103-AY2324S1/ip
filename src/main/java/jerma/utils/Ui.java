package jerma.utils;

import jerma.tasks.Task;

public class Ui {

    public String hello() {
        String output = "Hello! I'm Jerma.";
        System.out.println(output);
        return output;
    }

    public String bye() {
        String output = "See ya soon!";
        System.out.println();
        return output;
    }

    public String error(String message) {
        String output = "Error: " + message;
        System.out.println(output);
        return output;
    }

    public String listTasks(TaskList tasks) {
        System.out.println(tasks);
        return tasks.toString();
    }

    public String newTask(Task task) {
        String type = task.getClass().getName().split("\\.")[1].toLowerCase();
        String output = String.format("Added %s: %s", type, task);
        System.out.println(output);
        return output;
    }

    public String markTask(Task task) {
        String output = "Marked as done: \n" + task;
        System.out.println(output);
        return output;
    }

    public String unmarkTask(Task task) {
        String output = "Marked as undone: \n" + task;
        System.out.println(output);
        return output;
    }

    public String deleteTask(Task task, int numberOfTasksRemaining) {
        String output = String.format(
                "Removed the task: \n%s \nYou have %d tasks remaining.", task,
                numberOfTasksRemaining);
        System.out.println(output);
        return output;
    }
}
