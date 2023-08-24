import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introduction = "____________________________________________________________\n" +
                " Hello! I'm eggbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println(introduction);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Type something for me to echo!");

            String input = scanner.nextLine();  // Read input

            if (Objects.equals(input, "bye")) {
                String output = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon! \n" +
                        "____________________________________________________________\n";

                System.out.println(output);
                break;
            }
            String output = "____________________________________________________________\n" +
                    input + "\n" +
                    "____________________________________________________________\n";

            System.out.println(output);
        }

    }
}
