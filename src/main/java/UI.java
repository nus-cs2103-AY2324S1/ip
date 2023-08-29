import java.util.ArrayList;

public class UI {
    public static void printMessage(String... msgs) {
        System.out.println(Greeting.linebreak);
        for (String msg : msgs) {
            System.out.println(msg);
        }
        System.out.println(Greeting.linebreak);
    }

    public static void printFileError() {
        UI.printMessage("Something is wrong with the list file");
    }

    public static void NoSuchTaskError() {
        UI.printMessage("No Such Task");
    }

    public static void printList(ArrayList<Task> items) {
        System.out.println(Greeting.linebreak);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < items.size(); i++) {
            String index = String.valueOf(i + 1);
            System.out.println(index + ". " + items.get(i).showTaskinList());
        }
        System.out.println(Greeting.linebreak);
    }
}
