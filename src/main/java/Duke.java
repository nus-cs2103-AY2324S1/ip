import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String space = "------------------------------------"; // for spacing purposes
        String name = "Adam's Bot"; // name of bot

        System.out.println(space);
        System.out.println("Hello! I'm "+ name);
        System.out.println("What can I do for you?");
        System.out.println(space);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        userInput = scanner.nextLine().trim();// remove trailing spaces and get use input

        //prompt for user input if input is not "bye"
        while(!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equals("list")) {
                System.out.println(space);
                System.out.println("list");
                System.out.println(space);

            } else {

                System.out.println(space);
                System.out.println(userInput);
                System.out.println(space);

            }
            userInput = scanner.nextLine().trim();
        }

        System.out.println(space);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(space);
    }
}
