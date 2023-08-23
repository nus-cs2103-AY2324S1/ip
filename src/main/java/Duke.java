import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    final static ArrayList<Task> array = new ArrayList<>();
    final static String horizontalLine = "   ------------------------\n";
    public static void main(String[] args) {
        printGreetings();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.nextLine();
            System.out.println("\n" + horizontalLine);
            if (text.equalsIgnoreCase("bye"))
                break;
            else if (text.equalsIgnoreCase("list")) {
                System.out.println("HERE IS LIST");
                printList();
            } else if (text.split(" ", 2)[0].equalsIgnoreCase("mark")){
                System.out.println("MARKED");
                Task selectedTask = array.get(Integer.parseInt(text.split(" ", 2)[1]) - 1);
                selectedTask.mark();
                System.out.println("     " + selectedTask);
            } else if (text.split(" ", 2)[0].equalsIgnoreCase("unmark")){
                System.out.println("UNMARKED");
                Task selectedTask = array.get(Integer.parseInt(text.split(" ", 2)[1]) - 1);
                selectedTask.unmark();
                System.out.println("     " + selectedTask);
            }
            else {
                array.add(new Task(text));
                text = "     " + text;
                System.out.println(text);
            }
            System.out.println("\n" + horizontalLine);
        }

        System.out.println("     BYE!\n" + horizontalLine);
    }

    private static void printGreetings() {
        System.out.println(horizontalLine
                + "     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A LISTMAKER\n"
                + horizontalLine);
    }
    private static void printList() {
        for (int i = 1; i <= array.size(); i++) {
            System.out.println("     " + i + ". " + array.get(i - 1));
        }
    }


}
