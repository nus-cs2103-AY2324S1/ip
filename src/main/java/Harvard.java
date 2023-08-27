import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
//write javacoc documentaion for the javadoc tool
public class Harvard {
    public static void main(String[] args) {
        String line = "----------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Harvard\nWhat can I do for you?");
        System.out.println(line);
        String filePath = "./data/tasks.txt";

        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        TaskReader taskReader = new TaskReader(filePath);
        ArrayList<Task> tasks = taskReader.readTasks();
        int taskCount = tasks.size();

        Scanner in = new Scanner(System.in);

        while (true) {
            String command = in.nextLine();
            try {
                if (command.equals("bye")) {
                    System.out.println(line);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (command.startsWith("todo")) {
                    if (command.length() < 5) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = command.substring(5);
                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    taskCount++;
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:\n" + todo +
                            "\nNow you have " + taskCount + " tasks in the list.");
                } else if (command.startsWith("deadline")) {
                    if (command.length() < 9) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (!command.contains(" /by ")) {
                        throw new DukeException("☹ OOPS!!! The deadline must be specified.");
                    }
                    String[] split = command.split(" /by ");
                    if (split[0].length() < 9) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (split.length != 2) {
                        throw new DukeException("☹ OOPS!!! The deadline must be specified.");
                    }
                    String description = split[0].substring(9);
                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String by = split[1];
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    taskCount++;
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:\n" + deadline +
                            "\nNow you have " + taskCount + " tasks in the list.");
                } else if (command.startsWith("event")) {
                    if (command.length() < 6) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    if (!command.contains(" /from ") || !command.contains(" /to ")) {
                        throw new DukeException("☹ OOPS!!! The event time must be specified.");
                    }
                    String[] split = command.split("/from ");
                    if (split[0].length() < 6) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] split2 = split[1].split(" /to ");
                    if (split2.length != 2) {
                        throw new DukeException("☹ OOPS!!! The event time must be specified.");
                    }
                    String description = split[0].substring(6);
                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String from = split2[0];
                    String to = split2[1];
                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    taskCount++;
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:\n" + event +
                            "\nNow you have " + taskCount + " tasks in the list.");
                } else if (command.equals("list")) {
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                } else if(command.startsWith("delete")) {
                    String[] split = command.split(" ");
                    if (split.length != 2) {
                        throw new DukeException("☹ OOPS!!! The index must be specified.");
                    }
                    int index = Integer.parseInt(split[1]) - 1;
                    if (index >= taskCount || index < 0) {
                        throw new DukeException("☹ OOPS!!! The index is invalid.");
                    }
                    Task task = tasks.get(index);
                    tasks.remove(index);
                    taskCount--;
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (command.startsWith("mark")) {
                    String[] split = command.split(" ");
                    int index = Integer.parseInt(split[1]) - 1;
                    if (index >= taskCount || index < 0) {
                        System.out.println(line);
                        System.out.println("Invalid index!");
                        System.out.println(line);
                        continue;
                    }
                    tasks.get(index).markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                } else if (command.startsWith("unmark")) {
                    String[] split = command.split(" ");
                    int index = Integer.parseInt(split[1]) - 1;
                    tasks.get(index).markAsUndone();
                    System.out.println(line);
                    System.out.println("Ok! I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                TaskWriter taskWriter = new TaskWriter(filePath);
                taskWriter.write(tasks);
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
            }
            System.out.println(line);
        }
        in.close();
    }
}
