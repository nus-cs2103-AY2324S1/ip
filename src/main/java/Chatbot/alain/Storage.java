package chatbot.alain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import chatbot.alain.tasks.Deadline;
import chatbot.alain.tasks.Event;
import chatbot.alain.tasks.Task;
import chatbot.alain.tasks.ToDo;
import chatbot.alain.uis.Ui;


/**
 * Handles the loading and saving of tasks, as well as processing user inputs for the Alain chatbot.
 */
public class Storage {
    private String filePath;
    private Ui ui = new Ui();
    private Boolean alrBye;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file containing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.alrBye = false;
    }
    /**
     * Saves the task list to a file with optional exception message.
     *
     * @param list The task list to save.
     * @param fileName The name of the file to save to.
     * @param except Whether an exception has occurred.
     * @param msg The exception message.
     * @throws IOException If an I/O error occurs.
     */
    public static void saveTasksToFile(TaskList list, String fileName, Boolean except, String msg) throws IOException {
        File listFile = new File(fileName);
        System.out.println("Attempting to save tasks to: " + fileName);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        if (listFile.getParentFile() != null && !listFile.getParentFile().exists()) {
            listFile.getParentFile().mkdirs();
        }
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        try (FileWriter writer = new FileWriter(listFile, false)) {
            StringBuilder filecontent = new StringBuilder();

            if (except) {
                filecontent.append("Oops! Seems like there is an exception detected in your input\n")
                        .append(msg).append("\n");
            } else {
                list.sort();
                for (int i = 0; i < list.size(); i++) {
                    filecontent.append(Storage.turnIntoTextForm(list.getTask(i))).append("\n");
                }
            }
            System.out.println(filecontent);
            writer.write(filecontent.toString());
        }
    }


    /**
     * Loads tasks from a file and processes user input.
     *
     * @return The task list loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList loadTasksFromFile() throws IOException, AlainException {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Storage.processLine(list, line);
            }
        }
        return list;
    }

    /**
     * Processes a line of text, creating the appropriate task type and adding it to the task list.
     * It then marks the task as done if specified. This method assumes a specific format for the
     * input text.
     *
     * @param list The list of tasks to which the parsed task will be added.
     * @param text The input string in the format: type|isDone|description|... other fields.
     */
    public static void processLine(TaskList list, String text) {
        String[] segments = text.split("\\|");
        String taskType = segments[0];
        boolean isMarked = segments[1].equals("1");
        String content = segments[2];
        String tmpText;
        if (taskType.equals("T")) {
            tmpText = "todo " + content;
        } else if (taskType.equals("D")) {
            tmpText = "deadline " + content + "/by " + segments[3];
        } else {
            tmpText = "event " + content + "/from " + segments[3] + "/to " + segments[4];
        }
        System.out.print(tmpText);
        ChatbotAlain.proccessCommands(list, tmpText);
        if (isMarked) {
            ChatbotAlain.proccessCommands(list, "mark " + list.size());
        }
    }
    /**
     * Converts a task object into a standardized string representation based on its type.
     *
     * @param task The task object to be converted to its string representation.
     * @return A string representation of the task in the format: type|isDone|description|... other fields.
     */
    public static String turnIntoTextForm(Task task) {
        ToDo tmpToDo;
        Deadline tmpDdl;
        Event tmpEvent;
        if (task instanceof ToDo) {
            tmpToDo = (ToDo) task;
            return "T|" + task.isDone() + "|" + task.getDescription();
        } else if (task instanceof Deadline) {
            tmpDdl = (Deadline) task;
            return "D|" + task.isDone() + "|" + task.getDescription() + "|" + tmpDdl.getByTime();
        } else {
            tmpEvent = (Event) task;
            return "E|" + task.isDone() + "|" + task.getDescription() + "|"
                    + tmpEvent.getFromTime() + "|" + tmpEvent.getToTime();
        }
    }
}
