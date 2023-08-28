import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskRepository {
    private static final String DIRECTORY_NAME = "/data";
    private static final String FILE_NAME = "tasks.txt";
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private final ArrayList<Task> tasks;
    private final ConsoleRenderer consoleRenderer;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
        this.consoleRenderer = new ConsoleRenderer();
    }

    public void loadSaveData() throws IOException, DateTimeParseException, DukeException {
        File directory = new File(Paths.get(CURRENT_DIRECTORY, DIRECTORY_NAME).toString());
        if (!directory.exists()) {
            directory.mkdir();
        }
        File saveFile = new File(Paths.get(CURRENT_DIRECTORY, DIRECTORY_NAME, FILE_NAME).toString());
        if (saveFile.exists()) {
            Scanner s = new Scanner(saveFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] data = line.split("\\|");
                String taskType = data[0].trim();
                switch (taskType) {
                case "D":
                    // Deadline
                    if (data.length == 4) {
                        // Ensure deadline date is a Datetime object
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        LocalDateTime localDateTime = LocalDateTime.parse(data[3].trim(), formatter);
                        // Create new task object
                        Task t = new Deadline(data[2].trim(), localDateTime);
                        if (Integer.parseInt(data[1].trim()) == 1) {
                            t.markAsDone();
                        }
                        tasks.add(t);
                    }
                    break;
                case "E":
                    // Event
                    if (data.length == 5) {
                        // Ensure deadline date is a Datetime object
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        Task t = new Event(data[2].trim(), LocalDateTime.parse(data[3].trim(), formatter),
                                LocalDateTime.parse(data[4].trim(), formatter));
                        if (Integer.parseInt(data[1].trim()) == 1) {
                            t.markAsDone();
                        }
                        tasks.add(t);
                    }
                    break;
                case "T":
                    // To Do
                    if (data.length == 3) {
                        Task t = new ToDo(data[2].trim());
                        if (Integer.parseInt(data[1].trim()) == 1) {
                            t.markAsDone();
                        }
                        tasks.add(t);
                    }
                    break;
                }
                // Decode the line to a task
            }
        } else {
            saveFile.createNewFile();
        }
    }

    public void updateSaveFile() throws IOException {
        FileWriter fw = new FileWriter(Paths.get(CURRENT_DIRECTORY, DIRECTORY_NAME, FILE_NAME).toString());
        for (Task t: tasks) {
            fw.write(t.toSaveDataFormat() + System.lineSeparator());
        }
        fw.close();
    }

    public void listTasks() {
        consoleRenderer.renderLine();
        System.out.println("Here are the tasks in your list:");
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i).toString());
            }
        } else {
            System.out.println("Nice!! You're all caught up and have no pending tasks to worry about.");
        }
        consoleRenderer.renderLine();
    }

    public void addTask(Task task) throws IOException {
        tasks.add(task);
        updateSaveFile();
        consoleRenderer.printMessage(
                String.format(
                        "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list.",
                        task.toString(),
                        tasks.size()
                )
        );
    }

    private boolean isInvalidTaskNumber(int taskNumber) {
        return (taskNumber <= 0) || (taskNumber > tasks.size());
    }

    public void markTask(int taskNumber) throws DukeException, IOException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else if (tasks.get(taskNumber - 1).isDone()) {
            throw new DukeException(DukeExceptionType.TASK_ALREADY_MARKED);
        } else {
            tasks.get(taskNumber - 1).markAsDone();
            updateSaveFile();
            consoleRenderer.printMessage(
                    String.format("Nice! I've marked this task as done:\n %s", tasks.get(taskNumber - 1).toString())
            );
        }
    }

    public void unmarkTask(int taskNumber) throws DukeException, IOException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else if (!tasks.get(taskNumber - 1).isDone()) {
            throw new DukeException(DukeExceptionType.TASK_ALREADY_UNMARKED);
        } else {
            tasks.get(taskNumber - 1).markAsNotDone();
            updateSaveFile();
            consoleRenderer.printMessage(
                    String.format(
                            "OK, I've marked this task as not done yet:\n %s",
                            tasks.get(taskNumber - 1).toString()
                    )
            );
        }
    }

    public void deleteTask(int taskNumber) throws DukeException, IOException {
        try {
            if (isInvalidTaskNumber(taskNumber)) {
                throw new DukeException(DukeExceptionType.INVALID_RANGE);
            } else {
                Task removedTask = tasks.remove(taskNumber - 1);
                updateSaveFile();
                consoleRenderer.printMessage(
                        String.format(
                                "Noted. I've removed this task:\n %s\nNow you have %d tasks in the list",
                                removedTask.toString(),
                                tasks.size()
                        )
                );
            }
        } catch (Exception exception) {
            consoleRenderer.printMessage(exception.getMessage());
        }
    }

    public void dueOn(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        System.out.println("Here are the tasks due on: " + dateString);
        Stream<Deadline> deadlineStream = tasks.stream()
                .filter(x -> x instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.getBy().toLocalDate().equals(date));
        consoleRenderer.renderLine();
        if (deadlineStream.findAny().isPresent()) {
            tasks.stream()
                    .filter(x -> x instanceof Deadline)
                    .map(task -> (Deadline) task)
                    .filter(deadline -> deadline.getBy().toLocalDate().equals(date))
                    .forEach(System.out::println);
        } else {
            System.out.printf("Great!! You've nothing due on %s!%n", dateString);
        }
        consoleRenderer.renderLine();
    }

}