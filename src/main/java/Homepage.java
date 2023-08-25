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
            } else if (answer.length() == 6 && answer.substring(0, 4).equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(answer.substring(answer.length() - 1));
                System.out.println(
                        "____________________________________________________________\n"
                                + list.markDone(taskNumber) + "\n"
                                + "____________________________________________________________\n");
                answer = sc.nextLine();
            } else if (answer.length() == 8 && answer.substring(0, 6).equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(answer.substring(answer.length() - 1));
                System.out.println(
                        "____________________________________________________________\n"
                        + list.unMarkDone(taskNumber) + "\n"
                        + "____________________________________________________________\n");
                answer = sc.nextLine();
            } else {
                Task newTask = new Task(answer);
                System.out.println(
                        "____________________________________________________________\n"
                                + list.addTask(newTask) + "\n"
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
