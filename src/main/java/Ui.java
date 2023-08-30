import java.nio.file.Path;
import java.nio.file.Paths;

public class Ui {
    private static final String name = "Bot";
    private static final String greeting = "Hello I'm " + name + "\nWhat can I do for you?";
    private static final String end = "Bye bye";
    private static final String mark = "mark";
    private static final String unmark = "unmark";
    private static final String bye = "bye";
    private static final String list = "list";
    private static final String delete = "delete";
    public static void greet() {
        System.out.println(greeting);
    }

    public static void printlist() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void exit() {
        System.out.println(end);
    }

}
