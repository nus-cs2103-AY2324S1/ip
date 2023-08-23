import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        ArrayList<String> store = new ArrayList<>();
        String name = "CodeBuddy";
        String horizontal = "----------------------------------------";
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(horizontal);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal);

        while(true) {
            String input = inputScanner.nextLine();
            System.out.println(horizontal);
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon !");
                System.out.println(horizontal);
                break;
            } else if(input.equals("list")) {
                int counter = 1;
                for(String text: store) {
                    System.out.println(counter + ". " + text);
                    counter++;
                }
            } else {
                System.out.println("added: " + input);
                store.add(input);
            }
            System.out.println(horizontal);
        }
    }
}
