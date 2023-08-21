import java.util.Scanner;
public class Duke {

    static String indent = "   ";
    static String megaIndent = "     ";
    static String horizontalLines = indent  +"_______________________________________";
    static Task taskArray[] = new Task[100];
    static int count = 0;
    public static void displayList() {
        System.out.println(horizontalLines);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            int num = i + 1;
            Task curr = taskArray[i];
            System.out.println(indent + num + ".[" + curr.getStatusIcon() + "] " + curr.description);
        }
        System.out.println(horizontalLines);
    }

    public static void main(String[] args) {
        String name = "zac";
        Scanner obj = new Scanner(System.in);

        System.out.println(horizontalLines);
        System.out.println(indent + "Hello! I'm " + name);
        System.out.println(indent + "What can I do for you?");
        System.out.println(horizontalLines);

        while (true) {
            String userInput = obj.nextLine();
            if (userInput.equals("list")) {
                displayList();
                continue;
            }
            if (userInput.contains("unmark")) {
                String clean = userInput.replaceAll("\\D+",""); //remove non-digits
                int pos = Integer.parseInt(clean) - 1;
                Task curr = taskArray[pos];
                curr.marked = false;

                System.out.println(indent + "OK, I've marked this task as not done yet:");
                System.out.println(megaIndent + "[" + curr.getStatusIcon() + "] " + curr.description);
                System.out.println(horizontalLines);
                continue;
            }
            if (userInput.contains("mark")) {
                String clean = userInput.replaceAll("\\D+",""); //remove non-digits
                int pos = Integer.parseInt(clean) - 1;
                Task curr = taskArray[pos];
                curr.marked = true;

                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(megaIndent + "[" + curr.getStatusIcon() + "] " + curr.description);
                System.out.println(horizontalLines);
                continue;
            }
            if (userInput.equals("bye")) {
                System.out.println(horizontalLines);
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(horizontalLines);
                break;
            }
            taskArray[count++] = new Task(userInput);
            System.out.println(horizontalLines);
            System.out.println(indent + "added: " + userInput);
            System.out.println(horizontalLines);
        }
    }
}
