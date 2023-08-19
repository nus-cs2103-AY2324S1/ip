import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm iP");
        Scanner input = new Scanner(System.in);
        String response = "";
        while (!Objects.equals(response, "bye")) {
            response = input.next();
            if (Objects.equals(response, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(response);
            }
        }
    }
}
