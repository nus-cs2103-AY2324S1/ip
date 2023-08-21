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
            String[] command = input.split(" ", 2);

            if (command[0].equals("bye") && command.length == 1) {
                break;
            }

            else if (command[0].equals("list") && command.length == 1) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.length; i++) {
                    if (taskList[i] == null) {
                        break;
                    }
                    System.out.print((i + 1) + "." + taskList[i].toString() + "\n");
                }
            }

            else if (command.length == 2 && (command[0].equals("mark") || command[0].equals("unmark"))) {
                int pos = Integer.parseInt(command[1]);

                if (command[0].equals("mark")) {
                    taskList[pos - 1].markTask();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    taskList[pos - 1].unmarkTask();
                    System.out.println("OK, I've marked this task as not done yet:");
                }

                System.out.println(taskList[pos - 1].toString());
            }

            // add tasks
            else if (command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")) {
                if (command[0].equals("todo")) {
                    taskList[ind] = new ToDo(command[1]);
                }

                else if (command[0].equals("deadline")) {
                    String[] task = command[1].split("/by ", 2);
                    taskList[ind] = new Deadline(task[0], task[1]);
                }

                else {
                    String[] event = command[1].split("/from ",2);
                    String[] dates = event[1].split("/to ", 2);
                    taskList[ind] = new Event(event[0], dates[0], dates[1]);
                }

                System.out.println("Got it. I've added this task:");
                System.out.println(taskList[ind].toString());
                ind++;
                System.out.println("Now you have " + ind + " tasks in the list.");
            }

            else {
                System.out.println("Invalid Command");
            }

            System.out.println(line);
        }

        System.out.println("Bye. May the force be with you!");
    }

}