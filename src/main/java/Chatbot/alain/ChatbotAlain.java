package Chatbot.alain;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class ChatbotAlain{
    private  Ui ui;
    private  Storage storage;
    private  TaskList tasks;

    public ChatbotAlain(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasksFromFile();
        } catch (IOException e) {
            ui.showError("Error Occurs when loading tasks from file");
        }
    }
    public static String stringToTimeString(String inputTime) throws AlainException {
        if (Pattern.matches("\\d+-\\d+-\\d+",inputTime)) {
            DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.from(LocalDate.parse(inputTime, inputPattern));
            DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
            String transformedTime = date.format(outputPattern);
            return transformedTime.toString();
        } else if (Pattern.matches("\\d+-\\d+-\\d+ .+",inputTime)) {
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

    public  void run() throws AlainException, IOException {
        if (this.storage.isBye()) {
            return;
        }
        TaskList list = this.tasks;
        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                String text = new String();
                text = s.nextLine();
                boolean isMatchMark = Pattern.matches("mark \\d+", text);
                boolean isMatchUnmark = Pattern.matches("unmark \\d+", text);
                boolean isDeadline = Pattern.matches("deadline .+", text);
                boolean isToDo = Pattern.matches("todo .+", text);
                boolean isEvent = Pattern.matches("event .+", text);
                boolean isDelete = Pattern.matches("delete .+", text);
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
                    ui.showAddTask(list.getTask(list.size() - 1),list);
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
                    ui.showAddTask(list.getTask(list.size() - 1),list);
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
                    list.addTask(new Events(parts[0], stringToTimeString(parts[1].substring(5)), stringToTimeString(parts[2].substring(3))));
                    ui.showAddTask(list.getTask(list.size() - 1),list);
                    continue;
                }
                if (text.equals("bye")) {
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
            } catch (AlainException e){
                ui.showError(e.getMessage());
                storage.saveTasksToFile(null, "list.txt", true, e.getMessage());
            }
        }

        try {
            ui.showList(list);
            storage.saveTasksToFile(list, "list.txt", false,null);
        } catch (IOException e) {
            ui.showError("Error saving tasks to file");
        } finally {
            ui.showGoodbye();
        }
    }

    public static void main(String[] args) throws AlainException, IOException {
        //System.out.println("hi");
        new ChatbotAlain("tasks.txt").run();
    }
}
