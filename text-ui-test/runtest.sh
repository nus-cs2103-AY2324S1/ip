#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# delete output from previous run
if [ -e "./TEMPORARY.TXT" ]
then
    rm TEMPORARY.TXT
fi

# stash content of SanaTasks.txt in a temporary txt file
if [ -e "/Users/ariellacallista/Desktop/SanaTasks.txt" ]
then
  cat /Users/ariellacallista/Desktop/SanaTasks.txt >> TEMPORARY.TXT
  > /Users/ariellacallista/Desktop/SanaTasks.txt
fi


# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/sana -Xlint:none -d ../bin ../src/main/java/sana/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi


# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin sana.Sana < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    cat TEMPORARY.TXT >> /Users/ariellacallista/Desktop/SanaTasks.txt
    exit 0
else
    echo "Test result: FAILED"
    cat TEMPORARY.TXT >> /Users/ariellacallista/Desktop/SanaTasks.txt
    exit 1
fi