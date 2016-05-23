package lab3.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortTime {
	public static int AlgNum = 2;
	
	public static List<Integer> generateList(int listSize){
		List<Integer> generatedList = new ArrayList<Integer>();
		for (int listIter = 0; listIter < listSize; listIter++) {
			generatedList.add(new Random (System.currentTimeMillis()).nextInt(2000)-1000);
		}
		
		return generatedList;
	}
	public static List<Integer> sortTime(List<Integer> generatedList){
		List<Integer> sortTimeList = new ArrayList<Integer>();
		long startTime, endTime, traceTime;
		startTime = System.nanoTime();
        Collections.sort(generatedList);
        endTime = System.nanoTime();
        traceTime = (endTime - startTime)/1000;
        sortTimeList.add((int) traceTime);
		
        startTime = System.nanoTime();
        bubbleSort(generatedList);
        endTime = System.nanoTime();
        traceTime = (endTime - startTime)/1000;
        sortTimeList.add((int) traceTime);
        
		return sortTimeList;
	}
	private static void bubbleSort(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int t = arr.get(j);
                    arr.set(j, j + 1);
                    arr.set(j + 1, t);
                }
            }
        }
    }
}
