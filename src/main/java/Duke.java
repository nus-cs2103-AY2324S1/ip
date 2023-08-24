import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static String line = "\t____________________________________________________________\n";
    public static ArrayList<Task> strList = new ArrayList<>();
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
                    Task t = strList.get(i);
                    String marked = t.getStatusIcon();
                    String description = t.getDescription();
                    System.out.println("\t" + number + "." + marked + " " + description);
                }
                System.out.println(line);
            } else if (echo.startsWith("mark")) {
                char lastCharacter = echo.charAt(echo.length() - 1);
                int index = Character.getNumericValue(lastCharacter) - 1;
                Task t = strList.get(index);
                t.markTask();
                System.out.println(line);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + "\t" + t.getStatusIcon() + " " + t.getDescription());
                System.out.println(line);
            } else if (echo.startsWith("unmark")) {
                char lastCharacter = echo.charAt(echo.length() - 1);
                int index = Character.getNumericValue(lastCharacter) - 1;
                Task t = strList.get(index);
                t.unmarkTask();
                System.out.println(line);
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t" + "\t" + t.getStatusIcon() + " " + t.getDescription());
                System.out.println(line);
            }
            else {
                System.out.println(line + "\tadded: " + echo + "\n" + line);
                Task newTask = new Task(echo);
                strList.add(newTask);
            }
            echo = myObj.nextLine();  // Read user input

        }
        System.out.println(exitMessage);
    }
}
