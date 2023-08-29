package duke.ui;
import duke.task.*;
import java.util.ArrayList;

public class UI {
    public static final String linebreak = "____________________________________________________________";
    public static void printMessage(String... msgs) {
        System.out.println(linebreak);
        for (String msg : msgs) {
            System.out.println(msg);
        }
        System.out.println(linebreak);
    }

    public static void printFileError() {
        UI.printMessage("Something is wrong with the list file");
    }

    public static void NoSuchTaskError() {
        UI.printMessage("No Such Task");
    }

    public static void printList(ArrayList<Task> items) {
        System.out.println(linebreak);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < items.size(); i++) {
            String index = String.valueOf(i + 1);
            System.out.println(index + ". " + items.get(i).showTaskinList());
        }
        System.out.println(linebreak);
    }
}
