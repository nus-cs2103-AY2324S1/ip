import java.util.Scanner;

public class Dre {
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Dre");
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            System.out.println(next);
            next = sc.nextLine();
        }
        exit();
    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}
