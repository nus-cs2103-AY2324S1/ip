import java.util.Objects;
import java.util.Scanner;

public class Alpha {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // End trigger word to trigger outro and end the program
        String END = "bye";

        // List trigger word to display a log of stored text
        String LIST = "list";

        // Intro message
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Alpha\n" +
                " What can I do for you?\n";

        //Outro message
        String end = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        // Create a List class that performs all list operations
        List list = new List();
        System.out.println(intro);
        String input = sc.nextLine();

        while (!input.equals(END)) {
            if (input.equals(LIST)) {
                list.display();
            } else {
                list.add(input);
            }

            input = sc.nextLine();

        }

        System.out.println(end);
    }
}
