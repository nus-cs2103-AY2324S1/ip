import exceptions.*;
import java.util.Arrays;
import java.util.Scanner;

public class Hachi {

    public static void main(String[] args) throws HachiException {
        String name = "Hachi";

        // Setting task and task length
        Task[] tasks = new Task[100];
        int currIndex = 0;

        // Printing opening line
        line();
        System.out.println("Hello! I'm " + name + "\nWhat cam I do for you?");
        line();

        Scanner sc = new Scanner(System.in);

        // repeats until user types bye, which quits the program
        while (true) {
            // getting command and argument separately
            String input = sc.nextLine();
            String[] words = input.split(" ");
            String command = words[0];
            String[] arguments = Arrays.copyOfRange(words, 1, words.length);

            line();
            // handling case for only one word (argument array is empty)
            try {
                if (command.equals("bye")) {
                    if (arguments.length > 0) {
                        throw new TooManyArgumentsException("bye", 0, arguments.length);
                    }
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    if (arguments.length > 0) {
                        throw new TooManyArgumentsException("list", 0, arguments.length);
                    }
                    for (int i = 0; i < currIndex; i++) {
                        int num = i + 1;
                        System.out.println(num + ". " + tasks[i]);
                    }
                } else if (command.equals("mark")) {
                    if (arguments.length > 1) {
                        throw new TooManyArgumentsException("mark", 1, arguments.length);
                    }
                    if (arguments.length < 1) {
                        throw new EmptyNumberException("mark");
                    }
                    try {
                        int number = Integer.parseInt(arguments[0]);
                        int i = number - 1;
                        if (tasks[i] == null) {
                            throw new NumberOutOfBoundsException(currIndex);
                        }
                        tasks[i].mark();
                        System.out.println("Nice! I've marked this task as done");
                        System.out.println("   " + tasks[i]);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("mark");
                    }
                } else if (command.equals("unmark")) {
                    if (arguments.length > 1) {
                        throw new TooManyArgumentsException("unmark", 1, arguments.length);
                    }
                    if (arguments.length < 1) {
                        throw new EmptyNumberException("unmark");
                    }
                    try {
                        int number = Integer.parseInt(words[1]);
                        int i = number - 1;
                        if (tasks[i] == null) {
                            throw new NumberOutOfBoundsException(currIndex);
                        }
                        tasks[i].unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid argument for command \"unmark\"");
                    }
                } else if (command.equals("todo")) {
                    if (arguments.length < 1) {
                        throw new EmptyTaskException("todo");
                    }
                    String todoTask = String.join(" ", arguments);
                    System.out.println("Got it. I've added this task:");
                    tasks[currIndex] = new Todo(todoTask);
                    System.out.println("   " + tasks[currIndex]);
                    currIndex++;
                    System.out.println(String.format("Now you have %d tasks in the list.", currIndex));
                } else if (command.equals("deadline")) {
                    if (arguments.length < 1) {
                        throw new EmptyTaskException("deadline");
                    }
                    int byIndex = -1;
                    for (int i = 0; i < arguments.length; i++) {
                        if (arguments[i].equals("/by")) {
                            byIndex = i;
                            break;
                        }
                    }
                    if (byIndex == -1) {
                        throw new NoDeadlineException();
                    }
                    if (byIndex == arguments.length - 1) {
                        throw new EmptyDeadlineException("deadline");
                    }
                    System.out.println("Got it. I've added this task:");
                    String deadlineTask = String.join(" ",
                            Arrays.copyOfRange(arguments, 0, byIndex));
                    String deadlineDate = String.join(" ",
                            Arrays.copyOfRange(arguments, byIndex + 1, arguments.length));
                    tasks[currIndex] = new Deadline(deadlineTask, deadlineDate);
                    System.out.println("   " + tasks[currIndex]);
                    currIndex++;
                    System.out.println(String.format("Now you have %d tasks in the list.", currIndex));
                } else if (command.equals("event")) {
                    if (arguments.length < 1) {
                        throw new EmptyTaskException("event");
                    }
                    int fromIndex = -1;
                    int toIndex = -1;
                    for (int i = 0; i < arguments.length; i++) {
                        if (arguments[i].equals("/from")) {
                            fromIndex = i;
                        } else if (arguments[i].equals("/to")) {
                            toIndex = i;
                        }
                    }
                    if (fromIndex == -1 && toIndex == -1) {
                        throw new EventDateException("/from and /to");
                    } else if (toIndex == -1){
                        throw new EventDateException("/to");
                    } else if (fromIndex == -1) {
                        throw new EventDateException("/from");
                    } else {
                        String eventTask = String.join(" ",
                                Arrays.copyOfRange(arguments, 0, fromIndex));
                        String eventStartDate = String.join(" ",
                                Arrays.copyOfRange(arguments, fromIndex + 1, toIndex));
                        String eventEndDate = String.join(" ",
                                Arrays.copyOfRange(arguments, toIndex + 1, arguments.length));
                        if (eventTask.equals("")) {
                            throw new EmptyTaskException("event");
                        } else if (eventStartDate.equals("") && eventEndDate.equals("")) {
                            throw new EventDateException("start date and end date");
                        } else if (eventEndDate.equals("")) {
                            throw new EventDateException("end date");
                        } else if (eventStartDate.equals("")) {
                            throw new EventDateException("start date");
                        }
                        tasks[currIndex] = new Event(eventTask, eventStartDate, eventEndDate);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("   " + tasks[currIndex]);
                        currIndex++;
                        System.out.println(String.format("Now you have %d tasks in the list.", currIndex));
                    }
                } else {
                    throw new InvalidCommandException(command);
                }
            } catch (HachiException e) {
                System.out.println(e.getMessage());
            }
            line();
        }

    }



    public static void line() {
        System.out.println("____________________________________________________________");
    }

}
