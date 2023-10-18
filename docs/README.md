# User Guide for Skog

## Features


Here are some basic commands to use Skog:
```
todo / t
deadline / d
event / e
list / l
find / f
mark / m 
unmark / um
delete / del
bye
```

## Usage

### `todo` - A task with only a description field and no time constraints

Format: 

`todo [STRING]`

Example of usage: 

`todo exercise`

Expected outcome:

```
Got it, I've added this task:
T | 0 | exercise
```

### `deadline` - A task with an end-date to meet

Format: 

`deadline [STRING] /by [yyyy-mm-dd]`

Example of usage:

`deadline homework /by 2023-08-12`

Expected outcome:

```
Got it, I've added this task:
D | 0 | homework | Aug 12 2023
```

### `event` - A task with a starting date and an ending date

Format: 

`event [STRING] /from [yyyy-mm-dd] /to [yyyy-mm-dd]`

Example of usage:

`event exam /from 2023-08-13 /to 2023-08-13`

Expected outcome:

```
Got it, I've added this task:
E | 0 | exam | Aug 13 2023 - Aug 13 2023
```

### `list` - Lists out all added tasks

Format: 

`list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. T | 0 | exercise
2. D | 0 | homework | Aug 12 2023
3. E | 0 | exam | Aug 13 2023 - Aug 13 2023
```

### `find` - Shows all the tasks with containing a specified word

Format: 

`find [STRING]`

Example of usage:

`find exercise`

Expected outcome:

```
Here are the matching tasks in your list:
T | 0 | exercise
```

### `mark` - Marks specified task as done

Format: 

`mark [int]`

Example of usage:

`mark 1`

Expected outcome:

```
Got it, I've marked this task:
T | 1 | exercise
```

### `unmark` - Unmarks a specified task

Format: 

`unmark [int]`

Example of usage:

`unmark 1`

Expected outcome:

```
Got it, I've unmarked this task:
T | 0 | exercise
```

### `delete` - Deletes a specified task from the list

Format: 

`delete [int]`

Example of usage:

`delete 1`

Expected outcome:

```
Noted, I've removed this task:
T | 0 | exercise
```

### `bye` - Ends the chat bot and saves the data into a text file

Format: 

`bye`

Example of usage:

`bye`

Expected outcome:

A text file and data file will be created containing all tasks

```
Goodbye!
```