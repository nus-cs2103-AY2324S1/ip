import java.io.*;
import java.util.Scanner;

public class Storage {
    private void load(String pathname) throws FileNotFoundException {
        File file = new File(pathname);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] arr = line.split("\\|");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }
            boolean isTaskMarkedDone = arr[1].equals("1");
            switch (arr[0]) {
                case "T":
                    tasks.add(new Todo(arr[2], isTaskMarkedDone));
                    break;
                case "D":
                    tasks.add(new Deadline(arr[2], isTaskMarkedDone, formatDateTime(arr[3])));
                    break;
                case "E":
                    tasks.add(new Event(arr[2], isTaskMarkedDone, formatDateTime(arr[3]), formatDateTime(arr[4])));
                    break;
            }
        }
        fileScanner.close();
    }

    private void add(String pathname, Task newTask) {
        try {
            File file = new File(pathname);
            FileWriter fw = new FileWriter(pathname, true);
            String newLine = "";
            String isTaskMarkedDone = newTask.isDone ? "1" : "0";
            if (newTask instanceof Todo) {
                newLine = String.join(" | ", "T", isTaskMarkedDone, newTask.description);
            } else if (newTask instanceof Deadline) {
                Deadline newDeadline = (Deadline) newTask;
                newLine = String.join(" | ", "D", isTaskMarkedDone, newDeadline.description, formatter.format(newDeadline.deadline));
            } else if (newTask instanceof Event) {
                Event newEvent = (Event) newTask;
                newLine = String.join(" | ", "E", isTaskMarkedDone, newEvent.description, formatter.format(newEvent.from), formatter.format(newEvent.to));
            }
            if (file.length() != 0) {
                fw.write(String.format("\n%s", newLine));
            } else {
                fw.write(newLine);
            }

            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void edit(String pathname, Bongo.FileAction action, int taskNumber) {
        try {
            File file = new File(pathname);
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            int currentLine = 1;
            while ((line = fileReader.readLine()) != null) {
                if (currentLine == taskNumber) {
                    String[] lineWordsArr = line.split("\\|");
                    for (int i = 0; i < lineWordsArr.length; i++) {
                        lineWordsArr[i] = lineWordsArr[i].trim();
                    }
                    switch (action) {
                        case MARK_TASK:
                            lineWordsArr[1] = "1";
                            stringBuilder.append(String.join(" | ", lineWordsArr)).append("\n");
                            break;
                        case UNMARK_TASK:
                            lineWordsArr[1] = "0";
                            stringBuilder.append(String.join(" | ", lineWordsArr)).append("\n");
                            break;
                        case DELETE_TASK:
                            currentLine++;
                            continue;
                    }
                } else {
                    stringBuilder.append(line).append("\n");
                }
                currentLine++;
            }
            fileReader.close();

            // Write the modified content back to the file
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringBuilder.toString().trim());
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Problem editing the file.");
            e.printStackTrace();
        }
    }
}
