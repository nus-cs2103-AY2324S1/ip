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
        String byeMessage = "      How lucky I am to have something that makes saying goodbye so hard. Bye!";
        System.out.println(horizontalLine);
        System.out.println(byeMessage);
        System.out.println(horizontalLine);
    }

    public static void generalRespond(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    public static void printTasksMsg(List<Task> taskList) {
        StringBuilder todoListString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String task = String.format("      %d. ", i + 1) + taskList.get(i) + "\n";
            todoListString.append(task);
        }
        System.out.println(horizontalLine);
        System.out.println("      Here are the tasks in your list:");
        System.out.println(todoListString.toString().stripTrailing());
        System.out.println(horizontalLine);
    }

    public static void taskDoneMsg(List<Task> taskList, Task task) {
        System.out.println(horizontalLine);
        System.out.println("      Nice! I've marked this task as done:\n      " + task);
        System.out.println(horizontalLine);
    }

    public static void taskUndoneMsg(List<Task> taskList, Task task) {
        System.out.println(horizontalLine);
        System.out.println("      OK, I've marked this task as not done yet:\n      " + task);
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        welcomeMsg();
        List<Task> todoList = new ArrayList<Task>();
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
                    printTasksMsg(todoList);
                }
            } else if (userCmd.startsWith("mark ")) {
                int index = Integer.parseInt(userCmd.split(" ")[1]) - 1;
                Task task = todoList.get(index);
                task.markAsDone();
                taskDoneMsg(todoList, task);
            } else if (userCmd.startsWith("unmark ")){
                int index = Integer.parseInt(userCmd.split(" ")[1]) - 1;
                Task task = todoList.get(index);
                task.markAsUndone();
                taskUndoneMsg(todoList, task);
            } else {
                todoList.add(new Task(userCmd));
                generalRespond("      added: " + userCmd);
            }
        }
    }
}