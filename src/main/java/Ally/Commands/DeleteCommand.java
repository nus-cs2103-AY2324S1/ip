package Ally.Commands;

import Ally.*;

public class DeleteCommand extends Commands {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) {
        try {
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + allyList.getTask(index));
            allyList.deleteElement(index);
            System.out.println("Now you have " + allyList.getSize() + " tasks in the list.");
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
