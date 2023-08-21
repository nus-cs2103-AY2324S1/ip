public class Store {
    Task[] tasks = new Task[100];
    int numOfTasks = 0;


    public void add(String description) {
        tasks[numOfTasks] = new Task(description);
        numOfTasks++;
        MessagePrint.print("added: " + description);
    }

    public void list() {
        String listOfItems = "Here are the tasks in your list:\n";
        for(int i = 0; i < numOfTasks; ++i) {
            listOfItems += (i + 1) + "."
                    + "[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].description + "\n";
        }
        MessagePrint.print(listOfItems);
    }
    public void mark(int index){
        tasks[index - 1].mark();
    }

    public void unmark(int index) {
        tasks[index - 1].unmark();
    }
}
