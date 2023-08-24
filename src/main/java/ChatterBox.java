import java.util.*;

public class ChatterBox {

    public static void main(String[] args) {

        ArrayList<String> taskList = new ArrayList<>();
        linePrinter();
        System.out.println("      Hello! I'm ChatterBox");
        System.out.println("      What can I do for you?");
        linePrinter();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                linePrinter(); 
                System.out.println("      Bye. Hope to see you again soon!");
                linePrinter();
                break;
            
            } else if (input.equals("list")) {
                linePrinter();
                for (int i = 0; i < taskList.size(); i++) {
                    tabPrinter(String.format("%d. %s", i + 1, taskList.get(i)));
                } 
                linePrinter();

            } else {
                linePrinter();
                tabPrinter("added: " + input);
                linePrinter();
                taskList.add(input);

            }
        }
    }

    private static void linePrinter() {
        System.out.println("      ___________________________________________________________");
    }

    private static void tabPrinter(String s) {
        System.out.println("      " + s);
    }
}
