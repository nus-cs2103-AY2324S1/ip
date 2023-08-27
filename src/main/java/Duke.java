import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tasks> task = new ArrayList<>();

        while (!FileHandler.readTasksFromFile(task)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String count;
        String logo  =   "____    ____  ________   ___    ___   __________    _____\n"
                     + "\\   \\  /   / |  ____  |  |  |   |  |  |  _____  |  / ____|\n"
                     + " \\   \\/   /  | |    | |  |  |   |  |  |  |___|  |  | (___\n"
                      + "  \\      /   | |    | |  |  |   |  |  |   ______|  \\ ___ \\\n"
                       + "   |    |    | |    | |  |  |   |  |  |  \\  \\           | |\n"
                       + "   |    |    | |____| |  |  |   |  |  |  | \\  \\     ____) |\n"
                       + "   |____|    |________|  \\_________/  |__|   \\__\\  |_____/\n";

        String name = "Yours";

        System.out.println("Hello! I'm " + name);
        System.out.println(logo);
        System.out.println("____________________________________________________________________________________");
        System.out.println("What can I do for you?");


        while (true) {
            String userInput = scanner.nextLine();
            //check for singular or plural "task"
            if (task.size() < 1) {
                count = "task";
            } else {
                count = "tasks";
            }

            if (userInput.isEmpty()) {
                System.out.println("Please enter something!!");
            } else if (userInput.equals("bye")) {
                System.out.println("   ____________________________________________________________________________________");
                System.out.println("   " + name + ": Bye. Hope to see you again soon!");
                System.out.println("   ____________________________________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("   ____________________________________________________________________________________");
                System.out.println("   " + name + ": Here are the tasks in your list.");
                for (int i = 0 ; i < task.size(); i ++) {
                    int j = i + 1;
                    System.out.println("     " + j + ". " +  task.get(i).toString());
                }
                System.out.println("   ____________________________________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(userInput.substring(5));
                    task.get(index - 1).markDone();
                    FileHandler.writeTasksToFile(task);
                    System.out.println("   ____________________________________________________________________________________");
                    System.out.println("   " + name + ": Well done! I've marked this task as done :");
                    System.out.println("   " + task.get(index - 1).toString());
                    System.out.println("   ____________________________________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("   " +  name + ": Please enter the correct task's index number.");
                }
            } else if (userInput.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(userInput.substring(7));
                    task.get(index - 1).markNotDone();
                    FileHandler.writeTasksToFile(task);
                    System.out.println("   ____________________________________________________________________________________");
                    System.out.println("   " + name + ": Alright, I've marked this task as not done yet");
                    System.out.println("   " + task.get(index - 1).toString());
                    System.out.println("   ____________________________________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("   " +  name + ": Please enter the correct task's index number.");
                }
            } else if (userInput.startsWith("todo")) {
                ToDos newtodo = new ToDos(userInput);
                if (newtodo.isValid()) {
                    task.add(newtodo);
                    FileHandler.writeTasksToFile(task);
                    System.out.println("   ____________________________________________________________________________________");
                    System.out.println("   " +  name + ": Help you added a new to-do.\n            " + newtodo.toString());
                    System.out.println("          Now you have " + task.size() + String.format(" %s in the list.", count));
                    System.out.println("   ____________________________________________________________________________________");
                }
            } else if (userInput.startsWith("deadline")) {
                Deadlines newdeadlines = new Deadlines(userInput);
                if (newdeadlines.isValid()) {
                    task.add(newdeadlines);
                    FileHandler.writeTasksToFile(task);
                    System.out.println("   ____________________________________________________________________________________");
                    System.out.println("   " + name + ": Help you added a new deadline.\n           " + newdeadlines.toString());
                    System.out.println("          Now you have " + task.size() + String.format(" %s in the list.", count));
                    System.out.println("   ____________________________________________________________________________________");
                }
            } else if (userInput.startsWith("event")) {
                Events newevents = new Events(userInput);
                if (newevents.isValid()) {
                    task.add(newevents);
                    FileHandler.writeTasksToFile(task);
                    System.out.println("   ____________________________________________________________________________________");
                    System.out.println("   " + name + ": Help you added a new event.\n           " + newevents.toString());
                    System.out.println("          Now you have " + task.size() + String.format(" %s in the list.", count));
                    System.out.println("   ____________________________________________________________________________________");
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(userInput.substring(7));
                    Tasks deleted = task.get(index - 1);
                    task.remove(index - 1);
                    FileHandler.writeTasksToFile(task);
                    System.out.println("   ____________________________________________________________________________________");
                    System.out.println("   " + name + ": Helped you deleted this task\n           " + deleted.toString());
                    System.out.println("          Now you have " + task.size() + String.format(" %s in the list.", count));
                    System.out.println("   ____________________________________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("   " +  name + ": Please enter the correct task's index number.");
                }
            } else {
                System.out.println("   ____________________________________________________________________________________");
                System.out.println("   " + name + ": I don't really understand what you mean.");
                System.out.println("   ____________________________________________________________________________________");
            }
        }
    }
}
