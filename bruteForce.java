// Brute Force algorithm
import java.util.*;

public class bruteForce {

	public static void main(String[] args) {

		/* Code to get input from file (into ArrayList) and 
		   the desired number to be checked against */

		Scanner input = new Scanner(System.in);

		// Write check as to if array has -1 at index 0: if so, no solution.

	}

	public int[] findSolution(int[] list, int checkNum) {

		int[] listIndexSolution = {-1, -1}; // Intialized with -1: if remains -1 after the loop, no solution exists
		int[] returnArray = {0, 0};
		int listPointer = 0; // Pointer to continue forward in the list
		while (listPointer < list.length) {
			for(int i = 0; i < listPointer; i++) {
				if(list[listPointer] + list[i] == checkNum) {
					listIndexSolution[0] = listPointer;
					listIndexSolution[1] = i;
					return returnArray;
				}
			}
		}

		if (listIndexSolution[0] != -1) {
			returnArray[0] = listIndexSolution[0];
			returnArray[1] = listIndexSolution[1];
			return returnArray;
		}

		return null;
	}
}