package parsers;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * The TaskParser class extends the abstract Parser class and provides
 * the implementation to parse string representations of tasks and
 * convert them into Task objects.
 */
public class TaskParser extends Parser<Task>{
    /**
     * The separator used to split task infos in the input string.
     */
    public static final String SEPARATOR = " | ";

    /**
     * Parses the given string representation of a task and converts it into a Task object.
     *
     * @param s The string representation of the task.
     * @return A Task object representing the parsed task.
     */
    @Override
    public Task parse(String s) {
        String[] infos = s.split(SEPARATOR);

        if (infos.length < 3) {

        }

        String taskType = infos[0];
        boolean status = infos[1].equals("1");
        String desc = infos[2];

        Task task = null;

        switch(taskType) {
        case "T":
            if (infos.length != 3) {

            }
            task = new ToDo(status, desc);
            break;
        case "D":
            if (infos.length != 4) {

            }
            task = new Deadline(status, desc, infos[3]);
            break;
        case "E":
            if (infos.length != 5) {

            }
            task = new Event(status, desc, infos[3], infos[4]);
            break;
        default:
            
        }

        return task;
    }
}
