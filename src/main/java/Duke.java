
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo =
                  "\n" +
                          "                                                     \n" +
                          "     / /                                             \n" +
                          "    / /         ___      _   __      ___       __    \n" +
                          "   / /        //___) ) // ) )  ) ) //   ) ) //   ) ) \n" +
                          "  / /        //       // / /  / / //   / / //   / /  \n" +
                          " / /____/ / ((____   // / /  / / ((___/ / //   / /   \n";
        System.out.println("Hello! I'm " + logo + "! \nWhat can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
