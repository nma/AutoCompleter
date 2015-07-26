Running on Command Line
=======================
<pre>
$> mvn clean compile assembly:single 
$> ./run.sh -f <file-name> -s <comma seperate search string>
</pre>
Example
=======

<pre>
$>  time ./run.sh -f words.txt
7.84s user 0.45s system 278% cpu 2.975 total

$> time ./run.sh -f words.txt -s "arm"
7.60s user 0.40s system 264% cpu 3.024 total

$> cat words.txt | wc -l
235886

$> ./run.sh -f words.txt -s "arm" | wc -l
60

$> ./run.sh -f words.txt -s "arm,bo,fo,lo,li,ba" | wc -l
5899

$> time ./run.sh -f words.txt -s "arm,bo,fo,lo,li,ba"
8.27s user 0.47s system 260% cpu 3.351 total
</pre>
