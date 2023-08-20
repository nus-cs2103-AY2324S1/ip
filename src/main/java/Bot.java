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
                displayList(lst);
            } else if (markPattern.matcher(str).matches()) {
                int index = Integer.parseInt(str.substring(5)) - 1;
                if (index < 0 || index >= lst.size()) {
                    System.out.println("Sorry, that index doesn't exist.");
                    continue;
                }
                lst.get(index).mark();
                System.out.println("I'll mark this as done:\n" + lst.get(index).toString());
            } else if (unmarkPattern.matcher(str).matches()) {
                int index = Integer.parseInt(str.substring(7)) - 1;
                if (index < 0 || index >= lst.size()) {
                    System.out.println("Sorry, that index doesn't exist.");
                    continue;
                }
                lst.get(index).unmark();
                System.out.println("I'll mark this as not done:\n" + lst.get(index).toString());
            } else if (Task.canMakeTask(str)) {
                addTask(Task.makeTask(str), lst);
            } else {
                System.out.println(str);
            }
        }
        System.out.println("Bye. You can find me at the nearest trash can!");
    }
    private static void displayList(ArrayList<Task> lst) {
        if (lst.size() == 0) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        StringBuilder out = new StringBuilder().append("Here are the tasks in your list:\n");
        Iterator<Task> iter = lst.iterator();
        int ctr = 1;
        while (iter.hasNext()) {
            out.append(ctr).append(". ").append(iter.next().toString()).append("\n");
            ctr++;
        }
        out.deleteCharAt(out.length() - 1);
        System.out.println(out.toString());
    }
    private static void displayListLength(ArrayList<Task> lst) {
        System.out.println("Now you have " + lst.size() + " task(s) in the list.");
    }
    private static void addTask(Task newTask, ArrayList<Task> lst) {
        System.out.println("Added:\n" + newTask.toString());
        lst.add(newTask);
        displayListLength(lst);
    }
}
