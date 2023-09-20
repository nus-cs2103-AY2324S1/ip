package com.mimi.commands;

import java.time.LocalDateTime;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.tasks.Deadline;
import com.mimi.ui.Ui;

/**
 * Representation of the deadline Command.
 * @author Yuheng
 */
public class DeadlineCommand extends Command {

    private boolean isCompleteCommand;
    private String taskName;
    private Storage storage;
    private ReadWriteData readWriteData;
    private LocalDateTime deadlineTime;

    /**
     * Creates an instance of the deadline Command
     * @param isCompleteCommand true if the given input has all the necessary components.
     * @param taskName the string representation of the deadline task name.
     * @param deadlineTime the deadline time of the task.
     * @param storage A Storage instance that stores the previous tasks.
     * @param readWriteData A ReadWriteData instance that access and writes data to the hard disk.
     */
    public DeadlineCommand(boolean isCompleteCommand, String taskName,
                       LocalDateTime deadlineTime, Storage storage, ReadWriteData readWriteData) {
        this.isCompleteCommand = isCompleteCommand;
        this.taskName = taskName;
        this.storage = storage;
        this.readWriteData = readWriteData;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Executes the given command.
     */
    @Override
    public void execute() {
        //first check if the command is complete and if the time format is correct.
        if (!this.isCompleteCommand || this.deadlineTime == LocalDateTime.MIN
            || this.isOverdue() || this.taskName.isBlank()) {
            return;
        }

        Deadline deadline = new Deadline(this.taskName, this.deadlineTime);
        this.storage.add(deadline);
        this.readWriteData.write(deadline);
    }

    /**
     * Calls the ui to give the appropriate response based on the type of command.
     * @param ui Ui class instance
     */
    @Override
    public void uiResponse(Ui ui) {
        if (!this.isCompleteCommand) {
            ui.incompleteDeadlineCommand();
        } else if (this.deadlineTime == LocalDateTime.MIN) {
            ui.wrongTimeFormat();
        } else if (this.isOverdue()) {
            ui.displayOverdue();
        } else if (this.taskName.isBlank()) {
            ui.incompleteInformation();
        }
    }

    private boolean isOverdue() {
        LocalDateTime currentTime = LocalDateTime.now();

        return this.deadlineTime.isBefore(currentTime);
    }

}
