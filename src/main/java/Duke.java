import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    private static final String line = "___________________________________\n";

    private static void getList() {
        Task[] temp = list.toArray(new Task[0]);
        System.out.println(line + "Here are the tasks in your list:");
        for (int i = 0; i < temp.length; i++) {
            System.out.println(i + 1 + ". " + temp[i].toString());
        }
        System.out.println(line);
    }

    private static void handleDelete(String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please indicate which item you wish to delete");
        }
        int amt = Integer.parseInt(pos.strip()) - 1;
        if (amt >= list.size()) throw new DukeException("☹ OOPS!!! That's not a valid item!");

        Task temp = list.get(amt);
        list.remove(amt);
        System.out.println(line);
        System.out.println("Okay I have deleted this task:\n\t" + temp.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);

    }

    private static void handleMark(String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please indicate which item you wish to mark");
        }
        int amt = Integer.parseInt(pos.strip()) - 1;
        if (amt >= list.size()) throw new DukeException("☹ OOPS!!! That's not a valid item!");
        System.out.println(line);
        list.get(amt).mark();
        System.out.println(line);

    }

    private static void handleUnMark(String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please indicate which item you wish to unmark");
        }
        int amt = Integer.parseInt(pos.strip()) - 1;
        if (amt >= list.size()) throw new DukeException("☹ OOPS!!! That's not a valid item!");
        System.out.println(line);
        list.get(amt).unMark();
        System.out.println(line);

    }

    protected static String[] handleInput(String input) {
        String[] arr = input.split(" ", 2);
        return arr.length == 1 ? new String[]{arr[0], ""} : arr;
    }

    public static void main(String[] args) {

        String name = "Duke but btr!";
        String exit = line + "Bye Bye. Hope to see you again soon!\n" + line;

        System.out.println(line + "Hello! I'm " + name + "\nWhat can I do for you?\n" + line);

        Scanner scan = new Scanner(System.in);

        while (true) {

            try {
                String input = scan.nextLine();
                String[] arr = handleInput(input);
                String type = arr[0];

                if ("bye".equals(input)) {
                    System.out.println(exit);
                    break;

                } else if (type.equals("mark")) {
                    Duke.handleMark(arr[1]);

                } else if (type.equals("unmark")) {
                    Duke.handleUnMark(arr[1]);

                } else if (type.equals("deadline")) {
                    Deadline.addDeadline(arr[1], list);

                } else if (type.equals("event")) {
                    Event.addEvent(arr[1], list);

                } else if (type.equals("todo")) {
                    ToDo.addTodo(arr[1], list);

                } else if ("list".equals(input.strip())) {
                    Duke.getList();

                } else if (type.equals("delete")) {
                    Duke.handleDelete(arr[1]);

                } else {
                    System.out.println(line + "That's not a valid action! Try again\n"  + line);
                }

            } catch (DukeException e) {
                System.out.println(line + e.getMessage() + "\n" + line);
            }

        }
    }
}
