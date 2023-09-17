# User Guide to Didier
Didier is a **task managing chatbot** application. Although it has a
graphical user interface, it is primarily built to meet the needs of
users that type fast and prefer using a command line interface.

## Features 

Notes about the command format:
- Words in `UPPER_CASE` are parameters to be supplied by the user, e.g.
in `find KEYWORD` the user must specify the keyword they are searching for in the tasks list.
- Any parameters beyond those that are mentioned are automatically ignored. 
e.g. in `list abc`, `abc` is ignored.
- The backslash is used to separate parameters when there are multiple parameters, 
e.g. in `deadline DESCRIPTION \by YYYY-MM-DD`, the backslash separates the
description parameter from the deadline date parameter.

Notes about the output format:
- Todos output in the format `[T] [STATUS] DESCRIPTION`.
- Deadlines output in the format `[D] [STATUS] DESCRIPTION DEADLINE_DATE`.
- Events output in the format `[E] [STATUS] DESCRIPTION START_DATE END_DATE`.
- The task `STATUS` can either be `X` (if it is done) or ` `(if it is not yet done).
- The dates are in the format `MMM DD YYYY`

### Listing all tasks
Shows a list of all the tasks in the list.

Command Format: `list`

Example outcome: 
```
The tasks in you list are as follows:
1. [T][] call Bob
2. [D][X] reply to John's email (by: Sep 18 2023)
```

### Locating a task by description keyword
Finds the task whose description contains the keyword.
The keyword can be a single word or even a phrase.

Command Format: `find KEYWORD`

Example: `find Bob`

Example outcome:
```
The tasks that match the keyword in your list are as follows:
1.[T][] call Bob
```

### Adding tasks
There are three types of tasks you can add as a user, a Todo, a Deadline, or an Event.
Upon successful addition of a task, Didier confirms that the task has
been added.

#### Adding a Todo 
Adds a Todo task. This is the simplest kind of task, and only consists
of a description.

Command Format: `todo DESCRIPTION`

Example: `todo make my bed`

#### Adding a Deadline
Adds a Deadline task. This is a task which consists of a description as well
as a date by which the task must be completed.

Command Format: `deadline DESCRIPTION \by YYYY-MM-DD`

Example: `deadline submit assignment \by 2023-09-18`

#### Adding an Event
Adds an Event task. This is a task which consists of a description, as 
well as a start and end date.

Command Format: `event DESCRIPTION \from YYYY-MM-DD \to YYYY-MM-DD`

Example: `event recess \from 2023-09-25 \to 2023-09-29`

### Deleting tasks
Deletes a task by its index number. The index numbers of the
tasks are given when `list` is run. Any numbers outside the range given
by `list` will not be accepted by Didier.
Upon successful deletion, Didier
will confirm that the task has been deleted.

Command Format: `delete INDEX`

Example: `delete 1`

### Exiting the program
Ends the user interaction with Didier and closes the program.

Command Format: `bye`

### Detecting duplicate tasks
Didier will automatically detect if the user attempts to add a duplicate task, i.e.
a task with the same description and same date parameters as an existing task.
Such additions are automatically rejected by Didier.

### Saving the data
All the task data passed to Didier is stored automatically in the
hard drive after all relevant commands. This data is automatically loaded
upon restarting the application. Users do not need to save/load data manually.