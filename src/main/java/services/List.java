package services;

import java.util.ArrayList;

public class List {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;

    public static void add(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        taskCount++;
        Format.print("added: " + newTask);
    }

    public static void show() {
        String result = "Sir, there are " +  taskCount + " tasks on your calendar:\n";
        for (int i = 1; i <= taskCount; i++) {
            result += i + ". " + taskList.get(i - 1) + "\n";
        }
        Format.print(result);
    }
}
