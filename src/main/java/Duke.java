import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> my_list = new ArrayList<>();
    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        while (x != null) {
            String[] stringList = x.split(" ",2);
            String first = stringList[0];
            switch (first) {
                case "bye":
                    break;
                case "list":
                    printList();
                    break;
                case "mark":
                    int i = Integer.parseInt(stringList[1]);
                    Task t = my_list.get(i-1);
                    t.markDone();
                    break;
                case "unmark":
                    int j = Integer.parseInt(stringList[1]);
                    Task p = my_list.get(j-1);
                    p.markUndone();
                    break;
                case "todo":
                    addTodo(stringList[1]);
                    break;
                case "deadline":
                    addDeadline(stringList[1]);
                    break;
                case "event":
                    addEvent(stringList[1]);
                    break;
            }
            if (x.equals("bye")) {
                break;
            }
            x = sc.nextLine();
        }
        ending();
    }
    private static void welcome() {
        System.out.println("Hello! I'm BoxBox \nWhat can I do for you?");
    }
    private static void ending() {
        System.out.println("Bye. Hope to see you again!");
    }
    private static void addTodo(String x) {
        Todo t = new Todo(x);
        my_list.add(t);
        addedTask(x);
    }
    private static void addDeadline(String x) {
        String[] s = x.split(" /by ");
        String description = s[0];
        String deadline = s[1];
        Deadline d = new Deadline(description, deadline);
        my_list.add(d);
        addedTask(description);
    }

    private static void addEvent(String x) {
        String[] s = x.split(" /from | /to ");
        String description = s[0];
        String from = s[1];
        String to = s[2];
        Event e = new Event(description, from, to);
        my_list.add(e);
        addedTask(description);
    }

    private static void addedTask(String x) {
        System.out.println("Added to list: " + x);
        System.out.println("Now you have " + my_list.size() + " tasks in the list");
    }

    private static void printList() {
        for (int i = 0; i < my_list.size(); i++) {
            System.out.println(i+1 + " " + my_list.get(i).toString());
        }
    }
}
