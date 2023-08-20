import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();
        System.out.println(Constants.opening);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(Constants.closing);
                break;
            } else if (input.startsWith("mark ")) {
                int idx = Integer.parseInt(input.substring(5));
                lst.get(idx - 1).markDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println("\t" + lst.get(idx - 1));
            } else if (input.startsWith("unmark ")) {
                int idx = Integer.parseInt(input.substring(7));
                lst.get(idx - 1).unMarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t" + lst.get(idx - 1));
            } else if (input.equals("list")) {
                System.out.println(Constants.divider + "\n" + "Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println((i + 1) + "." + lst.get(i));
                    }
                System.out.println(Constants.divider + "\n");
            } else {
                String[] arr = input.split(" ", 2);
                Task task;
                try {
                    switch (arr[0]) {
                        case "todo":
                            if (arr.length == 1) throw new IncompleteDescriptionException();
                            task = ToDo.create(arr[1]);
                            lst.add(task);
                            break;
                        case "deadline":
                            if (arr.length == 1) throw new IncompleteDescriptionException();
                            String[] arr2 = arr[1].split(" /by ");
                            if (arr2.length == 1) throw new IncompleteDescriptionException();
                            task = Deadline.create(arr2[0], arr2[1]);
                            lst.add(task);
                            break;
                        case "event":
                            if (arr.length == 1) throw new IncompleteDescriptionException();
                            String[] arr3 = arr[1].split(" /from ");
                            if (arr3.length == 1) throw new IncompleteDescriptionException();
                            String taskName = arr3[0];
                            String[] arr4 = arr3[1].split(" /to ");
                            if (arr4.length == 1) throw new IncompleteDescriptionException();
                            String from = arr4[0];
                            String to = arr4[1];
                            task = Event.create(taskName, from, to);
                            lst.add(task);
                            break;
                        default:
                            throw new UnknownCommandException();
                    }
                } catch (IncompleteDescriptionException ex) {
                    System.out.println(ex);
                    continue;
                } catch (UnknownCommandException ex) {
                    System.out.println(ex);
                    continue;
                }
                String output = Constants.divider + "\n"
                        + "Got it. I've added this task:\n"
                        + "    " + task.toString()
                        + "\nNow you have " + Integer.toString(lst.size())
                        + " tasks in the list.\n"
                        + Constants.divider + "\n";
                System.out.println(output);
            }
        }
    }
}
