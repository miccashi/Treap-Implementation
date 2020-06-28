
/**
 * This class is used to generate a weight distribution to three operations according to the experiment instruction.
 * @author shimh
 *
 */
public class Weight {
	private double insert = 0;
	private double delete = 0;
	private double search = 0;
	public Weight(double insert, double delete, double search) {
		this.insert = insert;
		this.delete = delete;
		this.search = search;
	}
	/**
	 * 
	 * @return a sequence of weights for three operations.
	 */
	public double[] getWeights() {
		double[] weights = {insert, delete, search};
		return weights;
	}
}
