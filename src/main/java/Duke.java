import java.util.Scanner;
import java.util.ArrayList;
public class Duke {


    public static void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        if (command.equals("bye")) {
            return;
        }
        printHorizontalLine();
        System.out.println(command);
        printHorizontalLine();
        echo();
    }

    public static void introduction() {
        String name = "Donk";
        printHorizontalLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void conclusion() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you soon again soon!");
        printHorizontalLine();
    }

    public static void list() {
        ArrayList<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        do {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                return;
            } else if (command.equals("list")) {
                printHorizontalLine();
                for (int i = 0; i < list.size(); i++) {
                    int printIndex = i + 1;
                    System.out.println( printIndex + ". " + list.get(i) );
                }
                printHorizontalLine();
                continue;
            }
            list.add(command);
            printHorizontalLine();
            System.out.println("added: " + command);
            printHorizontalLine();


        } while (true);

    }


    public static void main(String[] args) {
        introduction();

        list();

        conclusion();


    }
}
