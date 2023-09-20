package duke.reminder;

import java.util.concurrent.PriorityBlockingQueue;

import duke.tasks.Task;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Represents a thread that manages the reminders.
 */
public class ReminderManager extends Thread {
    private final PriorityBlockingQueue<Task> taskQueue;
    private final Object mutex;

    /**
     * Creates a ReminderManager object.
     * 
     * @param taskQueue
     * @param mutex
     */
    public ReminderManager(PriorityBlockingQueue<Task> taskQueue, Object mutex) {
        this.taskQueue = taskQueue;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (mutex) {
                while (taskQueue.isEmpty()) {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Task nextTask = taskQueue.peek();

                // Wait until it's time for the next task
                long timeUntilNextTask = nextTask.getDueTime() - System.currentTimeMillis();
                // Extra check if the task a doesn't have a duetime(ie Todo), remove it from the
                // queue
                if (nextTask.getDueTime() < 0) {
                    taskQueue.poll();
                    continue;
                }

                if (timeUntilNextTask > 0) {
                    try {
                        System.out.println("Waiting");
                        mutex.wait(timeUntilNextTask);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Notify user about the due task
                    // This is done on the JavaFX Application Thread to prevent concurrency issues
                    Platform.runLater(() -> {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Task Reminder");
                        alert.setHeaderText(null);
                        alert.setContentText("Task is due: " + nextTask.getDescription());

                        alert.showAndWait();
                    });
                    System.out.println("REMOVED" + taskQueue);
                    taskQueue.poll(); // Remove the task that was just notified
                }
            }
        }
    }
}
