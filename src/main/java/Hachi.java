import java.util.Scanner;

public class Hachi {

    public static void main(String[] args) {
        String name = "Hachi";
        printLine("Hello! I'm " + name + "\nWhat cam I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                printLine("Bye. Hope to see you again soon!");
                break;
            }
            else {
                printLine(command);
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
