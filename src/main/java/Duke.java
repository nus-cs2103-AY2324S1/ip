import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<String> my_list = new ArrayList<>();
    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        while (x != null) {
            if (x.equals("bye")) {
                break;
            } else if (x.equals("list")) {
                printList();
            } else {
                addTask(x);
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
        my_list.add(x);
        System.out.println("added: " + x);
    }
    private static void printList() {
        for (int i = 0; i < my_list.size(); i++) {
            System.out.println(i+1 + " " + my_list.get(i));
        }
    }
}
