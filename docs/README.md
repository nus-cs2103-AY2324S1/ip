# User Guide

## Bot name - Termina

### Feature-Greet (lvl 0)

Greets the user when the bot is opened at first

### Feature-List (Lvl 1/2)

Type `list` to get the items stored in the list current.

### Feature-Bye (Lvl 1)

Type `bye` to exit the program. (Need to wait a few seconds for it to close)

### Feature-Mark Done (Lvl 3)

Type `mark 1` to mark item 1 as done, change the number to the item you want to mark as done. Type `unmark2` to mark item 2 as not done, change 2 to any number you want likewise.

### Feature-TaskTypes (Lvl 2/4)

3 task type you can add like todo, deadline and event to the list. Details on how to use each are further below.
Note: Dates must be in the format yyyy-mm-dd
todo - `todo code` type todo + your task name
deadline - `deadline assignment /by 2022-01-01` type deadline + task name + /by + date 
event - `event party /from 2022-01-01 /to 2023-01-01` type event + task name + /from + date + /to + date

### Feature-TextUITesting (Lvl 1/2)

In an older version, enabled textUItesting with sample data. Run the script to check its working.

### Feature-Error (Lvl 5)

If you type input that does not make sense, the chatbot will tell you so.

### Feature-Delete (Lvl 6)

Type `delete 1` to delete the first item, type `delete 2` to deleted the 2nd item and so on...

### Feature-Save (Lvl 7)

Your list data is saved in a ser file in the program and loaded on program start up so the data persists even when the program is not running. If there are errors loading the data, just delete the ser file as it might be corrupted.

### Feature-Datetime (Lvl 8)

Type datetime in format yyyy-mm-dd and when you type `list` it will convert your stored datetime to mmm dd yyyy, that is 2022-01-01 is converted to Jan 01 2022. Note: In the confrimation note after you add a task, the datetime is not converted yet so you can manually check your input. It will only show the converted version after you type `list`.

### Feature-Gradle/Junit

For development purposes, this uses Gradle and has junit test case.

### Feature-Find (Lvl 9)

Type `find hi` to search for words that contain hi. That is type find + search term to find what you need.

### Feature-GUI (Lvl 10)

Has a user friendly GUI.

### Feature-Help (Lvl C-Help)

Type `help` to get some basic help.
