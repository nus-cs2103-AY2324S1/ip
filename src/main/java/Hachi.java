import java.util.Arrays;
import java.util.Scanner;

public class Hachi {

    public static void main(String[] args) {
        String name = "Hachi";
        Task[] tasks = new Task[100];
        int currIndex = 0;
        line();
        System.out.println("Hello! I'm " + name + "\nWhat cam I do for you?");
        line();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] words = input.split(" ");
            String command = words[0];
            String[] arguments = Arrays.copyOfRange(words, 1, words.length);

            line();
            if (words.length == 1) {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    for (int i = 0; i < currIndex; i++) {
                        int num = i + 1;
                        System.out.println(num + ". " + tasks[i]);
                    }
                } else {
                    System.out.println("Invalid command " + command);
                }
            } else {
                if (command.equals("mark")) {
                    try {
                        int number = Integer.parseInt(words[1]);
                        int i = number - 1;
                        tasks[i].mark();
                        System.out.println("Nice! I've marked this task as done");
                        System.out.println("   " + tasks[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid argument for command \"mark\"");
                    }
                } else if (command.equals("unmark")) {
                    try {
                        int number = Integer.parseInt(words[1]);
                        int i = number - 1;
                        tasks[i].unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid argument for command \"unmark\"");
                    }
                } else if (command.equals("todo")) {
                    String todoTask = String.join(" ", arguments);
                    System.out.println("Got it. I've added this task:");
                    tasks[currIndex] = new Todo(todoTask);
                    System.out.println("   " + tasks[currIndex]);
                    currIndex++;
                    System.out.println(String.format("Now you have %d tasks in the list.", currIndex));
                } else if (command.equals("deadline")) {
                    System.out.println("Got it. I've added this task:");
                    int byIndex = -1;
                    for (int i = 0; i < arguments.length; i++) {
                        if (arguments[i].equals("/by")) {
                            byIndex = i;
                            break;
                        }
                    }
                    if (byIndex != -1) {
                        String deadlineTask = String.join(" ",
                                Arrays.copyOfRange(arguments, 0, byIndex));
                        String deadlineDate = String.join(" ",
                                Arrays.copyOfRange(arguments, byIndex + 1, arguments.length));
                        tasks[currIndex] = new Deadline(deadlineTask, deadlineDate);
                        System.out.println("   " + tasks[currIndex]);
                        currIndex++;
                        System.out.println(String.format("Now you have %d tasks in the list.", currIndex));
                    }
                } else if (command.equals("event")) {
                    System.out.println("Got it. I've added this task:");
                    int fromIndex = -1;
                    int toIndex = -1;
                    for (int i = 0; i < arguments.length; i++) {
                        if (arguments[i].equals("/from")) {
                            fromIndex = i;
                        } else if (arguments[i].equals("/to")) {
                            toIndex = i;
                        }
                    }
                    if (fromIndex != -1 && toIndex != -1) {
                        String eventTask = String.join(" ",
                                Arrays.copyOfRange(arguments, 0, fromIndex));
                        String eventStartDate = String.join(" ",
                                Arrays.copyOfRange(arguments, fromIndex + 1, toIndex));
                        String eventEndDate = String.join(" ",
                                Arrays.copyOfRange(arguments, toIndex + 1, arguments.length));
                        tasks[currIndex] = new Event(eventTask, eventStartDate, eventEndDate);
                        System.out.println("   " + tasks[currIndex]);
                        currIndex++;
                        System.out.println(String.format("Now you have %d tasks in the list.", currIndex));
                    }
                } else {
                    System.out.println("Invalid command " + words[0]);
                }
            }
            line();
        }

    }



    public static void line() {
        System.out.println("____________________________________________________________");
    }

}
