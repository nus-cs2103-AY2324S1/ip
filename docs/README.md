# Sharky List

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/Blizzeracz/ip/releases/tag/Final).
3. Copy the file to the folder you want to use as the home folder for your ChatBot
4. Double-click the jar file or open a command terminal, cd into the folder you put the jar file in, 
and use the java -jar duke.jar command to run the application.
5. Refer to the Features below for details of each command.

## Features 

### Notes about the command format:

* Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in add NAME, NAME is a parameter which can be used as add John Doe.

* Extraneous parameters for commands that do not take in parameters (such as list, save and sort) will be give error.
e.g. if the command specifies help 123, it will be interpreted as help.

  

## Usage

### `list` - Listing all tasks

Shows a list of all persons in the address book.

Example of usage: 

`list`



Description of the outcome.

```
Here is a list of your tasks:
...
```

### `find` - Locating tasks by name

Find tasks whose name contain any of the given keywords.


Example of usage:

`find (arguments)`

* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only full words will be matched e.g. Han will not match Hans


Description of the outcome.

```
Here are the matching tasks in your list:
...
```

### `sort` - Sorts the list

Sort tasks based on their date


Example of usage:

`sort`

* The sort is sorted based on the date, giving a list of tasks that start from the earliest date.



Description of the outcome.

```
Here is the sorted list:
...
```

### `save` - Save the list for future reference

### `delete` - Delete a task from the list

Delete task based on their position in the list


Example of usage:

`delete (integer)`

Description of the outcome.

```
The following task is deleted:
...
```

### `mark/unmark` - Mark/Unmark a task

Mark/Unmark a task as done/undone based on their position in the list


Example of usage:

`mark/unmark (integer)`

Description of the outcome.

```
The following task is marked:
...
```

### `todo/deadline/event` - Adding a task

Add a task to the list

Example of usage:

`todo (argument)`

`deadline (argument) by: (date)`

`event (argument) from: (date) to: (date)`



Description of the outcome.

```
The following task is added to the list:
...
```