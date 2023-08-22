/**
 * Duke class that encapsulates a personal assistant chatbot.
 *
 * @author Pearlynn
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String getNumTasks(int num) {
        if (num == 1 || num == 0) {
            return "\t Now you have " + num + " task in the list.";
        }
        return "\t Now you have " + num + " tasks in the list.";
    }

    private static String textBtwnWords(String text, String first, String second) {
        return text.substring(text.indexOf(first) + first.length() + 1, text.indexOf(second));
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        boolean exit = false;
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t Hello! I'm Violet");
        System.out.println("\t What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);

        while (!exit) {
            String command = sc.nextLine();
            System.out.println(line);
            try {
                if (command.equals("bye")) {
                    System.out.println("\t Bye. Hope to see you again soon!");
                    exit = true;
                } else if (command.equals("list")) {
                    System.out.println("\t Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("\t " + (i + 1) + "." + list.get(i).toString());
                    }
                } else if (command.startsWith("mark")) {
                    String[] result = command.split(" ");
                    if (result.length == 1 && !command.startsWith("mark ") && command.length() > 4) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing mark.");
                    } else if (result.length == 1) {
                        throw new DukeException("☹ OOPS!!! Pls select a task to mark.");
                    }
                    int idx = Character.getNumericValue(command.charAt(5));
                    Task t = list.get(idx - 1);
                    t.markAsDone();
                    System.out.println("\t Nice! I've marked this task as done:");
                    System.out.println("\t\t" + t.toString());
                } else if (command.startsWith("unmark")) {
                    String[] result = command.split(" ");
                    if (result.length == 1 && !command.startsWith("unmark ") && command.length() > 6) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing unmark.");
                    } else if (result.length == 1) {
                        throw new DukeException("☹ OOPS!!! Pls select a task to unmark.");
                    }
                    int idx = Character.getNumericValue(command.charAt(7));
                    Task t = list.get(idx - 1);
                    t.markAsUndone();
                    System.out.println("\t OK, I've marked this task as not done yet:");
                    System.out.println("\t\t" + t.toString());
                } else if (command.startsWith("todo")) {
                    String[] result = command.split(" ");
                    if ((result.length == 1 && !command.startsWith("todo ") && command.length() > 4) ||
                            (result.length > 1 && !command.startsWith("todo "))) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing todo.");
                    } else if (result.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(command.substring(5));
                    list.add(todo);
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t\t" + todo.toString());
                    System.out.println(getNumTasks(list.size()));
                } else if (command.startsWith("deadline")) {
                    String[] result1 = command.split(" ");
                    String[] result2 = command.split("/by");
                    if ((result1.length == 1 && !command.startsWith("deadline ") && command.length() > 8) ||
                            (result1.length > 1 && !command.startsWith("deadline "))) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing deadline.");
                    } else if (result1.length == 1 ||
                            (command.contains("/by") &&
                                    textBtwnWords(command, "deadline", "/by").isBlank())) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (!command.contains("/by")) {
                        throw new DukeException("☹ OOPS!!! Pls provide a date/time for the deadline.");
                    } else if (!command.contains(" /by")) {
                        throw new DukeException("☹ OOPS!!! Pls add a space before typing /by");
                    } else if (result2.length == 1 || result2[1].isBlank()) {
                        throw new DukeException("☹ OOPS!!! The date/time for the deadline cannot be empty");
                    } else if (!command.contains("/by ")) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing /by.");
                    }
                    Deadline deadline = new Deadline(command.substring(9, command.indexOf("/") - 1),
                            command.substring(command.indexOf("/by") + 4));
                    list.add(deadline);
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t\t" + deadline.toString());
                    System.out.println(getNumTasks(list.size()));
                } else if (command.startsWith("event")) {
                    String[] result1 = command.split(" ");
                    String[] result2 = command.split("/to");
                    if ((result1.length == 1 && !command.startsWith("event ") && command.length() > 5) ||
                            (result1.length > 1 && !command.startsWith("event "))) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing event.");
                    } else if (result1.length == 1 ||
                            (command.contains("/from") &&
                                    textBtwnWords(command, "event", "/from").isBlank())) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if (!command.contains("/from")) {
                        throw new DukeException("☹ OOPS!!! Pls provide a start date/time for the event.");
                    } else if (!command.contains(" /from")) {
                        throw new DukeException("☹ OOPS!!! Pls add a space before typing /from");
                    } else if (!command.contains("/to")) {
                        throw new DukeException("☹ OOPS!!! Pls provide an end date/time for the event.");
                    } else if (command.contains("/from ") && command.contains("/to") &&
                            textBtwnWords(command, "/from", "/to").isBlank()) {
                        throw new DukeException("☹ OOPS!!! The start date/time for the event cannot be empty");
                    } else if (!command.contains("/from ")) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing /from.");
                    } else if (result2.length == 1 || result2[1].isBlank()) {
                        throw new DukeException("☹ OOPS!!! The end date/time for the event cannot be empty");
                    } else if (!command.contains("/to ")) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing /to.");
                    }
                    Event event = new Event(command.substring(6, command.indexOf("/") - 1),
                            command.substring(command.indexOf("/from") + 6, command.indexOf("/to") - 1),
                            command.substring(command.indexOf("/to") + 4));
                    list.add(event);
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t\t" + event.toString());
                    System.out.println(getNumTasks(list.size()));
                } else if (command.startsWith("delete")) {
                    String[] result = command.split(" ");
                    if (result.length == 1 && !command.startsWith("delete ") && command.length() > 6) {
                        throw new DukeException("☹ OOPS!!! Pls add a space after typing delete.");
                    } else if (result.length == 1) {
                        throw new DukeException("☹ OOPS!!! Pls select a task to delete.");
                    }
                    int idx = Character.getNumericValue(command.charAt(7));
                    Task t = list.get(idx - 1);
                    list.remove(idx - 1);
                    System.out.println("\t Noted. I've removed this task:");
                    System.out.println("\t\t" + t.toString());
                    System.out.println(getNumTasks(list.size()));
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IndexOutOfBoundsException e) {
                if (list.isEmpty()) {
                    System.out.println("\t ☹ OOPS!!! You don't have any task in your list. Pls add a task.");
                } else if (list.size() == 1) {
                    System.out.println("\t ☹ OOPS!!! You only have one task in your list. Pls select 1.");
                } else {
                    System.out.println("\t ☹ OOPS!!! Pls select a task number between 1 and " + list.size());
                }
            } catch (DukeException e) {
                System.out.println("\t " + e.toString());
            }
            System.out.println(line);
        }
    }
}
