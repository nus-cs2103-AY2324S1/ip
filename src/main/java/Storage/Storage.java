package main.java.Storage;

import main.java.Command.Commands;
import main.java.Duke;
import main.java.Parser.Parser;
import main.java.Task.ListOfTask;
import main.java.Task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void save(ArrayList<Task> listOfTask) {
        File writeData = new File("./src/data/duke.txt");
        try {
            writeData.createNewFile();
            FileWriter writer = new FileWriter(writeData);
            listOfTask.forEach(x-> {
                try {
                    writer.write(x.write());
                    if (x.isDone()) {
                        writer.write("mark\n");
                    }
                } catch (IOException e) {
                    System.out.println("You do not have access to write to your save file");
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.println("You do not have access to save your file");
        }
    }

    public static boolean load(ListOfTask taskList) {
        File saveData = new File("./src/data/duke.txt");
        try {
            saveData.createNewFile();
            Scanner readData = new Scanner(saveData);
            while (readData.hasNextLine()) {
                quickLoad(readData.nextLine(),taskList);
            }
            readData.close();
        } catch (IOException f) {
            if (clarify() == 0) {
                return false;
            }
        }
        return true;
    }

    private static int clarify() {
        System.out.println("You do not have access to create a save file");
        System.out.println("Do you wish to continue? (yes/no) None of your data will be saved.");
        Scanner scan = new Scanner(System.in);
        String respond = scan.nextLine();
        if (respond.equals("yes")) {
            return 1;
        } else if (respond.equals("no")) {
            return 0;
        } else {
            return clarify();
        }
    }

    public static void quickLoad(String command, ListOfTask taskList) {
        Parser cmd = new Parser(command);
        Commands.COMMANDS firstWord = cmd.mainCommand();

        switch (firstWord) {
            case TODO:
                if (cmd.secondWord() != null) {
                    taskList.loadTask(cmd.secondWord());
                } else {
                    System.out.println("ToDo line corrupted: " + command);
                }
                return;

            case DEADLINE:
                try {
                    String task = cmd.phaseParse();
                    String dayDate = cmd.phaseTwo();
                    Parser parseDayDate = new Parser(dayDate);
                    if (parseDayDate.mainCommand().equals(Commands.COMMANDS.BY)) {
                        LocalDateTime date = LocalDateTime.parse(parseDayDate.secondWord().trim(), Duke.FORMAT);
                        taskList.loadTask(task, date);
                    }
                } catch (NullPointerException | DateTimeParseException e) {
                    System.out.println("Deadline line corrupted: " + command);
                }
                return;

            case EVENT:
                try {
                    String task2 = cmd.phaseParse();
                    String startDayDateTime = cmd.phaseTwo();
                    Parser parseStartDayDateTime = new Parser(startDayDateTime);
                    String endDayDateTime = cmd.phaseThree();
                    Parser parseEndDayDateTime = new Parser(endDayDateTime);
                    if (parseStartDayDateTime.mainCommand().equals(Commands.COMMANDS.FROM) && parseEndDayDateTime.mainCommand().equals(Commands.COMMANDS.TO)) {
                        LocalDateTime startDate = LocalDateTime.parse(parseStartDayDateTime.secondWord().trim(),Duke.FORMAT);
                        LocalDateTime endDate = LocalDateTime.parse(parseEndDayDateTime.secondWord().trim(),Duke.FORMAT);
                        taskList.loadTask(task2, startDate, endDate);
                    }
                } catch (NullPointerException | DateTimeParseException e) {
                    System.out.println("Event line corrupted: " + command);
                }
                return;

            case MARK:
                int i = taskList.size();
                taskList.loadMark(i);
                return;

            default:
                System.out.println("line corrupted: " + command);
        }
    }
}
