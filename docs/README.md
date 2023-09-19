# Harry Potter

## Features 
1. Add / Delete task
2. Mark / Unmark task
3. Mark / Unmark / Delete all tasks 
4. Find tasks with a matching keyword
5. Save latest list of tasks
6. Produce help list of commands

### Add / Delete task

Allows users to add or delete tasks of type (ToDo, Deadline & Event)
from the list of tasks.

### Mark / Unmark task

Allows users to mark task at the specified index of the list as done or not done.

### Mark / Unmark / Delete all tasks

Allows users to mark, unmark or delete all the tasks in the list in one go.

### Find tasks with matching keyword

Allows users to view tasks with the matching keyword in their description.

### Save latest list of tasks

Most updated version of the list of tasks is saved so that users can access it next time.

### Produce help list of commands

Users can access this help list of commands to learn more about the different commands handled.

## Usage

### `help` - View the list of commands

Users can view the list of commands handled under the help page

Example of usage: 

`help`

Expected outcome:
```
No worries, muggle! Harry Potter is here to help :D Accio Help Guide! 
Here are the commands for use: 
1. help: shows this list of commands
2. mark<index>: marks task at <index> in the list as done \n"
3. mark all: marks all tasks in the list as done 
...

```

### `mark` - Mark task in list as done

Users can mark the task at specified index of the list as done

Example of usage:

`mark 3`

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] apply for summer internship
```

### `todo` - Add task of type ToDo to list

Users can add tasks with no deadline of type ToDo to list

Example of usage:

`todo go on a holiday`

Expected outcome:
```
Got it. I've added this task:
    [T][] go on a holiday
Now you have X tasks in the list.
```
### `mark all` - mark all tasks in list as done

Users can now mark all the tasks in the list as done easily

Example of usage:

`mark all`

Expected outcome:
```
Nice! I've marked all tasks as done:
1. [T][X] go on a holiday
2. [D][X] assignment (by: Oct 4 2023 2359)
...
```
