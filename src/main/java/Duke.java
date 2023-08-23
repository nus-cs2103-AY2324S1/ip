import java.util.Scanner;

import TaskPackages.TaskList;

public class Duke {

    /*
    public enum Commands {
        BYE("bye"),
        LIST("list");

        private String code;

        Commands(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    };*/

    public static String line = 
    "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n";

    public static String logo = 
    "     ______     _                     ______          __     ____        __ \n"+
    "    / ____/____(_)___  ____ ____     /_  __/__  _  __/ /_   / __ )____  / /_\n"+
    "   / /   / ___/ / __ \\/ __ `/ _ \\     / / / _ \\| |/_/ __/  / __  / __ \\/ __/\n"+
    "  / /___/ /  / / / / / /_/ /  __/    / / /  __/>  </ /_   / /_/ / /_/ / /_  \n"+
    "  \\____/_/  /_/_/ /_/\\__, /\\___/    /_/  \\___/_/|_|\\__/  /_____/\\____/\\__/  \n"+
    "                    /____/                                                  \n";


    private static String[] inputParser(String input) {
        int index = input.indexOf(' ');
        if (index > -1) {
            return input.split(" ", 2);
        } else {
            String[] tempString = {input, ""};
            return tempString;
        }

    }

    public static void main(String[] args) {
        TaskList tasklist = new TaskList();
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("%sHi, I am a\n%s\nHow can I help you hehe.. (° ͜ʖ °)\n%s", line, logo, line));

        while(true) {
            String input = scanner.nextLine();
            String[] parsedInput = inputParser(input);
            String command = parsedInput[0];
            String rest = parsedInput[1];
            System.out.println("You said: " + input + "\n" + line);
            
            if (command.equals("bye")) {
                System.out.println("Aw goodbye.. ಠ_ಠ\n" + line);
                scanner.close();
                break;
            } else if (command.equals("list")) {
                System.out.println(tasklist.toString() + line);
            } else if (command.equals("mark")) {
                System.out.println(tasklist.markAsDone(rest) + line);
            } else if (command.equals("unmark")) {
                System.out.println(tasklist.unmarkAsDone(rest) + line);
            } else if (command.equals("todo")) {
                System.out.println(tasklist.addTodo(rest) + line);
            } else if (command.equals("deadline")) {
                System.out.println(tasklist.addDeadline(rest) + line);
            } else if (command.equals("event")) {
                System.out.println(tasklist.addEvent(rest) + line);
            } else {
                tasklist.addTask(command);
                System.out.println("added: "+ command + "\n" + line);
            }
        }
    }
}
