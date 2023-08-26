import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.Scanner;

/**
 * @author Donovan Chan Jia Jun
 */
public class Duke {
    /**
     * Temporary data storage to store user text.
     */
//    static ArrayList<Task> storage;
    static final String dir = "/data";
//    public static final String outputPath = System.getProperty("user.dir") + dir + "/ipOutput.txt";
    private String outputPath;
    static FileWriter writer;
    private Storage data;
    private TaskList tasks;
    private Ui ui;
    static String line = "---------------------------------------------------------------------------------------------";

    public Duke(String filePath) {
        this.ui = new Ui();
        this.data = new Storage(filePath);
        this.outputPath = filePath;
        try {
            this.tasks = new TaskList(this.data.LoadOutputFile());
        } catch (FileNotFoundException e) {

        }
    }
    /**
     * Starting point of the bot.
     * Says hello - Carries out data storage for user text - Says goodbye
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        Duke bot = new Duke(System.getProperty("user.dir") + dir + "/ipOutput.txt");
        bot.echo();
    }

    /**
     * Handles and stores user inputs.
     * When arraylist changes, the entire output file is overwritten and all contents is transferred over
     */
    public void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (Parser.parsable(userInput)) {
            if (!this.outputPath.equals("")) {
                try {
                    Duke.writer = new FileWriter(outputPath, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.ui.emptyFilePath();
            }
            Parser.parse(userInput, ui, this.tasks, this.data);
            this.ui.createLine();
            userInput = scanner.nextLine();
        }
    }

    /**
     * Creates the output file if does not exists. Also creates directories that are missing.
     *
     * @param outputPath String of the path
     * @return File filePointer to to output file
     */
//    public static File createOutputFile(String outputPath) {
//        File filePointer = new File(outputPath);
//        if (!filePointer.exists()) {
//            File directory = new File(System.getProperty("user.dir") + Duke.dir);
//            // create directory if it doesn't exist
//            if (!directory.exists()) {
//                boolean result = directory.mkdirs();
//            }
//            try {
//                // create file in that directory
//                if (!filePointer.createNewFile()) {
//                    throw new FileNotFoundException();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return filePointer;
//    }

    /**
     * Loads the data stored in the hard disk.
     *
     * @param outputPath String of the path
     * @throws FileNotFoundException
     */
//    public static void LoadOutputFile(String outputPath) throws FileNotFoundException{
//        File filePointer = Duke.createOutputFile(outputPath);
//        Scanner storageScanner = new Scanner(filePointer);
//        while (storageScanner.hasNext()) {
//            String item = storageScanner.nextLine();
//            if (item != "") {
//                // process the item
//                // T|1|read book
//                String[] itemParts = item.split("\\|");
//                boolean itemComplete = itemParts[1].equals("0");
//                String name = itemParts[2];
//                switch (itemParts[0]) {
//                case "T":
//                    Duke.storage.add(new Todos(name, itemComplete));
//                    break;
//                case "D":
//                    String deadline = itemParts[3];
//                    Duke.storage.add(new Deadlines(name, deadline, itemComplete));
//                    break;
//                case "E":
//                    System.out.println(item);
//                    String from = itemParts[3];
//                    String to = itemParts[4];
//                    Duke.storage.add(new Events(name, from, to, itemComplete));
//                    break;
//                default:
//                    System.out.println("Error when reading file");
//                }
//            }
//        }
//        storageScanner.close();
//    }

    /**
     * Overwrites data in the arrayList to the output file in the hard disk.
     */
//    public static void updateTasks() {
//        Consumer<Task> storeTask = task -> task.writeToFile(Duke.writer);
//        Duke.storage.forEach(storeTask);
//        try {
//            Duke.writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
