package BranchAndBoundParallel;
import java.util.Arrays;


public class LowestSequence {
	private int[] sequence;
	private int cost;
	private int counter;
	
	public synchronized int getCounter() {
		return counter;
	}

	public synchronized void addCounter() {
		this.counter++;
	}
	
	public synchronized void setCounter(int counter) {
		this.counter = counter;
	}

	public LowestSequence(int[] sequence, int cost) {
		this.sequence = sequence;
		this.cost = cost;
	}

	public synchronized int[] getSequence() {
		return sequence;
	}

	public synchronized void setSequence(int[] sequence) {
		this.sequence = new int[sequence.length];
		for(int i = 0; i < sequence.length; i++) {
			this.sequence[i] = sequence[i];
		}
		
	}

	public synchronized int getCost() {
		return cost;
	}

	public synchronized void setCost(int cost) {
		this.cost = cost;
	}
	
	
}
