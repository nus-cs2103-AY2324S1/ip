import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final Path filePath = Paths.get("data", "duke.txt");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final List<Task> tasks = new ArrayList<>();

    private static void outputMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    private static void writeToFile(File file, String textToAdd) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        outputMessage(" Hello! I'm Pixel\n What can I do for you?\n");

        File file = new File(filePath.toUri());
        try {
            Scanner loader = new Scanner(file);
            if (file.length() != 0) {
                outputMessage(" Loaded tasks from database!\n");
            }

            while (loader.hasNextLine()) {
                String[] fields = loader.nextLine().split(";");
                String description = fields[2];
                boolean isDone = fields[1].equals("X");
                switch (fields[0]) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(description, isDone, LocalDateTime.parse(fields[3])));
                    break;
                case "E":
                    tasks.add(new Event(description, isDone, LocalDateTime.parse(fields[3]),
                            LocalDateTime.parse(fields[4])));
                    break;
                }
            }
        } catch (FileNotFoundException _e) {
            try {
                if (!file.getParentFile().mkdirs() && !file.createNewFile() && !file.exists()) {
                    System.out.println("Something went wrong.");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] splitInput = scanner.nextLine().split(" ", 2);
            String command = splitInput[0];
            switch (command) {
            case "bye": {
                outputMessage(" Bye. Hope to see you again soon!\n");
                scanner.close();
                writeToFile(file, tasks.stream().map(Task::encodeTask).collect(Collectors.joining("\n")));
                return;
            }
            case "list": {
                if (tasks.size() == 0) {
                    outputMessage(" There are no tasks in your list!\n");
                } else {
                    StringBuilder tasksString = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        tasksString.append(String.format("  %d. %s\n", i + 1, tasks.get(i).toString()));
                    }
                    outputMessage(String.format(" Here are the tasks in your list:\n%s", tasksString));
                }
                break;
            }
            case "mark": {
                Task task = tasks.get(Integer.parseInt(splitInput[1]) - 1);
                task.markAsDone();
                outputMessage(String.format(" Nice! I've marked this task as done:\n  %s\n", task));
                break;
            }
            case "unmark": {
                Task task = tasks.get(Integer.parseInt(splitInput[1]) - 1);
                task.markAsUndone();
                outputMessage(String.format(" OK, I've marked this task as not done yet:\n  %s\n", task));
                break;
            }
            case "todo": {
                if (splitInput.length == 1) {
                    outputMessage(" ☹ OOPS!!! The description of a todo cannot be empty.\n");
                    break;
                }
                Task task = new Todo(splitInput[1]);
                tasks.add(task);
                outputMessage(String.format(
                        " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                        task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                break;
            }
            case "deadline": {
                String[] splitInputBy = splitInput[1].split("/by ", 2);
                Task task = new Deadline(splitInputBy[0], LocalDateTime.parse(splitInputBy[1], dateTimeFormat));
                tasks.add(task);
                outputMessage(String.format(
                        " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                        task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                break;
            }
            case "event": {
                String[] splitInputFrom = splitInput[1].split("/from ", 2);
                String[] splitInputTo = splitInputFrom[1].split(" /to ", 2);
                Task task = new Event(splitInputFrom[0], LocalDateTime.parse(splitInputTo[0], dateTimeFormat),
                        LocalDateTime.parse(splitInputTo[1], dateTimeFormat));
                tasks.add(task);
                outputMessage(String.format(
                        " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                        task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                break;
            }
            case "delete": {
                Task task = tasks.get(Integer.parseInt(splitInput[1]) - 1);
                tasks.remove(task);
                outputMessage(String.format(
                        " Noted. I've removed this task:\n  %s\n Now you have %d task%s in the list.\n",
                        task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                break;
            }
            default:
                outputMessage(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }
}
