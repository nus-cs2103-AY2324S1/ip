import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();

        String logo =
                ".______     ______   .___________.\n" +
                        "|   _  \\   /  __  \\  |           |\n" +
                        "|  |_)  | |  |  |  | `---|  |----`\n" +
                        "|   _  <  |  |  |  |     |  |     \n" +
                        "|  |_)  | |  `--'  |     |  |     \n" +
                        "|______/   \\______/      |__|     \n";

        System.out.println("_________________________________________");
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");

        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println("_________________________________________");
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println((i + 1) + "." + data.get(i));
                    }
                } else if (input.contains("todo")) {
                    String subInput;
                    try {
                        subInput = input.substring(5);
                        if (subInput.trim().equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                    } catch (Exception e) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    ToDo t = new ToDo(subInput);
                    data.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");

                } else if (input.contains("deadline")) {
                    String[] split = input.split(" /by ", 2);
                    if (split.length == 1) {
                        throw new DukeException("Deadlines must have a /by.");
                    } else if (split[1].trim().equals("")) {
                        throw new DukeException("/by cannot be empty.");
                    }
                    String description = split[0].substring(9);
                    if (description.trim().equals("")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    Deadline t = new Deadline(description, split[1]);
                    data.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");
                } else if (input.contains("event")) {
                    String[] split = input.split(" /from ", 2);
                    if (split.length == 1) {
                        throw new DukeException("Events must have a /from and /to.");
                    }
                    String description;
                    try {
                        description = split[0].substring(6);
                        if (description.trim().equals("")) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                    } catch (Exception e) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }

                    String[] duration = split[1].split(" /to ", 2);
                    if (duration.length == 1) {
                        throw new DukeException("Events must have a /from and /to.");
                    } else if (duration[0].trim().equals("") || duration[1].trim().equals("")) {
                        throw new DukeException("/from and /to cannot be empty.");
                    }
                    Event t = new Event(description, duration[0], duration[1]);
                    data.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");
                } else if (input.contains("unmark")) {
                    if (input.length() < 7) {
                        throw new DukeException("Task number to be unmarked cannot be empty.");
                    }
                    String subInput = input.substring(7);
                    int targetIndex;
                    try {
                        targetIndex = Integer.parseInt(subInput);
                        if (targetIndex < 1 || targetIndex - 1 >= data.size()) {
                            throw new DukeException("Task number is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Task to be unmarked must be a number.");
                    }
                    System.out.println("Ok, I've marked this task as not done yet:");
                    Task marked = data.get(targetIndex - 1);
                    marked.markAsUndone();
                    System.out.println(marked);
                } else if (input.contains("mark")) {
                    if (input.length() < 5) {
                        throw new DukeException("Task number to be marked cannot be empty.");
                    }
                    String subInput = input.substring(5);
                    int targetIndex;
                    try {
                        targetIndex = Integer.parseInt(subInput);
                        if (targetIndex < 1 || targetIndex - 1 >= data.size()) {
                            throw new DukeException("Task number is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Task to be marked must be a number.");
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    Task marked = data.get(targetIndex - 1);
                    marked.markAsDone();
                    System.out.println(marked);
                } else if (input.contains("delete")) {
                    if (input.length() < 7) {
                        throw new DukeException("Task number to be deleted cannot be empty.");
                    }
                    String subInput = input.substring(7);
                    int targetIndex;
                    try {
                        targetIndex = Integer.parseInt(subInput);
                        if (targetIndex < 1 || targetIndex - 1 >= data.size()) {
                            throw new DukeException("Task number is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Task to be deleted must be a number.");
                    }
                    System.out.println("Noted. I've removed this task:");
                    Task marked = data.remove(targetIndex - 1);
                    System.out.println(marked);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("_________________________________________");
        }
    }
}
