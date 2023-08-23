import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ChatBot {
    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Desolute\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();

        while (!next.equals("bye")) {
            nextCommand(next);
            next = sc.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void nextCommand(String str) {
        switch(str) {
            case "list":
                showList();
                break;
            case "blah":
                System.out.println("____________________________________________________________\n" +
                        " blah\n" +
                        "____________________________________________________________\n");
                break;
            default:
                if (str.startsWith("mark ")){
                    String[] temp = str.split(" ");
                    try {
                        int num = Integer.parseInt(temp[1]) - 1;
                        Task curr = list.get(num);
                        curr.markDone();
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("This task number is not available. Please try again.");
                    } catch(NumberFormatException e) {
                        System.out.println("The task number you have keyed in is not an integer, please try again");
                    }
                } else if (str.startsWith("unmark ")) {
                    String[] temp = str.split(" ");
                    try {
                        int num = Integer.parseInt(temp[1]) - 1;
                        Task curr = list.get(num);
                        curr.unmarkedDone();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("This task number is not available. Please try again.");
                    } catch (NumberFormatException e) {
                        System.out.println("The task number you have keyed in is not an integer, please try again");
                    }
                } else {
                    // Adds ability to store text
                    Task curr = new Task(str);
                    list.add(curr);
                    System.out.println("____________________________________________________________\n" +
                            " added: " +
                            str +
                            "\n" +
                            "____________________________________________________________\n");
                }
        }
    }

    private static void showList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            Task temp = list.get(i);
            String current = String.format(" %d.%s", i + 1, temp.toString());
            System.out.println(current);
        }
        System.out.println("____________________________________________________________\n");
    }
}
