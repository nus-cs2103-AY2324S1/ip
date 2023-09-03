import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Dukduk {
    public static void main(String[] args) {
        ArrayList<Task> tasks = loadTasksFromFile("ip-master/src/main/java/data/duke.txt");
        printLine();
        System.out.println(" Hello! I'm Dukduk");
        System.out.println(" What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(" ");
                String input = scanner.nextLine();
                printLine();

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    printLine();
                    scanner.close();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println(" No tasks added yet.");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new DukdukException("OOPS!!! The description cannot be empty.");
                    }
                    tasks.add(new ToDo(input.substring(5)));
                    saveTasksToFile("ip-master/src/main/java/data/duke.txt", tasks);
                    System.out.println(" Got it. I've added this task:\n   " 
                            + tasks.get(tasks.size() - 1).toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1) {
                        throw new DukdukException("OOPS!!! The deadline format is incorrect. " +
                                "Use '/by' to specify the deadline.");
                    }
                    String description = input.substring(9, byIndex).trim();
                    String byString = input.substring(byIndex + 3).trim();
                    try {
                        LocalDateTime by = LocalDateTime.parse(byString, 
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        tasks.add(new Deadline(description, by));
                        saveTasksToFile("ip-master/src/main/java/data/duke.txt", tasks);
                        System.out.println(" Got it. I've added this task:\n   " 
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DateTimeParseException e) {
                        throw new DukdukException("OOPS!!! The date/time format is incorrect. " +
                                "Please use 'yyyy-MM-dd HHmm' format.");
                    }
                } else if (input.startsWith("event")) {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new DukdukException("OOPS!!! The event format is incorrect. " +
                                "Use '/from' and '/to' to specify the timings.");
                    }
                    String description = input.substring(6, fromIndex).trim();
                    String from = input.substring(fromIndex + 5, toIndex).trim();
                    String to = input.substring(toIndex + 3).trim();
                    try {
                        LocalDateTime fromDateTime = LocalDateTime.parse(from, 
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        LocalDateTime toDateTime = LocalDateTime.parse(to, 
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        if (!toDateTime.isAfter(fromDateTime)) {
                            throw new DukdukException("OOPS!!! The 'to' date/time must be " +
                                    "after the 'from' date/time.");
                        }
                        tasks.add(new Event(description, fromDateTime, toDateTime));
                        saveTasksToFile("ip-master/src/main/java/data/duke.txt", tasks);
                        System.out.println(" Got it. I've added this task:\n   " 
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DateTimeParseException e) {
                        throw new DukdukException("OOPS!!! The date/time format is incorrect. " +
                                "Please use 'yyyy-MM-dd HHmm' format.");
                    }
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsDone();
                        saveTasksToFile("ip-master/src/main/java/data/duke.txt", tasks);
                        System.out.println(" Nice! I've marked this task as done:\n ["
                                + tasks.get(taskIndex).getStatusIcon() + "] "
                                + tasks.get(taskIndex).getDescription());
                    }
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).unmark();
                        saveTasksToFile("ip-master/src/main/java/data/duke.txt", tasks);
                        System.out.println(" OK, I've marked this task as not done yet:\n ["
                                + tasks.get(taskIndex).getStatusIcon() + "] "
                                + tasks.get(taskIndex).getDescription());
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        String[] parts = input.split(" ");
                        if (parts.length != 2) {
                            throw new DukdukException("OOPS!!! Please specify the task number to delete.");
                        }

                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            Task removedTask = tasks.remove(taskIndex);
                            saveTasksToFile("ip-master/src/main/java/data/duke.txt", tasks);
                            System.out.println(" Noted. I've removed this task:\n   " + removedTask);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        } else {
                            throw new DukdukException("OOPS!!! Task not found. " +
                                    "Please provide a valid task number.");
                        }
                    } catch (DukdukException e) {
                        System.out.println(" ☹ " + e.getMessage());
                    }
                } else {
                    throw new DukdukException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                printLine();
            } catch (DukdukException e) {
                System.out.println(" ☹ " + e.getMessage());
                printLine();
            }
        }
    }

    public static ArrayList<Task> loadTasksFromFile(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("No existing tasks file found. Starting with an empty task list.");
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Task.createTaskFromDataString(line);
                    tasks.add(task);
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 2 && parts[1].equals("1")) {
                        task.markAsDone();
                    }
                } catch (DukdukException e) {
                    System.out.println("Error parsing task data: " + e.getMessage());
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }


    public static void saveTasksToFile(String filePath, ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
