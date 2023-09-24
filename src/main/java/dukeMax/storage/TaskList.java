package storage;

import java.util.ArrayList;

import tasks.Task;
import helper.Ui;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public void clear() {
        taskList.clear();
        Storage.save(taskList);
        Ui.print("Okay, I have cleared all tasks.");
    }

    public void delete(int num) {
        Ui.print("I've removed this task:");
        taskList.get(num - 1).getStatus();
        taskList.remove(num - 1);
        Storage.save(taskList);
        Ui.print("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
    }

    public void mark(int num, boolean isDone) {
        taskList.get(num - 1).markItem(isDone);
        Storage.save(taskList);
    }

    public void print() {
        int index = 1;
        for (Task task: taskList) {
            System.out.println(index + ". " + task.getStatus());
            index ++;
        }
        Ui.print("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
    }

    public void print(String time) {
        int index = 1;
        for (Task task: taskList) {
            if(task.getTime() != null && task.getTime().contains(time)) {
                System.out.println(index + ". " + task.getStatus());
                index ++;
            }
        }
        index -= 1;
        Ui.print("Current # of " + plural(index, "task") + ": " + index);
    }

    public void add(Task input) {
        taskList.add(input);
        Storage.save(taskList);
        String[] messageList = {("Got it! This task has been added: ")
        , (input.getStatus())
        , ("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size())};
        Ui.print(messageList);
    }

    public int size() {
        return taskList.size();
    }

    private static String plural(int count, String word) {
        return (count <= 1) ? word : (word + "s");
    }
}
