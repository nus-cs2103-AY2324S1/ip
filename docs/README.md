# RuaPro User Guide
RuaPro is a chatbot that can record and help you manage your various tasks. <br>

It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User
Interface (GUI).<br>

Simply run the jar file as the following guide to enjoy it!<br>
1. Copy the jar file into an empty folder.
2. Open a command window in that folder.
3. Run the command `java -jar rua.jar`  (i.e., run the command in the same folder as the jar file).

## Features 

Notes about the command format:

+ Words in UPPER_CASE are the parameters to be supplied by the user. <br>
e.g. in todo DESCRIPTION, DESCRIPTION is a parameter which can be used as `todo project`.

+ Items in square brackets are optional. <br>
e.g `todo DESCRIPTION [#TAG]` can be used as `todo project` or as `todo project #urgent`.

+ Items with `…` after them can be used multiple times, including zero times if it is
included in a square bracket . <br>
e.g. `[TAG]…` can be used as `  `(i.e. 0 times), `#urgent`, `#urgent #CS2103T` etc.

+ Parameters must follow the given order as the format. <br>
e.g. if the command specifies `DESCRIPTION DUE`, `DUE DESCRIPTION` is not acceptable.

### Adding a Todo task: `todo`

Adds a Todo task to the task list. <br>

Format: `todo DESCRIPTION [TAG]…` <br>
+ A Todo task is the type of task which does not have a strict time interval.
+ The newly added Todo task is not marked by default. If you want to mark it, please use
[`mark`](#marking-a-task-mark) command separately.

Example of usage:
+ `todo project`: This will add project as a Todo task to the task list. <br>
+ `todo coding #CS2103T`: This will add coding as a Todo task with tag #CS2103T to the task list.

### Adding a Deadline task: `deadline`

Adds a Deadline task to the task list. <br>

Format: `deadline DESCRIPTION /by DATE [TAG]…` <br>
+ A Deadline task is the type of task which has a strict due date.
+ `DATE` needs to follow the format `yyyy-mm-dd HH:mm` exactly and must be valid, otherwise you will get an error message of
DateTimeParseException and the Deadline task will not be successfully added.
+ The newly added Deadline task is not marked by default. If you want to mark it, please use 
[`mark`](#marking-a-task-mark) command separately.

Example of usage:
+ `deadline assignment /by 2023-10-01 08:00`: This will add assignment
as a Deadline task due at 8:00 on 1st October 2023 to the task list. <br>
+ `todo coding assignment /by 2023-10-01 08:00 #CS2103T`: This will add
coding assignment as a Deadline task due at 8:00 on 1st October 2023 
with tag #CS2103T to the task list.

### Adding an Event task: `event`

Adds an Event task to the task list. <br>

Format: `event DESCRIPTION /from STARTDATE /to ENDDATE [TAG]…` <br>
+ A Deadline task is the type of task which has a strict start date and end date.
+ `STARTDATE` and `ENDDATE` need to follow the format `yyyy-mm-dd HH:mm` exactly and must be valid, otherwise you will get
an error message of DateTimeParseException and the Event task will not be successfully added.
+ The newly added Event task is not marked by default. If you want to mark it, please use [`mark`](#marking-a-task-mark)
command separately.

Example of usage:
+ `event workshop /from 2023-10-01 09:00 /to 2023-10-07 16:00 #compulsory`: This will add workshop from 
09:00 on 1st October 2023 to 16:00 on 7th October 2023
with tag #compulsory as an Event task to the task list. <br>

### Deleting a task: `delete`

Deletes a task from the task list. <br>

Format: `delete INDEX` <br>
+ `INDEX` should be a positive integer no smaller than one and no greater than the current size of task list.
Otherwise, you will get an error message of IndexOutOfBoundException and no task will be deleted.
+ The `delete` process is irreversible.

Example of usage:
+ `delete 2`: This will delete the 2nd task in the task list. <br>

### Marking a task: `mark`

Marks a task in the task list. <br>

Format: `mark INDEX` <br>
+ `INDEX` should be a positive integer no smaller than one and no greater than the current size of task list.
  Otherwise, you will get an error message of IndexOutOfBoundException and no task will be deleted.
+ The marking status of the target will be set to marked regardless the initial marking status. Even if it is already
marked, no error will be raised and it will still be marked.

Example of usage:
+ `mark 2`: This will mark the 2nd task in the task list. <br>

### Unmarking a task: `mark`

Unmarks a task in the task list. <br>

Format: `unmark INDEX` <br>
+ `INDEX` should be a positive integer no smaller than one and no greater than the current size of task list.
  Otherwise, you will get an error message of IndexOutOfBoundException and no task will be deleted.
+ The unmarking status of the target will be set to marked regardless the initial marking status. Even if it is already
  unmarked, no error will be raised and it will still be unmarked.

Example of usage:
+ `unmark 2`: This will unmark the 2nd task in the task list. <br>

### Adding tags to a task: `addTag`

Adds tags to a task in the task list. <br>

Format: `addTag INDEX #TAG…` <br>
+ `INDEX` should be a positive integer no smaller than one and no greater than the current size of task list.
  Otherwise, you will get an error message of IndexOutOfBoundException and no task will be deleted.
+ Each `TAG` must start with `#` and no other `#` should appear in one single `TAG`.
+ Each `TAG` should be separated by a space.

Example of usage:
+ `addTag 2 #urgent #CCA`: This will add #urgent tag and #CCA tag to the 2nd task in the task list. <br>

### Deleting tags from a task: `deleteTag`

Delete tags from a task in the task list. <br>

Format: `deleteTag INDEX #TAG…` <br>
+ `INDEX` should be a positive integer no smaller than one and no greater than the current size of task list.
  Otherwise, you will get an error message of IndexOutOfBoundException and no task will be deleted.
+ Each `TAG` must start with `#` and no other `#` should appear in one single `TAG`.
+ Each `TAG` should be separated by a space.
+ It is possible that the target task does not have the tag to be deleted. In this case, it will skip
the deletion of this non-existing tag and move to delete the next tag to be deleted.

Example of usage:
+ `deleteTag 2 #urgent #CCA`: This will delete #urgent tag and #CCA tag from the 2nd task in the task list. <br>

### Clearing tags of a task: `clearTag`

Removes all tags from a task in the task list. <br>

Format: `clearTag INDEX` <br>
+ `INDEX` should be a positive integer no smaller than one and no greater than the current size of task list.
  Otherwise, you will get an error message of IndexOutOfBoundException and no task will be deleted.

Example of usage:
+ `clearTag 2`: This will remove all tags from the 2nd task in the task list. <br>

### Searching Tasks based on date

Searches the tasks which happens on the a given date. <br>

Format: `date TARGETDATE` <br>
+ `TARGETDATE` needs to follow the format `yyyy-mm-dd` exactly and must be valid, otherwise you will get an error
message of DateTimeParseException.
+ The search is inclusive. It will include Deadline task that dues on that day and Event task that starts or ends on
that day.

Example of usage:
+ `date 2023-10-01`: This will search all tasks that happen on 1st October 2023. <br>

### Searching Tasks based on keywords

Searches the tasks whose description contains the keywords. <br>

Format: `find KEYWORD` <br>
+ `KEYWORD` is not limited one word, it can also be a phrase. Special characters allowed.
+ The keyword searching will only search the description and tags will not be searched.
+ Keywords must appear consecutively in the description to be searchable.

Example of usage:
+ `find assignment`: This will search all tasks that contains 'assignment' in their descriptions. <br>

### Listing all tasks

Lists all the tasks in the task list. <br>

Format: `list` <br>
+ The tasks will be represented by a string in the format`"[Type][isMarked][Tags] Description (Time Interval)"`.
The round bracket will not appear for Todo task since it does not have any strict time interval.
+ The tasks will be displayed in the order they are added.

Example of usage:
+ `list`: This will list all tasks in the task list. <br>

### Clearing all tasks

Removes all the tasks from the task list. <br>

Format: `clear` <br>
+ The clearing process is irreversible.

Example of usage:
+ `clear`: This will remove all the tasks from the task list. <br>

### Saving the data
RuaPro task data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Editing the data file
RuaPro task data are saved automatically as a txt file [JAR file location]/data/tasks.txt. Advanced users
are welcome to update data directly by editing that data file.
+ If your changes to the data file makes its format invalid, AddressBook will discard all data and start with
an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.

## Acknowledgements
Special thanks to all classmates and tutors who provided me with their help and kindness.<br>

The idea for this product and its general structure follows the guidelines provided by the CS2103T teaching team.<br>

This user guide is inspired by 
[AB-3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html#adding-a-person-add).
