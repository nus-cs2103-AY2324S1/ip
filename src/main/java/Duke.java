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
            String[] stringList = x.split(" ");
            String firstWord = stringList[0];
            switch (firstWord) {
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
                default:
                    addTask(x);
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
    private static void addTask(String x) {
        Task t = new Task(x);
        my_list.add(t);
        System.out.println("Added to list: " + x);
    }
    private static void printList() {
        for (int i = 0; i < my_list.size(); i++) {
            System.out.println(i+1 + " " + my_list.get(i).toString());
        }
    }
}
