# User Guide - Sivraj
Sivraj is a easy to use chatbot style application to schedule and manage tasks. 

It can be distributed as a .jar file.

## Features 

### Feature- Add ToDo

Adds a task todo with only description and no deadline.

**Format** - `todo task`

**Example** - `todo walk`




### Feature- Add Deadline

Adds a deadline task with description and deadline date.

**Format** - `deadline task /by date`, (date should be in format yyyy-mm-dd)

**example** - `deadline return book /by 2017-08-07`



### Feature- Add Event
Adds an Event task with description, start date and end date.

**Format** - `event task /from start date & time /to end date & time`, (start/end date & time are in string format)

**example** - `event project meeting /from Mon 2pm /to 4pm`


### Feature- Delete task
Deletes a task based on its index number in the task list.

**Format** - `delete indexNumber`, (indexNumber is an integer)

**example** - `delete 3`



### Feature- Mark task
Marks a specific task as done/completed using index number.

**Format** - `mark indexNumber`, (indexNumber is an integer)

**example** - `mark 3`



### Feature- Unmark task
Marks a specific task as not done/completed using index number.

**Format** - `unmark indexNumber`, (indexNumber is an integer)

**example** - `unmark 3`



### Feature- List tasks
Lists all the current and previous tasks that are recorded in a text file.

**Format** - `list`


### Feature- Bye
Gives a goodbye message and saves the current state of task list into the text file.

**Format** - `bye`

