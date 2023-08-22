import java.util.Objects;
import java.util.Scanner;

public class Alpha {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String intro = "____________________________________________________________\n" +
                " Hello! I'm Alpha\n" +
                " What can I do for you?\n";

        String end = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(intro);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            String output = "____________________________________________________________\n" +
                    input + "\n" +
                    "____________________________________________________________";
            System.out.println(output);
            input = sc.nextLine();
        }

        System.out.println(end);
    }
}
