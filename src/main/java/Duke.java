import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void display_lines() {
        for (int i = 0; i < 20; i++) {
            System.out.print((i==0 ? "-" : " -"));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        display_lines();
        String logo = "                ;~~,__,\n" +
                ":-….,———-‘`----/   ._.*\n" +
                " `-,,,   BRUNO   ,’\n" +
                "     ;   ,~.——;  /\n" +
                "     :  |     :  |\n" +
                "     `_ ’     `_ ‘";
        System.out.println(logo);
        String name = "Bruno";
        System.out.println("Woof Woof! I'm " + name + " 🐾");
        System.out.println("How can I help you?");
        display_lines();
        List<Task> list = new ArrayList<>();
        String s = "";
        outer: do {
            s = sc.nextLine();
            display_lines();
            try {
                switch (s) {
                    case "bye":
                        System.out.print("\t");
                        System.out.println("Bye Bye! Hope to see you again soon! 🐶");
                        display_lines();
                        break outer;
                    case "list":
                        System.out.print("\t");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.print("\t\t");
                            System.out.println((i + 1) + ". " + list.get(i).getString());
                        }
                        display_lines();
                        break;
                    default:
                        if (s.startsWith("mark")) {
                            String markVal = s.substring(s.indexOf(' ') + 1);
                            if (!Character.isDigit(markVal.charAt(0))) {
                                System.out.print("\t");
                                throw new DukeIntegerMismatchException("mark");
                            } else {
                                int index = s.charAt(s.indexOf(' ') + 1) - 48;
                                if (index > list.size()) {
                                    System.out.print("\t");
                                    throw new DukeIndexOutOfBoundsException("mark");
                                } else if (index < 0) {
                                    System.out.print("\t");
                                    throw new DukeNegativeArgException("mark");
                                } else {
                                    list.get(index - 1).markAsDone();
                                    System.out.print("\t");
                                    System.out.println("Woof Woof! I have marked the task as done.");
                                    System.out.print("\t");
                                    System.out.println(list.get(index - 1).getString());
                                }
                            }
                        } else if (s.startsWith("unmark")) {
                            String markVal = s.substring(s.indexOf(' ') + 1);
                            if (!Character.isDigit(markVal.charAt(0))) {
                                System.out.print("\t");
                                throw new DukeIntegerMismatchException("unmark");
                            } else {
                                int index = s.charAt(s.indexOf(' ') + 1) - 48;
                                if (index > list.size()) {
                                    System.out.print("\t");
                                    throw new DukeIndexOutOfBoundsException("unmark");
                                } else if (index < 0) {
                                    System.out.print("\t");
                                    throw new DukeNegativeArgException("unmark");
                                } else {
                                    list.get(index - 1).unMark();
                                    System.out.print("\t");
                                    System.out.println("OK, I have marked the task as not done yet.");
                                    System.out.print("\t");
                                    System.out.println(list.get(index - 1).getString());
                                }
                            }
                        } else if (s.startsWith("delete")) {
                            String markVal = s.substring(s.indexOf(' ') + 1);
                            if (!Character.isDigit(markVal.charAt(0))) {
                                System.out.print("\t");
                                throw new DukeIntegerMismatchException("delete");
                            } else {
                                int index = s.charAt(s.indexOf(' ') + 1) - 48;
                                if (index > list.size()) {
                                    System.out.print("\t");
                                    throw new DukeIndexOutOfBoundsException("delete");
                                } else if (index < 0) {
                                    System.out.print("\t");
                                    throw new DukeNegativeArgException("delete");
                                } else {
                                    String s1 = list.get(index - 1).getString();
                                    list.remove(index - 1);
                                    System.out.print("\t");
                                    System.out.println("I have removed this task from your list:");
                                    System.out.print("\t");
                                    System.out.println(s1);
                                    System.out.println("\tNow you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list.");
                                }
                            }
                        } else {
                            TaskType type = TaskType.valueOf(s.split(" ")[0].toUpperCase());
                            switch (type) {
                                case TODO: {
                                    if (s.split(" ").length == 1) {
                                        System.out.print("\t");
                                        throw new DukeEmptyException(s.split(" ")[0]);
                                    } else {
                                        System.out.print("\t");
                                        System.out.println("Woof. I have added this task:");
                                        String task = s.substring(s.indexOf(' ') + 1);
                                        list.add(new ToDo(task));
                                        System.out.println("\t\t" + list.get(list.size() - 1).getString());
                                        System.out.println("\tNow you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list.");
                                    }
                                    break;
                                }
                                case DEADLINE: {
                                    if (s.split(" ").length == 1) {
                                        System.out.print("\t");
                                        throw new DukeEmptyException(s.split(" ")[0]);
                                    } else if (!s.contains("/by")) {
                                        System.out.print("\t");
                                        throw new DukeMissingDeadlineException();
                                    } else {
                                        System.out.print("\t");
                                        System.out.println("Woof. I have added this task:");
                                        String task = s.substring(s.indexOf(' ') + 1, s.indexOf('/') - 1);
                                        String by = s.substring(s.lastIndexOf('/') + 4);
                                        list.add(new Deadline(task, by));
                                        System.out.println("\t\t" + list.get(list.size() - 1).getString());
                                        System.out.println("\tNow you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list.");
                                    }
                                    break;
                                }
                                case EVENT: {
                                    if (s.split(" ").length == 1) {
                                        System.out.print("\t");
                                        throw new DukeEmptyException(s.split(" ")[0]);
                                    } else if (!s.contains("/from") || !s.contains("/to")) {
                                        System.out.print("\t");
                                        throw new DukeMissingEventException();
                                    } else {
                                        System.out.print("\t");
                                        System.out.println("Woof. I have added this task:");
                                        String task = s.substring(s.indexOf(' ') + 1, s.indexOf('/') - 1);
                                        String from = s.substring(s.indexOf("from") + 5, s.lastIndexOf('/') - 1);
                                        String by = s.substring(s.indexOf("to") + 3);
                                        list.add(new Event(task, from, by));
                                        System.out.println("\t\t" + list.get(list.size() - 1).getString());
                                        System.out.println("\tNow you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list.");
                                    }
                                    break;
                                }
                                default: {
                                    System.out.print("\t");
                                    throw new DukeUnknownTaskException();
                                }
                            }
                        }
                        display_lines();
                        break;
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
                display_lines();
            }
        } while (true);
    }
}
