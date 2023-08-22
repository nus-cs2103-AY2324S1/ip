import java.util.Scanner;

public class Dook {
    public static String name = "Dook";
    private enum Command{
        bye("Exits the program."), list("Displays the current tasks"),
        mark("Marks selected task as done."), unmark("Marks selected task as undone."),
        todo("Adds a task."), deadline("Adds a task with a deadline."),
        event("Adds a task with a start and end time."), delete("Deletes selected task from list."),
        invalid("You entered an invalid command.");

        private final String desc;

        Command(String desc) {
            this.desc = desc;
        }
        @Override
        public String toString() {
            return this.name() + ": " + this.desc;
        }

    }
    private static Command parseKeyword(String keyword) {
         try {
            return Command.valueOf(keyword);
         } catch (IllegalArgumentException e){
            return Command.invalid;
         }
    }

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();

            String[] tmp = input.split(" ", 2);
            Command command = parseKeyword(tmp[0].trim());
            String body = tmp.length == 1 ? "" : tmp[1].trim();

            try {
                switch (command) {
                    case bye:
                        bidFarewell();
                        return;
                    case list:
                        displayList();
                        break;
                    case mark:
                        handleMark(body, true);
                        break;
                    case unmark:
                        handleMark(body, false);
                        break;
                    case todo:
                        handleToDo(body);
                        break;
                    case deadline:
                        handleDeadline(body);
                        break;
                    case event:
                        handleEvent(body);
                        break;
                    case delete:
                        handleDelete(body);
                        break;
                    case invalid:
                        displayHelp();
                        break;
                }
            } catch (DookException e) {
                printMessage(e.getMessage());
            }

        }
    }
    private static void displayHelp() {
        StringBuilder result = new StringBuilder();
        result.append("Available commands:\n");
        for (Command c : Command.values()) {
            result.append(c.toString() + "\n");
        }
        printMessage(result.toString());
    }
    public static DookList dookList = new DookList();
    private static void handleToDo(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException(String.format("Usage: todo [name]"));
        }
        Task task = new Todo(body.trim());
        addToTaskList(task);
    }
    private static void handleDeadline(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException(String.format("Usage: deadline [name] /by [time]."));
        }

        String[] tmp = body.split("/by", 2);
        if (tmp.length <= 1) {
            throw new DookException(String.format("Usage: deadline [name] /by [time]."));
        }

        String desc = tmp[0].trim();
        String by = tmp[1].trim();
        if (desc.isBlank() || by.isBlank()) {
            throw new DookException(String.format("Some information is missing!\n" +
                    "Usage: deadline [name] /by [time]."));
        }
        Task task = new Deadline(desc, by);
        addToTaskList(task);
    }
    private static void handleEvent(String body) throws DookException{
        if (body.isBlank()) {
            throw new DookException(String.format("Usage: event [name] /from [start] /to [end]."));
        }
        String[] tmp1 = body.split("/from", 2);
        if (tmp1.length <= 1) {
            throw new DookException(String.format("Usage: event [name] /from [start] /to [end]."));
        }

        String desc = tmp1[0].trim();

        String[] tmp2 = tmp1[1].split("/to", 2);
        if (tmp2.length <= 1) {
            throw new DookException(String.format("Usage: event [name] /from [start] /to [end]."));
        }
        String from = tmp2[0].trim();
        String to = tmp2[1].trim();
        if (desc.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DookException(String.format("Some information is missing!\n" +
                    "Usage: event [name] /from [start] /to [end]."));
        }

        Task task = new Event(desc, from, to);
        addToTaskList(task);
    }
    private static void addToTaskList(Task task) {
        printMessage(dookList.addTask(task));
    }
    private static void displayList() {
        printMessage(dookList.toString());
    }
    private static void handleMark(String body, boolean value) throws DookException{
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            throw new DookException(String.format("Usage: %s [task number]", value ? "mark" : "unmark"));
        }
        printMessage(dookList.markTask(index, value));
    }
    private static void handleDelete(String body) throws DookException{
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            throw new DookException(String.format("Usage: delete [task number]"));
        }
        printMessage(dookList.deleteTask(index));
    }
    private static void greetUser() {
        printMessage(String.format("%s here.\nWhat can I do for you?", name));
    }
    public static void printMessage(String msg) {
        printDivider();
        System.out.println(msg);
        printDivider();
    }
    public static void printDivider() {
        System.out.println("_______________________________________");
    }
    private static void bidFarewell() {
        printMessage("Goodbye.");
    }
}
