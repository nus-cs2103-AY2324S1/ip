package Ally.Commands;
import Ally.*;

public class AddTodoCommand extends Commands {
    private final String description;
    public AddTodoCommand(String description) {
        this.description = description;
    }
    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        try{
            Todo todo = new Todo(description);
            allyList.addElements(todo);
            storage.appendToFile(todo);
            allyList.printNewList(todo);
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
