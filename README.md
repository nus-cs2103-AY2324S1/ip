# Bruno - Your Productivity Companion

> "If I could be half the person my dog is, I'd be twice the human I am." - Charles Yu [(source)](https://www.goodreads.com/quotes/488888-if-i-could-be-half-the-person-my-dog-is)

### Bruno helps you remember to complete all your tasks, in order to maximise your productivity.

## Getting started

- Download the ```.jar``` file from [here](https://github.com/MadLamprey/ip/releases/tag/v0.3)
- Open Terminal/Command Prompt and change directory to the directory that contains the ```bruno.jar``` file
- Run the command ```java -jar bruno.jar```


## Features
1. Add tasks based on type - ```ToDo```, ```Deadline``` or ```Event```
2. Save and load task list from a ```.txt``` file
3. Mark/unmark tasks on completion
4. View schedule for a particular date
5. Find tasks based on a keyword

What makes Bruno the best?

- [x] text-based
- [x] easy-to-learn with simple commands
- [x] ~FAST~ SUPER FAST to use

And, it is **FREE** to use!

## Usage

### `todo` - Add task without any time limit

Adds a task of type ```todo``` to the list.

Input format:

`todo TASK`

Example of usage:

`todo laundry`

Expected output:

```
Woof. I have added this task:
[T][ ] laundry
Now you have 1 task in your list.
```

### `deadline` - Add task with an upper time limit

Adds a task of type ```deadline``` to the list.

Input format:

`deadline TASK /by YYYY/MM/DD HH:MM`

Example of usage:

`deadline ip 2023-09-22 23:59`

Expected output:

```
Woof. I have added this task:
[D][ ] ip (by: 22 September 2023 23:59)
Now you have 2 tasks in your list.
```

### `event` - Add task with a start and end time

Adds a task of type ```event``` to the list.

Input format:

`event TASK /from YYYY/MM/DD HH:MM /to YYYY/MM/DD HH:MM`

Example of usage:

`event hackathon /from 2023-09-29 18:00 /to 2023-10-01 10:00`

Expected output:

```
Woof. I have added this task:
[E][ ] hackathon (from: 29 September 2023 18:00 to: 01 October 2023 10:00)
Now you have 3 tasks in your list.
```

### `mark` - Marks the task as done

Marks the task at a particular index in the list, as done.

Input format:

`mark INDEX`

Example of usage:

`mark 1`

Expected output:

```
Woof Woof! I have marked the task as done.
[T][X] laundry
```

### `unmark` - Unmarks the task

Unmarks the task at a particular index in the list to show that it is not yet done.

Input format:

`unmark INDEX`

Example of usage:

`unmark 1`

Expected output:

```
OK, I have marked the task as not done yet.
[T][ ] laundry
```

### `delete` - Deletes task from the list

Deletes the task at a particular index in the list.

Input format:

`delete INDEX`

Example of usage:

`delete 1`

Expected output:

```
I have removed this task from your tasks:
[T][ ] laundry
Now you have 2 tasks in your list.
```

### `find` - Searches for a task from the list

Searches for task that contains a keyword that is entered by the user.

Input format:

'find KEYWORD'

Example of usage:

`find hackathon`

Expected output:

```
Here are the tasks matching your search:
1. [E][ ] hackathon (from: 29 September 2023 18:00 to: 01 October 2023 10:00)
```

### `list` - Lists all the tasks

Displays all the tasks in the list.

Input format:

`list`

Example of usage:

`list`

Expected output:

```
Here is the list of your tasks:
1. [D][ ] ip (by: 22 September 23:59)
2. [E][ ] hackathon (from: 29 September 2023 18:00 to: 01 October 2023 10:00)
```

### `schedule` - Displays schedule for a day

Displays all the tasks that lie on, or during the date input by the user

Input format:

`schedule YYYY-MM-DD`

Example of usage:

`schedule 2023-09-22`

Expected output:

```
Here is the schedule for the given date:
1. [D][ ] ip (by: 22 September 2023 23:59)
```
### `note` - Adds a note to a task

Adds a note to a task at a given index in the list of tasks.

Input format:

`note INDEX NOTE`

Example of usage:

`note 1 urgent`

Expected output:

```
I have added note to the task:
[D][ ] ip (urgent) (by: 22 September 2023 23:59)
```

### `bye` - Exits the application

Displays a message and closes the application.

Input format:

`bye`

Example of usage:

`bye`

Expected output:

```
Bye Bye! Hope to see you again soon!
```

### Command Summary

<table>
<tr><th>Action</th><th>Format, Example</th></tr>
<tr><td>TODO</td><td>todo TASK<br>Eg: todo laundry</td></tr>
<tr><td>DEADLINE</td><td>deadline TASK /by YYYY-MM-DD HH:MM<br>Eg: deadline ip tasks 
/by 2023-09-22 23:59</td></tr>
<tr><td>EVENT</td><td>event TASK /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM<br>Eg: event hackathon 
/from 2023-09-29 18:00 /to 2023-10-01 10:00
</td></tr>
<tr><td>MARK</td><td>mark INDEX<br>Eg: mark 1</td></tr>
<tr><td>UNMARK</td><td>unmark INDEX<br>Eg: unmark 1</td></tr>
<tr><td>DELETE</td><td>DELETE INDEX<br>Eg: delete 1</td></tr>
<tr><td>FIND</td><td>find KEYWORD<br>Eg: find laundry</td></tr>
<tr><td>LIST</td><td>list<br>Eg: list</td></tr>
<tr><td>SCHEDULE</td><td>schedule YYYY-MM-DD<br>Eg: schedule 2023-09-29</td></tr>
<tr><td>NOTE</td><td>note INDEX NOTE<br>Eg: note 1 difficult</td></tr>
<tr><td>BYE</td><td>bye<br>Eg: bye</td></tr></table>
