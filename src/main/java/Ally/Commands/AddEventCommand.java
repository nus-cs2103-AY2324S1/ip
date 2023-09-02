package Ally.Commands;

import Ally.*;

public class AddEventCommand extends Commands {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        try {
            Event event = new Event(description, from, to);
            allyList.addElements(event);
            storage.appendToFile(event);
            allyList.printNewList(event);
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
