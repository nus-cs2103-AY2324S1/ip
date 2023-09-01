package commands;

import storage.DataFile;
import tasks.TaskList;

public class FindCommand extends Command{
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, DataFile dF) {
        System.out.println(this);
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDesc().contains(keyWord)) {
                System.out.println(i + 1 + "." + tasks.getTask(i));
            }
        }
    }

    @Override
    public String toString() {
        return "Here are the matching tasks from you list:";
    }
}
