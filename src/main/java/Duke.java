import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks;
        Scanner scanner = new Scanner(System.in);
        greet();
        try {
            tasks = Storage.readTasksFromFile();
        } catch(FileNotFoundException e) {
            tasks =  new ArrayList<>();
        }
        while(true) {
            String command = scanner.nextLine();
            evaluate(command, tasks);
            if(command.equals("bye")){
                tasks.clear();
                break;
            }
        }
    }
    public static void greet() {
        String logo = "       .__\n"
                + "  ____ |__| ____   ____\n"
                + "/    \\|  |/    \\ /  _  \\\n"
                + "|   |  \\  |   |  (  <_> )\n"
                + "|___|  /__|___|  /\\____/\n"
                + "     \\/        \\/";
        final String HORIZONTAL = "_____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL);
        System.out.println("Hello! I'm NINO!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL);
    }
    public static void evaluate(String command, ArrayList<Task> tasks) {
        final String HORIZONTAL = "_____________________________________________________________";
        // split the command into 2(words[0] first word, words[1] the rest)
        try {
            String[] words = command.split(" ", 2);
            if (command.equals("bye")) {
                System.out.println(HORIZONTAL);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL);
                try{
                    Storage.writeTasksToFile(tasks);
                } catch (IOException e) {
                    System.out.println("Error in writing taskList to file!");
                }
            } else if (command.equals("list")) {
                int length = tasks.size();
                System.out.println(HORIZONTAL);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < length; i++) {
                    int task_number = i + 1;
                    Task t = tasks.get(i);
                    System.out.println(task_number
                            + ". "
                            + t);
                }
                System.out.println(HORIZONTAL);
            } else if (words[0].equals("mark")) {
                try {
                    Task t = tasks.get(Integer.parseInt(words[1]) - 1);
                    t.markDone();
                    System.out.println(HORIZONTAL);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    System.out.println(HORIZONTAL);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(HORIZONTAL);
                    System.out.println("☹ OOPS!!! I'm sorry, please enter a valid index to mark");
                    System.out.println(HORIZONTAL);
                }
            } else if (words[0].equals(("unmark"))) {
                Task t = tasks.get(Integer.parseInt(words[1]) - 1);
                t.markUndone();
                System.out.println(HORIZONTAL);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t);
                System.out.println(HORIZONTAL);
            } else if (words[0].equals("todo")) {
                // no need to split words[1] further since no deadline
                if(words.length < 2) {
                    throw new InvalidArgumentException("todo");
                } else {
                    Todo t = new Todo(words[1]);
                    tasks.add(t);
                    System.out.println(HORIZONTAL);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + tasks.size() + " in the list.");
                    System.out.println(HORIZONTAL);
                }
            } else if (words[0].equals("deadline")) {
                //split words[1] into 2
                if(words.length < 2) {
                    throw new InvalidArgumentException("deadline");
                } else {
                    String[] taskWithDeadline = words[1].split("/", 2);
                    String c = taskWithDeadline[0];
                    if(taskWithDeadline.length < 2) {
                        throw new InvalidArgumentException("deadline");
                    }
                    String[] splitTask =  taskWithDeadline[1].split(" ", 2);
                    if(splitTask.length < 2) {
                        throw new InvalidArgumentException("deadline");
                    } else {
                        String deadline = taskWithDeadline[1].split(" ", 2)[1];
                        Deadline t = new Deadline(c, deadline);
                        tasks.add(t);
                        System.out.println(HORIZONTAL);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + tasks.size() + " in the list.");
                        System.out.println(HORIZONTAL);
                    }
                }
            } else if (words[0].equals("event")) {
                //split based on /from first
                if(words.length < 2) {
                    throw new InvalidArgumentException("event");
                } else {
                    String[] splitCommand = words[1].split("/", 2);
                    if(splitCommand.length < 2) {
                        throw new InvalidArgumentException("event");
                    }
                    String c = splitCommand[0];
                    String[] splitDeadline = splitCommand[1].split("/", 2);
                    if(splitDeadline.length < 2) {
                        throw new InvalidArgumentException("event");
                    } else {
                        if(splitDeadline[0].split(" ", 2).length < 2
                                || splitDeadline[1].split(" ", 2).length < 2) {
                            throw new InvalidArgumentException("event");
                        } else {
                            String from = splitDeadline[0].split(" ", 2)[1];
                            String to = splitDeadline[1].split(" ", 2)[1];
                            Event t = new Event(c, from, to);
                            tasks.add(t);
                            System.out.println(HORIZONTAL);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(t);
                            System.out.println("Now you have " + tasks.size() + " in the list.");
                            System.out.println(HORIZONTAL);
                        }
                    }
                }
            } else if(words[0].equals("delete")) {
                try {
                    Task t = tasks.get(Integer.parseInt(words[1]) - 1);
                    tasks.remove(Integer.parseInt(words[1]) - 1);
                    int length = tasks.size();
                    System.out.println(HORIZONTAL);
                    System.out.println("Noted! I've removed this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + length + " tasks in the list.");
                    System.out.println(HORIZONTAL);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(HORIZONTAL);
                    System.out.println("☹ OOPS!!! I'm sorry, please enter a valid index to mark");
                    System.out.println(HORIZONTAL);
                }
            } else {
                throw new InvalidTaskException();
            }
        } catch(InvalidTaskException | InvalidArgumentException e) {
            System.out.println(HORIZONTAL);
            System.out.println(e.getMessage());
            System.out.println(HORIZONTAL);
        }
    }
}
