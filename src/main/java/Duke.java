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
        List<Task> list = new ArrayList<>();

        System.out.println(horizontalLine + logo + "Hello! I'm ANNOY-O-TRON!\nWhat can I do for you?\n"
                + horizontalLine);

        String userInput = myObj.nextLine();
        while(!userInput.equals("bye")) {
            System.out.print(horizontalLine);
            if (userInput.equals("list")) {
                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println(i + "." + list.get(i - 1));
                }
            } else if(userInput.contains("mark")) {
                int index = userInput.charAt(userInput.length() - 1) - '0';
                Task currentTask = list.get(index - 1);
                if (userInput.contains("unmark")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    currentTask.markUndone();
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    currentTask.markDone();
                }
                System.out.println(currentTask);
            } else {
                System.out.println("added: " + userInput);
                Task newTask = new Task(userInput);
                list.add(newTask);
            }
            System.out.println(horizontalLine);
            userInput = myObj.nextLine();
        }

        System.out.println(byeMessage);
    }
}
