package duke;

import duke.Deadline;
import duke.DukeException;

public class Parser {

    public static void parse(String command, TaskList list, Storage storage, Ui ui) {
        if (command.toLowerCase().startsWith("delete")) {
            String[] sub = command.split(" ");

            if (sub.length == 2) {
                int number = Integer.parseInt(sub[1]) - 1;

                if (number < list.getSize() && number >= 0) {
                    Task removedTask = list.delete(number);

                    // print
                    ui.removeTask(list, removedTask);
                } else {
                    try {
                        throw new DukeException("OOPS!!! The task you entered is not in the list");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                try {
                    throw new DukeException("OOPS!!! Please fill in the task I need to delete");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            // list tasks
        } else if (command.equalsIgnoreCase("list")) {
            ui.printList(list);

            // mark task as done
        } else if (command.toLowerCase().startsWith("mark")) {
            String[] sub = command.split(" ");

            if (sub.length == 2) {
                int number = Integer.parseInt(sub[1]) - 1;

                if (number < list.getSize() && number >= 0) {
                    list.getTask(number).markAsDone();

                    // print
                    ui.mark(list, number);
                } else {
                    try {
                        throw new DukeException("OOPS!!! The task you entered is not in the list");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                try {
                    throw new DukeException("OOPS!!! Please fill in the task I need to mark");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            // unMark task
        } else if (command.toLowerCase().startsWith("unmark")) {
            String[] sub = command.split(" ");

            if (sub.length == 2) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;

                if (number < list.getSize() && number >= 0) {
                    list.getTask(number).markAsNotDone();

                    // print
                    ui.unMark(list, number);
                } else {
                    try {
                        throw new DukeException("OOPS!!! The task you entered is not in the list");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                try {
                    throw new DukeException("OOPS!!! Please fill in the task I need to unmark");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

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

                if (sub.length == 2) {

                    String description = sub[0].trim();
                    String by = sub[1].trim();

                    Deadline newDeadline = new Deadline(description, by);
                    list.add(newDeadline);

                    // print
                    ui.addDeadline(list, newDeadline);
                } else {
                    try {
                        throw new DukeException("OOPS!!! Please fill in the deadline");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
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

                if (sub.length == 2) {
                    String description = sub[0].trim();
                    String timing = sub[1].trim();

                    // separate start time and end time
                    String[] fromTo = timing.split("/to");

                    if (fromTo.length == 2) {
                        String from = fromTo[0].trim();
                        String to = fromTo[1].trim();

                        Event newEvent = new Event(description, from, to);
                        list.add(newEvent);

                        // print
                        ui.addEvent(list, newEvent);
                    } else {
                        try {
                            throw new DukeException("OOPS!!! Please fill in the time the event ends");
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    try {
                        throw new DukeException("OOPS!!! Please fill in the timings");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
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
