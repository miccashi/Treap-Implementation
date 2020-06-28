import java.util.Random;
/**
 * This class is used to implement multiple experiments in Treap and dynamic array by 
 * setting up different parameters to measure the performance of these two data structures.
 * @author shimh
 *
 */
public class Experiment {
	private Random r = new Random();
	private enum ExpDS {TREAP, DARRAY};
	private enum ExpOp {INS,DEL,SCH,MIX};
	private Opt[] o = {Opt.INS, Opt.DEL, Opt.SCH};

	

	public static void main(String[] args) {
		Experiment e = new Experiment();
		
		double[] lgt = {0.1, 0.2, 0.5, 0.8, 1};
		double[] sameLen = {1,1,1,1,1};

		double[] wb = new double[5];		
		double[] wghtBase = {0.1, 0.5, 1, 5, 10};
		
		
		e.e(ExpDS.TREAP, ExpOp.INS, wb, lgt);
		e.e(ExpDS.DARRAY, ExpOp.INS, wb, lgt);
		
		e.e(ExpDS.TREAP, ExpOp.DEL, wghtBase, sameLen);
		e.e(ExpDS.DARRAY, ExpOp.DEL, wghtBase, sameLen);
		
		e.e(ExpDS.TREAP, ExpOp.SCH, wghtBase, sameLen);
		e.e(ExpDS.DARRAY, ExpOp.SCH, wghtBase, sameLen);

		e.e(ExpDS.TREAP, ExpOp.MIX, wb, lgt);
		e.e(ExpDS.DARRAY, ExpOp.MIX, wb, lgt);
	}
	/**
	 * This method is used to conduct an experiment within different parameters for the same operation.
	 * @param ds explains the data structure which is used to do the experiment.
	 * @param eop explains the experiment operation that is mainly measured.
	 * @param w means a sequence of the weight of the operation that is measured in the experiment.
	 * @param length means a sequence of the number of elements that are needed to operated. 
	 */
	public void e(ExpDS ds, ExpOp eop, double[] wghtBase, double[] lgtBase) {
		
		// set up parameters for each experiment.
		Weight[] w = new Weight[lgtBase.length];
		int[] length = new int[lgtBase.length];
		for(int i=0; i<5;i++) {
			if(eop==ExpOp.INS)
				w[i] = new Weight(1,0,0);
			else if(eop==ExpOp.DEL)
				w[i] = new Weight(1-wghtBase[i]/100,wghtBase[i]/100,0);
			else if(eop==ExpOp.SCH)
				w[i] = new Weight(1-wghtBase[i]/100,0,wghtBase[i]/100);
			else
				w[i] = new Weight(0.9,0.05,0.05);
			length[i] = (int)(lgtBase[i]*Math.pow(10, 6));
		}
		
		// conduct an experiment in five times within different parameters.
		long[] duration = new long[length.length];
		for(int i=0;i<5;i++) {
//			System.out.println("*****turn "+i+"******");
			DataStruc object = null;
			if (ds==ExpDS.DARRAY)
				object = new DArray();
			else if (ds == ExpDS.TREAP)
				object = new Treap(); 
			double[] weights = w[i].getWeights();
			duration[i] = Exp(object, weights , length[i]);
//			System.out.println(object.getLen());
		}
		
		// show the durations of a data structure for a certain experiment 
		System.out.print(ds+"("+eop+"): [");
		for(int i=0;i<length.length;i++) {
			System.out.print((double)duration[i]/1000+", ");
		}
		System.out.println("]");
	}

	/**
	 * This method is used to conduct a sequence of one operation in a certain number of times.
	 * @param object is the data structure that is used to do the operation.
	 * @param weights means the different weights of insertion, deletion and search among required operations
	 * @param length means the number of operations that is needed to do.
	 * @return
	 */
	public long Exp(DataStruc object, double[] weights, int length) {

		Operation[] seq = updateSeq(weights,length);
		
		Long startTime = System.currentTimeMillis();
		for(int j=0;j<seq.length;j++) {
			Opt operation = seq[j].getOperation();
			Element element = seq[j].getElement();
			
			if (operation == Opt.INS) {
				object.insert(element);}
			else if (operation == Opt.DEL) {
				object.delete(element.getKey());}
			else
				object.search(element.getKey());
		}
		return System.currentTimeMillis()-startTime;
	}
	
	
	/**
	 * This method is used to generate a sequence of operations in a certain length according
	 * to the weights of different operations.
	 * @param weights donates the weights of different operations (insertion, deletion and search)
	 * @param length means the length of the sequence.
	 * @return a sequence of operations.
	 */
	public Operation[] updateSeq(double[] weights, int length) {
		DataGenerator dg = new DataGenerator();
		Operation[] seq = new Operation[length];
		double[] wghts = new double[weights.length];
		wghts[0] = weights[0];
		// The following two loops are used to generator a increasing sequence of the weights 
		// and which locate the random probability to a certain operation.
		for(int i=1;i<weights.length;i++) 
			wghts[i] = wghts[i-1]+weights[i];
		
		for(int i=0;i<length;i++) {
			double random = r.nextDouble();
			int k = 0;
			while (k<wghts.length) {
				if (random > wghts[k]) 
					k += 1;
				else
					break;
			}

			if (o[k]==Opt.INS)
				seq[i] = dg.gen_insertion();
			else if (o[k]==Opt.DEL)
				seq[i] = dg.gen_deletion();
			else
				seq[i] = dg.gen_search();
		}
		return seq;
	}
}
