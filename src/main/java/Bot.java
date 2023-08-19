import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm the trash gremlin Caelus!\nWhat can I do for you?");
        ArrayList<String> lst = new ArrayList<>();
        while (true) {
            String str = sc.nextLine();
            if (str.toLowerCase().equals("bye")) {
                break;
            } else if (str.toLowerCase().equals("list")) {
                System.out.println(displayList(lst));
            } else {
                lst.add(str);
                System.out.println("Added: " + str);
            }
        }
        System.out.println("Bye. You can find me at the nearest trash can!");
    }
    private static String displayList(ArrayList<String> lst) {
        StringBuilder out = new StringBuilder();
        Iterator<String> iter = lst.iterator();
        int ctr = 1;
        while (iter.hasNext()) {
            out.append(ctr + ". " + iter.next() + "\n");
            ctr++;
        }
        out.deleteCharAt(out.length() - 1);
        return out.toString();
    }
}
