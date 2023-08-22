import java.util.Scanner;
import java.util.regex.*;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String logo = " _ \n"
                + "| |\n"
                + "| |\n"
                + "| |___\n"
                + "|_____|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Hi! This is your intelligent friend L.\n" + "What can I do for you?");
        while(scanner.hasNextLine()) {
            String repeat = scanner.nextLine();
            Pattern markPattern = Pattern.compile("mark (\\d+)");
            Matcher markMatcher = markPattern.matcher(repeat);
            Pattern unmarkPattern = Pattern.compile("unmark (\\d+)");
            Matcher unmarkMatcher = unmarkPattern.matcher(repeat);
            if (repeat.equals("")){
                System.out.println("Empty message. Please tell me more.");
            }
            else if (repeat.contains("bye") || repeat.contains("Bye")) {
                System.out.println("Bye. See you next time!");
                break;
            }
            else if(markMatcher.matches()){
                int taskIndex = Integer.parseInt(markMatcher.group(1));
                System.out.println(storage.markTask(taskIndex,true));
            }
            //TODO: allow multiple marks in one line
            else if (unmarkMatcher.matches()){
                int taskIndex = Integer.parseInt(unmarkMatcher.group(1));
                System.out.println(storage.markTask(taskIndex,false));
            }
            else if (repeat.contains("list") || repeat.contains("List")) {
                System.out.println(storage.getTasks());
            } else {
                storage.addTask(new Task(repeat));
                System.out.println("Task recorded: " + repeat);
            }
        }
    }
}
