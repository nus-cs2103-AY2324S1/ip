/**
 * CS2103T Individual project
 * AY2023/24 Semester 1
 *
 * @author Anthony Tamzil
 */
public class ListOfTasks {
    String[] list = new String[100];
    int numOfTasks = 0;

    public ListOfTasks() {

    }

    public void addTask(String description) {
        list[numOfTasks] = description;
        numOfTasks++;
        System.out.println("added: " + description);
    }

    public void listTasks() {
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }
}
