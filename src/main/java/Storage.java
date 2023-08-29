import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Item> loadList() {

        ArrayList<Item> list = new ArrayList<Item>();
        try {
            Scanner listReader = new Scanner(new File(this.filePath));
            while (listReader.hasNextLine()) {
                String data = listReader.nextLine();
                switch (data.substring(data.indexOf("["), data.indexOf("]") + 1)) {
                    case "[T]":
                        list.add(new Todo(data.substring(data.indexOf("[") + 7)));
                        break;
                    case "[E]":
                        data.indexOf("(from: ");
                        data.indexOf(" to: ");
                        list.add(new Event(data.substring(data.indexOf("[") + 7, data.indexOf(" (from: ")), 
                                LocalDateTime.parse(data.substring(data.indexOf("(from: ") + 7, data.indexOf(" to: ")), DukeEnvironmentConstants.OUTPUT_FORMATTER),
                                LocalDateTime.parse(data.substring(data.indexOf("to: ") + 4, data.length() - 1), DukeEnvironmentConstants.OUTPUT_FORMATTER)));
                        break;
                    case "[D]":
                        list.add(new Deadline(data.substring(data.indexOf("[") + 7, data.indexOf(" (by: ")),
                                LocalDateTime.parse(data.substring(data.indexOf("(by: ") + 5, data.length()-1), DukeEnvironmentConstants.OUTPUT_FORMATTER)));
                        break;
                    default: throw new LoadListException();
                }
                if (data.substring(data.indexOf("[") + 4, data.indexOf("[") + 5).equals("X")) {
                            markItem(list, list.size() - 1);
                }
            }
            listReader.close();
        } catch (FileNotFoundException e) {
            list = new ArrayList<Item>();
        } catch (DateTimeParseException e) {
            list = new ArrayList<Item>();
        } catch (LoadListException e) {
            list = new ArrayList<Item>();
        } finally {
            return list;
        }
    }

    public void writeToSave(ChatBotList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DukeEnvironmentConstants.FILE_PATH));
            writer.write(taskList.toString());
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void markItem(ArrayList<Item> list, int idx) {
        list.get(idx).markCompleted();
    }
}
