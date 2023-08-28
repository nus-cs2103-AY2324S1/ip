import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {
    private Storage storage;

    private TaskList lst;

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Parser(Storage storage, ArrayList<Task> taskList) {
        this.storage = storage;
        this.lst = new TaskList(taskList);
    }

    public void parse(String input) throws DukeException {
        if (input.equals("list")) {
            this.printList();
        } else if (input.startsWith("mark")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("mark");
            }

            String[] parsedString = input.split(" ");
            try {
                int num = Integer.parseInt(parsedString[1]);
                if (num > lst.size() || num <= 0) {
                    throw new DukeInvalidIndexException(lst.size());
                }
                Task selectedTask = lst.get(num - 1);
                this.markCompletion(selectedTask, num);
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(lst.size());
            }
        } else if (input.startsWith("unmark")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("unmark");
            }

            String[] parsedString = input.split(" ");
            try {
                int num = Integer.parseInt(parsedString[1]);
                if (num > lst.size() || num <= 0) {
                    throw new DukeInvalidIndexException(lst.size());
                }
                Task selectedTask = lst.get(num - 1);
                this.unmarkCompletion(selectedTask, num);
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(lst.size());
            }
        } else if (input.startsWith("delete")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("delete");
            }

            String[] parsedString = input.split(" ");
            try {
                int num = Integer.parseInt(parsedString[1]);
                if (num > lst.size() || num <= 0) {
                    throw new DukeInvalidIndexException(lst.size());
                }
                this.deleteTask(num);
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(lst.size());
            }

        } else {
            Parser.TaskType taskType;
            if (input.startsWith("todo")) {
                taskType = Parser.TaskType.TODO;
            } else if (input.startsWith("deadline")) {
                taskType = Parser.TaskType.DEADLINE;
            } else if (input.startsWith("event")) {
                taskType = Parser.TaskType.EVENT;
            } else {
                taskType = null;
            }

            if (taskType == Parser.TaskType.TODO) {

                if (input.replaceAll("\\s", "").equals(input)) {
                    throw new DukeInvalidCommandException("todo");
                }

                String command = input.substring(0, input.indexOf(' '));
                String description = input.substring(input.indexOf(' ') + 1).trim();
                if (description.equals("")) {
                    throw new DukeInvalidCommandException(command);
                }
                this.addTodo(description, false);
            } else if (taskType == Parser.TaskType.DEADLINE) {

                if (input.replaceAll("\\s", "").equals(input)) {
                    throw new DukeInvalidCommandException("deadline");
                }

                String command = input.substring(0, input.indexOf(' '));
                String task = input.substring(input.indexOf(' ') + 1);
                String[] parsedTask = task.split("/", 2);
                String description = parsedTask[0].trim();

                if (parsedTask.length < 2) {
                    throw new DukeEmptyParametersException();
                }

                String by = parsedTask[1].trim();
                LocalDateTime deadlineDate = parseDate(by);

                if (description.equals("")) {
                    throw new DukeInvalidCommandException(command);
                } else if (deadlineDate == null) {
                    throw new DukeInvalidDateException();
                } else {
                    this.addDeadline(description, false, deadlineDate);
                }
            } else if (taskType == Parser.TaskType.EVENT) {

                if (input.replaceAll("\\s", "").equals(input)) {
                    throw new DukeInvalidCommandException("event");
                }

                String command = input.substring(0, input.indexOf(' '));
                String task = input.substring(input.indexOf(' ') + 1);
                String[] parsedTask = task.split("/");

                if (parsedTask.length < 3) {
                    throw new DukeEmptyParametersException();
                }

                String description = parsedTask[0].trim();
                String start = parsedTask[1].substring(parsedTask[1].indexOf(' ') + 1).trim();
                String by = parsedTask[2].substring(parsedTask[2].indexOf(' ') + 1).trim();
                if (description.equals("")) {
                    throw new DukeInvalidCommandException(command);
                } else if (start.equals("") || by.equals("")) {
                    throw new DukeEmptyParametersException();
                } else {
                    this.addEvent(description, false, start, by);
                }
            } else {
                throw new DukeInvalidCommandException();
            }
        }
    }

    private LocalDateTime parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("\\s+", 2);
            String dateString = parts.length > 1 ? parts[1] : parts[0]; // Use the second part if available

            LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return dateTime;
        } catch (Exception e) {
            return null;
        }
    }

    public void addTodo(String input, boolean isDone) {
        Todo newTask = new Todo(input, isDone);
        String newTaskString = newTask.fileFormat();

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public void addDeadline(String input, boolean isDone, LocalDateTime by) {
        Deadline newTask = new Deadline(input, isDone, by);
        String newTaskString = newTask.fileFormat();

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public void addEvent(String input, boolean isDone, String start, String end) {
        Event newTask = new Event(input, isDone, start, end);
        String newTaskString = newTask.fileFormat();

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i).toString());
        }
    }

    public void markCompletion(Task task, int num) {
        if (task.getStatusIcon().equals("X")) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + task);
        } else {
            System.out.println("Nice! I've marked this task as done:");

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            System.out.println("\t" + task);
        }
    }

    public void unmarkCompletion(Task task, int num) {
        if (task.getStatusIcon().equals(" ")) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t" + task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            System.out.println("\t" + task);
        }
    }

    public void deleteTask(Integer num) {
        System.out.println("Noted. I've removed this task:");

        Task selectedTask = lst.remove(num - 1);
        this.storage.updateTask(num - 1, null);

        System.out.println("\t" + selectedTask);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }
}
