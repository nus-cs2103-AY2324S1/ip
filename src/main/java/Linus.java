import java.util.Scanner;
public class Linus {

    public static void printWelcomeMessage(){
        String name = "LINUS";
        System.out.println(
                "____________________________________________________________\n"
                        + "Hello! I'm " + name + "\n"
                        + "What can I do for you?\n"
                        + "____________________________________________________________"
        );
    }
    public static void printExitMessage() {
        System.out.println(
                "____________________________________________________________\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________"
        );
    }
    public static void chat() {
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            input = sc.nextLine();
            System.out.println(
                    "____________________________________________________________\n"
                            + input + "\n"
                            + "____________________________________________________________"
            );
        }
        while (!input.equals("bye"));
    }
    public static void main(String[] args) {
        printWelcomeMessage();
        chat();
        printExitMessage();
    }
}