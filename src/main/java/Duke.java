import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.*;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.time.*;
import java.time.format.*;

public class Duke {
    private static StringBuilder tempData = new StringBuilder();

    public Duke(String filePath) throws Exception{
        try {
            LoadFile loadFile = new LoadFile(filePath);
            loadFile.load();
            Ui ui = new Ui();
            ui.greet();
            TaskList taskList = new TaskList();
            taskList.Answer();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String logo = "Zenith";
        String zenithData = "src/main/java/data/zenith.txt";
        Duke duke = new Duke(zenithData);


    }
}
