package duke;
import dukeUiElements.Ui;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Parser {
    public enum TaskKeyVal {ToDo, Deadline, Event, Delete, mark, unmark, bye, list};
    public static boolean parse(String userInput) throws DukeException {
        String[] userInputList = userInput.split(" ", 2);
        String userTaskChoiceKey = userInputList[0];
        //Stores enum value. might throw exception if invalid input entered.
        TaskKeyVal taskKeyVal = TaskKeyVal.valueOf(userTaskChoiceKey);

        if (taskKeyVal == TaskKeyVal.bye) {
            userExit();
            return false;
        } else if (taskKeyVal == TaskKeyVal.list) {
            TaskList.userListChoice();
            return true;
        } else if (taskKeyVal == TaskKeyVal.mark || taskKeyVal == TaskKeyVal.unmark) {
            String userMarkerChoice = userInputList[1];
            TaskList.userMarkUnmark(userMarkerChoice, userTaskChoiceKey);
            return true;
        } else if (userInputList.length == 1 && enumCheck(userTaskChoiceKey)) {
            throw new DukeException(" ☹ OOPS!!! The description of a task cannot be empty.");
        } else if (taskKeyVal == TaskKeyVal.ToDo) {
            TaskList.addToDo(userInputList[1]);
            return true;
        } else if (taskKeyVal == TaskKeyVal.Deadline) {
            String[] deadlineList = userInputList[1].split("/", 2);
            TaskList.addDeadline(deadlineList[0], deadlineList[1]);
            return true;
        } else if (taskKeyVal == TaskKeyVal.Event) {
            String[] eventList = userInputList[1].split("/", 3);
            TaskList.addEvent(eventList[0], eventList[1], eventList[2]);
            return true;
        } else if (taskKeyVal == TaskKeyVal.Delete) {
            Integer delUserChoice = Integer.parseInt(userInputList[1]);
            TaskList.deleteTask(delUserChoice);
            return true;
        } else {
            throw new DukeException("☹ OOPS!!! Sorry, but i do not know what that means :-(");
        }
    }
    public static boolean enumCheck (String input){
        for (TaskKeyVal taskKey : TaskKeyVal.values()) {
            if (taskKey.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
    public static void userExit() {
        try {
            Files.write(Duke.pathOfDirectory, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);    //closes file and truncates it
            for (int i = 0; i < TaskList.storeTask.size(); i++) {
                String taskToString = TaskList.storeTask.get(i).storeToDiskFormat() + "\n";
                Files.write(Duke.pathOfDirectory, taskToString.getBytes(), StandardOpenOption.APPEND);
            }
            Ui.endDukeMsg();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred...");
        }
    }
}
