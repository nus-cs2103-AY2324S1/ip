package duke;

import duke.Deadline;
import duke.DukeException;

public class Parser {

    public static void parse(String command, TaskList list, Storage storage, Ui ui) {
        if (command.toLowerCase().startsWith("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;
            Task removedTask = list.delete(number);

            // print
            ui.removeTask(list, removedTask);

            // list tasks
        } else if (command.equalsIgnoreCase("list")) {
            ui.printList(list);

            // mark task as done
        } else if (command.toLowerCase().startsWith("mark")) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;
            list.getTask(number).markAsDone();

            // print
            ui.mark(list, number);

            // unMark task
        } else if (command.toLowerCase().startsWith("unmark")) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;
            list.getTask(number).markAsNotDone();

            // print
            ui.unMark(list, number);

            // add todo
        } else if (command.toLowerCase().startsWith("todo")) {
            String todo = command.substring(4).trim();
            if (todo.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Todo newTodo = new Todo(todo);
                list.add(newTodo);

                //print
                ui.addTodo(list, newTodo);
            }

            // add deadline
        } else if (command.toLowerCase().startsWith("deadline")) {
            String deadline = command.substring(8).trim();

            if (deadline.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                // separate the task and its deadline
                String[] sub = deadline.split("/by");

                String description = sub[0].trim();
                String by = sub[1].trim();

                Deadline newDeadline = new Deadline(description, by);
                list.add(newDeadline);

                // print
                ui.addDeadline(list, newDeadline);
            }

            // add event
        } else if (command.toLowerCase().startsWith("event")) {
            String event = command.substring(5).trim();

            if (event.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                // separate event and timing
                String[] sub = event.split("/from");
                String description = sub[0].trim();
                String timing = sub[1].trim();

                // separate start time and end time
                String[] fromTo = timing.split("/to");
                String from = fromTo[0].trim();
                String to = fromTo[1].trim();

                Event newEvent = new Event(description, from, to);
                list.add(newEvent);

                // print
                ui.addEvent(list, newEvent);
            }

        } else {
            try {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        storage.save(list);
    }

}
