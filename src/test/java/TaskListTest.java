import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    /**
     * StorageStub is used to emulate Storage to allow unit testing of TaskList.
     */
    private class StorageStub extends Storage {
        String[] data;
        protected StorageStub() {
            super();
            data = new String[] {
                    "T::0::sing a song",
                    "D::0::hi world ::05/09/2023 2359",
                    "E::0::apocalypse ::03/09/2023 0100-03/09/2023 1200"
            };
        }

        @Override
        public boolean checkFileExists() {
            return true;
        }

        @Override
        public ArrayList<Task> retrieveTasks() throws FileNotFoundException {
            ArrayList<Task> tasks = new ArrayList<>();

            for(int i = 0; i < data.length; i++) {
                char type = data[i].charAt(0);
                String[] descriptions = data[i].split("::"); //:: demarcates a different field

                switch (type) {
                    case 'T':
                        tasks.add(new ToDo(descriptions[2], descriptions[1].matches("1")));
                        break;

                    case 'D':
                        //Events.Deadline
                        tasks.add(new Deadline(descriptions[2], descriptions[3], descriptions[1].matches("1")));
                        break;

                    case 'E':
                        //Events.Event
                        tasks.add(new Event(descriptions[2], descriptions[3], descriptions[1].matches("1")));
                        break;

                    default:
                        System.out.println("Wrong file format");
                }
            }

            return tasks;
        }

        @Override
        public boolean updateData(ArrayList<Task> tasks, boolean isAddingTask) throws IOException {
            return true;
        }
    }
    TaskList tasks = new TaskList(new StorageStub());

    @Test
    public void load_readFromStorageStub_true() {
        assertEquals(true, tasks.load());
    }

    @Test
    public void deleteTask_invalidIndex_null() {
        //tasks have only 3 tasks
        final Task DELETED_TASK = tasks.deleteTask(5);

        assertEquals(null, DELETED_TASK);
    }

    @Test
    public void deleteTask_validIndex_todoTask() {
        //task at index 0 is a todotask with description "sing a song"
        tasks.load();
        final Task DELETED_TASK = tasks.deleteTask(0);

        assertEquals(new ToDo("sing a song").convertToStorageForm(), DELETED_TASK.convertToStorageForm());
    }

    @Test
    public void deleteTask_postDeletionLength_two() {
        tasks.load();
        tasks.deleteTask(0);

        final int LENGTH = tasks.getTasks().size();

        assertEquals(2, LENGTH);
    }

    @Test
    public void markAsDone_taskIsNotDone_true() {
        tasks.load();
        final boolean IS_SUCCESSFUL = tasks.markAsDone(0);

        assertEquals(true, IS_SUCCESSFUL);
    }

    @Test
    public void markAsDone_taskIsDone_true() {
        tasks.load();
        tasks.markAsDone(0);
        final boolean IS_SUCCESSFUL = tasks.markAsDone(0);

        assertEquals(true, IS_SUCCESSFUL);
    }

    @Test
    public void markAsDone_taskIndexInvalid_false() {
        tasks.load();
        final boolean IS_SUCCESSFUL = tasks.markAsDone(5);

        assertEquals(false, IS_SUCCESSFUL);
    }

    @Test
    public void markUndone_taskIsDone_true() {
        tasks.load();
        tasks.markAsDone(0);
        final boolean IS_SUCCESSFUL = tasks.markUndone(0);

        assertEquals(true, IS_SUCCESSFUL);
    }

    @Test
    public void markUndone_taskIsNotDone_true() {
        tasks.load();
        final boolean IS_SUCCESSFUL = tasks.markUndone(0);

        assertEquals(true, IS_SUCCESSFUL);
    }

    @Test
    public void markUndone_taskIndexInvalid_false() {
        tasks.load();
        final boolean IS_SUCCESSFUL = tasks.markUndone(5);

        assertEquals(false, IS_SUCCESSFUL);
    }
}
