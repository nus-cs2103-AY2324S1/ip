package peko.memory;

import peko.tasks.Task;

import java.io.*;

public class ArchiveHandler {

    private static File file = new File("src/main/Archive.txt");
    private static PrintWriter printWriter;
    public static void archive(Task task) {
        fileManager();
        String toStore = task.toStore() + "\n";
        try {
            Writer temp;
            temp = new BufferedWriter(new FileWriter("src/main/Archive.txt", true));
            temp.append(toStore);
            temp.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void fileManager() {
        if (!file.exists()) {

            File temp = new File(file.getParentFile(), "List.txt");
            file = temp;
        }
    }

}
