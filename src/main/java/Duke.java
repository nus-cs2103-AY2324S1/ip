import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String botName = "Dude";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        //Add the ability to store whatever text entered by the user and display them back to the user when requested.
        ArrayList<String> userTexts = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("list")) {
                for (int i = 0; i < userTexts.size(); i++) {
                    System.out.println(i + 1 + ". " + userTexts.get(i));
                }
                continue;
            }
            userTexts.add(input);
            System.out.println("added: " + input);
        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
