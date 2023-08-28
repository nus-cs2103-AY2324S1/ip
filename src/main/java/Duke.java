import java.util.Scanner;

import Utility.DukeException;

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

    public static void main(String[] args) {
        DukeHandler dukeHandler = new DukeHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("%sHi, I am a\n%s\nHow can I help you hehe.. (° ͜ʖ °)\n%s", line, logo, line));

        while(true) {
            String input = scanner.nextLine();
            System.out.println(String.format("You said: %s\n%s", input, line));

            if(dukeHandler.checkExit(input)) {
                System.out.println("Aw goodbye.. ಠ_ಠ\n" + line);
                break;
            }

            try {
                System.out.println(dukeHandler.handle(input) + line);
            } catch (DukeException e) {
                System.out.println(e.toString() + line);
            }
        }

        dukeHandler.writeTaskList();

        scanner.close();
    }
}
