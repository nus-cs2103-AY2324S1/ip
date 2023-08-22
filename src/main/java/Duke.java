import java.util.ArrayList;
import java.util.Scanner;

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
   
    /*
    private static String getFirstWord(String tempString) {
        int index = tempString.indexOf(' ');
        if (index > -1) {
            return tempString.substring(0, index).trim();
        } else {
            return tempString;
        }
    }*/


    public class TaskList {
        protected ArrayList<Task> list;

        public TaskList() {
            this.list = new ArrayList<Task>();
        }

        public void addList(Task entry) {
            list.add(entry);

        }

        public String toString() {
            String returnString = new String("");
            int i = 1;
            for(Task entry : list) {
                returnString += (i + ". " + entry.toString() + "\n");
                i++;
            }
            return returnString;
        }
    }

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String toString() {
            return this.description;
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        TaskList tasklist = duke.new TaskList();
        Scanner scanner = new Scanner(System.in);

        System.out.println(line + "Hi, I am a\n" + logo + "\nHow can I help you hehe.. (° ͜ʖ °)\n" + line);

        while(true) {
            String command = scanner.nextLine();
            System.out.println(command + "\n" + line);
            
            if (command.equals("bye")) {
                System.out.println("Aw goodbye.. ಠ_ಠ\n" + line);
                scanner.close();
                break;
            } else if (command.equals("list")) {
                System.out.println(tasklist.toString() + line);
            } else {
                tasklist.addList(duke.new Task(command));
                System.out.println("added: "+ command + "\n" + line);
            }
        }
    }
}
