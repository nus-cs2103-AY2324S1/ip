import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Bot {
    private static final Pattern markPattern = Pattern.compile("mark -?\\d+");
    private static final Pattern unmarkPattern = Pattern.compile("unmark -?\\d+");
    private static final Pattern deletePattern = Pattern.compile("delete -?\\d+");
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm the trash gremlin Caelus!\nWhat can I do for you?");
        ArrayList<Task> lst = new ArrayList<>();
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("bye")) {
                    break;
                } else if (str.equalsIgnoreCase("list")) {
                    displayList(lst);
                } else if (str.startsWith("mark")) {
                    markTask(str, lst);
                } else if (str.startsWith("unmark")) {
                    unmarkTask(str, lst);
                } else if (Task.isTaskCommand(str)) {
                    addTask(str, lst);
                } else if (str.startsWith("delete")) {
                    deleteTask(str, lst);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (BotException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. I'll be at the nearest trash can!");
    }
    private static void displayList(ArrayList<Task> lst) throws EmptyListException {
        if (lst.size() == 0) {
            throw new EmptyListException();
        }
        StringBuilder out = new StringBuilder().append("Here are the tasks in your list:\n");
        Iterator<Task> iter = lst.iterator();
        int ctr = 1;
        while (iter.hasNext()) {
            out.append(ctr).append(". ").append(iter.next().toString()).append("\n");
            ctr++;
        }
        out.deleteCharAt(out.length() - 1);
        System.out.println(out);
    }
    private static void displayListLength(ArrayList<Task> lst) {
        System.out.println("Now you have " + lst.size() + " task(s) in the list.");
    }
    private static void addTask(String str, ArrayList<Task> lst) throws InvalidTaskException {
        Task newTask = Task.makeTask(str);
        System.out.println("Added:\n" + newTask.toString());
        lst.add(newTask);
        displayListLength(lst);
    }
    private static void markTask(String str, ArrayList<Task> lst) throws InvalidIndexException {
        if (!markPattern.matcher(str).matches()) {
            throw new InvalidIndexException();
        }
        int index = Integer.parseInt(str.substring(5)) - 1;
        if (index < 0 || index >= lst.size()) {
            throw new InvalidIndexException();
        }
        lst.get(index).mark();
        System.out.println("I'll mark this as done:\n" + lst.get(index).toString());
    }
    private static void unmarkTask(String str, ArrayList<Task> lst) throws InvalidIndexException {
        if (!unmarkPattern.matcher(str).matches()) {
            throw new InvalidIndexException();
        }
        int index = Integer.parseInt(str.substring(7)) - 1;
        if (index < 0 || index >= lst.size()) {
            throw new InvalidIndexException();
        }
        lst.get(index).unmark();
        System.out.println("I'll mark this as not done:\n" + lst.get(index).toString());
    }
    private static void deleteTask(String str, ArrayList<Task> lst) throws InvalidIndexException {
        if (!deletePattern.matcher(str).matches()) {
            throw new InvalidIndexException();
        }
        int index = Integer.parseInt(str.substring(7)) - 1;
        if (index < 0 || index >= lst.size()) {
            throw new InvalidIndexException();
        }
        System.out.println("I've removed this task:\n" + lst.remove(index).toString());
        displayListLength(lst);
    }
}
