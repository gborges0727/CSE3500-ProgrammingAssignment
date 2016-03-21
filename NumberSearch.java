// Brute Force algorithm
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NumberSearch {

	public static void main(String[] args) {

		Integer desiredNumber = 99832;
		List<Integer> integers = new ArrayList<>();

		try {
			File file = new File("CollectionNumbers/listNumbers-10000.txt");
			Scanner scanner = new Scanner(file);
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

		// Run bruteForce solution on the list
		long bruteForceStartTime = System.nanoTime();
		int[] solution1 = bruteForce(integerArray, desiredNumber);
		long bruteForceEndTime = System.nanoTime();
		long bruteForceRunTime = bruteForceEndTime - bruteForceStartTime;
		if (solution1 != null) {
			System.out.println(solution1[0] + " and " + solution1[1] + " add to your desired number and exist in the list.");
			System.out.println("Run time for bruteForce = " + bruteForceRunTime + " in nano-seconds or "  + (bruteForceRunTime / 1000000000.0) + " seconds");
		}

		// Run binarySearch solution on the list
		long binarySearchStartTime = System.nanoTime();
		int[] solution2 = binarySearch(integerArray, desiredNumber);
		long binarySearchEndTime = System.nanoTime();
		long binarySearchRunTime = binarySearchEndTime - binarySearchStartTime;
		if (solution2 != null) {
			System.out.println(solution2[0] + " and " + solution2[1] + " add to your desired number and exist in the list.");
			System.out.println("Run time for binarySearch = " + binarySearchRunTime + " in nano-seconds or " + (binarySearchRunTime / 1000000000.0) + " seconds");
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
				}
			}
			listPointer++;
		}

		if (listIndexSolution[0] != -1) {
			returnArray[0] = list[listIndexSolution[0]];
			returnArray[1] = list[listIndexSolution[1]];
			return returnArray;
		}

		return null;
	}

	public static int[] binarySearch(int[] list, int checkNum) {
		Arrays.sort(list);
		int[] solution = new int[2];
		int frontPointer = 0;
		int backPointer = list.length - 1;

		while (frontPointer < backPointer) {
			int pointerSum = list[frontPointer] + list[backPointer];
			if (pointerSum == checkNum) {
				solution[0] = list[frontPointer];
				solution[1] = list[backPointer];
				return solution;
			}
			else if (pointerSum < checkNum) {
				frontPointer++;
			}
			else {
				backPointer--;
			}
		}

		return null;
	}
}

