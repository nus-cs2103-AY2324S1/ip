import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private enum command {
        bye("bye"),
        list("list"),
        mark("mark"),
        unmark("unmark"),
        deadline("deadline"),
        event("event"),
        todo("todo"),
        delete("delete");

        private final String command;

        command(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return command;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String Start = "Hello! I'm Red\nWhat can I do for you?";
        System.out.println(Start);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals(command.bye.toString())) {
                    String End = "Bye. Hope to see you again soon!";
                    System.out.println(End);
                    break;
                }

                if (input.equals(command.list.toString())) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                    continue;
                }

                if (input.contains(command.unmark.toString())) {
                    int selected = Integer.parseInt(input.substring(7));
                    Task selTask = tasks.get(selected - 1);
                    selTask.taskNotCompleted();
                    System.out.println("OK, I've marked this task as not done yet:\n" + selTask);
                    continue;
                }

                if (input.contains(command.mark.toString())) {
                    int selected = Integer.parseInt(input.substring(5));
                    Task selTask = tasks.get(selected - 1);
                    selTask.taskCompleted();
                    System.out.println("Nice! I've marked this task as done:\n" + selTask);
                    continue;
                }

                if (input.contains(command.deadline.toString())) {
                    if (input.equals(command.deadline.toString())) {
                        throw new DukeException("Empty deadline");
                    }
                    if (input.charAt(8) != ' ') {
                        throw new DukeException("Invalid command");
                    }
                    if (!input.contains("/by")) {
                        throw new DukeException("Wrong deadline");
                    }

                    String desc = input.substring(9, input.indexOf("/by") - 1);
                    String by = input.substring(input.indexOf("/by") + 4);
                    Task dl = new Deadline(desc, by);
                    tasks.add(dl);
                    System.out.println("Got it. I've added this task:\n" + dl +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if (input.contains(command.event.toString())) {
                    if (input.equals(command.event.toString())) {
                        throw new DukeException("Empty event");
                    }
                    if (input.charAt(5) != ' ') {
                        throw new DukeException("Invalid command");
                    }
                    if (!input.contains("to") || !input.contains("from")) {
                        throw new DukeException("Wrong event");
                    }

                    String desc = input.substring(6, input.indexOf("/from") - 1);
                    String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                    String to = input.substring(input.indexOf("/to") + 4);
                    Task event = new Event(desc, from, to);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:\n" + event +
                            "\nNow you have " + tasks.size()  + " tasks in the list.");
                    continue;
                }

                if (input.contains(command.todo.toString())) {
                    if (input.equals(command.todo.toString())) {
                        throw new DukeException("Empty todo");
                    }
                    if (input.charAt(4) != ' ') {
                        throw new DukeException("Invalid command");
                    }
                    String desc = input.substring(input.indexOf("todo") + 5);
                    if (desc.isEmpty() || desc.isBlank()) {
                        throw new DukeException("Empty todo");
                    }
                    Task todo = new Todo(desc);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:\n" + todo +
                            "\nNow you have " + tasks.size()  + " tasks in the list.");
                    continue;
                }

                if (input.contains(command.delete.toString())) {
                    int selected = Integer.parseInt(input.substring(input.indexOf("delete") + 7));
                    Task task = tasks.remove(selected - 1);
                    System.out.println("Noted. I've removed this task:\n" + task +
                            "\nNow you have " + tasks.size()  + " tasks in the list.");
                    continue;
                }

                throw new DukeException("Invalid command");

            } catch (DukeException e) {
                if (e.getMessage().equals("Invalid command")) {
                    System.out.println("You have typed an invalid command");
                } else if (e.getMessage().equals("Empty todo")) {
                    System.out.println("You cannot have an empty todo");
                } else if (e.getMessage().equals("Empty deadline")) {
                    System.out.println("You cannot have an empty deadline");
                } else if (e.getMessage().equals("Empty event")) {
                    System.out.println("You cannot have an empty event");
                } else if (e.getMessage().equals("Wrong event")) {
                    System.out.println("Your event structure is wrong");
                } else if (e.getMessage().equals("Wrong deadline")) {
                    System.out.println("Your deadline structure is wrong");
                }
            }
        }
    }
}
