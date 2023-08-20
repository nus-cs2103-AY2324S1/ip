import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String EXIT_PHRASE = "bye";
        System.out.println(
                "Hello! I'm Sunacchi\n" +
                        "What can I do for you?\n"
        );

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        String userInput = myObj.nextLine();
        while (!userInput.equals(EXIT_PHRASE)) {
            System.out.println(userInput);
            userInput = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");




    }
}
