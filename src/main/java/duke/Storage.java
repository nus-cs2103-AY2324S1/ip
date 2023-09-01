package duke;

import java.util.ArrayList;

import java.io.*;

public class Storage {
    protected String fileName = "userData.txt";
    protected File file;

    public Storage(String filePath) {
        this.file = new File(filePath, fileName);
        //Making a new dir if the specified one does not exit
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public void write(String inputs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(inputs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> read(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    lines.add(line);
                }
            }
            if (lines.isEmpty()) {
                System.out.println("Keyword not found, please try again!");
                return null;
            }
            return convertToDisplayFormat(lines);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<String> convertToDisplayFormat(ArrayList<String> lines) {
        ArrayList<String> ans = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split("|");
            if (split[0].equals("[T]")) {
                ans.add("[T]" + split[2] + " " + split[4]);
            } else if (split[0].equals("[D]")) {
                ans.add("[D]" + split[2] + " " + split[4]
                        + " (by: " + split[6] + ")");
            } else {
                ans.add("[D]" + split[2] + " " + split[4]
                        + " (from: " + split[6] + ", to: " + split[8] + ")");
            }
        }
        return ans;
    }
}
