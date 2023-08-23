import java.util.Scanner;
import java.util.ArrayList;

public class ChatterChicken {
    public static void main(String[] args) {
        String line = "\n    _____________________________________________________________________________\n      ";
        Scanner sc = new Scanner(System.in);
        List list = new List();

        System.out.println(line + "Hello! I'm ChatterChicken!\n      What can I do for you?" + line);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                list.printList();
            } else if (input.startsWith("mark ")) {
                list.markTask(input);
            } else if (input.startsWith("unmark ")) {
                list.unmarkTask(input);
            } else {
                list.addTask(new Task(input));
            }
            input = sc.nextLine();
        }
        System.out.println(line + "Bye. Hope to see you again soon!" + line);
    }
}
