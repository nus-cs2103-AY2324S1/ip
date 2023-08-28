package teho.main;

import teho.main.Deadline;
import teho.main.Event;
import teho.main.Task;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Makes sense of the user command that was loaded in file.
 */
public class Parser {
    /**
     * Parses input and adds task to taskList.
     *
     * @param nextLine Line to be parsed.
     * @param taskList List to add the parsed task into.
     */
    public static void readLine(String nextLine, ArrayList<Task> taskList) {
        Task taskToAddFirst;
        //use (\\|) to treat \ as a legit character or else it will get split by any spaces
        String[] sections = nextLine.split("\\|");
        int taskNumber = taskList.size(); //counting from 0
        if (sections[0].equals("T")) {
            taskToAddFirst = new ToDo(sections[2]);
            taskList.add(taskToAddFirst);
            if (sections[1].equals("1")) {
                taskToAddFirst.markAsDone(taskNumber);
            }
        } else if (sections[0].equals("D")) {
            taskToAddFirst = new Deadline(sections[2], LocalDate.parse(sections[3]));
            taskList.add(taskToAddFirst);
            if (sections[1].equals("1")) {
                taskToAddFirst.markAsDone(taskNumber);
            }
        } else if (sections[0].equals("E")) {
            taskToAddFirst = new Event(sections[2], LocalDate.parse(sections[3]), LocalDate.parse(sections[4]));
            taskList.add(taskToAddFirst);
            if (sections[1].equals("1")) {
                taskToAddFirst.markAsDone(taskNumber);
            }
        } else {
            System.out.println("☹ OOPS!!! Error when loading task(s).");
        }
    }
}
