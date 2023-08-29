import java.io.IOException;
import java.util.ArrayList;

public class Command {
    private final String input;

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

    public Command(String input) {
        this.input = input;
    }

    public boolean isExit() {
        return input.equals("bye");
    }

    public void execute(TaskList tasks ,DataFile dF) throws IOException {
        try {
            if (input.equals(command.bye.toString())) {
                String exit = "Bye. Hope to see you again soon!";
                System.out.println(exit);
            } else if (input.equals(command.list.toString())) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.getSize(); i++) {
                    System.out.println(i + 1 + "." + tasks.getTask(i));
                }

            } else if (input.contains(command.unmark.toString())) {
                if (tasks.isTaskListEmpty()) {
                    throw new DukeException("0 tasks in the list");
                }
                int selected = Integer.parseInt(input.substring(7)) - 1;
                Task selTask = tasks.getTask(selected);
                selTask.taskNotCompleted();
                System.out.println("OK, I've marked this task as not done yet:\n" + selTask);
                dF.editFileAtLineN(selected, '0');

            } else if (input.contains(command.mark.toString())) {
                if (tasks.isTaskListEmpty()) {
                    throw new DukeException("0 tasks in the list");
                }
                int selected = Integer.parseInt(input.substring(5)) - 1;
                Task selTask = tasks.getTask(selected);
                selTask.taskCompleted();
                System.out.println("Nice! I've marked this task as done:\n" + selTask);
                dF.editFileAtLineN(selected, '1');

            } else if (input.contains(command.deadline.toString())) {
                if (input.equals(command.deadline.toString())) {
                    throw new DukeException("Empty deadline");
                }
                if (input.charAt(8) != ' ') {
                    throw new DukeException("Invalid command");
                }
                if (!input.contains("/by")) {
                    throw new DukeException("Wrong deadline");
                }

                Parser parser = new Parser("D");
                ArrayList<String> texts = parser.convert(input);
                CustomDate cD = new CustomDate();
                Task dl = new Deadline(texts.get(0), cD.strToDateTime(texts.get(1)));
                tasks.addTask(dl);

                System.out.println("Got it. I've added this task:\n" + dl +
                        "\nNow you have " + tasks.getSize() + " tasks in the list.");

                dF.writeToFile(dl);

            } else if (input.contains(command.event.toString())) {
                if (input.equals(command.event.toString())) {
                    throw new DukeException("Empty event");
                }
                if (input.charAt(5) != ' ') {
                    throw new DukeException("Invalid command");
                }
                if (!input.contains("to") || !input.contains("from")) {
                    throw new DukeException("Wrong event");
                }
                Parser parser = new Parser("E");
                ArrayList<String> texts = parser.convert(input);
                CustomDate cD = new CustomDate();
                Task event = new Event(texts.get(0),
                        cD.strToDateTime(texts.get(1)), cD.strToDateTime(texts.get(2)));
                tasks.addTask(event);
                System.out.println("Got it. I've added this task:\n" + event +
                        "\nNow you have " + tasks.getSize()  + " tasks in the list.");
                dF.writeToFile(event);

            } else if (input.contains(command.todo.toString())) {
                if (input.equals(command.todo.toString())) {
                    throw new DukeException("Empty todo");
                }
                if (input.charAt(4) != ' ') {
                    throw new DukeException("Invalid command");
                }
                Parser parser = new Parser("T");
                String desc = parser.convert(input).get(0);
                if (desc.isEmpty() || desc.isBlank()) {
                    throw new DukeException("Empty todo");
                }
                Task todo = new Todo(desc);
                tasks.addTask(todo);
                System.out.println("Got it. I've added this task:\n" + todo +
                        "\nNow you have " + tasks.getSize()  + " tasks in the list.");
                dF.writeToFile(todo);

            } else if (input.contains(command.delete.toString())) {
                int selected = Integer.parseInt(input.substring(input.indexOf("delete") + 7)) - 1;
                Task task = tasks.remTask(selected);
                System.out.println("Noted. I've removed this task:\n" + task +
                        "\nNow you have " + tasks.getSize()  + " tasks in the list.");
                dF.deleteTaskFromFile(selected);

            } else {
                throw new DukeException("Invalid command");
            }
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
                System.out.println("Your event structure is wrong, it is " +
                        "event <your task> /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm");
            } else if (e.getMessage().equals("Wrong deadline")) {
                System.out.println("Your deadline structure is wrong, it is" +
                        " deadline <your task> /by dd/mm/yyyy hhmm");
            } else {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
