package BranchAndBoundParallel;
import java.lang.reflect.Array;
import java.util.Arrays;

import BranchAndBoundParallel.BranchThread;

public class BranchAndBound {
	int[] objectSet = { 1, 2, 3, 4, 5, 6, 7, 8, 9/*, 10, 11, 12, 13*/};
	int counter = 0;
	BranchThread bt[];
	LowestSequence lowest;
	
	public BranchAndBound() {
		bt = new BranchThread[objectSet.length];
		this.lowest = new LowestSequence(new int[objectSet.length], Integer.MAX_VALUE);
	}

	public void search() {
		int[] sequence = new int[objectSet.length];
		boolean[] inSequence = new boolean[objectSet.length];
		for (int i = 0; i < objectSet.length; i++) {
			sequence = new int[objectSet.length];
			inSequence = new boolean[objectSet.length];
			sequence[0] = i+1;
			inSequence[i] = true;
			//search(1, inSequence, sequence);
			bt[i] = new BranchThread(1, inSequence, sequence, objectSet, lowest);
			System.out.println("Starting thread");
			bt[i].start();
		}
	    System.out.print("Wait for the thread to complete\n");
	    for (int i = 0; i < objectSet.length; i++) {
			try {
				bt[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	public void search(int aircraftInSequence, boolean[] inSequence,
			int[] sequence) {
		if (aircraftInSequence < this.aircraftSet.length) {
			int i = 0;
			while (i < this.aircraftSet.length) {
				if (!inSequence[i]) {
					inSequence[i] = true;
					sequence[aircraftInSequence] = this.aircraftSet[i];
					aircraftInSequence++;
					this.search(aircraftInSequence, inSequence, sequence);
					aircraftInSequence--;
					sequence[aircraftInSequence] = -1;
					inSequence[i] = false;
				}
				i++;
			}
		} else {
			System.out.println(Arrays.toString(sequence));
			counter++;
		}
	}*/

	public static void main(String[] args) {
		BranchAndBound bandb = new BranchAndBound();
		long t1 = System.currentTimeMillis();
		bandb.search();
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1 + " " + bandb.counter + " Lowest cost:" + bandb.lowest.getCost() + " " + Arrays.toString(bandb.lowest.getSequence()));
		System.out.println("Cuts:" + bandb.lowest.getCounter());
	}
}
