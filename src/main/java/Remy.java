import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Remy {

    private static String divider = "____________________________________________________________\n";
    private static String shortDivider = "_____________";

    public static void main(String[] args) {

        String welcomeContent =
                "I'm Remy, and it is NOT nice to see you.\n" +
                        "Faster tell me what you want and go away.";

        String exitMessage = "Hope to never see you again!\n" + divider;


        Path filePath = Paths.get(".", "data", "remy.ser");

        ArrayList<Task> taskList;
        if (Files.exists(filePath)) {
            taskList = loadTasksFromFile(filePath);
        } else {
            taskList = new ArrayList(100);
        }

        printSandwichContent(welcomeContent, "long");
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            String taskType = parseTaskType(input);
            try {
                if (taskType.equals("bye")) {
                    System.out.println(exitMessage);
                    break;
                } else if (taskType.equals("list")) {
                    System.out.println(shortDivider);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println(shortDivider);
                } else if (taskType.equals("mark")) {
                    // Marks item as done
                    if (input.length() < 6) throw new ChatbotException("missing info lah.");
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsDone();
                        String content = "Done. You happy?\n" + taskList.get(index).toString();
                        saveTasksToFile(filePath, taskList);
                        printSandwichContent(content, "short");

                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("unmark")) {
                    // Marks item as undone
                    if (input.length() < 8) throw new ChatbotException("missing info lah.");
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsUndone();
                        String content = "Done. You happy?\n" + taskList.get(index).toString();
                        saveTasksToFile(filePath, taskList);
                        printSandwichContent(content, "short");
                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("delete")) {
                    if (input.length() < 8) throw new ChatbotException("missing info lah.");
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        String task = taskList.get(index).toString();
                        taskList.remove(index);
                        String content = "Done. Can you don't be so troublesome?\n" + task;
                        saveTasksToFile(filePath, taskList);
                        printSandwichContent(content, "short");
                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("todo")) {
                    if (input.length() < 6) throw new ChatbotException("missing info lah.");
                    String description = input.substring(5);
                    Todo temp = new Todo(description);
                    taskList.add(temp);
                    saveTasksToFile(filePath, taskList);
                    addTask(temp, taskList.size());
                } else if (taskType.equals("deadline")) {
                    if (input.length() < 10) throw new ChatbotException("missing info lah.");
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length == 2) {
                        Deadline temp = new Deadline(parts[0], parts[1]);
                        taskList.add(temp);
                        saveTasksToFile(filePath, taskList);
                        addTask(temp, taskList.size());
                    } else {
                        throw new ChatbotException("wrong format lah.");
                    }
                } else if (taskType.equals("event")) {
                    if (input.length() < 7) throw new ChatbotException("missing info lah.");
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length == 3) {
                        Event temp = new Event(parts[0], parts[1], parts[2]);
                        taskList.add(temp);
                        saveTasksToFile(filePath, taskList);
                        addTask(temp, taskList.size());
                    } else {
                        // printSandwichContent("Wrong format lah.", "short");
                        throw new ChatbotException("wrong format lah.");
                    }
                } else {
                    throw new ChatbotException("that's not a command.");
                }
            } catch (ChatbotException e) {
                printSandwichContent(e.toString(), "long");
            }
        }
    }

    public static String parseTaskType(String input) {
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.toLowerCase().startsWith("mark")) {
            return "mark";
        } else if (input.toLowerCase().startsWith("unmark")) {
            return "unmark";
        } else if (input.toLowerCase().startsWith("todo")) {
            return "todo";
        } else if (input.toLowerCase().startsWith("deadline")) {
            return "deadline";
        } else if (input.toLowerCase().startsWith("event")) {
            return "event";
        } else if (input.toLowerCase().startsWith("delete")) {
            return "delete";
        } else if (input.toLowerCase().equals("bye")) {
            return "bye";
        } else {
            return "invalid";
        }
    }

    public static void printSandwichContent(String content, String dividerType) {

        if (dividerType == "short") {
            System.out.println(shortDivider);
            System.out.println(content);
            System.out.println(shortDivider);
        } else {
            System.out.println(divider);
            System.out.println(content);
            System.out.println(divider);
        }
    }

    public static void addTask(Task task, int num) {
        String content = "Added, now scram.\n" +
                task.toString() + "\n" +
                "Now you have " + num + " tasks in the list.";
        printSandwichContent(content, "short");
    }

    public static void saveTasksToFile(Path filePath, ArrayList<Task> tasks) {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent()); // Create parent directories if they don't exist
            }

            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filePath));
            oos.writeObject(tasks); // writes the tasks ArrayList to the file
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasksFromFile(Path filePath) {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath));
            tasks = (ArrayList<Task>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

}
