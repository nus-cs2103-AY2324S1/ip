import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String greeting = "Hello! I'm Toothless. \n" +
                "What can I do for you today? \n" +
                "---------------------------------";
        String farewell = "Goodbye. Hope to see you soon!\n" +
                "---------------------------------";
        String name = "Toothless";

        System.out.println(greeting);

        String bye = "bye";

        while (true) {

            String nextInput = scanner.nextLine();

            if (nextInput.equals(bye)) {
                break;
            }
            System.out.println(nextInput + "\n---------------------------------");
        }

        System.out.println(farewell);
    }
}
