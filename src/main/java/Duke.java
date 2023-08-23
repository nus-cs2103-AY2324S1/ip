import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    static List<Task> tasks = new ArrayList<>();

    private static void outputMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        outputMessage(" Hello! I'm Pixel\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] splitInput = scanner.nextLine().split(" ", 2);
            String command = splitInput[0];
            switch (command) {
                case "bye": {
                    outputMessage(" Bye. Hope to see you again soon!\n");
                    break;
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
                    outputMessage(String.format(" Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                    break;
                }
                case "deadline": {
                    String[] splitInputBy = splitInput[1].split("/by", 2);
                    Task task = new Deadline(splitInputBy[0], splitInputBy[1]);
                    tasks.add(task);
                    outputMessage(String.format(" Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                    break;
                }
                case "event": {
                    String[] splitInputFrom = splitInput[1].split("/from", 2);
                    String[] splitInputTo = splitInputFrom[1].split("/to", 2);
                    Task task = new Event(splitInputFrom[0], splitInputTo[0], splitInputTo[1]);
                    tasks.add(task);
                    outputMessage(String.format(" Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                    break;
                }
                case "delete": {
                    Task task = tasks.get(Integer.parseInt(splitInput[1]) - 1);
                    tasks.remove(task);
                    outputMessage(String.format(" Noted. I've removed this task:\n  %s\n Now you have %d task%s in the list.\n",
                            task, tasks.size(), tasks.size() == 1 ? "" : "s"));
                    break;
                }
                default: {
                    outputMessage(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            }
        }
    }
}
