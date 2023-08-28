import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final String fileName = "ChewieBrain.json";

    private static final Gson gson = new Gson();

    private SaveData save;

    public void save(TaskList list) {
        SaveData save = new SaveData(list.list().toArray(new Task[0]));
        String json = gson.toJson(save);

        // write to save file
        try {
            File saveFile = new File(fileName);
            FileWriter fw = new FileWriter(saveFile);

            fw.write(json);
            fw.close();
        } catch (IOException e) {
            System.out.print("Chewie have difficulty remembering your tasks.");
        }
    }

    public void load(TaskList list) {
        try {
            JsonReader reader = new JsonReader(new FileReader(fileName));
            save = gson.fromJson(reader, SaveData.class);
        } catch (FileNotFoundException e) {
            save = new SaveData(new Task[0]);
        } finally {

            for (int i = 0; i < save.type.length; i++) {
                String s = save.type[i];
                if (s.equals("ToDo")) {
                    list.add(save.toDos[i]);
                } else if (s.equals("Events")) {
                    list.add(save.events[i]);
                } else {
                    list.add(save.deadlines[i]);
                }
            }
        }
    }
}
