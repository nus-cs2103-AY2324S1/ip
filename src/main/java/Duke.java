
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, I'm JavAI.\nWhat can I do for you?\n");
        String output = input.nextLine();
        while (!output.equals("bye")) {
            System.out.println(output);
            output = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");



    }
}
