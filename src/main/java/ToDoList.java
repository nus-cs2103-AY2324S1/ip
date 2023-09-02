import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class ToDoList {
    protected ArrayList<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "Osps! Your to-do list is empty. Add some tasks now:)";
        }

        String result = "YOUR TO-DO LIST:\n";
        for (int i = 0; i < this.taskList.size(); i++) {
            int order = i + 1;
            result+= order + ". " + taskList.get(i) +"\n";
        }
        result+= "----------END OF YOUR TO-DO LIST----------\n";
        result+= taskList.size() + " tasks in total";
        return result;
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("added: " + task);
    }

    public void markTask(int i) {
        int order = i - 1;
        taskList.get(order).markAsDone();
    }

    public void unmarkTask(int i) {
        int order = i - 1;
        taskList.get(order).markAsNotDone();
    }

    public void deleteTask(int i) {
        int order = i - 1;
        Task task = taskList.get(order);
        taskList.remove(order);
        System.out.println("Noted. I've removed this task: " + task);
    }
}
