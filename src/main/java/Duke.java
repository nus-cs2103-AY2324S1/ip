import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        while (! x.equals("bye")) {
            repeat(x);
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
    private static void repeat(String x) {
        System.out.println(x);
    }
}
