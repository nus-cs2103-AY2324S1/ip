import java.util.Scanner;
public class UI {
    private void horizontalLines() {
        System.out.println("____________________________________________________________"); //length taken from sample
    }

    public void skeletalChatbot() {
        Scanner scanner = new Scanner(System.in);
        horizontalLines();
        System.out.println("Hello! I'm Whelmed.");
        System.out.println("What can I do for you?");
        horizontalLines();
        String input = scanner.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            horizontalLines();
            System.out.println(input);
            horizontalLines();
            input = scanner.nextLine();
        }
        horizontalLines();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLines();
    }
}
