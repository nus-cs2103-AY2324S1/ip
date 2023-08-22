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
   
    private static String getFirstWord(String tempString) {
        int index = tempString.indexOf(' ');
        if (index > -1) {
            return tempString.substring(0, index).trim();
        } else {
            return tempString;
        }
    }

    public static void main(String[] args) {

        TaskList tasklist = new TaskList();
        Scanner scanner = new Scanner(System.in);

        System.out.println(line + "Hi, I am a\n" + logo + "\nHow can I help you hehe.. (° ͜ʖ °)\n" + line);

        while(true) {
            String command = scanner.nextLine();
            System.out.println("You said: " + command + "\n" + line);
            
            if (command.equals("bye")) {
                System.out.println("Aw goodbye.. ಠ_ಠ\n" + line);
                scanner.close();
                break;
            } else if (command.equals("list")) {
                System.out.println(tasklist.toString() + line);
            } else if (getFirstWord(command).equals("mark")) {
                System.out.println(tasklist.markAsDone(command) + line);
            } else if (getFirstWord(command).equals("unmark")) {
                System.out.println(tasklist.unmarkAsDone(command) + line);
            } else if (getFirstWord(command).equals("todo")) {
                tasklist.addTodo(command);
                System.out.println("added: " + command + "\n" + line);
            } else if (getFirstWord(command).equals("deadline")) {
                tasklist.addDeadline(command);
                System.out.println("added: " + command + "\n" + line);
            } else if (getFirstWord(command).equals("event")) {
                tasklist.addEvent(command);
                System.out.println("added: " + command + "\n" + line);
            } 
            
            else {
                tasklist.addList(command);
                System.out.println("added: "+ command + "\n" + line);
            }
        }
    }
}
