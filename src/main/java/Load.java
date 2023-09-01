import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.io.FileReader;

public class Load {

    TaskList taskList;
    private String loadFilePath;

    public Load(String loadFilePath) {
        this.loadFilePath = loadFilePath;
        this.taskList = new TaskList();
    }

    public TaskList load() throws IOException {
        FileReader f = null;

        try {
            f = new FileReader(loadFilePath);
        } catch (IOException e) {
            System.out.println("File does not exist in directory.");
            return null;
        }

        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String currentTaskAsString = s.nextLine();
            String taskType = currentTaskAsString.substring(1,2).toUpperCase();
            boolean isDone = currentTaskAsString.substring(4, 5).toUpperCase().equals("X");
            int descriptionBeginIndex = 7;
            Integer descriptionEndIndex = null;

            String description = null;

            switch (taskType) {
                case "T":
                    description = currentTaskAsString.substring(descriptionBeginIndex);
                    ToDo todo = new ToDo(description, isDone);
                    taskList.add(todo);
                    break;

                case "D":
                    descriptionEndIndex = currentTaskAsString.indexOf("(by:")-1;
                    int deadlineStartIndex = currentTaskAsString.indexOf("(by:") + 5;
                    description = currentTaskAsString.substring(descriptionBeginIndex, descriptionEndIndex);
                    String deadlineDeadline = currentTaskAsString.substring(deadlineStartIndex, currentTaskAsString.length()-1);
                    Deadline deadline = new Deadline(description, deadlineDeadline, isDone);
                    taskList.add(deadline);
                    break;

                case "E":
                    descriptionEndIndex = currentTaskAsString.indexOf("(from:")-1;
                    int fromTimingStartIndex = descriptionEndIndex + 8;
                    int fromTimingEndIndex = currentTaskAsString.indexOf("to:")-1;
                    int toTimingStartIndex = fromTimingEndIndex + 5;
                    int toTimingEndIndex = currentTaskAsString.length() - 1;
                    description = currentTaskAsString.substring(descriptionBeginIndex, descriptionEndIndex);
                    String from = currentTaskAsString.substring(fromTimingStartIndex, fromTimingEndIndex);
                    String to = currentTaskAsString.substring(toTimingStartIndex, toTimingEndIndex);
                    Event event = new Event(description, from, to, isDone);
                    taskList.add(event);
                    break;
            }

        }
        f.close();
        return this.taskList;
    };
}
