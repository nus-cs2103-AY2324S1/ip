package Eddie.Commands;

import Eddie.TaskList;
import Eddie.Tasks.Task;

public class FindCommand {
    public static String execute(String s) {
        String output = "Search for tasks with:<" + s + ">\n";
        int size = TaskList.size();

        for (int i = 0; i < size; i++) {
            Task t = TaskList.get(i);
            String taskName = t.getName();
            int inName = taskName.indexOf(s);

            if (inName >= 0) {
                int num = i + 1;
                output = output + num + ". " + t.toString() + "\n";
            }
        }

        return output;
    }
}
