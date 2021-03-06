import java.io.*;

class ArrayPar {
	private double[] theArray;
	private int nElems;

	public ArrayPar(int max) {
		theArray = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		if(nElems <= theArray.length)
			theArray[nElems++] = value;         // insert it
	}

	public int size() { return nElems; }

	public void display() {
		for(int j=0; j<nElems; j++)       // for each element,
			System.out.print(theArray[j] + " ");  // display it
		System.out.println("");
	}

	public double median(int left, int right, int mid) {
		double pivot = theArray[right];
		int partition = partitionIt(left, right, pivot);
		if(partition == mid)
			return theArray[partition];
		else if(partition > mid)
			return median(left, partition - 1, mid);
		else
			return median(partition + 1, right, mid);
	}

	public int partitionIt(int left, int right, double pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;
		while(true) {
			while(leftPtr < right && theArray[leftPtr] < pivot)
				leftPtr++;
			while(rightPtr > left && theArray[rightPtr] > pivot)
				rightPtr--;

			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr++, rightPtr--);
		}
		swap(leftPtr, right);
		return leftPtr;
	}

	public void swap(int dex1, int dex2) {
		double temp;
		temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	public void quickSort() {
		recQuickSort(0, nElems - 1);
	}

	public void recQuickSort(int left, int right) {
		if(left >= right)
			return;
		else {
			double pivot = theArray[right];

			int partition = partitionIt(left, right, pivot);
			// display();
			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}
	}
}

class MedianApp {
	public static void main(String[] args) {
		int maxSize = 16;             // array size
		ArrayPar arr;                 // reference to array
		arr = new ArrayPar(maxSize);  // create the array
		for(int j=0; j<maxSize; j++) {  // fill array with   
			double n = (int)(java.lang.Math.random()*199); // random numbers
			arr.insert(n);
		}
		arr.display();                // display unsorted array
		// double pivot = 100;            // pivot value

		int size = arr.size();
		int mid = size / 2;
                                       // partition array
		double median = arr.median(0, size-1, mid);
		System.out.println("Median is " + median);

		arr.quickSort();
		arr.display();

	}  // end main()
}  // end class PartitionApp

