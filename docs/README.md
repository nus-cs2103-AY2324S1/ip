# Puke Bot User Guide

# Features 

## Task List

Puke features a task list which allows you to create, mark and delete tasks.
There are three different types of tasks that can be added to the task list:

1. ToDo Tasks - Standard tasks that can be marked done or undone.
2. Deadline Tasks - Tasks with an associated deadline.
3. Event Tasks - Tasks that last a certain period of time, requiring a start and end time.

### Creating Tasks

To create a task, the task details have to be input in a specific format.

ToDo Tasks
- `todo (task description)`

Example: 
- `todo delete gym`

Deadline Tasks
- `deadline (task description) /by (date and time of deadline)`

Example: 
- `deadline hit the lawyer /by 2019-12-01T10:00`

Event Tasks
- `event (task description) /from (starting date and time) /to (end date and time)`

Example: 
- `event Facebook up /from 2023-08-30T14:00 /to 2023-08-30T16:00`

### Viewing Tasks

Tasks can be viewed at any time by inputting the following command:
- `list`


Do note that adding any other input after "list" will result in an error.

### Marking and Unmarking Tasks

Tasks can be marked as done and unmarked afterwards

- `mark (task index)`
- `unmark (task index)`

Example: 
- `mark 2`
- `unmark 902351`

### Finding Tasks

Specific tasks can be searched for via the use of a key word or keywords. the following command returns
all tasks that contain the specified keywords:
- `find (keywords)`

Example: 
- `find the reason why we're still here`

Leaving the keywords as empty will return an error instead.

The order of the keywords as well as spaces will matter.
### Deleting Tasks

Tasks can be easily deleted using the following command:
- `delete (task index)`

Example: 
- `delete 42`

The index must be positive and returns an error if it is negative, out of range or is not an integer.

Additionally, all tasks can be cleared immediately using the following command:
- `clearall`

Do note that there is no confirmation for this command, and it is done immediately after input.
### Adding Tags
Tags can be added to tasks to signify that a task is important. However, there is no additional
functionality such as searching by tags for now.
- `addTag (task index) (tag)`

Example: 
- `addTag 2 reoccurring`

### Data Saving

Puke Bot automatically saves all recorded tasks in a .txt file which is created automatically.
