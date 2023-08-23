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
            String[] temp = input.split(" ", 2);
            String command = temp[0];
            String args = temp.length > 1 ? temp[1] : "";
            Monke.printHorizontalLine();

            if (command.equals("bye")) {
                break;
            }
            switch (command) {
                case "list": {
                    Monke.displayList();
                    break;
                }
                case "mark": {
                    int n = Integer.parseInt(args);
                    Task task = Monke.list[n - 1];
                    task.mark();
                    Monke.speak("Ooga booga! I've marked this task as done:");
                    Monke.speak("\t" + task);
                    break;
                }
                case "unmark": {
                    int n = Integer.parseInt(args);
                    Task task = Monke.list[n - 1];
                    task.unmark();
                    Monke.speak("Ooga, I've marked this task as not done yet:");
                    Monke.speak("\t" + task);
                    break;
                }
                case "todo": {
                    Todo todo = new Todo(args);
                    Monke.addToList(todo);
                    break;
                }
                case "deadline": {
                    String[] tmp = args.split(" /by ", 2);
                    String description = tmp[0];
                    String date = tmp[1];
                    Deadline deadline = new Deadline(description, date);
                    Monke.addToList(deadline);
                    break;
                }
                case "event": {
                    String[] tmp = args.split(" /from ", 2);
                    String description = tmp[0];
                    String[] tmp2 = tmp[1].split(" /to ", 2);
                    String start = tmp2[0];
                    String end = tmp2[1];
                    Event event = new Event(description, start, end);
                    Monke.addToList(event);
                    break;
                }
                default: {
                    Monke.speak("invalid command");
                    break;
                }
            }
            Monke.printHorizontalLine();
        }
        sc.close();
    }

    public static void addToList(Task task) {
        Monke.speak("Got it. I've added this task:");
        Monke.speak("\t" + task);
        Monke.list[index] = task;
        Monke.index++;
        Monke.speak("Now you have " + index + " tasks in the list.");
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
