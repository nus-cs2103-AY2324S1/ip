import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        System.out.println("Hi! This is your intelligent friend L.\n\"Dream big.\"\n" +
                "What can I do for you today?");
        while(scanner.hasNextLine()) {
            String repeat = scanner.nextLine();
            Pattern markPattern = Pattern.compile("(mark|unmark) (\\d+)");
            Matcher markMatcher = markPattern.matcher(repeat);
            Pattern taskPattern = Pattern.compile("(todo|deadline|event) (.+)");
            Matcher taskMatcher = taskPattern.matcher(repeat);
            if (repeat.equals("")){
                System.out.println("Empty message. Please tell me more.");
            }
            else if (repeat.contains("bye") || repeat.contains("88")) {
                System.out.println("Bye!\n\"Beware the barrenness of a busy life.\"");
                break;
            }
            //TODO: allow multiple marks in one line
            else if(markMatcher.matches()){
                boolean isDone = markMatcher.group(1).equals("mark");
                int taskIndex = Integer.parseInt(markMatcher.group(2));
                System.out.println(storage.markTask(taskIndex,isDone));
            }
            else if (taskMatcher.matches()){
                System.out.println(storage.addTask(taskMatcher.group(1), taskMatcher.group(2)));
            }
            else if (repeat.contains("list") || repeat.contains("List")) {
                System.out.println(storage.getTasks());
            } else {
                System.out.println("Sorry! I can't understand you.");
            }
        }
    }
}
