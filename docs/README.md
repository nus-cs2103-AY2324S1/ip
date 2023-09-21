# Budget Cleo Task List User Guide
![cleo](../src/main/resources/images/dancing_cleo.gif)

## Features

### Managing Tasks
Add, delete and view tasks. 

### Completing tasks
Mark tasks as complete for better management.

### Tag management
Add tags and filter your tasks based on tags.

### Hidden surprise
:eyes:

## Things to note
### Case sensitivity
Commands are not case sensitive (e.g. typing "list" and "lISt" will give the same output).

### Resize
App is only able to resize vertically and not horizontally. 

### Sound
This apps contains some audio clips. 

## Usage
### `list` - Lists out tasks
Prints out list of existing tasks.

Example of usage: 
`list`

Expected outcome:
```
1. [T][ ] poop
2. [D][ ] CS2103T quiz
    (by: Sep 22 2023)
3. [E][ ] reading week
    (from: Sep 23 2023 to: Sep 30 2023)
```

### `find` - Find task
Finds task containing given string and prints them out.

Example of usage: 
`find read`

Expected outcome:
Prints out tasks that contains string (in this case, "read").
```
3. [E][ ] reading week
    (from: Sep 23 2023 to: Sep 30 2023)
```

### `todo` - Adds ToDo task
Adds a ToDo task.

Example of usage: 

`todo Do MA1521 Tutorial`

Expected outcome:
Prints out task successfully added.
```
Task Do MA1521 Tutorial successfully added
Now you have 4 tasks in the list. 
```

### `event` - Adds Event Task
Adds a Event task, has the following sub commands: `from`, `to`

Follows only yyyy-mm-dd format. 

Example of usage: 

`event Seminar /from 2023-09-22 /to 2023-09-23`

Expected outcome:
Prints out task successfully added.
```
Task Seminar successfully added
Now you have 5 tasks in the list. 
```

### `deadline` - Adds Deadline Task
Adds a Deadline task, has the following sub commands: `by`.

Follows only yyyy-mm-dd format. 

Example of usage: 

`deadline CS2100 Assignment /by 2023-09-25`

Expected outcome:
Prints out task successfully added.
```
Task CS2100 Assignment successfully added
Now you have 4 tasks in the list. 
```

### `mark` - Marks task
Marks task as complete, use task index to mark.

Example of usage: 

Marking second task as complete `mark 2`

Expected outcome:
Prints that you have marked that task successfully.

```
Task at index 2 has been successfully marked. 
```

Print the list to confirm that it has been marked using `list`.

### `unmark` - Unmarks task
Unmarks task as incomplete, use task index to mark.

Example of usage: 
Unarking second task as complete
`unmark 2`

Expected outcome:
Prints that you have unmarked that task successfully.

```
Task at index 2 has been successfully unmarked. 
```

Print the list to confirm that it has been unmarked using `list`.

### `tag` - Adds tag to task
Adds a tag to task using its index.

Example of usage: 

`tag 2 assignment`

Expected outcome:
```
Tag #assignment successfully added
```

### `tags` - View tags of task
Prints all tags of the task using its index.

Example of usage: 

`tags 2`

Expected outcome:
```
Tags for cs2103t quiz:
#high priority
#assignment
```

## Credits
[Overflow by Noiseless-World](https://www.youtube.com/watch?v=LzFEkOck7ZA)
