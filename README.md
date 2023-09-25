# DogeBot :dog2:
> "Im your best friend that helps you manage your tasks c:" -DogeBot

DogeBot remembers all the things you need to do with its **excellent** memory, DogeBot is:
- a dog
- ~~cute~~friendly
- _easy_ to use

All you need to do is:
1. access it from [here](https://github.com/kenvynKwek/ip)
2. run it
3. add your tasks and take your mind off things :)

Features:
- [x] Manage different types of tasks
- [x] Search keywords in tasks
- [ ] Graphical user interface (coming soon)

Just look at how ~~cute~~friendly DogeBot's response is when you `mark` a task:
```ruby
public static void mark(int index) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Try adding some tasks first c:");
        }

        tasks.get(index).markTask(true);
        System.out.println("Good job on completing a task ! You deserve a cookie C:");
        System.out.println("\t" + tasks.get(index).toString());
    }
```
