import java.util.Scanner;
public class Duke {
    public static String line = "----------------------------------------------------\n";
    public static String format_response(String response) {
        return response + "\n\n" + line;
    }
    public static void greet() {
        System.out.println(line);
        System.out.println(format_response(
                "Hello I'm Project54\n" +
                "What can I do for you?"
        ));
    }
    public static void bye() {
        System.out.println(format_response(
                "Bye. Hope to see you again soon"
        ));
    }
    public static void main(String[] args) {

        greet();

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String response = myObj.nextLine();
        while (!response.equals("bye")) {
            System.out.println(format_response(response));  // Output user input
            response = myObj.nextLine(); // Read user input
        }

        bye();

    }
}
