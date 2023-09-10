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

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/koko -Xlint:none -d ../bin ../src/main/java/koko/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# test should be run without pre-saved data
mkdir ./backup-data
mv ./data/*.txt ./backup-data


# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin koko.Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi

# restore original data files
rm ./data/*.txt
mv ./backup-data/*.txt ./data
