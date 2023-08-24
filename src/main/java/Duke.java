import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        String line = "──────────────────────────────────────────────────────────────────────────";
        String logo = " _____   __                 _____ _           _   _           _  ___\n"
                + "|  _\\ \\ / /                /  __ \\ |         | | | |         | ||_  |\n"
                + "| |  \\ V /___  _   _ _ __  | /  \\/ |__   __ _| |_| |__   ___ | |_ | |\n"
                + "| |   \\ // _ \\| | | | '__| | |   | '_ \\ / _` | __| '_ \\ / _ \\| __|| |\n"
                + "| |   | | (_) | |_| | |    | \\__/\\ | | | (_| | |_| |_) | (_) | |_ | |\n"
                + "| |_  \\_/\\___/ \\__,_|_|     \\____/_| |_|\\__,_|\\__|_.__/ \\___/ \\__|| |\n"
                + "|___|                                                           |___|\n";

        String greet = line
                + "\n"
                + "Hello! I'm\n"
                + logo
                + "What can I do for you?\n"
                + line
                + "\n";
        System.out.println(greet);
        Scanner input = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();
        boolean end = false;
        while (!end) {
            try {
                String userInput = input.nextLine();

                int spaceIndex = userInput.indexOf(" ");
                if (spaceIndex == -1) {
                    switch (userInput) {
                        case "list":
                            System.out.println(line);
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println(Integer.toString(i + 1)
                                        + ". "
                                        + list.get(i));
                            }
                            System.out.println(line + "\n");
                            break;
                        case "bye":
                            end = true;
                            break;
                        case "todo":
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        case "event":
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        case "deadline":
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    switch (userInput.substring(0, spaceIndex)) {
                        case "todo":
                            String todoDesc = userInput.substring(spaceIndex + 1);
                            if (todoDesc.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            list.add(new Todo(todoDesc));
                            System.out.println(line
                                    + "\n"
                                    + "Got it. I've added this task:\n"
                                    + list.get(list.size() - 1)
                                    + "\n"
                                    + "Now you have " + list.size() + " tasks in the list.\n"
                                    + line
                                    + "\n");
                            break;
                        case "event":
                            int fromIndex = userInput.indexOf("/from");
                            int toIndex = userInput.indexOf("/to");
                            String eventDesc = userInput.substring(spaceIndex + 1, fromIndex - 1);
                            if (eventDesc.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                            }
                            String from = userInput.substring(fromIndex + 6, toIndex - 1);
                            String to = userInput.substring(toIndex + 4);
                            list.add(new Event(eventDesc, from, to));
                            System.out.println(line
                                    + "\n"
                                    + "Got it. I've added this task:\n"
                                    + list.get(list.size() - 1)
                                    + "\n"
                                    + "Now you have " + list.size() + " tasks in the list.\n"
                                    + line
                                    + "\n");
                            break;
                        case "deadline":
                            int byIndex = userInput.indexOf("/by");
                            String deadlineDesc = userInput.substring(spaceIndex + 1, byIndex - 1);
                            if (deadlineDesc.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                            String by = userInput.substring(byIndex + 4);
                            list.add(new Deadline(deadlineDesc, by));
                            System.out.println(line
                                    + "\n"
                                    + "Got it. I've added this task:\n"
                                    + list.get(list.size() - 1)
                                    + "\n"
                                    + "Now you have " + list.size() + " tasks in the list.\n"
                                    + line
                                    + "\n");
                            break;
                        case "mark":
                            int i = Integer.parseInt(userInput.split(" ", 2)[1]);
                            list.get(i - 1).markAsDone();
                            System.out.println(line
                                    + "\n"
                                    + "Nice! I've marked this task as done:\n"
                                    + list.get(i - 1)
                                    + "\n"
                                    + line
                                    + "\n");
                            break;
                        case "unmark":
                            int j = Integer.parseInt(userInput.split(" ", 2)[1]);
                            list.get(j - 1).markAsNotDone();
                            System.out.println(line + "\n"
                                    + "OK, I've marked this task as not done yet:\n"
                                    + list.get(j - 1)
                                    + "\n"
                                    + line);
                            break;
                        case "delete":
                            int k = Integer.parseInt(userInput.split(" ", 2)[1]);
                            Task deletedTask = list.get(k - 1);
                            list.remove(k - 1);
                            System.out.println(line + "\n"
                                    + "Noted. I've removed this task:\n"
                                    + deletedTask
                                    + "\n"
                                    + "Now you have " + list.size() + " tasks in the list.\n"
                                    + line
                                    + "\n");

                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line + "\n");

            }



        }

        String sendOff = line
                + "\n"
                + "Bye. Hope to see you again soon!\n"
                + line
                + "\n";
        System.out.println(sendOff);
    }
}