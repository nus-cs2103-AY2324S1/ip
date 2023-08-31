package peko;

public class Find {
    Task[] tempTaskList;
    String searchQuery;

    public Find(String s) {
        if (s.isEmpty()) {
            System.out.println("You're not searching for anything Peko?");
        } else {
            searchQuery = s;
            tempTaskList = new Task[100];
        }
    }

    private void search() {

    }

}
