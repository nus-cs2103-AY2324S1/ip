package commands;

import ari.AriException;
import ari.Storage;
import ari.TaskList;
import ari.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * RescheduleCommand task for command that asks to reschedule an event
 */
public class RescheduleCommand extends Command {

    private int indexToReschedule;
    private String newFromInStr;
    private LocalDateTime newFrom;
    private String newToInStr;
    private LocalTime newTo;
    private final DateTimeFormatter FROM_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
    private final DateTimeFormatter TO_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    public RescheduleCommand(String[] taskInfo) {
        this.indexToReschedule = java.lang.Integer.parseInt(taskInfo[0].substring(0,1)) - 1;
        this.newFromInStr = taskInfo[1].substring("from ".length(), "from ".length() + "yyyyMMdd HHmm".length());
        this.newToInStr = taskInfo[2].substring("to ".length());

        this.newFrom = LocalDateTime.parse(newFromInStr, FROM_FORMATTER);
        this.newTo = LocalTime.parse(newToInStr, TO_FORMATTER);


    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, AriException {
        tasks.rescheduleEvent(this.indexToReschedule, this.newFrom, this.newTo);
        storage.updateFileAfterReschedule(tasks.getTasks().get(indexToReschedule),
                this.indexToReschedule + 1,
                this.newFromInStr,
                this.newToInStr);
        return ui.printAfterReschedule(tasks.getTasks().get(indexToReschedule));
    }
}
