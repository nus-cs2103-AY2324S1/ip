package peko;

public class Find {
    private Task[] tempTaskList;
    private String searchQuery;

    public Find(String s) {
        System.out.println("Find: " + s);
        if (s.isEmpty()) {
            System.out.println("You're not searching for anything Peko?");
        } else {
            searchQuery = s;
            tempTaskList = new Task[100];
            search();
            //display();
        }
    }

    private void search() {
        this.tempTaskList = StorageHandler.search(searchQuery);
    }
    public String display() {
        String out = "";
        for (Task t : tempTaskList) {
            if (t == null) {
                break;
            }
            System.out.println(t);
            out += t + "\n";
        }
        return out;
    }

}
