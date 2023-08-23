import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
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
            }
            System.out.println(input);
            System.out.println(horizontal);
        }
    }
}
