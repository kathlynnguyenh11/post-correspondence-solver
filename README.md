# Post Correspondence Problem

## Notes
> Programming is a creative work and the academic regulations that apply to plagiarizing prose also apply to plagiarizing code. Plagiarism is “the use of any outside source without proper acknowledgment.” It ranges from “verbatim copying” (e.g., cutting-and-pasting code) to “thorough paraphrasing” (e.g., changing variable names or rearranging code). 

## About
```
Post Correspondence Problem is a popular undecidable problem that was introduced by Emil Leon Post in 1946. It is simpler than Halting Problem.

In this problem we have N number of Dominos (tiles). The aim is to arrange tiles in such order that string made by Numerators is same as string made by Denominators.
In simple words, lets assume we have two lists both containing N words, aim is to find out concatenation of these words in some sequence such that both lists yield same result.

Let’s try understanding this by taking two lists A and B

A=[aa, bb, abb] and B=[aab, ba, b] 
Now for sequence 1, 2, 1, 3 first list will yield aabbaaabb and second list will yield same string aabbaaabb.
So the solution to this PCP becomes 1, 2, 1, 3.
```

## General
Recommended: Use the command line arguments to run this program </br>
	
	# Step 1: Download the two JAVA files: Node.java and PostCorrespondence.java
	# Step 2: Run the following commands to create class paths
		- javac -d "classes" "<Path to Node.java>"
		- javac -d "classes" -classpath "classes" "<Path to PostCorrespondence.java"
	# Step 3: Run the following command to Execute the program to solve the problem
		- java -classpath "classes" "<Path to PostCorrespondence.java>"

## Example of running the program
```
% javac -d "classes" "Node.java" 

% javac -d "classes" -classpath "classes" "PostCorrespondence.java" 

% java -classpath "classes" "PostCorrespondence.java" 
Enter max size of queue 
5 
Enter max size of depth 
50
Enter flag
0 
Enter number of domino 
3 
Enter your domino(s): 
1 bb b 
2 a aab
3 abbba bb 
Solution Size (# of dominoes): 4 (Solution found in Stage 1) 
The solution sequence is: D2,D3,D2,D1 
The top and bottom of the sequence looks like: aabbbaabb/aabbbaabb 
```


