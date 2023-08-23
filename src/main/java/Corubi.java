import java.util.ArrayList;
import java.util.Scanner;

public class Corubi {
    public static void main(String[] args) {
        // Setting of final parameters
        final String name = "Corubi";
        final String divider = "---------------------------------------------------";
        ArrayList<String> enteredText = new ArrayList();

        // Initiate the bot greeting
        Scanner sc = new Scanner(System.in);
        System.out.println(divider);
        System.out.println("Hello! I am " + name + ". \nWhat can I do for you?");
        System.out.println(divider);

        // Allow user input
        String input = sc.nextLine();

        // Exit the chatbot if the user says "bye"
        while (!input.equals("bye") && !input.equals("Bye")) {

            // If input is "list" command, show the list
            if (input.equals("list") || input.equals("List")) {
                for (int i = 0; i < enteredText.size(); i++) {
                    System.out.printf("%d. %s \n", i + 1, enteredText.get(i));
                }
                System.out.println(divider);
                input = sc.nextLine();
            } else {
                System.out.println("added: " + input);
                System.out.println(divider);
                enteredText.add(input);
                input = sc.nextLine();
            }
        }
        System.out.println(input + " " + input + "...please come back soon :(");
    }
}
