
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int counter = 0;
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
            if (input.equals("list")) {
                for (int i = 0; i < counter; i++ ){
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            } else {
                System.out.println("added: " + input);
                tasks[counter] = input;
                counter++;
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
