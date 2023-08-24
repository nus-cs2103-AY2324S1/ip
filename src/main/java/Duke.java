import com.sun.jdi.ArrayReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static String name = "WallE";
    private static List<Task> tasks = new ArrayList<>();
    private static class DukeException extends RuntimeException {
        public DukeException() {
            super();
        }
        public DukeException(String message) {
            super(message);
        }
    }
    public static void printDivider(boolean isIndented) {
        if (isIndented)
            System.out.println('\t' + "_________________________________________");
        else
            System.out.println("_________________________________________");
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println("\t\t Got it. I've added this task:");
        System.out.println(String.format("\t\t\t %s", task.toString()));
        System.out.println(String.format("\t\tNow you have %d tasks in the list.", tasks.size()));
    }

    public static String extractSecondWordOnwards(String str) {
        String[] wordArray = str.split(" ");
        String secondWordOnwards = wordArray.length >= 2 ? wordArray[1] : "";
        for (int i = 2; i < wordArray.length; i++)  {
            secondWordOnwards += " " + wordArray[i];
        }
        return secondWordOnwards;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printDivider(true);
        System.out.println("\tHello! I'm " + name + "!");
        System.out.println("\tWhat can I do for you?");
        printDivider(true);
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                printDivider(true);
                if (!input.equals("list")) {
                    String[] inputWords = input.split(" ");
                    int id;
                    switch (inputWords[0]) {
                        case "todo":
                            String todoName = extractSecondWordOnwards(input);
                            if (todoName.length() == 0) {
                                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                                break;
                            }
                            Task todo = new ToDo(todoName, false);
                            tasks.add(todo);
                            System.out.println("\tadded: " + input);
                            printTaskAddedMessage(todo);
                            break;
                        case "deadline":
                            String[] twoParts = input.split ("/by ");
                            String deadlineName = extractSecondWordOnwards(twoParts[0]);
                            if (deadlineName.length() == 0) {
                                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                            String deadlineString = twoParts[1];
                            Task deadline = new Deadline(deadlineName, false, deadlineString);
                            tasks.add(deadline);
                            System.out.println("\tadded: " + input);
                            printTaskAddedMessage(deadline);
                            break;
                        case "event":
                            String[] threeParts = input.split ("/");
                            String eventName = extractSecondWordOnwards(threeParts[0]);
                            if (eventName.length() == 0) {
                                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                            }
                            try {
                                String eventStart = extractSecondWordOnwards(threeParts[1]);
                                String eventEnd = extractSecondWordOnwards(threeParts[2]);
                                Task event = new Event(eventName, false, eventStart, eventEnd);
                                tasks.add(event);
                                System.out.println("\tadded: " + input);
                                printTaskAddedMessage(event);
                                break;
                            } catch (RuntimeException e) {
                                throw new DukeException("Index likely out of bounds due to incorrect format of input. Expected usage: event {eventName} /from {eventStart} /to {eventEnd}");
                            } finally {
                                break;
                            }
                        case "mark":
                            try {
                                id = Integer.valueOf(inputWords[1]) - 1;
                                tasks.get(id).markAsDone();
                                System.out.println("\tNice! I've marked this task as done:");
                                System.out.println("\t\t" + tasks.get(id).toString());
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("Index out of bounds. Expected: mark {id}");
                            } finally {
                                break;
                            }
                        case "unmark":
                            try {
                                id = Integer.valueOf(inputWords[1]) - 1;
                                tasks.get(id).markAsUndone();
                                System.out.println("\tOk, I've marked this task as not done yet:");
                                System.out.println("\t\t" + tasks.get(id).toString());
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("Index out of bounds. Expected: unmark {id}");
                            } finally {
                                break;
                            }
                        case "delete":
                            id = Integer.valueOf(inputWords[1]) - 1;
                            Task removedEvent = tasks.remove(id);
                            System.out.println("\tNoted. I've removed this task:");
                            System.out.println("\t\t" + removedEvent.toString());
                            break;
                        default:
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.format("\t%d.%s", i + 1, task.toString()));
                    }
                }
                printDivider(true);
            } else {
                break;
            }
        } while (true);

        printDivider(true);
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider(true);
    }
}
