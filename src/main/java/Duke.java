import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        // Hello! I'm [YOUR CHATBOT NAME]

        String print1 = "Hello! I'm Afro\n"
                        + "What can I do for you?\n";

        String str;
        List<Task> taskForce = new ArrayList<Task>();

        System.out.println(print1);

        File file = new File("./data/duke.txt");

        try {
            Scanner printSC = new Scanner(file);
            while (printSC.hasNextLine()) {
                System.out.println(printSC.nextLine());
            }
            printSC.close();

        } catch (FileNotFoundException e) {
            System.out.println("No file found!");
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                break;
                
            } else if (str.equals("list")) {
                String toBeSaved = "";
                for (Task task : taskForce) {
                    int index = taskForce.indexOf(task) + 1;

                    toBeSaved = toBeSaved + task + "\n";
                    System.out.println(index + ":" + task);
                }

                File path = new File("./data/duke.txt");

                try {
                    FileWriter wr = new FileWriter(path);
                    wr.write(toBeSaved);
                    wr.flush();
                    wr.close();
                } catch (IOException e) {
                    System.out.println("This will never occur.");
                }
                
                
            } else if (str.startsWith("unmark")) {
                try {
                    validateInput(str, 7);
                } catch (UserInputException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;
                
                Task task = taskForce.get(index);
                task.markTaskNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.getStatusIcon() + " " + task.description);

            } else if (str.startsWith("mark")) {
                try {
                    validateInput(str, 5);
                } catch (UserInputException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;

                Task task = taskForce.get(index);
                task.markTaskDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getStatusIcon() + " " + task.description);

            } else if (str.startsWith("delete")) {
                try {
                    validateInput(str, 7);
                } catch (UserInputException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;
                Task task = taskForce.get(index);

                System.out.println("Noted. I have removed this task.");
                System.out.println(task);
                taskForce.remove(index);

                System.out.println("Now you have " + taskForce.size() + " tasks in the list.");

            } else {
                if (!(str.contains("todo") || str.contains("event") 
                        || str.contains("deadline"))) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                } else if (str.startsWith("todo")) {
                    try {
                        validateInput(str, 5);
                    } catch (UserInputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
    
                    String[] moreStrings = str.split(" ", 2);

                    Task todo = new Todo(moreStrings[1]);
                    taskForce.add(todo);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);

                } else if (str.startsWith("deadline")) {
                    try {
                        validateInput(str, 9);
                    } catch (UserInputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    String[] moreStrings = str.split("/", 2);
                    String[] getDescription = moreStrings[0].split(" ", 2);

                    String returnBy = moreStrings[1].substring(3);

                    Task deadline = new Deadline(getDescription[1], returnBy);
                    taskForce.add(deadline);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);

                } else if (str.startsWith("event")) {
                    try {
                        validateInput(str, 6);
                    } catch (UserInputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    String[] moreStrings = str.split("/");
                    String[] pullStrings = moreStrings[0].split(" ", 2);

                    String from = moreStrings[1].substring(4);
                    String to = moreStrings[2].substring(2);

                    Task event = new Event(pullStrings[1], from, to);
                    taskForce.add(event);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                } 

                System.out.println("Now you have " + taskForce.size() + " tasks in the list.");
            }
        }

        sc.close();
        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void validateInput(String str, int minimum) throws UserInputException {
        if (str.length() <= minimum) {
            throw new UserInputException("OOPS!!! The description of a " + str + " cannot be empty.");
        }
    }
}
