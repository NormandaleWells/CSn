echo @off
rem DisplayGridComponents <file> <x> <y>
rem Supplied files are g_<x>_<y>_<percent>.txt
rem where <percent> is the percent of possible edges used.
echo @on
java -cp CSn.jar edu.normandale.csn.DisplayGridComponents %1 %2 %3
