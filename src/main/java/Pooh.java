import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pooh {
    protected static final String horizontalLine = "      _______________________________________________________________________________";

    public static void welcomeMsg() {
        String logo = "      .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "      | .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "      | |   ______     | || |     ____     | || |     ____     | || |  ____  ____  | |\n" +
                "      | |  |_   __ \\   | || |   .'    `.   | || |   .'    `.   | || | |_   ||   _| | |\n" +
                "      | |    | |__) |  | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |__| |   | |\n" +
                "      | |    |  ___/   | || |  | |    | |  | || |  | |    | |  | || |   |  __  |   | |\n" +
                "      | |   _| |_      | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  | |_  | |\n" +
                "      | |  |_____|     | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                "      | |              | || |              | || |              | || |              | |\n" +
                "      | '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                "       '----------------'  '----------------'  '----------------'  '----------------' ";

        String greetings = "      Hi there! Good to see you! I'm Pooh!\n      What can I do for you?";
        System.out.println(logo);
        System.out.println(horizontalLine);
        System.out.println(greetings);
        System.out.println(horizontalLine);
    }

    public static void exitMsg() {
        String byeMessage = "      How lucky I am to have something that makes saying goodbye so hard. Pooh says Bye!";
        System.out.println(horizontalLine);
        System.out.println(byeMessage);
        System.out.println(horizontalLine);
    }

    public static void generalRespond(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    public static void printTasks(List<String> tasks) {
        StringBuilder todoListString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String task = String.format("      %d. ", i) + tasks.get(i) + "\n";
            todoListString.append(task);
        }
        System.out.println(horizontalLine);
        System.out.println(todoListString.toString().stripTrailing());
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        welcomeMsg();
        List<String> todoList = new ArrayList<String>();
        Scanner userInput = new Scanner(System.in);
        while  (userInput.hasNextLine()) {
            String userCmd = userInput.nextLine();
            if (userCmd.equalsIgnoreCase("bye")) {
                exitMsg();
                userInput.close();
                System.exit(0);
            } else if (userCmd.equalsIgnoreCase("list")) {
                if (todoList.isEmpty()) {
                    generalRespond("      No tasks added. Add one now!");
                } else {
                    printTasks(todoList);
                }
            } else {
                todoList.add(userCmd);
                generalRespond("      added: " + userCmd);
            }
        }
    }
}