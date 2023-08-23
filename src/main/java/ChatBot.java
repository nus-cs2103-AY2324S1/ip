import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ChatBot {
    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        // Introduction message
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Desolute\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();

        // Loops till bye command is given
        while (!next.equals("bye")) {
            nextCommand(next);
            next = sc.nextLine();
        }

        // Exit message
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void nextCommand(String str) {
        // Splitting of string adapted from
        // https://stackoverflow.com/questions/9378394/remove-first-word-from-a-string-in-java
        String[] temp = str.split(" ", 2);

        // Checks if any pre determined commands are given
        switch(temp[0]) {
            case "list":
                showList();
                break;
            case "blah":
                System.out.println("____________________________________________________________\n" +
                        " blah\n" +
                        "____________________________________________________________\n");
                break;
            case "mark":
                markDone(temp[1]);
                break;
            case "unmark":
                unmarkDone(temp[1]);
                break;
            case "todo":
                Task toDo = new ToDo(temp[1]);
                list.add(toDo);
                System.out.println("____________________________________________________________\n" +
                        " Got it. I've added this task:\n  " +
                        toDo.toString() +
                        getTaskCount() +
                        "____________________________________________________________\n");
                break;
            case "deadline":
                String[] temp2 = temp[1].split(" /by");
                Task deadline = new Deadline(temp2[0], temp2[1]);
                list.add(deadline);
                System.out.println("____________________________________________________________\n" +
                        " Got it. I've added this task:\n  " +
                        deadline.toString() +
                        getTaskCount() +
                        "____________________________________________________________\n");
                break;
            case "event":
                String[] temp3 = temp[1].split(" /from");
                String[] temp4 = temp3[1].split("/to");
                Task event = new Event(temp3[0], temp4[0], temp4[1]);
                list.add(event);
                System.out.println("____________________________________________________________\n" +
                        " Got it. I've added this task:\n  " +
                        event.toString() +
                        getTaskCount() +
                        "____________________________________________________________\n");
                break;
            default:
                System.out.println("____________________________________________________________\n" +
                        "Invalid command. Please try again." +
                        "____________________________________________________________\n");

        }
    }

    private static void showList() {
        System.out.println("____________________________________________________________");
        if (list.isEmpty()) {
            System.out.println(" The list is empty. Please add tasks in!");
        } else {
            System.out.println(" Here are the tasks in your list:");
        }
        for (int i = 0; i < list.size(); i++) {
            Task temp = list.get(i);
            String current = String.format(" %d.%s", i + 1, temp.toString());
            System.out.println(current);
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void markDone(String str) {
        try {
            int num = Integer.parseInt(str) - 1;
            Task curr = list.get(num);
            curr.markDone();
        } catch(IndexOutOfBoundsException e) {
            System.out.println("____________________________________________________________\n" +
                    "This task number is not available. Please try again." +
                    "____________________________________________________________\n");
        } catch(NumberFormatException e) {
            System.out.println("The task number you have keyed in is not an integer, please try again");
        }
    }

    private static void unmarkDone(String str) {
        try {
            int num = Integer.parseInt(str) - 1;
            Task curr = list.get(num);
            curr.unmarkedDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("____________________________________________________________\n" +
                    "This task number is not available. Please try again." +
                    "____________________________________________________________\n");
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________\n" +
                    "The task number you have keyed in is not an integer, please try again" +
                    "____________________________________________________________\n");
        }
    }

    private static String getTaskCount() {
        return String.format("\n Now you have %d tasks in the list.\n", list.size());
    }
}
