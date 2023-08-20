import java.util.Scanner;
public class Duke {

    private static Task[] tasks = new Task[100];
    private static int position = 0;

    public static void start() {
        String intro = "Hi! This is your AI assistant LoyBoy!\n";
        String question = "What can I do for you today?";
        System.out.println(intro + question);
        Scanner scanner = new Scanner(System.in);


        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                String outro = "I wish you a pleasant day ahead, goodbye!\n";
                System.out.println(outro);
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listTask();
            } else if (command.length() > 5) {
                if (command.substring(0,4).equalsIgnoreCase("mark")) {
                    tasks[Integer.valueOf(command.substring(command.length() - 1)) - 1].markTask();
                } else if (command.substring(0,6).equalsIgnoreCase("unmark")) {
                    tasks[Integer.valueOf(command.substring(command.length() - 1)) - 1].unmarkTask();
                } else {
                    addTask(command);
                }
            } else {
                addTask(command);
            }

        }
        scanner.close();
    }

    private static void addTask(String task) {
        tasks[position] = new Task(task);
        position++;
        System.out.println("You added '" + task + "' to the list!");
    }
    private static void listTask() {
        if (position == 0) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int item = 0; tasks[item] != null; item++)  {
                System.out.println(item + 1 + ". " + tasks[item]);
            }
        }

    }



    public static void main(String[] args) {
        Duke.start();


    }
}

