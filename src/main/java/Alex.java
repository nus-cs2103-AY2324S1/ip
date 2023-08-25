import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alex {
    public static void main(String[] args) {
        String horizontalLine = "_____________________________________________________________\n";
        String greeting = horizontalLine
                + "Hello! I'm your personal task assistant, Alex\n"
                + "What can I do for you today?\n\n"
                + horizontalLine;

        String bye = horizontalLine
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine;

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            int inputLength = userInput.length();
            if (userInput.equals("bye")) {
                System.out.println(bye);
                break;
            } else if(userInput.equals("list")) {
                UserInputStorage.printAllContent();
            } else if (inputLength >= 6 && userInput.substring(0, 5).equals("mark ")) {
                int index = Integer.parseInt(userInput.substring(5, 6));
                Task targetedTask = UserInputStorage.getTaskByIndex(index);
                targetedTask.mark();
            } else if (inputLength >= 8 && userInput.substring(0, 7).equals("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7, 8));
                Task targetedTask = UserInputStorage.getTaskByIndex(index);
                targetedTask.unmark();
            } else if (inputLength >= 8 && userInput.substring(0, 7).equals("delete ")) {
                int toDeleteIndex = Integer.parseInt(userInput.substring(7, 8));
                UserInputStorage.delete(toDeleteIndex);
            } else if (inputLength >= 4 && userInput.substring(0, 5).equals("todo ")) {
                Task todo = new ToDos(userInput.substring(5));
                UserInputStorage.store(todo);
            } else if (inputLength >= 8 && userInput.substring(0, 9).equals("deadline ")) {
                String regex = "\\b /by \\b";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(userInput);
                matcher.find();
                int startIndex = matcher.start();
                int endIndex = matcher.end();
                String description = userInput.substring(9, startIndex);
                String by = userInput.substring(endIndex);
                Task deadline = new Deadline(description, by);
                UserInputStorage.store(deadline);
            } else if (inputLength >= 5 && userInput.substring(0, 6).equals("event ")) {
                String regex = "\\b /from \\b";
                Pattern pattern1 = Pattern.compile(regex);
                Matcher matcher1 = pattern1.matcher(userInput);
                matcher1.find();
                int firstStart = matcher1.start();
                int firstEnd = matcher1.end();

                String regex2 = "\\b /to \\b";
                Pattern pattern2 = Pattern.compile(regex2);
                Matcher matcher2 = pattern2.matcher(userInput);
                matcher2.find();
                int secondStart = matcher2.start();
                int secondEnd = matcher2.end();

                String description = userInput.substring(6, firstStart);
                String fromTime = userInput.substring(firstEnd, secondStart);
                String toTime = userInput.substring(secondEnd);

                Task event = new Event(description, fromTime, toTime);
                UserInputStorage.store(event);
            }
        }
    }
}
