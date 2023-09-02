package gbot;

import exceptions.TaskException;
import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class TaskList {
    private Storage storage;
    private ArrayList<Task> list;
    private int taskCount;
    private static final String LINE = "____________________________________________________________";

    public TaskList(Storage storage) {
        this.storage = storage;
        this.list = storage.load();
        this.taskCount = this.list.size();
    }

    public void listTasks() {
        if (taskCount == 0) {
            Ui.print("No tasks available.");
        } else {
            System.out.println(LINE);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public void markTask(int taskNum) {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsDone();
        storage.updateFile(list);
        Ui.print("Nice, I've marked this task as done:\n" + curr);
    }

    public void unmarkTask(int taskNum) {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsUndone();
        storage.updateFile(list);
        Ui.print("Okay, I've unmarked this task:\n" + curr);
    }

    public void addTodo(String str) {
        Todo newTodo = new Todo(str);
        list.add(newTodo);
        storage.appendFile(newTodo.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        Ui.print("Got it. I've added this task:\n" + newTodo + count);
    }

    public void addDeadline(String str) {
        String desc = str.split(" /by ")[0];
        String by = str.split(" /by ")[1];
        Deadline newDeadline = new Deadline(desc, by);
        list.add(newDeadline);
        storage.appendFile(newDeadline.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        Ui.print("Got it. I've added this task:\n" + newDeadline + count);
    }

    public void addEvent(String str) {
        String desc = str.split(" /from ")[0];
        String from = str.split(" /from ")[1].split(" /to ")[0];
        String to = str.split(" /from ")[1].split(" /to ")[1];
        Event newEvent = new Event(desc, from, to);
        list.add(newEvent);
        storage.appendFile(newEvent.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        Ui.print("Got it. I've added this task:\n" + newEvent + count);
    }

    public void deleteTask(int taskNum) {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task toDelete = list.get(taskNum - 1);
        list.remove(taskNum - 1);
        storage.updateFile(list);
        taskCount--;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        Ui.print("Noted. I've removed this task:\n" + toDelete + count);
    }
}
