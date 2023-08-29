import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Saving {
    static String filePath = "./data/duke.txt";

    public Saving(String filePath) {

        this.filePath = filePath;
    }

    // Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84
    public static void saveTasks(ArrayList<Task> tasks) {

        try (FileWriter writer = new FileWriter(filePath)) {

            for (Task task : tasks) {
                String taskType = "";
                if (task instanceof Event) {
                    taskType = "Event";
                } else if (task instanceof Todo) {
                    taskType = "Todo";
                } else if (task instanceof Deadline) {
                    taskType = "Deadline";
                }

                String statusIcon = (task.getStatusIcon().equals("X")) ? "1" : "0";

                if (taskType.equals("Event")) {
                    Event event = (Event) task;
                    String customFrom = event.from.replace("from:", "");
                    String customTo = event.to.replace("to:", "-");
                    writer.write("E " + "| " + statusIcon + " | " + event.description +
                            " | " + customFrom + customTo + "\n");
                } else if (taskType.equals("Deadline")) {
                    Deadline deadline = (Deadline) task;
                    String customBy = deadline.by.replace("by: ", "");
                    writer.write("D " + "| " + statusIcon + " | " + deadline.description
                            + " | " + customBy + "\n");
                } else if (taskType.equals("Todo")) {
                    Todo todo = (Todo) task;
                    writer.write("T " + "| " + statusIcon + " | " + todo.description + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to: " + filePath);
        }
    }

    // Solution adapted and inspired by https://stackoverflow.com/questions/3090761/how-to-create-a-new-file-together-with-missing-parent-directories
    // Solution below adapted and inspired by https://www.guru99.com/buffered-reader-in-java.html
    // Solution below adapted and inspired by https://chat.openai.com/share/4f6c03e6-99d5-47c0-8887-1762a36b15fb
    static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // Handling data file to be in a specific folder
                if (file.getParentFile() != null) {
                    // Solution below adapted from
                    // https://www.oreilly.com/library/view/java-cookbook/0596001703/ch10s10.html#:~:text=Of%20the%20two%20methods%20used,%2Fian%2Fbin%22).
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String dataToWrite;
                while ((dataToWrite = reader.readLine()) != null) {
                    System.out.println(dataToWrite); // Debug line


                    String[] sections = dataToWrite.split(" \\| ");
                    if (sections.length >= 3) {
                        String taskType = sections[0];
                        String taskStatus = sections[1];
                        String taskDescription = sections[2];
                        String taskOtherInfo = sections.length > 3 ? sections[3] : "";

                        // declares variable of type Task and initialize with null value
                        Task task = null;
                        boolean isDone = taskStatus.equals("1");

                        switch (taskType) {

                            case "E":
                                String[] eventParts = taskOtherInfo.split("-");
                                if (eventParts.length == 2) {
                                    task = new Event(taskDescription, eventParts[0], eventParts[1]);
                                }
                                break;
                            case "D":
                                task = new Deadline(taskDescription, taskOtherInfo);
                                break;
                            case "T":
                                task = new Todo(taskDescription);
                                break;
                        }

                        if (task != null) {
                            if (isDone) {
                                task.markAsDone();
                            } else {
                                task.markAsNotDone();
                            }
                            tasks.add(task);
                        }
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading from: " + filePath);
        }
        return tasks;
    }
}

