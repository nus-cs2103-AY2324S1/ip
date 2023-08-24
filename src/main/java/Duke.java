import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    final static ArrayList<Task> tasks = new ArrayList<>();
    final static String horizontalLine = "   ------------------------\n";
    public static void main(String[] args) {
        printGreetings();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.nextLine();
            System.out.println("\n" + horizontalLine);
            String[] splitText = text.toLowerCase().split(" ");
            String command = splitText[0];

            switch (command) {
                case "bye":
                    System.out.println("     BYE!\n" + horizontalLine);
                    System.exit(0);
                case "list":
                    printList();
                    break;
                case "todo": {
                    String[] splitText2 = text.toLowerCase().split(" ", 2);
                    if (splitText2.length == 2) {
                        tasks.add(new ToDo(splitText2[1]));
                        printTasksLength();
                    } else {
                        System.out.println("     You need to type in a task u silly dog.");
                    }

                    break;
                }
                case "deadline": {
                    String by = getUsingCommand(splitText, "by");
                    String task = getTask(splitText);
                    tasks.add(new Deadline(task, by));
                    printTasksLength();
                    break;
                }
                case "event": {
                    String from = getUsingCommand(splitText, "from");
                    String to = getUsingCommand(splitText, "to");
                    String task = getTask(splitText);
                    tasks.add(new Event(task, from, to));
                    printTasksLength();
                    break;
                }
                case "mark":
                case "unmark": {
                    String[] splitText2 = text.toLowerCase().split(" ", 2);
                    if (splitText2.length == 2) {
                        try {
                            int taskNumber = Integer.parseInt(splitText2[1]);
                            if (taskNumber > 1 && taskNumber <= tasks.size()) {
                                if (command.equals("mark"))
                                    tasks.get(taskNumber - 1).mark();
                                else
                                    tasks.get(taskNumber - 1).unmark();
                            } else {
                                System.out.println("     Enter a number that is within the list: ");
                                printList();
                            }
                        } catch (NumberFormatException ne) {
                            System.out.println("     You need to enter a number");
                            continue;
                        }

                    } else {
                        System.out.println("     You need to type in something u silly dog.");
                    }
                    break;
                }
                default:
                    System.out.println("     I can only understand the following commands: \n"
                            + "     bye, list, mark, unmark, todo, deadline, event");
                    break;
            }
            System.out.println("\n" + horizontalLine);
        }
    }

    private static void printGreetings() {
        System.out.println(horizontalLine
                + "     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A LISTMAKER\n"
                + horizontalLine);
    }
    private static void printList() {
        if (tasks.isEmpty()) {
            System.out.println("     You have no tasks added yet");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("     " + i + ". " + tasks.get(i - 1));
            }
        }
    }

    private static String getUsingCommand(String[] splitText, String command) {
        boolean found = false;
        StringBuilder text = new StringBuilder();
        for (String s : splitText) {
            if (!found) {
                if (s.equals("/" + command)) {
                    found = true;
                }
            } else {
                if (s.charAt(0) == '/')
                    break;
                text.append(" ").append(s);
            }
        }

       if (found && (text.length() > 0)) {
           return text.substring(1);
       } else if (found)
           return "Add something after the command " + command;
       else {
           return "Unable to find Command";
       }
    }

    private static String getTask(String[] splitText) {
        String task = "";
        if (splitText.length > 1) {
            for (int i = 1; i < splitText.length; i++) {
                if (splitText[i].charAt(0) != '/')
                    task = task + " " + splitText[i];
                else
                    break;
            }
            return task.substring(1);
        }
        return "     You need to type in a task :D";
    }

    private static void printTasksLength() {
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

}
