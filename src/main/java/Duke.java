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

    public static void printList(List<String> list){
        String response = "";
        for (int i = 0; i < list.size(); i++) {
            response += String.format("%d. %s\n", i+1, list.get(i));
        }
        System.out.println(format_response(response.substring(0, response.length() - 1)));
    }

    public static void reply() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String response = myObj.nextLine();
        List<String> list = new ArrayList<String>();
        while (!response.equals("bye")) {
            switch(response) {
                case "list":
                    printList(list);
                    response = myObj.nextLine();
                    break;
                default: {
                    list.add(response);
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
