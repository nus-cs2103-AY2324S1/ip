import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dre {
    private List<Task> list;
    private final String dataFilePath = "./data/dre.txt";

    public Dre() {

        list = new ArrayList<>();
        loadTasks();
    }

    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath))) {
            for (Task task : list) {
                // Write task details to the file
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to the file.");
        }
    }

    public void loadTasks() {
        try {
            File file = new File(dataFilePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        parseTask(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading tasks from file.");
                }
            } else {
                System.out.println("No saved tasks found.");
            }
        } catch (SecurityException e) {
            System.out.println("Access to file is denied.");
        }
    }

    private void parseTask(String line) {
        String type = line.substring(1, 2);
        boolean isDone = line.charAt(4) == 'X';

        String description = line.substring(7);
        Task task = null;

        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                description = description.substring(0, description.indexOf('(') - 1);
                String additionalInfo = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
                String[] deadlineInfo = additionalInfo.split(": ");
                String by = deadlineInfo[1];
                task = new Deadline(description, by);
                break;
            case "E":
                description = description.substring(0, description.indexOf('(') - 1);
                String addInfo = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
                String[] eventInfo = addInfo.split(":");
                String from = eventInfo[1].substring(0, eventInfo[1].length() - 2).trim();
                String to = eventInfo[2].trim();
                task = new Event(description, from, to);
                break;
        }

        if (task != null) {
            if (isDone) {
                task.done();
            }
            list.add(task);
        }
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Dre");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void add(String next) {
        if (next.startsWith("todo")) {
            String desc = next.substring(5);
            if (desc.isBlank()) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                return;
            }
            ToDo task = new ToDo(desc);
            list.add(task);

            System.out.println("Got it. I've added this task:");
            System.out.println(task);
        } else if (next.startsWith("deadline")) {
            String desc = next.substring(9, next.lastIndexOf('/') - 1);
            String by = next.substring(next.lastIndexOf("/by") + 4);
            if (desc.isBlank()) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                return;
            }
            if (by.isBlank()) {
                System.out.println("OOPS!!! The date of a deadline cannot be empty.");
                return;
            }
            Deadline task = new Deadline(desc, by);
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
        } else if (next.startsWith("event")) {
            String desc = next.substring(6, next.indexOf("/from") - 1);
            String from = next.substring(next.indexOf("/from") + 6, next.indexOf("/to") - 1);
            String to = next.substring(next.indexOf("/to") + 4);
            if (desc.isBlank()) {
                System.out.println("OOPS!!! The description of an event cannot be empty.");
                return;
            }
            if (from.isBlank() || to.isBlank()) {
                System.out.println("OOPS!!! The dates of an event cannot be empty.");
                return;
            }
            Event task = new Event(desc, from, to);
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
        } else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void list() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public void mark(String next) {
        int start = next.lastIndexOf(' ');
        try {
            int taskIndex = Integer.parseInt(next.substring(start + 1));

            if (taskIndex <= 0 || taskIndex > list.size()) {
                System.out.println("Invalid task index.");
            } else {
                Task currTask = list.get(taskIndex - 1);
                currTask.done();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currTask);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Task index must be an integer.");
        }
    }

    public void unmark (String next) {
        int start = next.lastIndexOf(' ');
        try {
            int taskIndex = Integer.parseInt(next.substring(start + 1));

            if (taskIndex <= 0 || taskIndex > list.size()) {
                System.out.println("Invalid task index.");
            } else {
                Task currTask = list.get(taskIndex - 1);
                currTask.done();
                System.out.println("Ok! I've marked this task as undone:");
                System.out.println(currTask);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Task index must be an integer.");
        }
    }

    public void delete(String next) {
        int start = next.lastIndexOf(' ');
        try {
            int taskIndex = Integer.parseInt(next.substring(start + 1));

            if (taskIndex <= 0 || taskIndex > list.size()) {
                System.out.println("Invalid task index.");
            } else {
                Task deletedTask = list.remove(taskIndex - 1);
                System.out.println("Task deleted:");
                System.out.println(deletedTask.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Task index must be an integer.");
        }
    }

    public static void main(String[] args) {
        Dre dre = new Dre();
        dre.greet();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                dre.list();
            } else if (next.startsWith("mark")) {
                dre.mark(next);
            } else if (next.startsWith("unmark")) {
                dre.unmark(next);
            } else if (next.startsWith("delete")) {
                dre.delete(next);
            } else if (!next.trim().isEmpty()) {
                dre.add(next);
            } else {
                System.out.println("Invalid input.");
            }
            next = sc.nextLine();
        }
        sc.close();
        dre.saveTasks();
        dre.exit();
    }
}
