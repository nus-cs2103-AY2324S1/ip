import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Bot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm the trash gremlin Caelus!\nWhat can I do for you?");
        ArrayList<Task> lst = new ArrayList<>();
        Pattern markPattern = Pattern.compile("mark \\d+");
        Pattern unmarkPattern = Pattern.compile("unmark \\d+");
        while (true) {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                break;
            } else if (str.equalsIgnoreCase("list")) {
                System.out.println(displayList(lst));
            } else if (markPattern.matcher(str).matches()) {
                int index = Integer.parseInt(str.substring(5)) - 1;
                lst.get(index).mark();
                System.out.println("I'll mark this as done:\n" + lst.get(index).toString());
            } else if (unmarkPattern.matcher(str).matches()) {
                int index = Integer.parseInt(str.substring(7)) - 1;
                lst.get(index).unmark();
                System.out.println("I'll mark this as not done:\n" + lst.get(index).toString());
            } else {
                lst.add(new Task(str));
                System.out.println("Added: " + str);
            }
        }
        System.out.println("Bye. You can find me at the nearest trash can!");
    }
    private static String displayList(ArrayList<Task> lst) {
        StringBuilder out = new StringBuilder();
        Iterator<Task> iter = lst.iterator();
        int ctr = 1;
        while (iter.hasNext()) {
            // I'm not smart enough to figure just how much faster this is.
            out.append(ctr).append(". ").append(iter.next().toString()).append("\n");
            ctr++;
        }
        out.deleteCharAt(out.length() - 1);
        return out.toString();
    }
}
