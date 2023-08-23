import java.util.Scanner;

public class Glen {
    static final String HORLINE = "_____________________________________________________\n";
    
    public static void main(String[] args) {
        System.out.println(intro());
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            System.out.println(repeater(input));
            input = scan.nextLine();
        }
        System.out.println(exit());
        scan.close();
    }

    static String intro() {
        String logo = "  _____ _            \n" +
                      " / ____| |           \n" +
                      "| |  __| | ___ _ __  \n" +
                      "| | |_ | |/ _ \\  _ \\ \n" +
                      "| |__| | |  __/ | | |\n" +
                      " \\_____|_|\\___|_| |_|\n\n";
        String introText = "Hello, I'm Glen!\n" + 
                           "What can I do for you?\n";
        return HORLINE + logo + introText + HORLINE;
    }

    static String repeater(String inp) {
        return HORLINE + inp + "\n" + HORLINE;
    }

    static String exit() {
        return HORLINE + "Bye. Hope to see you again soon!\n" + HORLINE;
    }
}
