import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String name = "Obi-wan Kenobi";
        String line = "_____________________________________";
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int ind = 0;

        System.out.println("Hello There! I am " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ");

            if (command[0].equals("bye") && command.length == 1) {
                break;
            }

            else if (command[0].equals("list") && command.length == 1) {
                for (int i = 0; i < taskList.length; i++) {
                    if (taskList[i] == null) {
                        break;
                    }

                    System.out.print((i + 1) + "." + taskList[i].getStatusIcon() +
                            " " + taskList[i].getName() + "\n");
                }
            }

            else if (command.length == 2 && (command[0].equals("mark") || command[0].equals("unmark"))) {
                int pos = Integer.parseInt(command[1]);

                if (command[0].equals("mark")) {
                    taskList[pos - 1].markTask();
                    System.out.println("Nice! I've marked this task as done");
                } else {
                    taskList[pos - 1].unmarkTask();
                    System.out.println("OK, I've marked this task as not done yet:");
                }

                System.out.println("\t" + taskList[pos - 1].getStatusIcon() + " " + taskList[pos - 1].getName());
            }

            else {
                taskList[ind] = new Task(input);
                ind++;
                System.out.println("added: " + input);
            }

            System.out.println(line);
        }

        System.out.println("Bye. May the force be with you!");
    }

}
