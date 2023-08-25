import java.util.Scanner;

public class Homepage {
    public static void main(String[] args) {
        System.out.println(
                "____________________________________________________________\n"
                + "Hello! I'm Bobi >.< , your trusted companion.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n"
        );

        TaskList list = new TaskList();
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();

        while (true) {
            if (answer.equalsIgnoreCase("bye")) {
                break;
            } else if (answer.equalsIgnoreCase("list")) {
                System.out.println(
                        "____________________________________________________________\n"
                        + list.toString() + "\n"
                        + "____________________________________________________________\n");
                answer = sc.nextLine();
            } else {
                System.out.println(
                        "____________________________________________________________\n"
                        + list.addTask(answer) + "\n"
                        + "____________________________________________________________\n");
                answer = sc.nextLine();
            }
        }

        System.out.println(
                "____________________________________________________________\n"
                + "Bye! Hope you have a good day today :)\n"
                + "____________________________________________________________\n"
        );
        sc.close();
        return;
    }
}
