import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "    ___\n"
                + " __/_  `.  .-\"\"\"-."         + "           |                      |             |   \n"
                + " \\_,` | \\-'  /   )`-')"      + "    _` |   _ \\    _` |   _ \\  __ \\    _ \\   __| \n"
                + "  \"\") `\"`    \\  ((`\"`"    + "    (   |  (   |  (   |   __/  |   |  (   |  |   \n"
                + " ___Y  ,    .'7 /|"            + "      \\__,_| \\___/  \\__, | \\___| _.__/  \\___/  \\__| \n"
                + "(_,___/...-` (_/_/"            + "                    |___/";

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
                int index = sc.nextInt() - 1;
                tasks[index].markTask(true);
                System.out.println("Good job on completing a task ! You deserve a cookie C:");
                System.out.println("\t" + tasks[index].toString());
            } else if (s.equals("unmark")) { // unmark tasks
                int index = sc.nextInt() - 1;
                tasks[index].markTask(false);
                System.out.println("Oh nyo, did someone make a mistake ?");
                System.out.println("\t" + tasks[index].toString());
            } else { // adding tasks
                System.out.println("Mama mia ! I've just added this task:");
                String words = sc.nextLine(); // sc.nextLine() to get the remaining words

                if (s.equals("todo")) { // to-do task
                    tasks[tasksCounter] = new ToDos(words);
                    System.out.println("\t" + tasks[tasksCounter].toString());
                } else if (s.equals("deadline")) { // deadline task
                    int split = words.indexOf("/");
                    // substring w/o the spaces
                    String taskDescription = words.substring(0, split - 1);
                    String taskDeadline = words.substring(split + 4, words.length());

                    tasks[tasksCounter] = new Deadline(taskDescription, taskDeadline);
                    System.out.println("\t" + tasks[tasksCounter].toString());
                } else if (s.equals("event")) { // event task
                    // substring w/o the spaces
                    int startSplit = words.indexOf("/");
                    String taskDesription = words.substring(0, startSplit - 1);
                    int endSplit = words.indexOf("/", startSplit + 1); // find "/" after startSplit index
                    String start = words.substring(startSplit + 6, endSplit - 1);
                    String end = words.substring(endSplit + 4, words.length());

                    tasks[tasksCounter] = new Event(taskDesription, start, end);
                    System.out.println("\t" + tasks[tasksCounter].toString());
                }

                tasksCounter++;
                System.out.println("You now have " + tasksCounter + " task(s) in your list");
            }
        }

        System.out.println("Bye~ See you again");
        sc.close();
    }
}
