import java.util.Scanner;

public class ChatterChicken {
    public static final String LINE = "\n    _____________________________________________________________________________\n";
    public static final String INDENT = "      ";
    public static final String INDENT_BIG = "        ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list = new List();

        System.out.println(LINE + INDENT + "Hello! I'm ChatterChicken!\n" + INDENT + "What can I do for you?" + LINE);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                list.printList();
            } else {
                String action = input.substring(0, input.indexOf(' '));
                switch (action) {
                    case "mark":
                        list.markTask(input);
                        break;
                    case "unmark":
                        list.unmarkTask(input);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        list.addTask(action, input);
                        break;
                }
            }
            input = sc.nextLine();
        }
        System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!" + LINE);
    }
}
