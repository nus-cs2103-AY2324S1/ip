import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ItemList items;

    public Storage(){
        try {
            this.items = loadAll();
        } catch (FileNotFoundException e) {
            this.items = new ItemList(new File(Duke.LISTPATH));
            System.out.println(Greeting.linebreak);
            System.out.println("List not found, empty list will be created");
            System.out.println(Greeting.linebreak);
        }
    };

    public ItemList getItems(){
        return this.items;
    }
    private static ItemList loadAll() throws FileNotFoundException {
        File file = new File(Duke.LISTPATH);
        ArrayList<Task> items = new ArrayList<Task>();
        int len = 0;
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.charAt(0) == 'T') {
                String[] para = line.split(" | ");
                String description = para[4];
                ToDo newtask = new ToDo(description);
                if (!para[2].equals("0")) {
                    newtask.setDone();
                }
                items.add(newtask);
                len++;
                continue;
            }
            if (line.charAt(0) == 'D') {
                String[] para = line.split(" \\| ", 4);
                String description = para[2];
                String by = para[3];
                Deadline newtask;
                if (Dates.checkDateString(by)) {
                    newtask = new Deadline(description, Dates.createDateTime(by));
                } else {
                    newtask = new Deadline(description, by);
                }

                if (!para[1].equals("0")) {
                    newtask.setDone();
                }
                items.add(newtask);
                len++;
                continue;
            }


            if(line.charAt(0) == 'E') {
                String[] para = line.split(" \\| ", 4);
                String description = para[2];
                String block = para[3];
                String[] fromTo = block.split(" to ", 2);
                Event newtask;
                if (Dates.checkDateString(fromTo[0]) && Dates.checkDateString(fromTo[1])) {
                    newtask = new Event(description, Dates.createDateTime(fromTo[0]),
                            Dates.createDateTime(fromTo[1]));
                } else {
                    newtask = new Event(description, fromTo[0],fromTo[1]);
                }

                if (!para[1].equals("0")) {
                    newtask.setDone();
                }
                items.add(newtask);
                len++;

            }
        }
        s.close();
        return new ItemList(file, len, items);
    }
}
