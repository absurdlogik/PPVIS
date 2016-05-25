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
	public static long sortTime(List<Integer> generatedList){
		long startTime, endTime, traceTime;
		startTime = System.nanoTime();
        insertionSort(generatedList);
        endTime = System.nanoTime();
        traceTime = (endTime - startTime)/1000;
        return traceTime;
	}
	public static long sortAverageTime(int size, int iterationsNum){
		long averageTime = 0, time;
		for (int num = 0; num < iterationsNum; num++){
			time = sortTime(generateList(size));
			averageTime += time;
		}
		averageTime /= iterationsNum;
		return averageTime;
	}
	private static void insertionSort(List<Integer> arr) {
		int i, j, index;
		for (i=1; i < arr.size(); i++) {
		    index = arr.get(i);
		    j = i;
		    while ((j > 0) && (arr.get(j-1) > index)) {
		      arr.set(j, arr.get(j-1));
		      j = j - 1;
		    }
		    arr.set(j, index);
		  }
    }
}
