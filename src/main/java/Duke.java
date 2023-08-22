import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    private static String name = "Chewbacca";

    private static List<Task> list = new ArrayList<>();

    private static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);

        start();

    }

    private static String drawLine() {
        char horizontal_line = '\u2500';
        String line = "";
        for (int i = 0; i < 50; i++)
            line += horizontal_line;
        return line;
    }
    private static void start() {

        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, I am " + name + ", son of Attichitcuk");
        System.out.println("How can Chewie help?\n");
        System.out.println(drawLine());
        readInput();

    }

    private static void end() {
        System.out.println(drawLine());
        System.out.println("Chewie is going home now.\nBye bye.\n");
        System.out.println(drawLine());

    }

    private static void echo(String s) {
        System.out.println(drawLine());
        System.out.println(s + "\n");
        System.out.println(drawLine());
    }

    private static void readInput() {

        String command = scanner.next();

        try {
            switch (command) {
                case "bye":
                    end();
                    break;
                case "list":
                    readList();
                    readInput();
                    break;
                case "mark":
                    if (!scanner.hasNextInt())
                        throw new DukeException("Chewie doesn't see the index of task list.");

                    int i = scanner.nextInt();
                    if (i < 1 || i > list.size())
                        throw new DukeException("The list doesn't have this index.");

                    mark(i);
                    readInput();
                    break;
                case "unmark":
                    if (!scanner.hasNextInt())
                        throw new DukeException("Chewie doesn't see the index of task list.");

                    int k = scanner.nextInt();
                    if (k < 1 || k > list.size())
                        throw new DukeException("The list doesn't have this index.");

                    unmark(k);
                    readInput();
                    break;
                case "delete":
                    if (!scanner.hasNextInt())
                        throw new DukeException("Chewie doesn't see the index of task list.");

                    int j = scanner.nextInt();
                    if (j < 1 || j > list.size())
                        throw new DukeException("The list doesn't have this index.");

                    delete(j);
                    readInput();
                    break;
                case "deadline":
                    String deadlinePrompt = scanner.nextLine();
                    if (deadlinePrompt.isBlank())
                        throw new DukeException("Chewie says deadline's description cannot be empty.");

                    String[] deadlineRemain = deadlinePrompt.split(" /by ");
                    if (deadlineRemain.length != 2 || deadlineRemain[0].isBlank() || deadlineRemain[1].isBlank())
                        throw new DukeException("Chewie says deadline's description is wrong.");

                    String task = deadlineRemain[0];
                    String date = deadlineRemain[1];
                    createDeadline(task, date);
                    readInput();
                    break;
                case "todo":
                    String ToDoRemain = scanner.nextLine();
                    if (ToDoRemain.isBlank()) {
                        throw new DukeException("Chewie says todo's description cannot be empty.");
                    }

                    createToDo(ToDoRemain);
                    readInput();
                    break;
                case "event":
                    String eventPrompt = scanner.nextLine();
                    if (eventPrompt.isBlank())
                        throw new DukeException("Chewie says event's description cannot be empty.");

                    String[] eventRemain = eventPrompt.split(" /from ");
                    if (eventRemain.length != 2 || eventRemain[0].isBlank())
                        throw new DukeException("Chewie says event's description is wrong.");

                    String eventTask = eventRemain[0];
                    String[] eventDate = eventRemain[1].split(" /to ");
                    if (eventDate.length !=2)
                        throw new DukeException("Chewie says event's time is wrong.");

                    String startDate = eventDate[0];
                    String endDate = eventDate[1];
                    if (startDate.isBlank() || endDate.isBlank())
                        throw new DukeException("Chewie says event's time is wrong.");

                    createEvent(eventTask, startDate, endDate);
                    readInput();
                    break;
                default:
                    throw new DukeException("Chewie doesn't recgonize this command: " + command);
            }
        } catch (DukeException e) {
            System.out.println(drawLine());
            System.out.println(e.getMessage());
            System.out.println("\n" + drawLine());
        } finally {
            scanner.nextLine();
            readInput();
        }
    }

    private static void createToDo(String task) {
        ToDo td = new ToDo(task);
        list.add(td);
        System.out.println(drawLine());
        System.out.println("Chewie gotcha, task added:\n" + td.status() + task);
        System.out.println("Chewie now find " + list.size() + " tasks in the list" + "\n");
        System.out.println(drawLine());
    }

    private static void createDeadline(String task, String date) {
        Deadline dl = new Deadline(task,date);
        list.add(dl);
        System.out.println(drawLine());
        System.out.println("Chewie gotcha, task added:\n" + dl.status() + dl.taskName());
        System.out.println("Chewie now find " + list.size() + " tasks in the list" + "\n");
        System.out.println(drawLine());
    }

    private static void createEvent(String task, String start, String end) {
        Events ev = new Events(task,start,end);
        list.add(ev);
        System.out.println(drawLine());
        System.out.println("Chewie gotcha, task added:\n" + ev.status() + ev.taskName());
        System.out.println("Chewie now find " + list.size() + " tasks in the list" + "\n");
        System.out.println(drawLine());
    }

    private static void readList() {
        System.out.println(drawLine());
        System.out.println("Chewie found your task list:");
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            Task task = list.get(i);

            System.out.println(index + "." + task.status() + task.taskName());
        }
        System.out.println("\n" + drawLine());
    }

    private static void mark(int i) {
        Task task = list.get(i-1);
        task.mark();
        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, Chewie has marked the task.");
        System.out.println(task.status() + task.taskName());
        System.out.println("\n" + drawLine());
    }

    private static void unmark(int i) {
        Task task = list.get(i-1);
        task.unmark();
        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, Chewie has unmarked the task.");
        System.out.println(task.status() + task.taskName());
        System.out.println("\n" + drawLine());
    }

    private static void delete(int i) {
        Task task = list.get(i-1);
        list.remove(i-1);
        System.out.println(drawLine());
        System.out.println("Chewie gotcha, task removed:\n" + task.status() + task.taskName());
        System.out.println("Chewie now find " + list.size() + " tasks in the list" + "\n");
        System.out.println(drawLine());
    }
}
