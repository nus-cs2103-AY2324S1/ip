package seedu;

public class Parser {
    public static Command parse(String command, Ui ui, Storage storage, TaskList tasks) {
        if(command.equals("bye")) {
            return new Command(command, ui, storage, tasks);
        } else if (command.equals("list")) {
            return new Command(command, ui, storage, tasks);
        } else if (command.startsWith("delete ")) {
            String[] s = command.split(" ");
            int removeIndex = Integer.parseInt(s[1]) - 1;
            return new Command("delete", ui, storage, tasks, removeIndex);
        } else if (command.startsWith("mark ")) {
            int current = Integer.parseInt(command.substring(5)) - 1;
            return new Command("mark", ui, storage, tasks, current);
        } else if (command.startsWith("find ")) {
            return new Command(command, ui, storage, tasks);
        } else {
            return new Command(command, ui, storage, tasks);
        }
    }


}
