// Brute Force algorithm
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NumberSearch {

	public static void main(String[] args) {

		/* Code to get input from file (into ArrayList) and 
		   the desired number to be checked against TAKEN FROM
		   http://stackoverflow.com/questions/3806062/how-to-open-a
		   -txt-file-and-read-numbers-in-java */

		Integer desiredNumber = 0;
		List<Integer> integers = new ArrayList<>();

		try {
			Path filePath = Paths.get("../CollectionNumbers/listNumbers-1000.txt");
			Scanner scanner = new Scanner(filePath);
			while (scanner.hasNext()) {
    			if (scanner.hasNextInt()) {
        			integers.add(scanner.nextInt());
    			} else {
        			scanner.next();
    			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Parse from list into an array
		int integerArray[] = new int[integers.size()];
		for (int i = 0; i < integers.size(); i++) {
			integerArray[i] = integers.get(i);
		}

		int[] solution = bruteForce(integerArray, desiredNumber);
		if (solution != null) {
			System.out.println(solution[0] + " and " + solution[1] + " add to your desired number and exist in the list.");
		}
	}

	public static int[] bruteForce(int[] list, int checkNum) {

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