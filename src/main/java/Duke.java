import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n"); // Greets user
        String echo = myObj.nextLine(); // Reads user input
        while (!echo.equals("bye")) {
            // Echos the input unless input is "bye"
            String polite = "Pardon me, but I believe you said ";
            System.out.println(polite + echo + "?");
            echo = myObj.nextLine();
        }
        System.out.println("Goodbye and have a nice day.\n"); // Exits the bot
    }
}
