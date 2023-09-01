import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Paths;


public class Duke {
    public enum CommandType {
        LIST, DELETE, MARK, ADD, UNKNOWN, BYE
    }

    private static CommandType parseCommand(String userInput) {
        if (userInput.equals("bye")) {
            return CommandType.BYE;
        } else if (userInput.startsWith("list")) {
            return CommandType.LIST;
        } else if (userInput.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (userInput.contains("mark")) {
            return CommandType.MARK;
        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            return CommandType.ADD;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    public static void main(String[] args){

        String logo = "    ___    _   ___   ________  __      ____      __________  ____  _   __\n"
                + "   /   |  / | / / | / / __ \\ \\/ /     / __ \\    /_  __/ __ \\/ __ \\/ | / /\n"
                + "  / /| | /  |/ /  |/ / / / /\\  /_____/ / / /_____/ / / /_/ / / / /  |/ / \n"
                + " / ___ |/ /|  / /|  / /_/ / / /_____/ /_/ /_____/ / / _, _/ /_/ / /|  /  \n"
                + "/_/  |_/_/ |_/_/ |_/\\____/ /_/      \\____/     /_/ /_/ |_|\\____/_/ |_/   \n";
        String horizontalLine = "__________________________________________________________________________\n";
        String byeMessage = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        // Create data folder and file if they don't exist
        String folderPath = Paths.get("data").toString();
        String filePath = Paths.get("data", "tasks.txt").toString();
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        Scanner myObj = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        // Read existing tasks from file
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Task task = parseFromFile(line); // Assuming you implement a static method `parseFromFile`
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(horizontalLine + logo + "Hello! I'm ANNOY-O-TRON!\nWhat can I do for you?\n"
                + horizontalLine);

        CommandType command;
        do {
                String userInput = myObj.nextLine();
                command = parseCommand(userInput);
                System.out.print(horizontalLine);
            try {
                switch (command) {
                    case LIST:
                        handleListCommand(list);
                        break;
                    case DELETE:
                        handleDeleteCommand(userInput, list);
                        break;
                    case MARK:
                        handleMarkCommand(userInput, list);
                        break;
                    case ADD:
                        handleAddTaskCommand(userInput, list);
                        break;
                    case UNKNOWN:
                        throw new InvalidArgumentException();
                }
                System.out.println(horizontalLine);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } while (command != CommandType.BYE);

        System.out.println(byeMessage);
    }
    private static void saveTasks(List<Task> list) {
        String filePath = Paths.get("data", "tasks.txt").toString();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : list) {
                writer.write(task.toFileString() + "\n"); // Assuming you implement `toFileString` in Task
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to disk.");
        }
    }

    private static void handleListCommand(List<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < list.size() + 1; i++) {
            System.out.println(i + "." + list.get(i - 1));
        }
        saveTasks(list);
    }
    private static void handleDeleteCommand(String userInput, List<Task> list) {
        int index = getTaskIndex(userInput);
        if (isValidIndex(index, list.size())) {
            Task currentTask = list.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + currentTask);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } else {
            System.out.println("Invalid task index.");
        }
        saveTasks(list);
    }

    private static void handleMarkCommand(String userInput, List<Task> list) {
        int index = getTaskIndex(userInput);
        if (isValidIndex(index, list.size())) {
            Task currentTask = list.get(index - 1);
            if (userInput.contains("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                currentTask.markUndone();
            } else {
                System.out.println("Nice! I've marked this task as done:");
                currentTask.markDone();
            }
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task index.");
        }
        saveTasks(list);
    }
    private static void handleAddTaskCommand(String userInput, List<Task> list) throws InvalidArgumentException, EmptyDescriptionException {
        Task newTask = getTask(userInput);
        System.out.println("Got it. I've added this task:\n" + newTask);
        list.add(newTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        saveTasks(list);
    }
    private static int getTaskIndex(String userInput) {
        return userInput.charAt(userInput.length() - 1) - '0';
    }
    private static boolean isValidIndex(int index, int size) {
        return index > 0 && index <= size;
    }
    private static Task getTask(String userInput) throws EmptyDescriptionException {
        String afterSpace = userInput.substring(userInput.indexOf(' ') + 1).trim();
        if (userInput.equals("todo")) {
            throw new EmptyDescriptionException();
        }
        if (userInput.startsWith("todo")) {
            return new Todo(afterSpace);
        } else if (userInput.startsWith("deadline")) {
            int deadlineIndex = afterSpace.indexOf("/by");
            String taskDeadline = afterSpace.substring(deadlineIndex + 4).trim();
            String taskName = afterSpace.substring(0, deadlineIndex).trim();
            return new Deadline(taskName, taskDeadline);
        } else {
            int fromIndex = afterSpace.indexOf("/from");
            int toIndex = afterSpace.indexOf("/to");
            String taskFrom = afterSpace.substring(fromIndex + 6, toIndex - 1).trim();
            String taskTo = afterSpace.substring(toIndex + 4).trim();
            String taskName = afterSpace.substring(0, fromIndex).trim();
            return new Event(taskName, taskFrom, taskTo);
        }
    }
    private static Task parseFromFile(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String deadline = parts[3];
                task = new Deadline(description, deadline);
                break;
            case "E":
                String eventTime = parts[3];
                String[] times = eventTime.split("-");
                String from = times[0];
                String to = times[1];
                task = new Event(description, from, to);
                break;
        }

        if (task != null && isDone) {
            task.markDone();
        }

        return task;
    }


}