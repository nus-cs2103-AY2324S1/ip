import java.util.Scanner;
import java.util.*;
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

    public static String getCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null; // Return null for empty input or whitespace
        }

        input = input.trim(); // Remove leading and trailing whitespace
        int spaceIndex = input.indexOf(' '); // Find the index of the first space

        if (spaceIndex == -1) {
            return input; // If no space found, the whole string is the first word
        } else {
            return input.substring(0, spaceIndex); // Extract the first word using substring
        }
    }

    public static void printList(List<Task> list){
        String response = "";
        for (int i = 0; i < list.size(); i++) {
            response += String.format("%d. %s\n", i+1, list.get(i));
        }
        System.out.println(format_response(response.substring(0, response.length() - 1)));
    }

    public static void reply() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String response = myObj.nextLine();
        String command = getCommand(response);
        List<Task> list = new ArrayList<Task>();
        while (!response.equals("bye")) {
            switch(response.split(" ")[0]) {
                case "list":
                    printList(list);
                    response = myObj.nextLine();
                    break;
                case "mark":
                    list.get(Integer.parseInt(response.split(" ")[1])-1).setDone();
                    printList(list);
                    response = myObj.nextLine();
                    break;
                case "unmark":
                    list.get(Integer.parseInt(response.split(" ")[1])-1).setNotDone();
                    printList(list);
                    response = myObj.nextLine();
                    break;
                default: {
                    list.add(new Task(response));
                    System.out.println(format_response("added: " + response));  // Output user input
                    response = myObj.nextLine(); // Read user input
                }
            }

        }
    }

    public static void bye() {
        System.out.println(format_response(
                "Bye. Hope to see you again soon"
        ));
    }
    public static void main(String[] args) {

        greet();
        reply();
        bye();

    }
}
