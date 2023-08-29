import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.TaskException;
import exceptions.TodoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Commands {
    private Disk disk;
    private ArrayList<Task> list;
    private int taskCount;
    private static final String LINE = "____________________________________________________________";

    public Commands(Disk disk) throws FileNotFoundException {
        this.disk = disk;
        this.list = disk.openFile();
        this.taskCount = this.list.size();
    }

    public static void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void listTasks() {
        if (taskCount == 0) {
            print("No tasks available.");
        } else {
            System.out.println(LINE);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public void markTask(String message) throws TaskException, IOException {
        if (message.length() <= 5) {
            throw new TaskException();
        }

        int taskNum = Integer.parseInt(message.split(" ")[1]);
        if (taskNum > list.size()) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsDone();
        disk.updateFile(list);
        print("Nice, I've marked this task as done:\n" + curr);
    }

    public void unmarkTask(String message) throws TaskException, IOException {
        if (message.length() <= 5) {
            throw new TaskException();
        }

        int taskNum = Integer.parseInt(message.split(" ")[1]);
        if (taskNum > list.size()) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsUndone();
        disk.updateFile(list);
        print("Okay, I've unmarked this task:\n" + curr);
    }

    public void addTodo(String str) throws TodoException, IOException {
        if (str.isBlank()) {
            throw new TodoException();
        }

        Todo newTodo = new Todo(str);
        list.add(newTodo);
        disk.appendFile(newTodo.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        print("Got it. I've added this task:\n" + newTodo + count);
    }

    public void addDeadline(String str) throws DeadlineException, IOException {
        if (str.isBlank()) {
            throw new DeadlineException();
        }
        String desc = str.split(" /by ")[0];
        String by = str.split(" /by ")[1];
        Deadline newDeadline = new Deadline(desc, by);
        list.add(newDeadline);
        disk.appendFile(newDeadline.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        print("Got it. I've added this task:\n" + newDeadline + count);
    }

    public void addEvent(String str) throws EventException, IOException {
        if (str.isBlank()) {
            throw new EventException();
        }
        String desc = str.split(" /from ")[0];
        String from = str.split(" /from ")[1].split(" /to ")[0];
        String to = str.split(" /from ")[1].split(" /to ")[1];
        Event newEvent = new Event(desc, from, to);
        list.add(newEvent);
        disk.appendFile(newEvent.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        print("Got it. I've added this task:\n" + newEvent + count);
    }

    public void deleteTask(String str) throws TaskException, IOException {
        if (str.length() <= 7) {
            throw new TaskException();
        }

        int taskNum = Integer.parseInt(str.split(" ")[1]);
        if (taskNum > list.size()) {
            throw new TaskException();
        }

        Task toDelete = list.get(taskNum - 1);
        list.remove(taskNum - 1);
        disk.updateFile(list);
        taskCount--;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        print("Noted. I've removed this task:\n" + toDelete + count);
    }
}
