import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "    ___    _   ___   ________  __      ____      __________  ____  _   __\n"
                + "   /   |  / | / / | / / __ \\ \\/ /     / __ \\    /_  __/ __ \\/ __ \\/ | / /\n"
                + "  / /| | /  |/ /  |/ / / / /\\  /_____/ / / /_____/ / / /_/ / / / /  |/ / \n"
                + " / ___ |/ /|  / /|  / /_/ / / /_____/ /_/ /_____/ / / _, _/ /_/ / /|  /  \n"
                + "/_/  |_/_/ |_/_/ |_/\\____/ /_/      \\____/     /_/ /_/ |_|\\____/_/ |_/   \n";
        String horizontalLine = "__________________________________________________________________________\n";
        String byeMessage = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        Scanner myObj = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println(horizontalLine + logo + "Hello! I'm ANNOY-O-TRON!\nWhat can I do for you?\n"
                + horizontalLine);

        String userInput = myObj.nextLine();
        while(!userInput.equals("bye")) {
            System.out.print(horizontalLine);
            if (userInput.equals("list")) {
                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                System.out.println("added: " + userInput);
                list.add(userInput);
            }
            System.out.println(horizontalLine);
            userInput = myObj.nextLine();
        }

        System.out.println(byeMessage);
    }
}
