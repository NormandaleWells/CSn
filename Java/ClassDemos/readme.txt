To run the Show*Sort programs, download the algs4.jar file from

    https://algs4.cs.princeton.edu/code/algs4.jar

and compile as, for example:

    javac -cp .;algs4.jar ShowHeapSort.java
    java -cp .;algs4.jar ShowHeapSort

The HashAnalysis program requires the CSn.jar file.  To
generate that, open the ../CSn folder, open a Java file
to force it to load Java projects, and in the Java
projects section select Rebuild All and Export Jar.
For the Export Jar dialog, select 'bin' as the
files to add (not JUnit or the other one that will
be listed).

    javac -cp .;CSn.jar HashAnalysis.java
    java -cp .;CSn.jar HashAnalysis Point2d 1MPts.txt 250007 18
    java -cp .;CSn.jar HashAnalysis String Dickens.txt 46181 16
