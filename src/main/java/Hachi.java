import exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hachi {

    public static void main(String[] args) throws HachiException {
        String name = "Hachi";

        // Setting task and task length
        ArrayList<Task> tasks = new ArrayList<>();
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
                    for (int i = 0; i < tasks.size(); i++) {
                        int num = i + 1;
                        System.out.println(num + ". " + tasks.get(i));
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
                        if (number > tasks.size()) {
                            throw new NumberOutOfBoundsException(tasks.size());
                        }
                        tasks.get(i).mark();
                        System.out.println("Nice! I've marked this task as done");
                        System.out.println("   " + tasks.get(i));
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
                        if (number > tasks.size()) {
                            throw new NumberOutOfBoundsException(tasks.size());
                        }
                        tasks.get(i).unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(i));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid argument for command \"unmark\"");
                    }
                } else if (command.equals("delete")) {
                    if (arguments.length > 1) {
                        throw new TooManyArgumentsException("delete", 1, arguments.length);
                    }
                    if (arguments.length < 1) {
                        throw new EmptyNumberException("delete");
                    }
                    try {
                        int number = Integer.parseInt(arguments[0]);
                        int i = number - 1;
                        if (number > tasks.size()) {
                            throw new NumberOutOfBoundsException(tasks.size());
                        }
                        Task t = tasks.remove(i);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("   " + t);
                        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("delete");
                    }
                } else if (command.equals("todo")) {
                    if (arguments.length < 1) {
                        throw new EmptyTaskException("todo");
                    }
                    String todoTask = String.join(" ", arguments);
                    System.out.println("Got it. I've added this task:");
                    Todo td = new Todo(todoTask);
                    tasks.add(td);
                    System.out.println("   " + td);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
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
                    Deadline dl = new Deadline(deadlineTask, deadlineDate);
                    tasks.add(dl);
                    System.out.println("   " + dl);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
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
                        Event ev = new Event(eventTask, eventStartDate, eventEndDate);
                        tasks.add(ev);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("   " + ev);
                        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
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
