package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;
import monke.tasks.Deadline;
import monke.tasks.Task;

import java.time.LocalDateTime;

public class SnoozeCommand extends Command {
    public static final String COMMAND_WORD = "snooze";
    private String taskNum;
    private LocalDateTime date;

    public SnoozeCommand(String taskNum, LocalDateTime date) {
        this.taskNum = taskNum;
        this.date = date;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Task task = tasks.getTask(taskNum);
        if (!(task instanceof Deadline)) {
            throw new MonkeException("You can only snooze a deadline! OOGA BOOGAA!!!!!!");
        }
        Deadline deadline = (Deadline) task;
        deadline.setDate(date);
        return "I have postponed this task! OOGA BOOGA!!\n" + ui.getListString(tasks);
    }
}