import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Command {

    private String command;
    private String details;
    private boolean isValidDetail;

    public Command(String command, String details) {

        this.command = command;
        if (details.equals("")) {
            this.isValidDetail = false;
        } else {
            this.isValidDetail = true;
        }
        this.details = details;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchCommandException, InvalidIndexException {

        try {
            if (command.equals("list")) {
                tasks.listAllTask();
            } else if (command.equals("mark")) {

                if (!isValidDetail) {
                    throw new InvalidIndexException();
                }
                int index = Integer.parseInt(details);
                if (index > 0 && index <= tasks.size()) {
                    mark(index, tasks, storage);
                } else {
                    throw new InvalidIndexException();
                }
            } else if (command.equals("delete")) {

                if (!isValidDetail) {
                    throw new InvalidIndexException();
                }
                int index = Integer.parseInt(details);
                if (index > 0 && index <= tasks.size()) {
                    delete(index, tasks, storage);
                } else {
                    throw new InvalidIndexException();
                }
            } else if (command.equals("unmark")) {

                if (!isValidDetail) {
                    throw new InvalidIndexException();
                }
                int index = Integer.parseInt(details);
                if (index > 0 && index <= tasks.size()) {
                    unmark(index, tasks, storage);
                } else {
                    throw new InvalidIndexException();
                }
            } else if (command.equals("todo")) {
                addTodo(details, tasks, storage);
            } else if (command.equals("deadline")) {
                addDeadline(details, tasks, storage);
            } else if (command.equals("event")) {
                addEvent(details, tasks, storage);
            } else if (command.equals("due")) {
                checkTaskDue(details, tasks);
            } else if (command.equals("bye")) {
                isExit();
            } else {
                throw new NoSuchCommandException();
            }
        } catch (NoSuchCommandException e) {
            System.out.println(e);
        } catch (UnmatchedArgumentException e) {
            System.out.println(e);
        } catch (InvalidIndexException e) {
            System.out.println(e);
        } catch (EmptyDescriptionException e) {
            System.out.println(e);
        } catch (NumberFormatException | StringIndexOutOfBoundsException | DateTimeException e) {

            System.out.println(Ui.showLine());
            System.out.println("\tPlease enter a proper date.");
            System.out.println("\t" + e.getMessage());
            System.out.println();
            System.out.println(Ui.showLine());
        }
    }

    public static void mark(int i, TaskList tasks, Storage storage) {

        tasks.get(i - 1).mark();
        System.out.println(Ui.showLine());
        System.out.println(" \tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.get(i - 1).toString());
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    public static void unmark(int i, TaskList tasks, Storage storage) {

        tasks.get(i - 1).unmark();
        System.out.println(Ui.showLine());
        System.out.println(" \tOk! I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.get(i - 1).toString());
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    public static void addTodo(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException {

        if (message.isBlank()) {
            throw new UnmatchedArgumentException(0, 1);
        }
        System.out.println(Ui.showLine());
        System.out.println("\tGot it. I've added this task: ");
        Task todo = new Todo(message, false);
        tasks.add(todo);
        System.out.println("\t  " + todo);
        System.out.println("\tNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    public static void addDeadline(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException, EmptyDescriptionException {

        String[] arr = message.split(" /");
        Parser p = new Parser();
        if (arr.length < 2) {
            throw new UnmatchedArgumentException(arr.length, 2);
        }
        if (arr[0].isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        String dateAndTime = arr[1].substring(3).replace(" ", "/");
        Deadline dl = new Deadline(arr[0], false, p.checkDateAndTime(dateAndTime));
        tasks.add(dl);
        System.out.println(Ui.showLine());
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + dl);
        System.out.println("\tNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    public static void addEvent(String message, TaskList tasks, Storage storage) throws UnmatchedArgumentException, EmptyDescriptionException {

        String[] arr = message.split(" /");
        Parser p = new Parser();
        if (arr.length < 3) {
            throw new UnmatchedArgumentException(arr.length, 3);
        }
        if (arr[0].isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        LocalDateTime start = p.checkDateAndTime(arr[1].substring(5).replace(" ", "/"));
        LocalDateTime end = p.checkDateAndTime(arr[2].substring(3).replace(" ", "/"));
        if (end.isBefore(start) || end.isEqual(start)) {
            throw new DateTimeException("The end is date should not be earlier or the same as the start date.");
        }
        Event e = new Event(arr[0], false, start, end);
        tasks.add(e);
        System.out.println(Ui.showLine());
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + e);
        System.out.println("\tNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    public static void delete(int index, TaskList tasks, Storage storage) {

        System.out.println(Ui.showLine());
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("\tNow that you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.");
        System.out.println();
        System.out.println(Ui.showLine());
        storage.writeInto(tasks);
    }

    public static void checkTaskDue(String dueDate, TaskList tasks) {

        ArrayList<Task> dueDateList = new ArrayList<>();
        Parser p = new Parser();
        LocalDateTime date = p.checkDateAndTime(dueDate.replace(" ", "/"));

        for (Task t : tasks.getTasks()) {
            if (t instanceof Deadline &&
                    ((Deadline) t).dueDate.isEqual(date)){
                dueDateList.add(t);
            } else if (t instanceof Event &&
                    (((Event) t).start.isEqual(date) ||
                            ((Event) t).end.isEqual(date))) {
                dueDateList.add(t);
            }
        }
        System.out.println(Ui.showLine());
        System.out.println("\tHere are the " + (dueDateList.size() > 1 ? "tasks that contain" : "task that contains")
                + " the date:");
        for (Task t : dueDateList) {
            System.out.println("\t\t" + t);
        }
        System.out.println(Ui.showLine());
        System.out.println();
    }

    public boolean isExit() {
        return (command.equals("bye"));
    }
}
