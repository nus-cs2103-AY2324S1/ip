import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents Duke, a Personal Assistant Chatbot that helps a person to keep track of
 * various things. The name Duke was chosen as a placeholder name, in honor of Duke,
 * the Java Mascot. The current name of the Chatbot is John.
 */
public class Duke {

    /**
     * Our main method for the Chatbot to work.
     * TODO: can split into different methods to make the code neater
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // substring: begIndex (inclusive) up to the endIndex (exclusive)
        int itemsAdded = 0;
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("Hello friend :> My name is John, nice to meet you! " +
                "What do you have to do today?");

        Scanner sc = new Scanner(System.in); // do not put this in the while loop

        while (true) {
            System.out.print("Reply John: ");
            String command = sc.nextLine(); // using .next() is wrong - only reads first word

            if (command.equals("bye")) {
                System.out.println("Bye for now, hope to see you soon.");
                break;
            } else {
                try {
                    if (command.equals("list")) {
                        System.out.println("Here are the tasks in your list:");

                        for (int i = 1; i <= itemsAdded; i++) {
                            Task task = taskList.get(i - 1);
                            System.out.println(i + ". " + taskList.get(i - 1).toString());
                            // adding toString() to use the overridden one in Task, etc.
                        }
                    } else if (command.startsWith("mark ")) { // space behind is needed!, number index = 5
                        int taskPos = Integer.parseInt(command.substring(5)) - 1;
                        // convert substring to int, -1 for index

                        // only numbers >= 0 and < total number are valid
                        if (taskPos >= 0 && taskPos < itemsAdded) {
                            taskList.get(taskPos).markAsDone();
                            System.out.println("Nice! I've marked this task as done:\n" + "[X] "
                                    + taskList.get(taskPos).description);
                        } else {
                            throw new InvalidNumberException();
                        }

                    } else if (command.startsWith("unmark ")) { // number index = 7
                        int taskPos = Integer.parseInt(command.substring(7)) - 1;
                        // convert substring to int, -1 for index

                        // only numbers >= 0 and < total number are valid
                        if (taskPos >= 0 && taskPos < itemsAdded) {
                            taskList.get(taskPos).unmark();
                            System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] "
                                    + taskList.get(taskPos).description);
                        } else {
                            throw new InvalidNumberException();
                        }

                    } else if (command.startsWith("todo ")) { // description starting index = 5
                        String description = command.substring(5);

                        if (description.isEmpty()) {
                            throw new InvalidDescriptionException("todo");
                        }

                        taskList.add(new ToDo(description)); // add new command

                        System.out.println("Got it. I've added this task:\n" + "  " + taskList.get(itemsAdded)
                                + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

                        itemsAdded++; // increment number of items

                    } else if (command.startsWith("deadline ")) { // description starting index = 9
                        // indexOf: searches for the substring and returns the index of the first character
                        if (command.indexOf(" /by ") != -1) {
                            String description = command.substring(9, command.indexOf(" /by "));
                            String by = command.substring(command.indexOf(" /by ") + 5);
                            // from " " to the specified date is 5

                            if (description.isEmpty()) {
                                throw new InvalidDescriptionException("deadline");
                            }

                            if (by.isEmpty()) {
                                throw new InvalidDeadlineException();
                            }

                            taskList.add(new Deadline(description, by));; // add new command

                            System.out.println("Got it. I've added this task:\n" + "  " + taskList.get(itemsAdded)
                                    + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

                            itemsAdded++; // increment number of items
                        } else {
                            throw new InvalidDeadlineException();
                        }

                    } else if (command.startsWith("event ")) { // description starting index = 6
                        if (command.indexOf(" /from ") != -1 && command.indexOf(" /to ") != -1) {
                            String description = command.substring(6, command.indexOf(" /from "));
                            // from " " to 'from' date is 7
                            String from = command.substring(command.indexOf(" /from ") + 7, command.indexOf(" /to "));
                            // from " " to 'to' date is 5
                            String to = command.substring(command.indexOf(" /to ") + 5);

                            if (description.isEmpty()) {
                                throw new InvalidDescriptionException("event");
                            }

                            if (from.isEmpty() || to.isEmpty()) {
                                throw new InvalidEventException();
                            }

                            taskList.add(new Event(description, from, to)); // add new command

                            System.out.println("Got it. I've added this task:\n" + "  " + taskList.get(itemsAdded)
                                    + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

                            itemsAdded++; // increment number of items
                        } else {
                            throw new InvalidEventException();
                        }

                    } else if (command.startsWith("delete ")) { // number index = 7
                        int taskPos = Integer.parseInt(command.substring(7)) - 1;
                        // convert substring to int, -1 for index
                        if (taskPos >= 0 && taskPos < itemsAdded) {
                            Task deleted = taskList.remove(taskPos);

                            System.out.println("Noted. I've removed this task:\n" + "  " + deleted
                                    + "\nNow you have " + (itemsAdded - 1) + " tasks in the list.");

                            itemsAdded--; //decrement number of items
                        } else {
                            throw new InvalidNumberException();
                        }

                    } else {
                        if (command.startsWith("todo")) {
                            throw new InvalidDescriptionException("todo");
                        } else if (command.startsWith("deadline")) {
                            throw new InvalidDescriptionException("deadline");
                        } else if (command.startsWith("event")) {
                            throw new InvalidDescriptionException("event");
                        } else {
                            throw new InvalidCommandException();
                        }
                    }
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidNumberException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidDescriptionException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidDeadlineException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidEventException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! There is an error: " + e.getMessage());
                }
            }
        }

        sc.close();

    }
}


