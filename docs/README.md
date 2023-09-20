# User Guide


## Usage

### `todo` taskDescription

This adds a todo task to your list of tasks.

Example of usage: 

`todo Homework`

Expected outcome:

Got it! I've added this task:
[T][ ] Homework
Now you have 1 task in the list.

 <br />

### `deadline` taskDescription /by dd-mm-yyyy HH:MM

This adds a deadline task to your list of tasks specified with a date.

Example of usage: 

`deadline CS2103T assignment /by 21-09-2023 23:59`

Expected outcome:

Got it! I've added this task:
[D][ ] CS2103T assignment (by: Sep 21 2023, 23:59)
Now you have 2 tasks in the list.


### `event` taskDescription /from dd-mm-yy HH:MM /to dd-mm-yy HH:MM

This adds an event to your list of tasks with a frome date and a to date.

Example of usage: 

`event CS2103T Team Meeting /from 21-09-2023 21:00 /to 21-09-2023 22:00`

Expected outcome:

Got it! I've added this task:
[E][ ] CS2103T Team Meeting (from Sep 21 2023, 21:00 to Sep 21 2023, 22:00)
Now you have 3 tasks in the list.


### `list`

This command displays the list of tasks you have.

Example of usage: 

`list`

Expected outcome:
Here are the tasks in your list:
1. [T][ ] Homework
2. [D][ ] CS2103T assignment (by: Sep 21 2023, 23:59)
3. [E][ ] CS2103T Team Meeting (from Sep 21 2023, 21:00 to Sep 21 2023, 22:00)


### `mark` taskNumber

This command marks the corresponding task in the list as done

Example of usage: 

`mark 1`

Expected outcome:
Nice! I've marked this task as done:
[T][X] Homework


### `unmark` taskNumber

This command unmarks the corresponding task in the list

Example of usage: 

`umark 1`

Expected outcome:
Nice! I've marked this task as not done yet:
[T][ ] Homework


### `delete` taskNumber

This command deletes the corresponding task from the list

Example of usage: 

`delete 1`

Expected outcome:
Noted. I've removed this task:
[T][ ] Homework


### `find` taskDescription

This command returns a list of task that matches the taskDescription given

Example of usage: 

`find CS2103T`

Expected outcome:
Here are the matching tasks in your list:
1. [D][ ] CS2103T assignment (by: Sep 21 2023, 23:59)
2. [E][ ] CS2103T Team Meeting (from Sep 21 2023, 21:00 to Sep 21 2023, 22:00)


### `update` taskNumber /by dd-mm-yy HH:MM

This command updates the time of an event or deadline task

Example of usage: 

`update 1 /by 22-09-2023 23:59`

Expected outcome:
That task has been updated:
[D][ ] CS2103T assignment (by: Sep 22 2023, 23:59)
