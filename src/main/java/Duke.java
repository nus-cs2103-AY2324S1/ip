import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "          ▄              ▄\n"
                + "        ▌▒█           ▄▀▒▌\n"
                + "        ▌▒▒▀▄       ▄▀▒▒▒▐\n"
                + "      ▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐\n"
                + "     ▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐\n"
                + "   ▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀██▀▒▌\n"
                + "  ▐▒▒▒▄▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▒▌\n"
                + "  ▌▒▒▐▄█▀▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐\n"
                + " ▐▒▒▒▒▒▒▒▒▒▒▒▌██▀▒▒▒▒▒▒▒▒▀▄▌\n"
                + " ▌▒▀▄██▄▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌\n"
                + " ▌▀▐▄█▄█▌▄▒▀▒▒▒▒▒▒░░░░░░▒▒▒▐\n"
                + "▐▒▀▐▀▐▀▒▒▄▄▒▄▒▒▒▒▒░░░░░░▒▒▒▒▌\n"
                + "▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒░░░░░░▒▒▒▐\n"
                + " ▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌\n"
                + " ▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐\n"
                + "  ▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▌\n"
                + "    ▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀\n"
                + "   ▐▀▒▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀\n"
                + "  ▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀\n";

        System.out.println(logo + "\nHi ! I'm DogeBot \nHow may I help you today ?\n");

        Task[] tasks = new Task[100];
        int tasksCounter = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            String s = sc.next();
            if (s.equals("bye")) break;

            if (s.equals("list")) { // view the list of tasks
                System.out.println("Stuff to do:");
                int i = 1;
                for (Task task : tasks) {
                    if (task == null) break;
                    System.out.println(i++ + "." + task.toString());
                }
            } else if (s.equals("mark")) { // mark tasks
                int temp = sc.nextInt() - 1;
                tasks[temp].markTask(true);
                System.out.println("Good job on completing a task ! You deserve a cookie C:");
                System.out.println("\t" + tasks[temp].toString());
            } else if (s.equals("unmark")) { // unmark tasks
                int temp = sc.nextInt() - 1;
                tasks[temp].markTask(false);
                System.out.println("Oh nyo, did someone make a mistake ?");
                System.out.println("\t" + tasks[temp].toString());
            } else { // adding tasks
                String temp = s + sc.nextLine(); // sc.nextLine() to add the remaining words
                System.out.println("added: " + temp); // echo command

                tasks[tasksCounter] = new Task(temp); // store into 'tasks'
                tasksCounter++;
            }
        }

        System.out.println("Bye~ See you again");
        sc.close();
    }
}
