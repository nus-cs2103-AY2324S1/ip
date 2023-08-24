import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static String line = "\t____________________________________________________________\n";
    public static ArrayList<String> strList = new ArrayList<>();
    public static void main(String[] args) {

        String greeting =
                line +
                "\tHello! I'm DukeBot\n" +
                "\tWhat can I do for you?\n" +
                line;

        String exitMessage = line + "\tBye. Hope to see you again\n" + line;
        System.out.println(greeting);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String echo = myObj.nextLine();  // Read user input

        while (!echo.equalsIgnoreCase("bye")) {

            if (echo.equalsIgnoreCase("list")) {
                int arrLength = strList.size();
                System.out.println(line);
                for (int i = 0; i < arrLength; i++) {
                    int number = i + 1;
                    System.out.println("\t" + number + ". " + strList.get(i));
                }
                System.out.println(line);
            } else {
                System.out.println(line + "\tadded: " + echo + "\n" + line);
                strList.add(echo);
            }
            echo = myObj.nextLine();  // Read user input

        }
        System.out.println(exitMessage);
    }
}
