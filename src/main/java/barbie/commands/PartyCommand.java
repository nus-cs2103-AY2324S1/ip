package barbie.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Party;
import barbie.types.Task;

/**
 * Represents the command when a "party" is called by the user.
 */
public class PartyCommand extends Command {
    private String desc;
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an instance of a PartyCommand, saves the from and to dates.
     * @param desc description of the party
     * @param from the start time of the party
     * @param to the end time of the party
     */
    public PartyCommand(String desc, LocalDate from, LocalDate to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
        this.isExit = false;

    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application
     * @param taskList current list of tasks
     * @return String to be returned to user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        Party party = new Party(this.desc, this.from, this.to);
        taskList.add(party);
        Storage.addToList(this.desc, this.from, this.to);
        return Ui.taskAdded(party);

    }

}
