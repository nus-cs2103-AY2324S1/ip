package Commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class HelpCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Sorry, I don't understand what do you mean.");
        System.out.println("Here are some sample usages for your reference:");
        System.out.println("1. Add a deadline task to your list: deadline + (description) + (deadline)");
        System.out.println("2. Add a todo task to your list: todo + (description)");
        System.out.println("3. Add an event task to your list: event + (description) + from + (startdate) + to + (enddate)");
        System.out.println("4. Delete a task: delete + (line number)");
        System.out.println("5. Mark a task: mark + (line number)");
        System.out.println("6. Unmark a class: unmark + (line number)");
        System.out.println("content in ( ) is for you to fill out");
    }
}
