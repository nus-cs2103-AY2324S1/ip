import java.util.ArrayList;
import java.util.Scanner;

public class Corubi {
    public static void main(String[] args) {
        // Setting of final parameters
        final String name = "Corubi";
        final String divider = "---------------------------------------------------";
        ArrayList<Object[]> enteredText = new ArrayList();
        final String isDone = "[X] ";
        final String notDone = "[] ";

        // Initiate the bot greeting
        Scanner sc = new Scanner(System.in);
        System.out.println(divider);
        System.out.println("Hello! I am " + name + ". \nWhat can I do for you?");
        System.out.println(divider);

        // Allow user input
        String input = sc.nextLine();

        // Exit the chatbot if the user says "bye"
        while (!input.equals("bye") && !input.equals("Bye")) {

            // If input is "list" command, show the list.
            if (input.equals("list") || input.equals("List")) {
                for (int i = 0; i < enteredText.size(); i++) {

                    // Check if item is done yet
                    if ((boolean) enteredText.get(i)[1]) {
                        System.out.printf("%d. %s %s \n", i + 1, isDone, enteredText.get(i)[0]);
                    } else {
                        System.out.printf("%d. %s %s \n", i + 1, notDone, enteredText.get(i)[0]);
                    }
                }
                System.out.println(divider);
                input = sc.nextLine();
            } else if (input.contains("unmark") || input.contains("Unmark")) {
                // If command is unmark, then unmark the item

                // Split the input by spaces
                String[] splitInput = input.split(" ");

                // Iterate through the parts to find the number
                for (String num : splitInput) {
                    try {
                        int number = Integer.parseInt(num);
                        enteredText.get(number - 1)[1] = false;
                        System.out.println("Damn bro...I've unmarked it... : \n" + notDone + enteredText.get(number - 1)[0]);
                        System.out.println(divider);
                        input = sc.nextLine();
                        break; // Stop searching after first number is found
                    } catch (NumberFormatException e) {
                        // Not a number, continue searching
                    }
                }
            } else if (input.contains("mark") || input.contains("Mark")) {
                // If the input contains the word mark, mark the item number as done

                // Split the input by spaces
                String[] splitInput = input.split(" ");

                // Iterate through the parts to find the number
                for (String num : splitInput) {
                    try {
                        int number = Integer.parseInt(num);
                        enteredText.get(number - 1)[1] = true;
                        System.out.println("Oki, I've marked this task as done: \n" + isDone + enteredText.get(number - 1)[0]);
                        System.out.println(divider);
                        input = sc.nextLine();
                        break; // Stop searching after first number is found
                    } catch (NumberFormatException e) {
                        // Not a number, continue searching
                    }
                }
            } else {

                // Add the input to the list
                System.out.println("added: " + input);
                System.out.println(divider);
                enteredText.add(new Object[]{input, false});
                input = sc.nextLine();
            }
        }
        System.out.println(input + " " + input + "...please come back soon :(");
    }
}
