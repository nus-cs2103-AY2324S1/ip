import java.util.ArrayList;
import java.util.Scanner;
public class Sae {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Sae\nWhat can I do for you?");

        ArrayList<String> store = new ArrayList<>();

        while (true) {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            if (str.equals("bye")) {
                break;
            }
            else if (str.equals("list")) {
                int len = store.size();
                for (int i = 1; i < len + 1; i++) {
                    System.out.println(i + ". " + store.get(i - 1));
                }
            }
            else {
                store.add(str);
                System.out.println("added: " + str);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
