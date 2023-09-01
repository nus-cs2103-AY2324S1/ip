import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {
    private final TaskList tasks;

    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Greets the user and creates the requisite folder and text file, if required.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void launchOnStart() {
        File f = new File("./data");
        f.mkdirs();
        try {
            File file = new File("./data/tasks.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Due to technical issues, I'm only available in guest mode.%n" +
                    "I sincerely apologise to the inconvenience caused.");
        }
    }

    public void readTasksFromDisk(String filePath) throws FileNotFoundException,
            IllegalArgumentException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] args = sc.nextLine().split("-", -1);
            TaskList.TaskType type;
            LocalDateTime due = null;
            LocalDateTime start = null;
            LocalDateTime end = null;
            try {
                type = TaskList.TaskType.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Task not found");
                return;
            }
            String details = args[1];
            boolean isCompleted = args[2].equals("Y");
            try {
                if (type == TaskList.TaskType.DEADLINE) {
                    due = LocalDateTime.parse(args[3]);
                } else if (type == TaskList.TaskType.EVENT) {
                    start = LocalDateTime.parse(args[3]);
                    end = LocalDateTime.parse(args[4]);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Could not retrieve date and/or time");
                return;
            }
            switch (type) {
                case TASK:
                    Task t = new Task(details, isCompleted);
                    tasks.add(t);
                    break;
                case TODO:
                    ToDo todo = new ToDo(details, isCompleted);
                    tasks.add(todo);
                    break;
                case DEADLINE:
                    Deadline d = new Deadline(details, isCompleted, due);
                    tasks.add(d);
                    break;
                case EVENT:
                    Event e = new Event(details, isCompleted, start, end);
                    tasks.add(e);
                    break;
                default:
                    // Shouldn't reach here
                    break;
            }
        }
    }

    public void saveTasksToDisk(String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (int i = 0; i < tasks.getNumOfTasks(); i++) {
                Task t = tasks.get(i);
                TaskList.TaskType type = tasks.getTaskType(t);
                switch (type) {
                    case TODO:
                        bw.write(String.format("ToDo-%s-%c",
                                t.getDetails(),
                                t.isCompleted ? 'Y' : 'N'));
                        break;
                    case DEADLINE:
                        bw.write(String.format("Deadline-%s-%c-%s",
                                t.getDetails(),
                                t.isCompleted ? 'Y' : 'N',
                                ((Deadline) t).due));
                        break;
                    case EVENT:
                        bw.write(String.format("Event-%s-%c-%s-%s",
                                t.getDetails(),
                                t.isCompleted ? 'Y' : 'N',
                                ((Event) t).start,
                                ((Event) t).end));
                        break;
                    case TASK:
                        bw.write(String.format("Task-%s-%c",
                                t.getDetails(),
                                t.isCompleted ? 'Y' : 'N'));
                        break;
                    default:
                        // Shouldn't reach here
                        break;
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
