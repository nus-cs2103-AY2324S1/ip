import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String openingStr =
                "____________________________________________________________\n" +
                        " Hello! I'm JEOE\n" +
                        " What can I do for you?\n" +
                        " type :\n" +
                        " list => to list out items in storage\n" +
                        " _Anything else_ => store in storage\n" +
                        "____________________________________________________________\n";
        System.out.println(openingStr);
        Scanner scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console
        List<Task> storage = new ArrayList<>();
        boolean isRunning = true;
        while (isRunning) {
            String reply = "____________________________________________________________\n";
            try {
                String input = scanner.nextLine();
                String command = input.split(" ")[0]; // even if its empty string, the 0 index will still be ""

                if (command.equals("bye")) {
                    isRunning = false;
                    reply += " Bye. Hope to see you again soon!\n";
                } else if (command.equals("list")) {
                    reply += "Here are the tasks in your list:\n";

                    for (int i = 0; i < storage.size(); i++) {
                        String num = String.valueOf(i + 1) + ". ";
                        Task task = storage.get(i);
                        reply += num + task + "\n";
                    }
                } else if (command.equals("todo")) {
                    // create the actual task
                    String description = input.replaceFirst("todo ", "");
                    Todo todo = new Todo(description);
                    // add to the storage
                    storage.add(todo);
                    // add to the reply
                    reply += todo.addTaskString(storage.size());

                } else if (command.equals("deadline")) {
                    String[] arr = input.replaceFirst("deadline ", "").split(" /by ");
                    String description = arr[0];
                    String by = arr[1];
                    Deadline deadline = new Deadline(description, by);
                    // add to storage
                    storage.add(deadline);
                    //add to the reply
                    reply += deadline.addTaskString(storage.size());

                } else if (command.equals("event")) {
                    String[] arr = input.replaceFirst("event ", "").split(" /from "); // arr have description
                    String description = arr[0];
                    String[] arr2 = arr[1].split(" /to "); // arr2 have the from & to
                    String from = arr2[0];
                    String to = arr2[1];
                    Event event = new Event(description, from, to);
                    // add to storage
                    storage.add(event);
                    // add to the reply
                    reply += event.addTaskString(storage.size());

                } else if (command.equals("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    // mark the task
                    storage.get(index).mark();
                    // format the reply
                    reply +=
                            ("Nice! I've marked this task as done:\n" +
                                    storage.get(index).toString() + "\n");

                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    // unmark the task
                    storage.get(index).unmark();
                    // format the reply
                    reply +=
                            ("OK, I've marked this task as not done yet:\n" +
                                    storage.get(index).toString() + "\n");
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    // delete from the array
                    Task t = storage.remove(index);
                    // add to the reply
                    reply +=
                            ("Noted. I've removed this task:\n" +
                                    t.toString() + "\n" +
                                    "Now you have " + storage.size() + " tasks in the list.\n");
                } else {
                    // nothing happens, since we only want the 3 specific types of tasks
                    // throw exception when it doesnt have a command word
                    throw new NoCommandException(input);
                }
            } catch (Exception e) {
                reply += e.getMessage();
            }
            reply += "____________________________________________________________\n";
            System.out.println(reply);
        }
        scanner.close();
    }
}
