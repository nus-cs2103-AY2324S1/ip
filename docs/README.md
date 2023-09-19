# User Guide for Scarlet

Scarlet is a chat bot used to track all your scheduling needs. 

It might judge you in the process, but it'll get it done.

## Quick Start

All you need to do to get started is,

1. Ensure you have Java ```11``` or later installed on your computer
2. Download and install the file to your desired directory
3. Open the folder either through an IDLE or CLI and run the ```Duke.Launcher``` class
4. A pop-up will then appear on your desktop. Use the box at the bottom of the window to input your command and then the box adjacent can be clicked to submit the response.

## Features 


### Adding Tasks

Scarlet will add tasks if it begins with ```todo```, ```deadline``` or ```event```. 

-If the input is incomplete, Scarlet will let you know.

-If the input is accepted, Scarlet will repeat the task and let you know how many tasks it has recorded. 

-Scarlet can also read dates if they are in the format ```yyyy-MM-dd HHmm```. If it is not, it will simply record the date as inputted.

-Scarlet will also let you handle repeated tasks. If you input a task that is already happened, Scarlet will let you know, but if you enter it again, Scarlet will then allow you to add the tasks.


Deadline tasks must be of the following format ``` deadline [task description] /by [by] ```.
Similarily, event tasks must be of the following format``` event [task description] /from [from] /to [to]  ```.


Example of usage:

`todo`


Description of the outcome.

```
_______________ 

Come on now... don't be shy, go on 
_______________ 
```

Example of usage:

`todo homework`


Description of the outcome.

```
_______________ 

I'm totally not judging... 
[T] | [ ] | cs2103t project 
 ... added to the list 
I wonder how you'll mess up this... 1
_______________ 
```

Example of usage:

`todo homework`


Description of the outcome.

```
_______________ 

I'm totally not judging... 
[T] | [ ] | cs2103t project 
 ... added to the list 
I wonder how you'll mess up this... 1
_______________ 
```

Example of usage:

`event project meeting /from 2003-06-06 1200 /to 4pm`


Description of the outcome.

```
_______________ 

I'm totally not judging... 
[E] | [ ] | project meeting  | Jun 6 2003 1200 - 4pm 
 ... added to the list 
I wonder how you'll mess up this... 2
_______________ 
```

Example of usage:

`event project meeting /from 2003-06-06 1200 /to 4pm`


Description of the outcome.

```
_______________ 

Is the schizophrenia finally starting to kick in? It's duplicated, but if you do it again I'll allow it. 
_______________ 
```

### Listing Tasks

Scarlet will list all the tasks if you input ```list```. A file of all the added tasks will be maintained even when the application is closed.

Example of usage:

`list`


Description of the outcome.

```
_______________ 

What a terrible day to be alive. 
1 - [T] | [ ] | cs2103t project 
2 - [E] | [ ] | project meeting  | Jun 6 2003 1200 - 4pm 
 
_______________ 

```

### Deleting Tasks

Scarlet will let you delete the task at a specific index using the command ```delete [index number]```. If the input is not valid, an error message will be shown.

Example of usage:

`delete 1`


Description of the outcome.

```
_______________ 

Not another mistake I hope... 
[T] | [ ] | cs2103t project 

_______________ 

```


### Marking and Unmarking Tasks

Scarlet will let you mark and unmark tasks using the command ```mark [index number]``` and ```unmark [index number]```. If you use a invalid index number, Scarlet will let you know.

Example of usage:

`mark 1`


Description of the outcome.

```
_______________ 

A proud moment of your life I am sure... 
  [E] | [X] | project meeting  | Jun 6 2003 1200 - 4pm 
_______________ 

```

### Find

Scarlet will filter through your task and show you the tasks that have the given description. The command is ```find [task desc]```.

Example of usage:

A sample tasks list was used

`list`

`find homework`


Description of the outcome.

```

What a terrible day to be alive. 
1 - [T] | [ ] | homework 
2 - [T] | [ ] | study 
3 - [D] | [ ] | homework  | tonight 
4 - [E] | [ ] | party  | 20th sept - 21st sept 
 
_______________ 

```

```
_______________ 

Let's see what you've got, shall we?
1 - [T] | [ ] | homework
2 - [D] | [ ] | homework  | tonight

_______________ 
```

### Detecting Invalid Inputs and Index Numbers

Scarlet will let you know if you have enter an input it does not understand or if you used an input at the wrong index number.

Example of usage:

`invalid input`


Description of the outcome.

```
_______________ 

I'm totally not judging... 
[E] | [ ] | party  | 20th sept - 21st sept 
 ... added to the list 
I wonder how you'll mess up this... 4
_______________ 

```
Example of usage:

`mark 0`


Description of the outcome.

```
_______________ 

1 mama and 1 papa made 2 dummies
_______________ 
```






