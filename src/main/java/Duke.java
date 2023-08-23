import java.util.Scanner;

public class Duke {
    private static Task[] items = new Task[100];
    private static int itemsCount = 0;

    public static void addTask(Task t) {
        items[itemsCount] = t;
        itemsCount++;

        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + itemsCount + " task(s) in the list.");
    }

    public static void main(String[] args) {
        // Greeting
        System.out.println("Hello! I'm Eepy\nWhat can I do for you?");

        // Get input and store it
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("");
            String s = in.nextLine();
            System.out.println("You entered: " + s);

            try {
                if (s.equals("bye")) {
                    // Exit
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (s.equals("list")) {
                    // List out all items
                    System.out.println("List of items:");
                    for (int i = 0; i < itemsCount; i++) {
                        int index = i + 1;
                        System.out.println(index + "." + items[i]);
                    }
                } else if (s.startsWith("mark")) {
                    // Mark item as done
                    try {
                        int index = Integer.parseInt(s.substring(4).trim());
                        if (index <= 0 || index >= itemsCount) {
                            throw new DukeException("Index out of range!");
                        }
                        items[index - 1].markAsDone();
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid number!");
                    }
                } else if (s.startsWith("unmark")) {
                    // Mark item as done
                    try {
                        int index = Integer.parseInt(s.substring(6).trim());
                        if (index <= 0 || index >= itemsCount) {
                            throw new DukeException("Index out of range!");
                        }
                        items[index - 1].markAsUndone();
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid number!");
                    }
                } else if (s.startsWith("todo")) {
                    // New ToDo item
                    String name = s.substring(4).trim();
                    if (name.length() == 0) {
                        throw new DukeException("Description of todo cannot be empty!");
                    }
                    addTask(new ToDo(name));
                } else if (s.startsWith("deadline")) {
                    // New Deadline item
                    // Extract name and by
                    int byIndex = s.indexOf("/by");
                    if (byIndex == -1) {
                        // "/by" not found
                        throw new DukeException("Please include when the deadline is! (`deadline name \\by date`)");
                    }

                    String name = s.substring(8, byIndex).trim();
                    String by = s.substring(byIndex + 3).trim();

                    if (name.equals("") || by.equals("")) {
                        // No name or deadline
                        throw new DukeException("Please include name and deadline! (`deadline name \\by date`)");
                    }

                    addTask(new Deadline(name, by));
                } else if (s.startsWith("event")) {
                    // New Event item
                    // Extract name, from and to
                    int fromIndex = s.indexOf("/from");
                    int toIndex = s.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1) {
                        // "/from" or "/to" not found
                        throw new DukeException("Please include when the event is from and to! (`event name \\from date \\to date`)");
                    }

                    String name = s.substring(5, fromIndex).trim();
                    String from = s.substring(fromIndex + 5, toIndex).trim();
                    String to = s.substring(toIndex + 3).trim();
                    if (name.equals("") || from.equals("") || to.equals("")) {
                        // No name, from or to
                        throw new DukeException("Please include the name of the event" +
                                "and when the event is from and to! (`event name \\from date \\to date`)");
                    }

                    addTask(new Event(name, from, to));
                } else {
                    throw new DukeException("I do not understand :(((");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
