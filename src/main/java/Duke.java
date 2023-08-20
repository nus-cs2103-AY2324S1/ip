import java.util.Scanner;

public class Duke {
    private static String indent = "     ";
    private static String section = indent + "________________________________________\n";
    public static void main(String[] args) {

        //Print intro
        System.out.println(section
                + indent + "Hello! I'm Evan\n"
                + indent + "What can I do for you?\n"
                + section);

        //Start user interaction
        application();
    }

    public static void application() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printDialog("Bye. Hope to see you again soon!");
                return;
            } else {
                printDialog(input);
            }
        }
    }

    public static void printDialog(String dialog) {
        System.out.println(section + indent + dialog + "\n" + section);
    }
}
