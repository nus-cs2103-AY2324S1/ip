import java.util.Scanner;

public class Duke {

    private final Task[] data = new Task[100];
    private int count = 0;

    public void greet() {
        String logo = " ____                \n"
                + "|  _ \\  _____  _____ \n"
                + "| |/ / |  _  ||  _  |\n"
                + "| |\\ \\ | |_| || |_| |\n"
                + "|_| \\_\\|_____||_____|\n";
        String hello = "Hello! I am Roo!!\n" + "What can I do for you ah?\n";
        System.out.println("Hello from\n" + logo + hello);
    }

    public void bye() {
        System.out.println("Ciao! Hope to see you soon yorr!!");
    }

    public void add(Task task) {
        data[count] = task;
        count++;
        System.out.println("\"" + task.toString() + "\" added :)\n");
    }

    public void list() {
        System.out.println("Although I dunwan to list... But here is your list:");
        for (int i = 0; i < count; i++) {
            Task dt = data[i];
            if (dt.done()) {
                System.out.println((i + 1) + ". [x] " + dt.toString());
            } else {
                System.out.println((i + 1) + ". [ ] " + dt.toString());
            }
        }
        System.out.println(" ");
    }

    public void markDone(int i) {
        data[i].markDone();
        System.out.println("Yay! \"" + data[i] + "\" done liao!!\n");
    }

    public void markUndone(int i) {
        data[i].markUndone();
        System.out.println("Hmm... Why just now don't mark \"" + data[i] + "\" as done properly...\n");
    }

    public static void main(String[] args) {
        Duke roo = new Duke();
        roo.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            if (input.equals("list")) {
                roo.list();
                input = sc.nextLine();
            } else if (input.startsWith("unmark ")) {
                int t = Integer.parseInt(input.substring(7));
                roo.markUndone(t - 1);
                input = sc.nextLine();
            } else if (input.startsWith("mark ")) {
                int t = Integer.parseInt(input.substring(5));
                roo.markDone(t - 1);
                input = sc.nextLine();
            } else if (!input.equals("bye")) {
                roo.add(new Task(input));
                input = sc.nextLine();
            } else {
                sc.close();
                roo.bye();
                break;
            }
        }

    }
}
