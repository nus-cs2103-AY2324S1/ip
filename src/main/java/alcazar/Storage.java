package alcazar;
import alcazar.Tasks.Deadline;
import alcazar.Tasks.Event;
import alcazar.Tasks.Task;
import alcazar.Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for file storage related functionality
 */
public class Storage {
    private final String filePath;
    Storage(String filePath) {
        this.filePath = filePath;
        try {
            printTasks();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file");
        }
    }

    /**
     * Prints the contents of the file stored at filePath
     * @throws FileNotFoundException It is thrown in a situation where the file
     *      does not exist at the given filePath
     */
    public void printTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes the new tasks to file
     * @param t Stores the tasks in a TaskList object
     */
    public void writeUp(TaskList t) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(t.getTasks());
            fw.close();
            this.printTasks();
        } catch (IOException e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }

    /**
     * Converts the input text into its equivalent ToDo Task
     * @param text String to be converted to a ToDo task
     * @return The ToDo task converted from text
     */
    public ToDo writeTaskToDo(String text) {
        ToDo taskToDo = new ToDo(text.substring(10).trim());
        if (text.charAt(7) == 'X') {
            taskToDo.markTask();
        }
        return taskToDo;
    }

    /**
     * Converts the input text into its equivalent Event Task
     * @param text String to be converted to Event Task
     * @return The Event task converted from text
     */
    public Event writeTaskEvent(String text) {

        String[] eventPromptElements = text.split("[()]");
        String taskDescription = eventPromptElements[0].trim().substring(10);

        String timeInfo = eventPromptElements[1].trim();
        String[] timeParts = timeInfo.split("from:|to:");
        String startTime = timeParts[1].trim();
        String endTime = timeParts[2].trim();

        String[] extractedValues = { taskDescription, startTime, endTime };
        Event extractedEvent = new Event(extractedValues[0], extractedValues[1], extractedValues[2]);

        boolean isMarked = text.charAt(7) == 'X';
        if (isMarked) {
            extractedEvent.markTask();
        }

        return extractedEvent;
    }

    /**
     * Converts the input text into its equivalent Deadline Task
     * @param deadlinePrompt The String to be converted to a Deadline Task
     * @return The Deadline Task from the converted String
     */
    public Deadline writeTaskDeadline(String deadlinePrompt) {

        int index;
        String deadlineContent = "";
        String deadlineTiming = "";
        boolean isMarked = deadlinePrompt.charAt(7) == 'X';
        deadlinePrompt = deadlinePrompt.substring(10);
        String[] deadlinePromptElements = deadlinePrompt.split(" ");

        for (index = 0; index < deadlinePromptElements.length; index++) {
            String word = deadlinePromptElements[index];
            if (word.equals("(by:")) {
                break;
            }
            deadlineContent += word + " ";
        }

        for (index = index + 1; index < deadlinePromptElements.length; index++) {
            deadlineTiming += deadlinePromptElements[index] + " ";
        }
        deadlineContent = deadlineContent.trim();
        deadlineTiming = deadlineTiming.trim().substring(0, deadlineTiming.length() - 2);
//        String wrd = "";
//        String str = "";
//        int i;
//        for (i = 0; i < deadlinePrompt.length(); i++) {
//            char ch = deadlinePrompt.charAt(i);
//            if (ch == ' ') {
//                if (wrd.equals("(by:")) {
//                    break;
//                }
//                str += wrd + " ";
//                wrd = "";
//            } else {
//                wrd += ch;
//            }
//        }
//        String[] deadArray = new String[2];
//        deadArray[0] = str.trim();
//        deadArray[1] = deadlinePrompt.substring(i + 1, deadlinePrompt.length() - 1);
        Deadline extractedDeadline = new Deadline(deadlineContent, deadlineTiming);
        if (isMarked) {
            extractedDeadline.markTask();
        }
        return extractedDeadline;
    }

    /**
     * This method reads a file and converts its text into an ArrayList of Task objects
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String prompt = s.nextLine();
                char taskDefinitionCharacter = prompt.charAt(4);
                if (taskDefinitionCharacter == 'T') {
                    tasks.add(writeTaskToDo(prompt));
                } else if (taskDefinitionCharacter == 'E') {
                    tasks.add(writeTaskEvent(prompt));
                } else {
                    tasks.add(writeTaskDeadline(prompt));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Could not find file!");
        }
        return tasks;
    }
}
