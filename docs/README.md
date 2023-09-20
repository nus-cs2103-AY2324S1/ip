# Harry Potter

## Features
1. Add / Delete task
2. Update attributes of the specified task in the list
2. Mark / Unmark task
3. Mark / Unmark / Delete all tasks
4. Find tasks with a matching keyword
5. Save latest list of tasks
6. Produce help list of commands

### Add / Delete task

Allows users to add or delete tasks of type (ToDo, Deadline & Event)
from the list of tasks.

### Update task

Update description or dates of the task at specified index from the list of tasks.

### Mark / Unmark task

Allows users to mark task at the specified index of the list as done or not done.

### Mark / Unmark / Delete all tasks

Allows users to mark, unmark or delete all the tasks in the list in one go.

### Find tasks with a matching keyword

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
### `update` - Update attributes of a task in the list

Users can specify the task and update attributes like description & start or end dates & times depending on the type of Task

Example of usage:

`update 1 /description go on a holiday`

Expected outcome:
```
Nice! I've updated this task:
[T][ ] go on a holiday

```
`update 1 /deadline 2023-12-12 2359`

Expected outcome:
```
Nice! I've updated this task:
[D][ ] homework (by: Dec 12 2023 2359)

```
`update 2 /event start date 2023-12-12 1900`

Expected outcome:
```
Nice! I've updated this task:
[E][ ] networking (from: Dec 12 2023 1900 to: Dec 12 2130)

```
`update 2 /event end date 2023-12-12 2100`

Expected outcome:
```
Nice! I've updated this task:
[E][ ] networking (from: Dec 12 2023 1900 to: Dec 12 2100)

```
### `list` - returns list of tasks

Users can access the most updated list of tasks using this command.

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] go on a holiday
2. [D][X] assignment (by: Oct 4 2023 2359)
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

### `deadline` - Add task of type Deadline to list

Users can add tasks with a deadline of type Deadline to list

Example of usage:

`deadline homework /by 2023-09-22 2359`

Expected outcome:
```
Got it. I've added this task:
    [D][] homework (by: Sep 22 2023 2359)
Now you have X tasks in the list.
```

### `event` - Add task of type Event to list

Users can add tasks with a start and end date & time of type Event to list

Example of usage:

`event networking /from 2023-09-22 1900 /to 2023-09-22 2130`

Expected outcome:
```
Got it. I've added this task:
    [E][] networking (from: Sep 22 2023 1900 to: Sep 22 2023 2130)
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
