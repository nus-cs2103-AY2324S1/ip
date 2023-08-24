import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static String line = "\t____________________________________________________________\n";
    public static ArrayList<Task> strList = new ArrayList<>();

    public static void printAddTask(Task t) {
        System.out.println(line + "\tGot it. I've added this task: ");
        System.out.println("\t\t" + t.toString());
        System.out.println("\tNow you have " + strList.size() + " tasks in the list.");
        System.out.println(line);
    }
    public static void main(String[] args) throws EmptyDescriptionException, InvalidCommandException, NotANumberException{

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
            try {
                String[] words = echo.split(" ");
                if (echo.equalsIgnoreCase("list")) {
                    int arrLength = strList.size();
                    System.out.println(line);
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < arrLength; i++) {
                        int number = i + 1;
                        Task t = strList.get(i);
                        System.out.println("\t" + number + "." + t.toString());
                    }
                    System.out.println(line);
                } else if (words[0].equalsIgnoreCase("mark")) {
                    char lastCharacter = echo.charAt(echo.length() - 1);
                    if (!Character.isDigit(lastCharacter)) {
                        throw new NotANumberException();
                    } else {
                        int index = Character.getNumericValue(lastCharacter) - 1;
                        Task t = strList.get(index);
                        t.markTask();
                        System.out.println(line);
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t" + "\t" + t.toString());
                        System.out.println(line);
                    }
                } else if (words[0].equalsIgnoreCase("unmark")) {
                    char lastCharacter = echo.charAt(echo.length() - 1);
                    if (!Character.isDigit(lastCharacter)) {
                        throw new NotANumberException();
                    } else {
                        int index = Character.getNumericValue(lastCharacter) - 1;
                        Task t = strList.get(index);
                        t.unmarkTask();
                        System.out.println(line);
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        System.out.println("\t" + "\t" + t.getStatusIcon() + " " + t.getDescription());
                        System.out.println(line);
                    }
                } else if (words[0].equalsIgnoreCase("todo")) {
                    String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    if (description.equals("")) {
                        throw new EmptyDescriptionException("todo");
                    } else {
                        Task newToDo = new ToDos(description);
                        strList.add(newToDo);
                        printAddTask(newToDo);
                    }
                } else if (words[0].equalsIgnoreCase("deadline")) {
                    String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    if (description.equals("")) {
                        throw new EmptyDescriptionException("deadline");
                    } else {
                        String[] parts = description.split("/by");  // Split the input string by the delimiter "/"
                        String before = parts[0].trim();
                        String after = parts[1].trim();
                        Task newDeadline = new Deadline(before, after);
                        strList.add(newDeadline);
                        printAddTask(newDeadline);
                    }
                } else if (words[0].equalsIgnoreCase("event")) {
                    String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    if (description.equals("")) {
                        throw new EmptyDescriptionException("event");
                    } else {
                        int fromIndex = description.indexOf("/from");  // Find the index of "/from"
                        int toIndex = description.indexOf("/to");  // Find the index of "/to"
                        String eventDescription = description.substring(0, fromIndex).trim();
                        String from = description.substring(fromIndex + "/from".length(), toIndex).trim();
                        String to = description.substring(toIndex + "/to".length()).trim();
                        Task newEvent = new Event(eventDescription, from, to);
                        strList.add(newEvent);
                        printAddTask(newEvent);
                    }
                } else {
                    throw new InvalidCommandException();
                }
            } catch (EmptyDescriptionException e) {
                //do something
            } catch (InvalidCommandException e) {
                //error caught
            } catch (NotANumberException e) {
                //NaN Exception caught
            } catch (Exception e) {
                System.out.println("An error occurred.");
            }
            echo = myObj.nextLine();  // Read user input
        }
        System.out.println(exitMessage);
    }
}
