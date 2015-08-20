package BranchAndBoundSerial;
import java.util.Arrays;

public class BranchAndBoundSerial {
	int[] aircraftSet = { 1, 2, 3, 4, 5,6,7,8,9/*,10,11,12,13,14,15,16,17,18,19,20*/};
	int counter;
    int[] sequence;
    boolean[] inSequence;
    int aircraftInSequence;
	int lowestCost;
	int[] lowestSequence;
	int[] costSorted;
    int[][] costGrid = {{0,440,66,92,465,485,408,821,833,702,597,272,534,795,252,176,120,628},
			{440,0,374,348,38,58,78,394,406,275,170,168,80,557,560,264,320,188},
			{66,374,0,26,399,419,342,755,767,636,531,206,449,730,186,110,54,562},
			{92,348,26,0,373,393,312,668,741,610,505,180,442,756,212,84,28,536},
			{465,38,399,373,0,20,57,356,368,237,132,193,66,596,585,289,345,226},
			{485,58,419,393,20,0,77,376,388,257,152,213,89,616,605,309,365,246},
			{408,78,342,312,57,77,0,413,425,294,189,224,549,368,957,941,645,701},
			{821,394,755,668,356,376,413,0,250,119,224,549,368,957,941,645,701,582},
			{833,406,767,741,368,388,425,250,0,131,236,561,378,968,953,657,713,594},
			{702,275,636,610,237,257,294,119,131,0,105,430,247,833,822,526,582,463},
			{597,170,531,505,132,152,189,224,236,105,0,325,142,728,717,421,477,358},
			{272,168,206,180,193,213,224,549,561,430,325,0,243,728,392,96,152,356},
			{534,80,449,442,66,89,549,368,378,247,142,243,0,607,635,358,414,268},
			{795,557,730,756,596,616,368,957,968,833,728,728,607,0,541,822,783,369},
			{252,560,186,212,585,605,957,941,953,822,717,392,635,541,0,296,240,429},
			{176,264,110,84,289,309,941,645,657,526,421,96,358,822,296,0,56,452},
			{120,320,54,28,345,365,645,701,713,582,477,152,414,783,240,56,0,508},
			{628,188,562,536,226,246,701,582,594,463,358,356,268,369,429,452,508,0}};

    public BranchAndBoundSerial() {
        this.sequence = new int[aircraftSet.length];
        this.inSequence = new boolean[aircraftSet.length];
        this.lowestSequence = new int[aircraftSet.length];
        this.aircraftInSequence = 0;
        this.lowestCost = Integer.MAX_VALUE;
        this.costSorted = new int[costGrid.length*costGrid.length];
		//System.out.println(costSorted.length);
		for(int i = 0; i < costGrid.length; i++) {
			for(int j = 0; j < costGrid.length; j++) {
				if(costGrid[i][j] != 0) {
					costSorted[i*costGrid.length + j] = costGrid[i][j];
				}
				else  {
					costSorted[i*costGrid.length + j] = Integer.MAX_VALUE;
				}
					
			}
		}
		Arrays.sort(costSorted);
		//System.out.println(Arrays.toString(costSorted));
    }

    public void search() {
        if (this.aircraftInSequence < this.aircraftSet.length) {
            int i = 0;
            while (i < this.aircraftSet.length) {
                if (!this.inSequence[i]) {
                    this.inSequence[i] = true;
                    this.sequence[this.aircraftInSequence] = this.aircraftSet[i];
                    this.aircraftInSequence++;
					int costOfSequence = cost(aircraftInSequence);
                    if(costOfSequence < lowestCost) {
                    	this.search();
                    } else {
                    	this.counter++;
                    }
                    this.aircraftInSequence--;
                    this.sequence[this.aircraftInSequence] = -1;
                    this.inSequence[i] = false;
                }
                i++;
            }
        } else {
			int costOfSequence = cost(aircraftInSequence);
            //System.out.println(Arrays.toString(this.sequence));
			if(costOfSequence < lowestCost) {
				lowestCost = costOfSequence;
				this.lowestSequence = new int[sequence.length];
				for(int i = 0; i < sequence.length; i++) {
					this.lowestSequence[i] = sequence[i];
				}
			}
        }
    }
    
	public int cost(int aircrafts) {
		int cost = 0;
		if(aircrafts > 1) {
			for(int i = 0; i < (aircrafts-1); i++) {
				cost += costGrid[sequence[i]][sequence[i+1]];
			}
			for(int i = 0; i < sequence.length - aircrafts; i++) {
				cost += costSorted[i*2];
			}
		}
		for(int i = 0; i < 500; i++) {
			Math.pow(2, 5);
		}
		return cost;
	}


    public static void main(String[] args) {
		BranchAndBoundSerial bandb = new BranchAndBoundSerial();
		long t1 = System.currentTimeMillis();
		bandb.search();
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1 + " " + bandb.counter + " Lowest cost:" + bandb.lowestCost + " " + Arrays.toString(bandb.lowestSequence));
    }
}
