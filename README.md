ProjectEuler
============

![Euler badge!](http://projecteuler.net/profile/m09.png)

My attempt at solving project Euler problems, it'll spoil if you wanna
do them.

Some are in haskell, some in java.

Haskell programs
----------------

To launch the haskell programs, the preferred way is:

    ghc --make 1.hs -O
    ./1

Note the `-O` (with an O like Obama). For certain problems it is
required to go from exponential runtime to a linear one.

Java program
------------

To launch the java program, use maven to package it and use `-p` to
specify which problem to run. No `-p` means all the problems:

    mvn package
    java -jar target/project-euler-1.0-SNAPSHOT-jar-with-dependencies.jar -p 4

Have fun :)
