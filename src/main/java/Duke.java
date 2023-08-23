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
        Task[] tasks = new Task[100];
        String s = "";
        int counter = 0;
        outer: do {
            s = sc.nextLine();
            display_lines();
            switch(s) {
                case "bye":
                    System.out.print("\t");
                    System.out.println("Bye Bye! Hope to see you again soon! 🐶");
                    display_lines();
                    break outer;
                case "list":
                    System.out.print("\t");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.print("\t\t");
                        System.out.println((i + 1) + ". " + tasks[i].getString());
                    }
                    display_lines();
                    break;
                default:
                    if (s.startsWith("mark")) {
                        String markVal = s.substring(s.indexOf(' ') + 1);
                        if (!Character.isDigit(markVal.charAt(0))) {
                            System.out.print("\t");
                            System.out.println("Ruff Ruff! Task numbers to be marked can only be integers! ❌");
                        }
                        else {
                            int index = s.charAt(s.indexOf(' ') + 1) - 48;
                            if (index > counter) {
                                System.out.print("\t");
                                System.out.println("Ruff Ruff! Task numbers to be marked cannot be greater than number of tasks in the list! ❌");
                            }
                            else if (index < 0) {
                                System.out.print("\t");
                                System.out.println("Ruff Ruff! Task numbers to be marked cannot be negative! ❌");
                            }
                            else {
                                tasks[index - 1].markAsDone();
                                System.out.print("\t");
                                System.out.println("Woof Woof! I have marked the task as done.");
                                System.out.print("\t");
                                System.out.println(tasks[index - 1].getString());
                            }
                        }
                    }
                    else if (s.startsWith("unmark")) {
                        String markVal = s.substring(s.indexOf(' ') + 1);
                        if (!Character.isDigit(markVal.charAt(0))) {
                            System.out.print("\t");
                            System.out.println("Ruff Ruff! Task numbers to be unmarked can only be integers! ❌");
                        }
                        else {
                            int index = s.charAt(s.indexOf(' ') + 1) - 48;
                            if (index > counter) {
                                System.out.print("\t");
                                System.out.println("Ruff Ruff! Task numbers to be unmarked cannot be greater than number of tasks in the list! ❌");
                            }
                            else if (index < 0) {
                                System.out.print("\t");
                                System.out.println("Ruff Ruff! Task numbers to be unmarked cannot be negative! ❌");
                            }
                            else {
                                tasks[index - 1].unMark();
                                System.out.print("\t");
                                System.out.println("OK, I have marked the task as not done yet.");
                                System.out.print("\t");
                                System.out.println(tasks[index - 1].getString());
                            }
                        }
                    }
                    else {
                        switch (s.split(" ")[0]) {
                            case "todo": {
                                if (s.split(" ").length == 1) {
                                    System.out.print("\t");
                                    System.out.println("Ruff Ruff! Description of ToDo cannot be empty! ❌");
                                }
                                else {
                                    System.out.print("\t");
                                    System.out.println("Woof. I have added this task:");
                                    String task = s.substring(s.indexOf(' ') + 1);
                                    tasks[counter++] = new ToDo(task);
                                    System.out.println("\t\t" + tasks[counter - 1].getString());
                                    System.out.println("\tNow you have " + counter + (counter == 1 ? " task" : " tasks") + " in your list.");
                                }
                                break;
                            }
                            case "deadline": {
                                if (s.split(" ").length == 1) {
                                    System.out.print("\t");
                                    System.out.println("Ruff Ruff! Description of Deadline cannot be empty! ❌");
                                }
                                else if (!s.contains("/by")) {
                                    System.out.print("\t");
                                    System.out.println("Ruff Ruff! You cannot add a Deadline task without setting the deadline! ❌");
                                }
                                else {
                                    System.out.print("\t");
                                    System.out.println("Woof. I have added this task:");
                                    String task = s.substring(s.indexOf(' ') + 1, s.indexOf('/') - 1);
                                    String by = s.substring(s.lastIndexOf('/') + 4);
                                    tasks[counter++] = new Deadline(task, by);
                                    System.out.println("\t\t" + tasks[counter - 1].getString());
                                    System.out.println("\tNow you have " + counter + (counter == 1 ? " task" : " tasks") + " in your list.");
                                }
                                break;
                            }
                            case "event": {
                                if (s.split(" ").length == 1) {
                                    System.out.print("\t");
                                    System.out.println("Ruff Ruff! Description of ToDo cannot be empty! ❌");
                                }
                                else if (!s.contains("/from") || !s.contains("/to")) {
                                    System.out.print("\t");
                                    System.out.println("Ruff Ruff! You cannot add an Event task without setting start and end time! ❌");
                                }
                                else {
                                    System.out.print("\t");
                                    System.out.println("Woof. I have added this task:");
                                    String task = s.substring(s.indexOf(' ') + 1, s.indexOf('/') - 1);
                                    String from = s.substring(s.indexOf("from") + 5, s.lastIndexOf('/') - 1);
                                    String by = s.substring(s.indexOf("to") + 3);
                                    tasks[counter++] = new Event(task, from, by);
                                    System.out.println("\t\t" + tasks[counter - 1].getString());
                                    System.out.println("\tNow you have " + counter + (counter == 1 ? " task" : " tasks") + " in your list.");
                                }
                                break;
                            }
                            default: {
                                System.out.print("\t");
                                System.out.println("I am sorry, I do not understand what you mean.");
                            }
                        }
                    }
                    display_lines();
                    break;
            }
        } while (true);
    }
}
