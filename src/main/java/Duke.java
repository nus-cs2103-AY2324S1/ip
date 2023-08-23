import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Aaronbot";

        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();
            
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(userInput); 
            }
        }
        
        scanner.close();
    }
}
