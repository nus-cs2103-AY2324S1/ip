import java.io.*;

public class Save {
    protected String pathToSave = "Data";
    protected String fileName = "userData.txt";
    protected File file;

    public Save() {
        this.file = new File(pathToSave, fileName);
        //Making a new dir if the specified one does not exit
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            System.out.println("Directory does not exist. New directory created!");
        }
    }

    public void write(String inputs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(inputs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    return line;
                }
            }
            System.out.println("Keyword not found, please try again!");
            return "ERROR_KeyNotFound";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR_Exception";
        }
    }
}
