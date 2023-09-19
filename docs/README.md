# User Guide for Duke

## Features 

Duke allows you to add and manage tasks of various types, including Todos, Events, and Deadlines.

Duke provides a user-friendly interface for interacting with your task list. You can list tasks, mark them as done, and even search for specific tasks.

## Usage

### Example of usage: 

`list`

Expected outcome:

Duke will list all your tasks in a clear format

```
Here are the tasks in your list:
1. [T][X] Buy groceries
2. [D][ ] Finish homework (by: Sep 30 2023)
3. [E][ ] Team meeting (at: Oct 5 2023)
```

`mark 1`

Expected outcome:
Duke will mark the first task as done.

```
Nice! I've marked this task as done:
[T][X] Clean the house
```

`unmark 1`

Expected outcome:
Duke will mark the first task as not done.

```
OK, I've marked this task as not done yet:
[T][] Clean the house
```

`todo Clean the house`

Expected outcome:
Duke will add a new Todo task to your list.

```
Got it. I've added this task:
[T][ ] Clean the house
Now you have 1 task(s) in the list.
```

`deadline Finish iP /by 2023-09-19`

Expected outcome:
Duke will add a new Deadline task to your list.

```
Got it. I've added this task:
[D][ ] Finish iP (by: Sep 19 2023)
Now you have 2 task(s) in the list.
```

`event CS2103T Lecture /from 2023-09-22 /to 2023-09-22`

Expected outcome:
Duke will add a new Event task to your list.

```
Got it. I've added this task:
[E][ ] CS2103T Lecture (from: Sep 22 2023 to: Sep 22 2023)
Now you have 3 task(s) in the list.
```

`delete 1`

Expected outcome:
Duke will delete the first task.

```
Noted. I've removed this task:
[D][ ] Finish homework (by: Sep 30 2023)
Now you have 2 task(s) in the list.
```

`find Lecture`

Expected outcome:
Returns a list of matching tasks.

```
Here are the matching tasks in your list:
1. [E][ ] CS2103T Lecture (from: Sep 22 2023 to: Sep 22 2023)
2. [E][ ] CS2100 Lecture (from: Sep 19 2023 to: Sep 19 2023)
```