package Command;

import DukeException.DukeException;
import FileStorage.FileStorage;
import List.TaskList;
import Tasks.Task;
import Ui.Ui;

public class FindCommand extends Command{
    private String word;
    public FindCommand(String word) {
        this.word = word;
    }
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        TaskList newList = new TaskList();
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).toString().contains(word)) {
                newList.add(tasklist.get(i));
            }
        }
        ui.showFoundResults(newList);
    }
}
