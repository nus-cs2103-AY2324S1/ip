import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        String[] list = new String[100]; // List to be returned when input is "list"
        int counter = 0; // Items in list
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n"); // Greets user

        String echo = myObj.nextLine(); // Reads user input

        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(i + 1 + ". " + list[i]); // Returns the list
                    }
                }
            } else {
                // Echos the input
                String response = "Understood, I will add \"" + echo + "\" to your to do list.";
                System.out.println(response);

                // Insert action into list
                list[counter] = echo;
                counter += 1;
            }
            echo = myObj.nextLine();
        }
        System.out.println("Goodbye and have a nice day.\n"); // Exits the bot
    }
}
