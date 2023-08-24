import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        hello_world();
        converse();
        goodbye_world();
    }

    public static void hello_world() {
        System.out.println("Hello! I'm");
        printName();
        System.out.println("What can I do for you?");
        print_divider_line();
    }

    public static void converse() {
        final String END_COMMAND = "bye";
        final String LIST_COMMAND = "list";
        String[] harddisk = new String[100];
        int size = 0;

        Scanner scanner = new Scanner(System.in);
        
        String message;
        boolean talk = true;
        while (talk) {
            System.out.print("Message:");
            message = scanner.nextLine();

            switch (message) {
                case END_COMMAND:
                    talk = false;
                    break;
                case LIST_COMMAND:
                    print_data(harddisk, size);
                    break;
                default:
                    harddisk[size++] = message;
                    System.out.println("Written to hard disk:" + message);
            }

            print_divider_line();
        }
    }

    public static void print_data(String[] data, int size) {
        for (int i = 1; i <= size; ++i) {
            System.out.println(i + ". " + data[i - 1]);
        }
    }

    public static void goodbye_world() {
        System.out.println("Bye. Hope to see you again soon!");
        print_thank_you();
        print_divider_line();
    }

    public static void print_divider_line() {
        System.out.println("═".repeat(80));
    }

    public static void printName() {
        String name = "\n" +
                "     ██╗██╗███╗   ██╗ ██████╗     ███████╗██╗  ██╗███████╗███╗   ██╗ ██████╗ \n" +
                "     ██║██║████╗  ██║██╔════╝     ██╔════╝██║  ██║██╔════╝████╗  ██║██╔════╝ \n" +
                "     ██║██║██╔██╗ ██║██║  ███╗    ███████╗███████║█████╗  ██╔██╗ ██║██║  ███╗\n" +
                "██   ██║██║██║╚██╗██║██║   ██║    ╚════██║██╔══██║██╔══╝  ██║╚██╗██║██║   ██║\n" +
                "╚█████╔╝██║██║ ╚████║╚██████╔╝    ███████║██║  ██║███████╗██║ ╚████║╚██████╔╝\n" +
                " ╚════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝     ╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ \n";
        System.out.print(name);
    }

    public static void print_thank_you() {
        String thankYou = "\n" +
                "████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗    ██╗   ██╗ ██████╗ ██╗   ██╗\n" +
                "╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝    ╚██╗ ██╔╝██╔═══██╗██║   ██║\n" +
                "   ██║   ███████║███████║██╔██╗ ██║█████╔╝      ╚████╔╝ ██║   ██║██║   ██║\n" +
                "   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗       ╚██╔╝  ██║   ██║██║   ██║\n" +
                "   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗       ██║   ╚██████╔╝╚██████╔╝\n" +
                "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝       ╚═╝    ╚═════╝  ╚═════╝\n";
        System.out.print(thankYou);
    }
}