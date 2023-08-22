import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String logo = "\n   _____ _    _          _____   _____ _____ _______ \n" +
                "  / ____| |  | |   /\\   |  __ \\ / ____|  __ \\__   __|\n" +
                " | |    | |__| |  /  \\  | |  | | |  __| |__) | | |   \n" +
                " | |    |  __  | / /\\ \\ | |  | | | |_ |  ___/  | |   \n" +
                " | |____| |  | |/ ____ \\| |__| | |__| | |      | |   \n" +
                "  \\_____|_|  |_/_/    \\_\\_____/ \\_____|_|      |_|   \n";

        final String horizontal = "-------------------------------------------------------------------";

        System.out.println(horizontal + logo + horizontal);
        System.out.println("ChadGPT: Welcome to ChadGPT, What would you like to do today?\n" + horizontal);

        Scanner sc = new Scanner(System.in);
        System.out.print("User: ");
        while (!sc.hasNext("bye")) {
            System.out.println("ChadGPT: " + sc.nextLine() + "\n" + horizontal);
            System.out.print("User: ");
        }

        sc.close();

        System.out.println("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
    }
}
