import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> archive = new ArrayList<>();
    public static void main(String[] args) {
        String logo = "       ___          \n" +
                "    . -^   `--,      \n" +
                "   /# =========`-_   \n" +
                "  /# (--====___====\\ \n" +
                " /#   .- --.  . --.| \n" +
                "/##   |  * ) (   * ),\n" +
                "|##   \\    /\\ \\   / |\n" +
                "|###   ---   \\ ---  |\n" +
                "|####      ___)    #|\n" +
                "|######           ##|\n" +
                " \\##### ---------- / \n" +
                "  \\####           (  \n" +
                "   `\\###          |  \n" +
                "     \\###         |  \n" +
                "      \\##        |   \n" +
                "       \\###.    .)   \n" +
                "        `======/     ";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm HEAD");
        System.out.println("What can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("SHOW ME WHAT YOU'VE GOT");
            String input = sc.nextLine();
            int len = input.length();
            if (input.equals("GET SCHWIFTY")) {
                System.out.println("I LIKE WHAT YOU'VE GOT. GOOD JOB.");
                return;
            } else if (input.equals("list")) {
                for (int i = 0; i < archive.size(); i++) {
                    System.out.println(i + ". " + archive.get(i));
                }
            } else if (input.startsWith("mark")) {
                int item = input.charAt(5) - '0';
                Task curr = archive.get(item);
                curr.mark();
                System.out.println("I HAVE MARKED THIS TASK: ");
                System.out.println(curr);
            } else if (input.startsWith("unmark")) {
                int item = input.charAt(7) - '0';
                Task curr = archive.get(item);
                curr.unmark();
                System.out.println("YOU LIED. I HAVE UNMARKED THIS TASK: ");
                System.out.println(curr);
            }
            else {
                Task added = null;
                if (input.startsWith("todo")) {
                    String title = input.substring(5);
                    added = new Todo(title);
                } else if (input.startsWith("deadline")) {
                    int index = input.indexOf("/by");
                    String title = input.substring(9,index - 1);
                    String dueDate = input.substring(index + 4);
                    added = new Deadline(title,dueDate);
                } else if (input.startsWith("event")){
                    int fromIndex = input.indexOf("/from");
                    String title = input.substring(6 ,fromIndex - 1);
                    int toIndex = input.indexOf("/to");
                    String from = input.substring(fromIndex + 6, toIndex - 1);
                    String to = input.substring(toIndex + 4);
                    added = new Event(title,from,to);
                }
                if (added != null) {
                    archive.add(added);
                    System.out.println("I'VE ADDED THIS TASK: ");
                    System.out.println(added);
                    System.out.println("YOU HAVE " + archive.size() + " TASKS IN THE LIST");
                }
            }
        }
    }
}
