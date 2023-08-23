import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> my_list = new ArrayList<>();
    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String x = sc.nextLine();
                String[] stringList = x.split(" ", 2);
                String first = stringList[0];
                String second = null;
                try {
                    second = stringList[1];
                } catch (IndexOutOfBoundsException e) {
                    // do nothing
                }
                switch (first) {
                    case "bye":
                        break;
                    case "list":
                        printList();
                        break;
                    case "mark":
                        markDone(second);
                        break;
                    case "unmark":
                        markUndone(second);
                        break;
                    case "todo":
                        addTodo(second);
                        break;
                    case "deadline":
                        addDeadline(second);
                        break;
                    case "event":
                        addEvent(second);
                        break;
                    default:
                        throw new InvalidInputException("OOPS! I do not know what " + first + " means. Please try again :)");
                }
                if (x.equals("bye")) {
                    ending();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
    private static void welcome() {
        System.out.println("Hello! I'm BoxBox\nWhat can I do for you?");
    }
    private static void ending() {
        System.out.println("Bye. Hope to see you again!");
    }
    private static void addTodo(String x) {
        if (x == null) {
            throw new LackDescriptionException("todo");
        }
        Todo t = new Todo(x);
        my_list.add(t);
        addedTask(x);
    }
    private static void addDeadline(String x) {
        if (x == null || x.startsWith("/by") || x.startsWith(" /by")) {
            throw new LackDescriptionException("deadline");
        }
        String[] s = x.split(" /by ");
        String description = s[0];
        String deadline;
        try {
            deadline = s[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/by\"");
        }
        Deadline d = new Deadline(description, deadline);
        my_list.add(d);
        addedTask(description);
    }

    private static void addEvent(String x) {
        if (x == null || x.startsWith("/from") || x.startsWith(" /from")) {
            throw new LackDescriptionException("event");
        }
        String[] s = x.split(" /from ");
        String description = s[0];
        String fromto;
        try {
            fromto = s[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/from\"");
        }
        if (fromto.startsWith("/to") || fromto.startsWith(" /to")) {
            throw new LackInformationException("\"/from\"");
        }
        String[] ft = fromto.split(" /to ");
        String from = ft[0];
        String to;
        try {
            to = ft[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/to\"");
        }
        Event e = new Event(description, from, to);
        my_list.add(e);
        addedTask(description);
    }

    private static void addedTask(String x) {
        System.out.println("Added to list: " + x);
        System.out.println("Now you have " + my_list.size() + (my_list.size() == 1 ? " task " : " tasks ") + "in the list");
    }

    private static void markDone(String x) {
        if (x == null) {
            throw new InvalidMarking("Missing index");
        }
        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarking("Please provide a valid index");
        }
        if (j-1 > my_list.size()-1) {
            throw new InvalidMarking("There is no corresponding task in the list");
        }
        Task t = my_list.get(j-1);
        t.markDone();
    }

    private static void markUndone(String x) {
        if (x == null) {
            throw new InvalidMarking("Missing index");
        }
        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarking("Please provide a valid index");
        }
        if (j-1 > my_list.size()-1) {
            throw new InvalidMarking("There is no corresponding task in the list");
        }
        Task t = my_list.get(j-1);
        t.markUndone();
    }

    private static void printList() {
        if (my_list.isEmpty()) {
            System.out.println("list is empty :(");
        } else {
            for (int i = 0; i < my_list.size(); i++) {
                System.out.println(i + 1 + " " + my_list.get(i).toString());
            }
        }
    }
}
