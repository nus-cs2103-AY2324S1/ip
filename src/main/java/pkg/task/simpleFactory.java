package pkg.task;

public class simpleFactory {
    public static Task createTask(String type, String... descriptions) {
        switch (type) {
            case "todo":
                return new Todo(descriptions[0]);
            case "deadline":
                return new Deadline(descriptions[0], descriptions[1]);
            case "event":
                return new Event(descriptions[0], descriptions[1], descriptions[2]);
            default:
                return null;
        }
    }
    
}
