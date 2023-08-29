package main;

import storage.TaskList;
import storage.save.KniazLoader;
import storage.save.KniazSaver;
import ui.KniazInputController;
import ui.KniazOutputController;

public class KniazSession {
    private TaskList taskList;
    private KniazLoader loader;
    private KniazSaver saver;

    private KniazInputController inputController;

    private KniazOutputController outputController;

    public TaskList getTaskList() {
        return taskList;
    }

    public String quit(){
        // idk
        return null;
    }
}
