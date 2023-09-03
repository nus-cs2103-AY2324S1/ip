package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    int count;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.count = list.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public void deleteTask(int index) {
        this.list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(index));
        count--;
        System.out.println("Now you have " + count + " tasks in the list");
    }

    public Task getTask(int index) {
        for (int i = 0; i < count; i++) {
            if (index == i) {
                return list.get(i);
            }
        }
        return null;
    }
}
