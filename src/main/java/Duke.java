import java.util.Scanner;

public class Duke {

    private static String StringFormat(String str) {
        return "_______________________________________________\n"
                + str + "\n"
                + "_______________________________________________";
    }

    private static String Greet() {
        return StringFormat("Hi there! I'm Bob.\nHow can I help?");
    }

    private static String Exit() {
        return StringFormat("See you soon!");
    }

    private static String Echo(String input) {
        return StringFormat(input);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Greet());

        Scanner sc = new Scanner(System.in);
        while (true) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("bye")) {
                System.out.println(Exit());
                sc.close();
                break;
            }
            System.out.println(Echo(nextLine));
        }
    }
}
