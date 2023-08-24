import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String userCommand = sc.next();
        while(!userCommand.equals("bye")){
            System.out.println(userCommand);
            userCommand = sc.next();
        }
        exit();
    }
}
