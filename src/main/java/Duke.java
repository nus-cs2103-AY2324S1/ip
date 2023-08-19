import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm iP");
        String[] tasks = new String[100];
        int nextTask = 0;
        Scanner input = new Scanner(System.in);
        String response = "";
        while (!Objects.equals(response, "bye")) {
            response = input.next();
            if (Objects.equals(response, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (Objects.equals(response, "list")) {
                for (int i = 0; i < nextTask; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            } else {
                tasks[nextTask++] = response;
                System.out.println("added: " + response);
            }
        }
    }
}
