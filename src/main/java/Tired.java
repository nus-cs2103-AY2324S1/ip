import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;

enum TaskType {
    TODO, DEADLINE, EVENT
}
public class Tired {

    private static void createFile() {
        try {
            Path dataDirectoryPath = Path.of(".", "data");

            // Create directory because it doesn't exist
            if (!Files.exists(dataDirectoryPath)) {
                Files.createDirectories(dataDirectoryPath);
            }

            Path dataPath = dataDirectoryPath.resolve("duke.txt");

            // Create duke.txt if it doesn't exist
            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static ArrayList<Task> readFile() {
        try {
            Path dataPath = Path.of(".", "data", "duke.txt");
            List<String> fileLines = Files.readAllLines(dataPath);
            ArrayList<Task> tasks = new ArrayList<>();

            for (String line : fileLines) {
                Task task = convertToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static Task convertToTask(String line) {

        String[] parts = line.split("\\|\\|");

        if (parts.length < 5) {
            System.err.println("Invalid task format in file: " + line);
            return null;
        }

        String taskType = parts[0].trim();
        String done = parts[1].trim();
        String taskDescription = parts[2].trim();
        System.out.println(taskDescription);
        String start = parts[3].trim();
        String end = parts[4].trim();
        Task task = null;

        try {
            switch (taskType) {
            case "T":
                task = new ToDo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription, end);
                break;
            case "E":
                task = new Event(taskDescription, start, end);
                break;
            }
            if (done.equals("0") && task != null) {
                task.isDone = false;
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        return task;
    }

    private static void saveToFile(ArrayList<Task> tasks) {
        try {
            if (tasks == null) {
                return;
            }
            Path dataPath = Path.of(".", "data", "duke.txt");
            List<String> lines = new ArrayList<>();

            for (Task task : tasks) {
                lines.add(taskToFileString(task));
            }

            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String taskToFileString(Task task) {
        String taskType = "";
        String doneStatus = task.isDone() ? "1" : "0";
        String taskDescription = task.getDescription();
        String start = "";
        String end = "";

        if (task instanceof ToDo) {
            taskType = "T";
        } else if (task instanceof Deadline) {
            taskType = "D";
            end = ((Deadline) task).date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (task instanceof Event) {
            taskType = "E";
            start = ((Event) task).start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            end = ((Event) task).end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }
        return taskType + " || " + doneStatus + " || " + taskDescription + " || " + start + " || " + end;
    }

    public static void main(String[] args) {

        createFile();
        ArrayList<Task> list = readFile();

        String name = "Tired";
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine + "\n");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }

            System.out.println(horizontalLine);
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        list.get(taskIndex).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(taskIndex).toString());
                    }
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        list.get(taskIndex).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(taskIndex).toString());
                    }
                } else if (input.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (taskIndex > list.size() - 1 || taskIndex < 0) {
                        throw new DukeException("Invalid task number!");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(list.get(taskIndex).toString());
                    list.remove(taskIndex);
                } else {
                    TaskType taskType;
                    String[] parts = input.split("/");
                    String taskDetails = parts[0].trim();

                    if (taskDetails.startsWith("todo")) {
                        taskType = TaskType.TODO;
                        taskDetails = taskDetails.substring(4).trim();
                    } else if (taskDetails.startsWith("deadline")) {
                        taskType = TaskType.DEADLINE;
                        taskDetails = taskDetails.substring(8).trim();
                    } else if (taskDetails.startsWith("event")) {
                        taskType = TaskType.EVENT;
                        taskDetails = taskDetails.substring(5).trim();
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }

                    switch (taskType) {
                        case TODO:
                            Task t = new ToDo(taskDetails);
                            list.add(t);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(t);
                            break;
                        case DEADLINE:
                            if (parts.length != 2 || parts[1].length() < 2) {
                                // prevent java.lang.StringIndexOutOfBoundsException
                                throw new DukeException("Invalid input for a task with deadline. " +
                                        "Please input 'deadline <task name> /by <end>'");
                            }
                            String date = parts[1].substring(2).trim();
                            Task deadlineTask = new Deadline(taskDetails, date);
                            list.add(deadlineTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(deadlineTask);
                            break;
                        case EVENT:
                            if (parts.length != 3 || parts[1].length() < 5 || parts[2].length() < 3) {
                                // prevent java.lang.StringIndexOutOfBoundsException
                                throw new DukeException("Invalid input for an event. " +
                                        "Please input 'event <event name> /from <start> /to <end>'");
                            }
                            String start = parts[1].substring(5).trim();
                            String end = parts[2].substring(3).trim();
                            Task eventTask = new Event(taskDetails, start, end);
                            list.add(eventTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(eventTask);
                            break;
                    }
                }
                saveToFile(list);
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                System.out.println(horizontalLine + "\n");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontalLine + "\n");
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}