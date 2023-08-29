package chatbot.alain;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

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
     * Checks if the chatbot has received a "bye" command.
     *
     * @return True if the chatbot has received a "bye" command, false otherwise.
     */
    public Boolean isBye() {
        return this.alrBye;
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
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        FileWriter writer = new FileWriter(listFile, false);
        String filecontent = "";
        if (except) {
            filecontent += "Oops! Seems like there is an exception detected in your input\n";
            filecontent += msg + "\n";
        } else {
            filecontent += "____________________________________________________________\n"
                    + "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                filecontent += " " + (i + 1) + ". " + list.getTask(i) + "\n";
            }
            filecontent += "____________________________________________________________\n";
        }
        writer.write(filecontent);
        writer.close();
    }

    /**
     * Converts a string representing time to a different format.
     *
     * @param inputTime The input time string.
     * @return The transformed time string.
     * @throws AlainException If an exception occurs during the transformation.
     */
    public static String stringToTimeString(String inputTime) throws AlainException {
        if (Pattern.matches("\\d+-\\d+-\\d+", inputTime)) {
            DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.from(LocalDate.parse(inputTime, inputPattern));
            DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
            String transformedTime = date.format(outputPattern);
            return transformedTime.toString();
        } else if (Pattern.matches("\\d+-\\d+-\\d+ .+", inputTime)) {
            String[] dateAndTime = inputTime.split(" ");
            String addMsg = "";
            for (int i = 1; i < dateAndTime.length; i++) {
                addMsg += dateAndTime[i];
            }
            DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateAndTime[0], inputPattern);

            DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
            String transformedTime = date.format(outputPattern);
            return transformedTime.toString() + " " + addMsg;
        } else if (inputTime.length() == 0) {
            throw new AlainException(" OOPS!!! The description of a alain.Task cannot be empty.");
        } else {
            return inputTime;
        }
    }

    /**
     * Loads tasks from a file and processes user input.
     *
     * @return The task list loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList loadTasksFromFile() throws IOException, AlainException {
        ui.showWelcome();
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String text;
            while ((text = reader.readLine()) != null) {
                boolean isMatchMark = Pattern.matches("mark \\d+", text);
                boolean isMatchUnmark = Pattern.matches("unmark \\d+", text);
                boolean isDeadline = Pattern.matches("deadline .+", text);
                boolean isToDo = Pattern.matches("todo .+", text);
                boolean isEvent = Pattern.matches("event .+", text);
                boolean isDelete = Pattern.matches("delete .+", text);
                boolean isFind = Pattern.matches("find .+", text);
                if (isFind) {
                    String keyWord = text.substring(4);
                    TaskList tmpList = new TaskList();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.getTask(i).descriptionContain(keyWord)) {
                            tmpList.addTask(list.getTask(i));
                        }
                    }
                    ui.showListContainingKeyword(tmpList);
                    continue;
                }
                if (isDelete) {
                    String numericPart = text.substring(7);
                    int pos = Integer.parseInt(numericPart) - 1;
                    if (pos >= 0 && pos < list.size()) {
                        Task removedTask = list.removeTask(pos);
                        ui.showRemoveTask(removedTask, list);
                    } else {
                        throw new AlainException("Invalid task index.");
                    }
                    continue;
                }
                if (isToDo) {
                    String mission = text.substring(4);
                    if (mission.length() == 0) {
                        throw new AlainException("The description of a Todo cannot be empty.");
                    }
                    list.addTask(new ToDos(mission));
                    ui.showAddTask(list.getTask(list.size() - 1), list);
                    continue;
                }
                if (isDeadline) {
                    String mission = text.substring(8);
                    if (mission.length() == 0) {
                        throw new AlainException("The description of a Deadline cannot be empty.");
                    }
                    String[] parts = mission.split("/by ");
                    if (parts.length != 2) {
                        throw new AlainException("The description of a Deadline is invalid");
                    }
                    list.addTask(new Deadlines(parts[0], stringToTimeString(parts[1])));
                    ui.showAddTask(list.getTask(list.size() - 1), list);
                    continue;
                }
                if (isEvent) {
                    String mission = text.substring(5);
                    if (mission.length() == 0) {
                        throw new AlainException("The description of a Event cannot be empty.");
                    }
                    String[] parts = mission.split("/");
                    if (parts.length != 3) {
                        throw new AlainException("The description of a Event is invalid");
                    }
                    list.addTask(new Events(parts[0], stringToTimeString(parts[1].substring(5)),
                            stringToTimeString(parts[2].substring(3))));
                    ui.showAddTask(list.getTask(list.size() - 1), list);
                    continue;
                }
                if (text.equals("bye")) {
                    this.alrBye = true;
                    ui.showList(list);
                    ui.showGoodbye();
                    this.saveTasksToFile(list, "list.txt", false, null);
                    break;
                } else if (isMatchMark) {
                    String numericPart = text.substring(5);
                    list.getTask(Integer.parseInt(numericPart) - 1).markAsDone();
                    ui.showMarkTask(numericPart, list);
                    continue;
                } else if (isMatchUnmark) {
                    String numericPart = text.substring(7);
                    list.getTask(Integer.parseInt(numericPart) - 1).markAsUndone();
                    ui.showUnmarkTask(numericPart, list);
                    continue;
                } else if (text.equals("list")) {
                    ui.showList(list);
                    continue;
                }
                throw new AlainException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (AlainException e) {
            ui.showError(e.getMessage());
            ui.showGoodbye();
        }
        return list;
    }
}
