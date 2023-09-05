import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String horizontalLine = "_".repeat(60) + "\n";
    public static final String FILE_PATH = "src/main/data/duke.txt";
    public static List<Task> allTasks = new ArrayList<>();

    public static int run;

    public static void saveTaskToFile(String task) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(task);
        } catch (IOException e) {
            System.err.println("Error saving task to file: " + e.getMessage());
        }
    }

    public static void createDataLocation() {
        File dir = new File("src/main/data");

        // Check if the directory exists; if not, create it
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory: " + dir.getAbsolutePath());
                return;
            }
        }

        File db = new File(Duke.FILE_PATH);

        // Check if the file exists; if not, create it
        if (!db.exists()) {
            try {
                if (db.createNewFile()) {
                    System.out.println("File created: " + db.getAbsolutePath());
                } else {
                    System.err.println("Failed to create file: " + db.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Error when creating the data storage!");
            }
        }
    }

    public static void loadTasksFromFile() {
        List<Task> loadedTasks = new ArrayList<>(); // Create a temporary list

        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }
                String taskType = parts[0];
                boolean isDone = Integer.parseInt(parts[1]) == 1;
                String taskDescription = parts[2];

                // Check if the task is already in Duke.allTasks
                if (!isTaskInAllTasks(taskType, taskDescription)) {
                    Task task;

                    if (taskType.equals("T")) {
                        task = new Todo(taskDescription, false);
                    } else if (taskType.equals("D") && parts.length >= 4) {
                        String by = parts[3];
                        task = new Deadline(taskDescription, false, by);
                    } else if (taskType.equals("E") && parts.length >= 4) {
                        String from = parts[3];
                        String to = (parts.length > 4) ? parts[4] : "";
                        task = new Event(taskDescription, false, from, to);
                    } else {
                        continue;
                    }

                    if (isDone) {
                        task.setStatus(TaskStatus.DONE);
                    }

                    loadedTasks.add(task);
                }
            }

            allTasks.clear();
            allTasks.addAll(loadedTasks);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static boolean isTaskInAllTasks(String taskType, String taskDescription) {
        for (Task task : Duke.allTasks) {
            if (task.getTask().equals(taskDescription) && task.getTaskType().equals(taskType)) {
                return true;
            }
        }
        return false;
    }



    public static void deleteLineFromFile(int lineNumber) {
        try {
            File inputFile = new File(FILE_PATH);
            File tempFile = new File(FILE_PATH + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int lineCounter = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineCounter++;

                if (lineCounter == lineNumber) {
                    continue;
                }

                writer.write(currentLine);
                writer.newLine();
            }

            writer.close();
            reader.close();

            boolean successfulRename = tempFile.renameTo(inputFile);

            if (!successfulRename) {
                throw new IOException("Failed to rename temporary file");
            }
        } catch (IOException e) {
            System.err.println("Error deleting line from file: " + e.getMessage());
        }
    }

    public static void updateLineInFile(int lineNumber, String updatedContent) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            long position = 0;
            int currentLine = 1;

            while (currentLine < lineNumber) {
                String line = file.readLine();
                if (line == null) {
                    System.err.println("Invalid line number: " + lineNumber);
                    return;
                }
                position += line.length() + System.lineSeparator().length();
                currentLine++;
            }

            file.seek(position);

            file.writeBytes(updatedContent);

        } catch (IOException e) {
            System.err.println("Error updating line in file: " + e.getMessage());
        }
    }

        public static void main(String[] args) throws DukeException {
            createDataLocation();
            loadTasksFromFile();
            if (allTasks.size() == 0) {
                run = 1;
            }
            Scanner sc = new Scanner(System.in);
            String task = "";
            System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
            System.out.flush();
            while (true) {
                try {
                    task = sc.nextLine();
                    if (task.equals("bye")) {
                        run = 0;
                        break;
                    }
                    if (task.equals("list")) {
                        Task taskInstance = new Task();
                        taskInstance.printList();
                        continue;
                    }
                    String[] elements = task.split((" "));
                    if (elements.length >= 2) {
                        String instruction = elements[0];
                        if (instruction.equals("mark") || instruction.equals("unmark")) {
                            try {
                                int no = Integer.parseInt(elements[1]);
                                Task taskInstance = new Task();
                                if (instruction.equals("mark")) {
                                    taskInstance.mark(no);
                                } else {
                                    taskInstance.unmark(no);
                                }
                                continue;
                            } catch (NumberFormatException e) {
                                System.err.println(Duke.horizontalLine + "You did not enter a valid integer :(\n" + Duke.horizontalLine);
                                continue;
                            }
                        }
                        if (instruction.equals("delete")) {
                            try {
                                int no = Integer.parseInt(elements[1]);
                                Task taskInstance = new Task();
                                taskInstance.delete(no);
                                continue;
                            } catch (NumberFormatException e) {
                                System.err.println(Duke.horizontalLine + "You did not enter a valid integer :(\n" + Duke.horizontalLine);
                                continue;
                            }
                        }
                        int firstSpaceIndex = task.indexOf(' ');
                        String actualTask = task.substring(firstSpaceIndex + 1);
                        if (instruction.equals("todo")) {
                            Todo todo = new Todo(actualTask, true);
                            todo.print();
                        }
                        else if (instruction.equals("deadline")) {
                            String[] taskAndDeadline = actualTask.split(("/by"));
                            if (taskAndDeadline.length == 1 || taskAndDeadline.length == 0) {
                                throw new DukeException(Duke.horizontalLine + "OOPS!!! Invalid format for deadline :-(\n" + Duke.horizontalLine);
                            }
                            String onlyTask = taskAndDeadline[0];
                            String by = taskAndDeadline[1];
                            Deadline deadline = new Deadline(onlyTask, true, by);
                            deadline.print();
                        }
                        else if (instruction.equals("event")) {
                            String[] taskAndToFrom = actualTask.split(("/from"));
                            if (taskAndToFrom.length == 1 || taskAndToFrom.length == 0) {
                                throw new DukeException(Duke.horizontalLine + "OOPS!!! Invalid format for event :-(\n" + Duke.horizontalLine);
                            }
                            String onlyTask = taskAndToFrom[0];
                            if (onlyTask.trim().isEmpty()) {
                                throw new DukeException(Duke.horizontalLine + "OOPS!!! Invalid format for event :-(\n" + Duke.horizontalLine);
                            }
                            String[] ToFrom = taskAndToFrom[1].split("/to");
                            if (ToFrom.length == 1 || ToFrom.length == 0) {
                                throw new DukeException(Duke.horizontalLine + "OOPS!!! Invalid format for event :-(\n" + Duke.horizontalLine);
                            }
                            String from = ToFrom[0];
                            String to = ToFrom[1];
                            Event event = new Event(onlyTask, true, from, to);
                            event.print();
                        }
                        else {
                            throw new DukeException(Duke.horizontalLine + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + Duke.horizontalLine);
                        }
                    } else if (elements[0].equals("todo") || elements[0].equals("deadline") || elements[0].equals("event")) {
                        throw new DukeException(Duke.horizontalLine + "OOPS!!! The description of a " + elements[0] + " cannot be empty.\n" + Duke.horizontalLine);
                    }
                    else {
                        throw new DukeException(Duke.horizontalLine + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + Duke.horizontalLine);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
        }
}


