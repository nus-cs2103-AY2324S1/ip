import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Thea {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> tasks =  new ArrayList<>();
        greet();
        String command = input.nextLine();
        while (true) {
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                printList(tasks);
            } else {
                add(command, tasks);
            }
            command = input.nextLine();
        }
    }
    public static void greet() {
        System.out.println("Hello! I'm Thea •ᴗ•\nHow can I help you?\n");
    }
    public static void exit() {
        System.out.println("I hope I made your day easier with my service. See you again! >ᴗ<");
    }
    public static void add(String task, List<String> tasks) {
        tasks.add(task);
        System.out.println("added: " + task);
    }
    public static void printList(List<String> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }
}
