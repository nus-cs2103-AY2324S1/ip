import java.util.Scanner;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    public static String readCommand() {
        return sc.nextLine();
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printGreeting() {
        String name = "Chaty";

        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?" + "\n\n");
    }
}
