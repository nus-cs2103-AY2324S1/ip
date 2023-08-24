import java.util.*;

public class ChatterBox {

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();
        linePrinter();
        tabPrinter("Hello! I'm ChatterBox");
        tabPrinter("What can I do for you?");
        linePrinter();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String fullLine = sc.nextLine();
            String[] inputLine = fullLine.split(" ");
            String input = inputLine[0];
            if (input.equals("bye")) {
                linePrinter(); 
                tabPrinter("Bye. Hope to see you again soon!");
                linePrinter();
                break;
            
            } else if (input.equals("list")) {
                linePrinter();
                tabPrinter("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    tabPrinter(String.format("%d. %s", i + 1, 
                                taskList.get(i).toString()));
                } 
                linePrinter();
            
            } else if (input.equals("mark")) {
                int index = Integer.parseInt(inputLine[1]);
                taskList.get(index - 1).mark();
                linePrinter();
                tabPrinter("Nice! I've marked this task as done:");
                tabPrinter(taskList.get(index - 1).toString());
                linePrinter();
            
            } else if (input.equals("unmark")) {
                int index = Integer.parseInt(inputLine[1]);
                taskList.get(index - 1).unmark();
                linePrinter();
                tabPrinter("OK, I've marked this task as not done yet:");
                tabPrinter(taskList.get(index - 1).toString());
                linePrinter();

            } else {
                linePrinter();
                tabPrinter("added: " + fullLine);
                linePrinter();
                taskList.add(new Task(fullLine));

            }
        }
    }

    private static void linePrinter() {
        tabPrinter("___________________________________________________________");
    }

    private static void tabPrinter(String s) {
        System.out.println("      " + s);
    }
}
