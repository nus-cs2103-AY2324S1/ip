import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Tasks {
    private int size;
    private List<Task> tasks;

    public Tasks(int size, List<Task> tasks) {
        this.size = size;
        this.tasks = tasks;
    }

    public static void handleList(String input, List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            String task = String.valueOf(tasks.get(i));
            System.out.println(index + ". " + task);
        }
    }

    public static void handleDeadline(String input, List<Task> tasks) {
        String[] arr1 = input.split("/"); // 0: deadline + name , 1: by + date
        String[] arr2 = arr1[1].split("by "); // date
        String[] arr3 = arr1[0].split("deadline ");
        String date = arr2[1];
        Task deadline = new Deadline(arr3[1], date);
        tasks.add(deadline);
        saveTasks(tasks);
        System.out.println("Got it. I've added this task:\n" + deadline + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleTodo(String input, List<Task> tasks) {
        String[] arr0 = input.split("todo ");
        if (arr0.length == 1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task todo = new Todo(arr0[1]);
            tasks.add(todo);
            System.out.println("Got it. I've added this task:\n" + todo + "\n" +
                    "Now you have " + tasks.size() + " tasks in the list.");
        }
        saveTasks(tasks);
    }

    public static void handleEvent(String input, List<Task> tasks) {
        String[] arr1 = input.split("/from "); // [0]: event + name, [1]: timeframe
        String[] arr2 = arr1[1].split("/to "); // [0] from:..., [1] to:...
        String[] arr3 = arr1[0].split("event ");
        Task event = new Event(arr3[1], arr2[0], arr2[1]);
        tasks.add(event);
        saveTasks(tasks);
        System.out.println("Got it. I've added this task:\n" + event + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleMark(String input, List<Task> tasks) {
        String[] parts = input.split(" ");
        if (parts.length == 2) {
            try {
                int index = Integer.parseInt(parts[1]);
                    Task thisTask = tasks.get(index - 1);
                    tasks.get(index - 1).toggleDone();
                    saveTasks(tasks);
                if (thisTask.getDone()) {
                        System.out.println("Nice! I've marked this task as done:" + "\n" + thisTask);
                    } else {
                        System.out.println("OK, I've marked this task as not done yet:" + "\n" + thisTask);
                    }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("IndexOutOfBounds");
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
        }
    }

    public static void handleDelete(String input, List<Task> tasks) {
        String[] parts1 = input.split(" ");
        int index = Integer.parseInt(parts1[1]);
        String deleted = String.valueOf(tasks.get(index - 1));
        tasks.remove(index - 1);
        saveTasks(tasks);
        System.out.println("Noted. I've removed this task:\n" + deleted + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }
    public static void saveTasks(List<Task> tasks) {
        String filepath = "./data/duke.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write(""); // Clear the file by writing an empty string
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : tasks) {
            task.save(filepath);
        }
    }
}

