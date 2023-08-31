package jerma.utils;

import jerma.commands.AddTask;
import jerma.commands.Bye;
import jerma.commands.Command;
import jerma.commands.Delete;
import jerma.commands.List;
import jerma.commands.Mark;
import jerma.commands.Unmark;
import jerma.tasks.Deadline;
import jerma.tasks.Event;
import jerma.tasks.Task;
import jerma.tasks.Todo;

enum Keywords {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    public static Keywords parse(String input) {
        for (Keywords command : Keywords.values()) {
            if (command.toString().toLowerCase().equals(input)) {
                return command;
            }
        }
        throw new UnsupportedOperationException();
    }
}

public class Parser {

    public static Command parse(String input, Ui ui, TaskList tasks,
            Boolean[] running) {
        String[] inputArgs = input.split(" ", 2);
        Keywords command = Keywords.parse(inputArgs[0]);

        switch (command) {
        case LIST:
            return new List(ui, tasks);
        case BYE:
            return new Bye(ui, tasks, running);
        case MARK:
            int index = Integer.parseInt(inputArgs[1]);
            return new Mark(ui, tasks, index);
        case UNMARK:
            index = Integer.parseInt(inputArgs[1]);
            return new Unmark(ui, tasks, index);
        case DELETE:
            index = Integer.parseInt(inputArgs[1]);
            return new Delete(ui, tasks, index);
        case TODO:
            Task task = new Todo(inputArgs[1]);
            return new AddTask(ui, tasks, task);
        case DEADLINE:
            String[] split = inputArgs[1].split(" /by ", 2);
            String description = split[0];
            String by = split[1];

            task = new Deadline(description, by);
            return new AddTask(ui, tasks, task);
        case EVENT:
            String[] split1 = inputArgs[1].split(" /from ", 2);
            String[] split2 = split1[1].split(" /to ", 2);
            description = split1[0];
            String from = split2[0];
            String to = split2[1];

            task = new Event(description, from, to);
            return new AddTask(ui, tasks, task);
        default:
        }

        throw new UnsupportedOperationException();
    }
}
