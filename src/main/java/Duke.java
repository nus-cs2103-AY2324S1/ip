import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static TaskList list;
    public static Parser parser;
    private static Storage storage;



    public static void main(String[] args) {

        storage = new Storage();
        list = new TaskList();
        storage.load(list);

        parser = new Parser();

        Ui.ui.startPrompt();
        run();
    }

    public static void run() {
        try {
            Command cmd = parser.readInput();
            cmd.execute(list);
        } catch (DukeException e) {
            Ui.ui.errorPrompt(e);
            run();
        } catch (DateTimeParseException e) {
            Ui.ui.wrongDateFormatPrompt();
            run();
        }
    }

    public static int listSize() {
        return list.list().size();
    }

}
