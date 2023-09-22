# User Guide for nyanbot

## Features 
- [x] **Add a Todo**
- [x] **Add a Deadline**
- [x] **Add an Event**
- [x] **Accept a wide variety of dates**

### Add a Todo
Task without a time attached to it.

### Add a Deadline
Task with a deadline attached to it.

### Add an Event
Task with a start time and end time.

### Accept multiple date formats
Examples: `Sun` or `23/12/2023`

## Usage
Just click on the jar file to get started!

### `todo` - Tells nyan that the task after the command is a Todo task
Adds the task to your tasklist.

Example of usage: 

`todo buy bread`

Output:
```
Got it. I've added this task:
  [T][] buy bread
 Nyan you have 2 tasks in the list
```

### `deadline` - Tells nyan that the task after the command is a Deadline task
Adds the task to your tasklist.

Example of usage:

`deadline eat bread /by Wed`

Output:
```
Got it. I've added this task:
  [D][] eat bread (by: Sep 19 2023, 23.59pm)
 Nyan you have 3 tasks in the list
```

### `event` - Tells nyan that the task after the command is an Event task
Adds the task to your tasklist.

Example of usage:

`event make bread /from Thu /to fri`

Output:
```
Got it. I've added this task:
  [E][] make bread (from: Sep 20 2023, 23.59pm, to: Sep 21 2023, 23.59pm)
 Nyan you have 4 tasks in the list
```

### `list` - tells nyan to list out all your outstanding tasks

Example of usage:

`list`

Output:
```
Here are the tasks in your list :3
==========================================
1. [T][] think of bread
2. [T][] buy bread
3. [D][] eat bread (by: Sep 19 2023, 23.59pm)
4. [E][] make bread (from: Sep 20 2023, 23.59pm, to: Sep 21 2023, 23.59pm)
==========================================
```
