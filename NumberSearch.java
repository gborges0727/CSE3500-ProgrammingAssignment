// Brute Force algorithm
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NumberSearch {

	public static void main(String[] args) {

		List<Integer> solutionIntegers = new ArrayList<>();
		List<Integer> listIntegers = new ArrayList<>();

		try {
			File file1 = new File("CollectionNumbers/listNumbers-10000-nsol.txt");
			Scanner scanner = new Scanner(file1);
			while (scanner.hasNext()) {
    			if (scanner.hasNextInt()) {
        			solutionIntegers.add(scanner.nextInt());
    			} else {
        			scanner.next();
    			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			File file2 = new File("CollectionNumbers/listNumbers-10000.txt");
			Scanner scanner = new Scanner(file2);
			while (scanner.hasNext()) {
    			if (scanner.hasNextInt()) {
        			listIntegers.add(scanner.nextInt());
    			} else {
        			scanner.next();
    			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Parse from integerList into listArray
		int listArray[] = new int[listIntegers.size()];
		for (int i = 0; i < listIntegers.size(); i++) {
			listArray[i] = listIntegers.get(i);
		}

		// Parse from solutionIntegers into solutionArray
		int solutionArray[] = new int[solutionIntegers.size()];
		for (int i = 0; i < solutionIntegers.size(); i++) {
			solutionArray[i] = solutionIntegers.get(i);
		}

		// Run bruteForce solution on the list
		long bruteForceStartTime = System.nanoTime();
		for (int i = 0; i < solutionIntegers.size(); i++) {
			int desiredNumber = solutionIntegers.get(i);
			int[] solution1 = bruteForce(listArray, desiredNumber);
			if (solution1 != null) {
				System.out.println(solution1[0] + " and " + solution1[1] + " add to " + desiredNumber + " and exist in the list.");
			} else {
				System.out.println("No solution found in the list for: " + desiredNumber);
			}
		}
		long bruteForceEndTime = System.nanoTime();
		long bruteForceRunTime = bruteForceEndTime - bruteForceStartTime;
		System.out.println("Run time for bruteForce = " + bruteForceRunTime + " in nano-seconds or "  + (bruteForceRunTime / 1000000000.0) + " seconds");
		System.out.println();
		
		// Run binarySearch solution on the list
		long binarySearchStartTime = System.nanoTime();
		for (int i = 0; i < solutionIntegers.size(); i++) {
			int desiredNumber = solutionIntegers.get(i);
			int[] solution1 = binarySearch(listArray, desiredNumber);
			if (solution1 != null) {
				System.out.println(solution1[0] + " and " + solution1[1] + " add to " + desiredNumber + " and exist in the list.");
			} else {
				System.out.println("No solution found in the list for: " + desiredNumber);
			}
		}
		long binarySearchEndTime = System.nanoTime();
		long binarySearchRunTime = binarySearchEndTime - binarySearchStartTime;
		System.out.println("Run time for binarySearch = " + binarySearchRunTime + " in nano-seconds or " + (binarySearchRunTime / 1000000000.0) + " seconds");
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

