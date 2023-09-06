package catbot.io;

import catbot.task.Task;
import catbot.task.TaskList;

public interface TaskAssistantIo {

    void printTaskList(TaskList taskList);

    void printTaskAdded(TaskList taskList);
    void printTaskDeleted(Task deleted);
    void printTaskModified(TaskList taskList, int index);

}
