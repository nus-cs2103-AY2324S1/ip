package Iris;

import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<Task> list;

    public ToDoList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.remove(index - 1);
    }

    public Task get(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        return list.get(index - 1);
    }

    public void mark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.get(index - 1).markDone();
    }

    public void unmark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.get(index - 1).markUndone();
    }

    public int size() {
        return list.size();
    }

    public static void addTask(ToDoList toDoList, String command, String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description is missing.");
        }
        Task task = null;
        if (command.equalsIgnoreCase("todo")) {
            task = new Todo(description);
        } else if (command.equalsIgnoreCase("deadline")) {
            String[] deadlineSections = description.split(" /by ", 2);
            String name = deadlineSections[0];
            String deadlineString = deadlineSections[1];
            task = new Deadline(name, deadlineString);
        } else if (command.equalsIgnoreCase("event")) {
            String[] eventSections = description.split(" /from ", 2);
            String name = eventSections[0];
            String startAndEnd = eventSections[1];
            String[] startAndEndSections = startAndEnd.split(" /to ", 2);
            String startTime = startAndEndSections[0];
            String endTime = startAndEndSections[1];
            task = new Event(name, startTime, endTime);
        }
        toDoList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        Ui.printLength(toDoList);
    }

    public static void deleteTask(ToDoList toDoList, int index) {
        Task task = toDoList.get(index);
        toDoList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        Ui.printLength(toDoList);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append((i + 1) + ". " + list.get(i));
            if (i + 1 < list.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
