import java.util.Scanner;

public class Duke {
    private static final String LOGO = ",------.,--.              ,--. \n"
                                      + "|  .---\'|  |,-.,--.,--. ,-|  |  \n"
                                      + "|  `--, |     /|  ||  |' .-. |  \n"
                                      + "|  `---.|  \\\\  \\\\'  ''  '\\\\ `-' |  \n"
                                      + "`------'`--'`--'`----'  `---' \n";
    private static final String LINE = "-".repeat(60);

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                sc.close();
                break;
            }
            echo(input);
        }

        printFarewellMessage();
    }

    private static void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm \\n");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void printFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}