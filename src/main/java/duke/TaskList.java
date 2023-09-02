package duke;

import duke.Task;

import java.util.ArrayList;

//contains the task list e.g. has operations to add/delete tasks in the list
public class TaskList {
    ArrayList<Task> list;
    int count;

    //constructor
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.count = list.size();
    }

    //prints the list
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    //adds tasks into the list
    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    //delete tasks from the list
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
