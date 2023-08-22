import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Mikhil" + '\n' + "What can I do for you");
        boolean flag = true;
        while (flag == true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();

            if (input.equals("bye")) {
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(input);
            }
        }
    }
}
