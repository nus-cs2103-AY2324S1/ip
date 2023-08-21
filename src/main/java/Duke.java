import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String logo = "    ___    _   ___   ________  __      ____      __________  ____  _   __\n"
                + "   /   |  / | / / | / / __ \\ \\/ /     / __ \\    /_  __/ __ \\/ __ \\/ | / /\n"
                + "  / /| | /  |/ /  |/ / / / /\\  /_____/ / / /_____/ / / /_/ / / / /  |/ / \n"
                + " / ___ |/ /|  / /|  / /_/ / / /_____/ /_/ /_____/ / / _, _/ /_/ / /|  /  \n"
                + "/_/  |_/_/ |_/_/ |_/\\____/ /_/      \\____/     /_/ /_/ |_|\\____/_/ |_/   \n";
        String horizontalLine = "__________________________________________________________________________\n";
        String byeMessage = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        System.out.println(horizontalLine + logo + "Hello! I'm ANNOY-O-TRON!\nWhat can I do for you?\n"
                + horizontalLine);

        String userInput = myObj.nextLine();
        while(!userInput.equals("bye")) {
            System.out.println(horizontalLine + userInput + "\n" + horizontalLine);
            userInput = myObj.nextLine();
        }

        System.out.println(byeMessage);
    }
}
