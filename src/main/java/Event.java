import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static void addEvent(ArrayList<Task> list, String command) {
//        event project meeting /from Mon 2pm /to 4pm
        String splitCommand[] = command.split(" ");

        try {
            boolean hasFrom = false;
            boolean hasTo = false;

            if (splitCommand.length == 1) {
                throw new DukeException("Event Argument Empty");
            }

            for (int i = 0; i < splitCommand.length; i++) {
                if (splitCommand[i].equals("/from")) {
                    hasFrom = true;
                    if (i == 1) {
                        throw new DukeException("There is no description");
                    }
                    if (i == splitCommand.length - 1) {
                        throw new DukeException("Please insert /from dates");
                    }
                    if (splitCommand[i + 1].equals("/to")) {
                        throw new DukeException("Please insert /from dates");
                    }
                }

                if (hasFrom && splitCommand[i].equals("/to")) {
                    hasTo = true;
                    if (i == splitCommand.length - 1) {

                        throw new DukeException("Please insert /to dates");
                    }
                }
            }

            if (hasFrom == false) {
                throw new DukeException("Please insert event is /from when");
            }

            if (hasTo == false) {
                throw new DukeException("Please insert event is /to when");
            }

        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        printHorizontalLine();
        System.out.println("Got it, I've added this event.");
        String currStr = command.substring(6);
        int fromIndex = currStr.indexOf("/");
        String description = currStr.substring(0, fromIndex);
        currStr = currStr.substring(fromIndex + 6);
        int toIndex = currStr.indexOf("/");
        String from = currStr.substring(0, toIndex);
        String to = currStr.substring(toIndex + 4);
        Task eventTask = new Event(description, from, to);
        list.add(eventTask);
        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt", true);
            myWriter.write(  "E | 0 | " + description + "| " + from + "| " + to +"\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(eventTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }

    public static void readData(ArrayList<Task> list, String data) {
        String splitEvent[] = data.split(" \\| ");


        Task eventTask = new Event(splitEvent[2], splitEvent[3] + " ", splitEvent[4]);
        list.add(eventTask);
        if (splitEvent[1].equals("1")) {
            list.get(list.size()-1).markDoneNoPrint();
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + "to: " + this.to + ")";
    }
}