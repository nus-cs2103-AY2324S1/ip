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

    public static void toDo() {
        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                return;
            } else if (command.equals("list")) {
                printHorizontalLine();
                for (int i = 0; i < list.size(); i++) {
                    int printIndex = i + 1;
                    System.out.println( printIndex + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription() );
                }
                printHorizontalLine();
                continue;
            } else if (command.substring(0,5).equals("mark ")) {
                int curr = Integer.parseInt(command.substring(5)) - 1;
                Task currTask = list.get(curr);
                if (currTask.isDone) {
                    continue;
                } else {
                    printHorizontalLine();
                    currTask.isDone = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println( "[" + currTask.getStatusIcon() + "] " + currTask.getDescription() );
                    printHorizontalLine();

                }
                continue;
            } else if (command.substring(0,7).equals("unmark ")) {
                int curr = Integer.parseInt(command.substring(7)) - 1;
                Task currTask = list.get(curr);
                if (currTask.isDone) {
                    printHorizontalLine();
                    currTask.isDone = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println( "[" + currTask.getStatusIcon() + "] " + currTask.getDescription() );
                    printHorizontalLine();
                }
                continue;
            }
            list.add(new Task(command));
            printHorizontalLine();
            System.out.println("added: " + command);
            printHorizontalLine();
        }
    }




    public static void main(String[] args) {
        introduction();

        toDo();

        conclusion();


    }
}
