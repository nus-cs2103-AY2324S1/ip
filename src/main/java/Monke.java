import java.util.Scanner;

public class Monke {
    private static Task[] list = new Task[100];
    private static int index = 0;

    public static void speak(String msg) {
        System.out.println("\t" + msg);
    }
    public static void greet() {
        Monke.speak("Hello, I'm Monke. OOGA BOOGA!");
        Monke.speak("What can I do for you?");
        Monke.printHorizontalLine();
    }

    public static void exit() {
        Monke.speak("Bye. Hope to see you again soon! OOGA BOOGA!");
        Monke.printHorizontalLine();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            Monke.printHorizontalLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                Monke.displayList();
            }
            else if (input.startsWith("mark")) {
                int n = Integer.parseInt(input.split(" ", 2)[1]);
                Task task = Monke.list[n - 1];
                task.mark();
                Monke.speak("Ooga booga! I've marked this task as done:");
                Monke.speak("\t" + task);
            }
            else if (input.startsWith("unmark")) {
                int n = Integer.parseInt(input.split(" ", 2)[1]);
                Task task = Monke.list[n - 1];
                task.unmark();
                Monke.speak("Ooga, I've marked this task as not done yet:");
                Monke.speak("\t" + task);
            } else {
                Monke.speak("added: " + input);
                Monke.addToList(new Task(input));
            }
            Monke.printHorizontalLine();
        }
        sc.close();
    }

    public static void addToList(Task item) {
        Monke.list[index] = item;
        Monke.index++;
    }

    public static void displayList() {
        for (int i = 0; i < Monke.index; i++) {
            Monke.speak((i + 1) + ". " + Monke.list[i]);
        }
    }

    public static void main(String[] args) {
        Monke.printHorizontalLine();
        Monke.greet();
        Monke.listen();
        Monke.exit();
    }
}
