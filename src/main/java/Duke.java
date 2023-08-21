import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String botName = "Dude";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        //Add the ability to store whatever text entered by the user and display them back to the user when requested.
        TaskManager userTasks = new TaskManager();

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            try {
                System.out.println("####################");
                System.out.println("Your request is: " + input);
                userTasks.handleAction(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } 
        } while (!input.equals("bye"));
    }
}
