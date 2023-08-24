import java.util.*;

public class ChatterBox {

    public static void main(String[] args) {

        linePrinter();
        System.out.println("      Hello! I'm ChatterBox");
        System.out.println("      What can I do for you?");
        linePrinter();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                linePrinter(); 
                System.out.println("      Bye. Hope to see you again soon!");
                linePrinter();
                break;

            } else {
                linePrinter();
                tabPrinter(input);
                linePrinter();

            }
        }
    }

    private static void linePrinter() {
        System.out.println("      ___________________________________________________________");
    }

    private static void tabPrinter(String s) {
        System.out.println("      " + s);
    }
}
