import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Potato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();
        int size = 0;

        System.out.println("-----------------------------------------\n" +
                "Hello! I'm Potato\n" + "What can I do for you?\n" +
                "-----------------------------------------");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("-----------------------------------------\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "-----------------------------------------");
                break;

            } else if (input.startsWith("mark")) {
                // not followed by number
                int index = Integer.parseInt(input.substring(5)) - 1;
                store.get(index).setStatus(true);
                System.out.println("-----------------------------------------\n" +
                        "Nice! I've marked this task as done:\n" +
                        store.get(index).toString() + "\n" +
                        "-----------------------------------------");

            } else if (input.startsWith("unmark")) {
                // not followed by number
                int index = Integer.parseInt(input.substring(7)) - 1;
                store.get(index).setStatus(false);
                System.out.println("-----------------------------------------\n" +
                        "Ok, I've marked this task as not done yet:\n" +
                        store.get(index).toString() + "\n" +
                        "-----------------------------------------");

            } else if (input.startsWith("delete")) {
                size--;
                int index = Integer.parseInt(input.substring(7)) - 1;
                System.out.println("-----------------------------------------\n" +
                        "Noted. I've removed this task:\n" +
                        store.get(index).toString() + "\n" +
                        "Now you have " + size + " tasks in the list.\n" +
                        "-----------------------------------------");
                store.remove(index);

            } else if (input.equals("list")) {
                int count = 0;
                System.out.println("-----------------------------------------\n" +
                        "Here are the tasks in your list: ");

                for (Task t : store) {
                    if (t == null) break;
                    count++;
                    System.out.println(String.valueOf(count) + "." +
                            t.toString());
                }

                System.out.println("-----------------------------------------\n");

            } else {
                if (!input.startsWith("todo") &&
                        !input.startsWith("deadline") &&
                        !input.startsWith("event")) {
                    new PotatoException("-----------------------------------------\n" +
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "-----------------------------------------");
                    continue;

                } else if (input.startsWith("todo")) {
                    if (input.length() < 6) {
                        new PotatoException("-----------------------------------------\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "-----------------------------------------");
                        continue;
                    } else {
                        store.add(new Todo(input.substring(5)));
                    }

                } else if (input.startsWith("deadline")) {
                    // empty deadline
                    // no by
                    int indexBy = input.indexOf("/by");
                    store.add(new Deadline(input.substring(9, indexBy),
                            input.substring(indexBy + 4)));

                } else if (input.startsWith("event")) {
                    // empty event
                    // no from
                    // no to
                    int indexFrom = input.indexOf("/from");
                    int indexTo = input.indexOf("/to");
                    store.add(new Event(input.substring(6, indexFrom),
                            input.substring(indexFrom + 6, indexTo),
                            input.substring(indexTo + 4)));
                }

                size++;
                System.out.println("-----------------------------------------\n" +
                        "Got it. I've added this task:\n" +
                        store.get(size - 1).toString() + "\n" +
                        "Now you have " + size + " tasks in the list.\n" +
                        "-----------------------------------------");
            }
        }
    }
}