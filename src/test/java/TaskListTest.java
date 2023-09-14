import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.SavedDataFormatException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class TaskListTest {

    /**
     * StorageStub is used to emulate ./Storage to allow unit testing of TaskList.
     */
    private class StorageStub extends Storage {
        private String[] data;
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

            for (int i = 0; i < data.length; i++) {
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
    private TaskList tasks = new TaskList(new StorageStub());

    @Test
    public void load_readFromStorageStub_true() {
        try {
            assertEquals(true, tasks.load());
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }
    }

    @Test
    public void deleteTask_invalidIndex_returnNull() {
        //tasks have only 3 tasks
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.deleteTask(5));

    }

    @Test
    public void deleteTask_validIndex_returnTodoTask() {
        //task at index 0 is a todotask with description "sing a song"
        try {
            tasks.load();
            final Task deletedTask = tasks.deleteTask(0);

            assertEquals(new ToDo("sing a song").convertToStorageForm(), deletedTask.convertToStorageForm());
        } catch (IOException e) {
            System.out.println("Error");
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }
    }

    @Test
    public void deleteTask_postDeletionLength_two() {
        try {
            tasks.load();
            Task expected = tasks.getTasks().get(0);
            final Task actual = tasks.deleteTask(0);

            assertEquals(expected, actual);
        } catch (IOException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }
    }

    @Test
    public void markAsDone_taskIsNotDone_true() {
        try {
            tasks.load();
            final String message = tasks.markAsDone(0);

            assertEquals(
                    "Mission status updated! Mission completed successfully.\n" + tasks.getTasks().get(0),
                    message);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }

    }

    @Test
    public void markAsDone_taskIsDone_true() {
        try {
            tasks.load();
            tasks.markAsDone(0);
            final String message = tasks.markAsDone(0);

            assertEquals(
                    "Mission has been completed previously.",
                    message);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }
    }

    @Test
    public void markAsDone_taskIndexInvalid_false() {
        try {
            tasks.load();
            final String message = tasks.markAsDone(5);

            assertEquals(
                    "Invalid index! Please ensure you correctly key in your target index.",
                    message);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }


    }

    @Test
    public void markAsUndone_taskIsDone_true() {
        try {
            tasks.load();
            tasks.markAsDone(0);
            final String message = tasks.markAsUndone(0);

            assertEquals(
                    "Mission status updated! Mission completion status reverted.\n" + tasks.getTasks().get(0),
                    message);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }
    }

    @Test
    public void markAsUndone_taskIsNotDone_true() {
        try {
            tasks.load();
            final String message = tasks.markAsUndone(0);

            assertEquals(
                    "Mission is already marked as undone!",
                    message);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }

    }

    @Test
    public void markAsUndone_taskIndexInvalid_false() {
        try {
            tasks.load();
            final String message = tasks.markAsUndone(5);

            assertEquals(
                    "Invalid index! Please ensure you correctly key in your target index.",
                    message);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (SavedDataFormatException e) {
            System.out.println(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error in parsing saved data.");
        }
    }
}
