import java.util.Scanner;

public class Hachi {

    public static void main(String[] args) {
        String name = "Hachi";
        String[] tasks = new String[100];
        int currIndex = 0;
        printLine("Hello! I'm " + name + "\nWhat cam I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                printLine("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                StringBuilder s = new StringBuilder("");
                line();
                for (int i = 0; i < currIndex; i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + tasks[i]);
                }
                line();
            }
            else {
                printLine("added: " + command);
                tasks[currIndex++] = command;
            }
        }

    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void printLine(String s) {
        line();
        System.out.println(s);
        line();
    }
}
