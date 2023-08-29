import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            outputMessage(String.format("Something went wrong with loading the tasks: %s\n", e.getMessage()));
            this.tasks = new TaskList();
        }
    }

    private static void outputMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    public void run() {
        outputMessage(" Hello! I'm Pixel\n What can I do for you?\n");
        if (tasks.getSize() != 0) {
            outputMessage(" Loaded tasks from database!\n");
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] splitInput = scanner.nextLine().split(" ", 2);
            String command = splitInput[0];
            switch (command) {
                case "bye": {
                    outputMessage(" Bye. Hope to see you again soon!\n");
                    scanner.close();
                    try {
                        storage.saveData(tasks);
                        return;
                    } catch (IOException e) {
                        outputMessage(String.format("Something went wrong with saving the tasks; please try again: %s",
                                e.getMessage()));
                        break;
                    }
                }
                case "list": {
                    if (tasks.getSize() == 0) {
                        outputMessage(" There are no tasks in your list!\n");
                    } else {
                        StringBuilder tasksString = new StringBuilder();
                        for (int i = 0; i < tasks.getSize(); i++) {
                            tasksString.append(String.format("  %d. %s\n", i + 1, tasks.getTask(i).toString()));
                        }
                        outputMessage(String.format(" Here are the tasks in your list:\n%s", tasksString));
                    }
                    break;
                }
                case "mark": {
                    Task task = tasks.getTask(Integer.parseInt(splitInput[1]) - 1);
                    task.markAsDone();
                    outputMessage(String.format(" Nice! I've marked this task as done:\n  %s\n", task));
                    break;
                }
                case "unmark": {
                    Task task = tasks.getTask(Integer.parseInt(splitInput[1]) - 1);
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
                    tasks.addTask(task);
                    outputMessage(String.format(
                            " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.getSize(), tasks.getSize() == 1 ? "" : "s"));
                    break;
                }
                case "deadline": {
                    String[] splitInputBy = splitInput[1].split("/by ", 2);
                    Task task = new Deadline(splitInputBy[0], LocalDateTime.parse(splitInputBy[1], dateTimeFormat));
                    tasks.addTask(task);
                    outputMessage(String.format(
                            " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.getSize(), tasks.getSize() == 1 ? "" : "s"));
                    break;
                }
                case "event": {
                    String[] splitInputFrom = splitInput[1].split("/from ", 2);
                    String[] splitInputTo = splitInputFrom[1].split(" /to ", 2);
                    Task task = new Event(splitInputFrom[0], LocalDateTime.parse(splitInputTo[0], dateTimeFormat),
                            LocalDateTime.parse(splitInputTo[1], dateTimeFormat));
                    tasks.addTask(task);
                    outputMessage(String.format(
                            " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.getSize(), tasks.getSize() == 1 ? "" : "s"));
                    break;
                }
                case "delete": {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    Task task = tasks.getTask(index);
                    tasks.removeTask(index);
                    outputMessage(String.format(
                            " Noted. I've removed this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.getSize(), tasks.getSize() == 1 ? "" : "s"));
                    break;
                }
                default:
                    outputMessage(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt").toAbsolutePath().toString()).run();
    }
}
