# Post Correspondence Problem
Name: Thi Thu Hang Nguyen

## General
Recommended: Use the command line arguments to run this program </br>
	
	# Step 1: Download the two JAVA files: Node.java and PostCorrespondence.java
	# Step 2: Run the following commands to create class paths
		- javac -d "classes" "<Path to Node.java>"
		- javac -d "classes" -classpath "classes" "<Path to PostCorrespondence.java"
	# Step 3: Run the following command to Execute the program to solve the problem
		- java -classpath "classes" "<Path to PostCorrespondence.java>"

## Example of running the program
'''
% javac -d "classes" "/Users/knguyen/Google Drive/NJIT/SPRING2021/CS370-452/Assignment1/FINAL/Node.java" </br>

% javac -d "classes" -classpath "classes" "/Users/knguyen/Google Drive/NJIT/SPRING2021/CS370-452/Assignment1/FINAL/PostCorrespondence.java" </br>

% java -classpath "classes" "/Users/knguyen/Google Drive/NJIT/SPRING2021/CS370-452/Assignment1/FINAL/PostCorrespondence.java" </br>
Enter max size of queue </br>
5 </br>
Enter max size of depth </br>
50 </br>
Enter flag </br>
0 </br>
Enter number of domino </br>
3 </br>
Enter your domino(s): </br>
1 bb b </br>
2 a aab </br>
3 abbba bb </br>
Solution Size (# of dominoes): 4 (Solution found in Stage 1) </br>
The solution sequence is: D2,D3,D2,D1 </br>
The top and bottom of the sequence looks like: aabbbaabb/aabbbaabb </br>
'''
