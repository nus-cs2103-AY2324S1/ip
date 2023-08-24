import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    static boolean allowNext = true;
    static boolean addStr = true;
    static ArrayList<String> storedStr = new ArrayList<>();

    public static void handleExit(String s) {
        String exit = "Bye. See you soon! :)\n";

        if (s.equals("bye")) {
            System.out.println(exit);
            allowNext = false;
        }
    }

    public static void handleList(String s) {
        if (s.equals("list")) {
            addStr = false;
            int len = storedStr.size();
            if (len > 0) {
                for (int i = 1; i < len + 1; i++) {
                    System.out.println(i + ". " + storedStr.get(i - 1));
                }
            } else {
                System.out.println("Nothing found!");
            }
        }
    }

    public static void main(String[] args) {
        String greet = "Hi! I'm Uke\n" + "What can I do for you?\n";
        System.out.println(greet);

        Scanner input = new Scanner(System.in);

        while (allowNext) {
            String str = input.nextLine();
            handleExit(str);
            handleList(str);

            if (allowNext && addStr) {
                storedStr.add(str);
                System.out.println("added: " + str);
            } else if (!addStr) {
                addStr = true;
            }
        }
    }
}
