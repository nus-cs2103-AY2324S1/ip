package barbie.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Party;
import barbie.types.Task;


public class PartyCommand extends Command {
    String desc;
    LocalDate from;
    LocalDate to;

    public PartyCommand(String desc, LocalDate from, LocalDate to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
        this.isExit = false;

    }

    @Override
    public String run(ArrayList<Task> taskList) {
        Party party = new Party(this.desc, this.from, this.to);
        taskList.add(party);
        Storage.addToList(this.desc, this.from, this.to);
        return Ui.taskAdded(party);

    }

}
