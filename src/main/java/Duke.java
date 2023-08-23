import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -\n");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye") && !str.equals("Bye")) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println(str);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
            str = sc.nextLine();
        }

        sc.close();
        System.out.println("\nBye. Hope to see you again soon!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
}
