package catbot;

import catbot.io.CatbotJavaFxIo;
import javafx.application.Application;

public class CatBotEntrypoint {

    public static void main(String[] args) {
        //CatBot catBot = new CatBot(new CatbotConsoleIo(), new TaskList("Tasks.txt"));
        //CatBot catBot = new CatBot(new CatbotJavaFxIo(), new TaskList("Tasks.txt"));
        //catBot.run();
        Application.launch(CatbotJavaFxIo.class, args);
    }

}
