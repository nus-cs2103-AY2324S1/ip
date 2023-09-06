package qi.command;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

public class FindCommand extends Command {
    private String keyWord;

    public FindCommand(String keyWord) {
        super(false);
        this.keyWord = keyWord;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        ui.showMatching(tasks.matchingKeyWord(this.keyWord));
    }
}
