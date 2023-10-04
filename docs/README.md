# KnowledgeYuan

## Features 

### Tasks

Categorizes Tasks into 3 categories:
1. Todo
   - A short task to be done
2. Deadline
    - A Task to be done before a given date
3. Event
    - A Task to be done throughout a given period of time

### Task List

Stores a list of your tasks.

A user can add, remove, and mark a task as done/undone with the user of commands.

### Reminder

Reminds you of the most urgent tasks to be completed.

A user can also see what needs to be done in the next few days with a command.

## Usage

### `todo` - Add a new Todo Task

Adds a new Todo Task to the Task List

Example of usage: 

`todo [name]`

Expected outcome:

A new Todo Task will be added to your Task List

### `deadline` - Add a new Deadline Task

Adds a new Deadline Task to the Task List

Example of usage:

`Deadline [name] /by [time]`

*The time should be in `D/M/YYYY HHmm` format*

Expected outcome:

A new Deadline Task will be added to your Task List

### `event` - Add a new Event Task

Adds a new Event Task to the Task List

Example of usage:

`event [name] /from [time] /to [time]`

*The time should be in `D/M/YYYY HHmm` format*

Expected outcome:

A new Event Task will be added to your Task List

### `list` - List all Tasks

List all Tasks in your Task List

Example of usage:

`list`

Expected outcome:

A list of Tasks currently in your Task List

### `delete` - Delete a Task

Deletes the specified Task from your Task List

Example of usage:

`delete [index]`

*[index] is the number of the Task as shown in the Task List*

Expected outcome:

The Task will be removed from your Task List

### `mark` - Marks a Task as done

Marks the specified Task as done

Example of usage:

`mark [index]`

*[index] is the number of the Task as shown in the Task List*

Expected outcome:

The marked task should now have a "tick" sign in the status

### `unmark` - Marks a Task as not done

Marks the specified Task as not done

Example of usage:

`unmark [index]`

*[index] is the number of the Task as shown in the Task List*

Expected outcome:

The marked task should no longer have a "tick" sign in the status

### `find` - Finds Tasks in the Task List

Finds all Tasks in the Task List that has the specified keyword

Example of usage:

`find [keyword]`

Expected outcome:

A list of Task with the keyword in their name

### `remind` - Reminds you of urgent Tasks

Tells you the urgent Tasks that should be completed in a specified time

Example of usage:

`remind (number of days)`

*(number of days) represents the number of days you want to seek ahead*  
*By default it looks for tasks 7 days in advance*

Expected outcome:

A list of task that you should complete within the next n days









