import java.util.Scanner;

public class Duke {

    public static String line = 
    "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n";

    public static String logo = 
    "     ______     _                     ______          __     ____        __ \n"+
    "    / ____/____(_)___  ____ ____     /_  __/__  _  __/ /_   / __ )____  / /_\n"+
    "   / /   / ___/ / __ \\/ __ `/ _ \\     / / / _ \\| |/_/ __/  / __  / __ \\/ __/\n"+
    "  / /___/ /  / / / / / /_/ /  __/    / / /  __/>  </ /_   / /_/ / /_/ / /_  \n"+
    "  \\____/_/  /_/_/ /_/\\__, /\\___/    /_/  \\___/_/|_|\\__/  /_____/\\____/\\__/  \n"+
    "                    /____/                                                  \n";
   
    private static String StringHandler(String tempString) {
        return (tempString + "\n" + line);
    }

    public static void main(String[] args) {

        System.out.println(line + "Hi, I am a\n" + logo + "\nHow can I help you hehe.. (° ͜ʖ °)\n" + line);
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String command = scanner.nextLine();
            System.out.println(command + "\n" + line);
            if (command.equals("bye")) {
                System.out.println("Aw goodbye.. ಠ_ಠ\n" + line);
                scanner.close();
                break;
            }
            String output = StringHandler(command);
            System.out.println(output);
        }

    }
}
