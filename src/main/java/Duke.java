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
        while (true) {
            String userInput = input.nextLine();

            int spaceIndex = userInput.indexOf(" ");
            if (spaceIndex == -1) {
                if (userInput.equals("list")) {
                    System.out.println(line);
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(Integer.toString(i + 1)
                                + ". "
                                + list.get(i));
                    }
                    System.out.println(line + "\n");
                } else if (userInput.equals("bye")) {
                    break;
                }
            } else {
                switch (userInput.substring(0, spaceIndex)) {
                    case "todo":
                        list.add(new Todo(userInput.substring(spaceIndex + 1)));
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
                        String DeadlineDesc = userInput.substring(spaceIndex + 1, byIndex - 1);
                        String by = userInput.substring(byIndex + 4);
                        list.add(new Deadline(DeadlineDesc, by));
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
                }
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