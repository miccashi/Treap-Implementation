/**
 * This class define the information an operation contains. This is used to 
 * unify the operation expression in a sequence of different operations.
 * @author shimh
 *
 */
public class Operation {
	Opt operation;
	Element e;
	/**
	 * This method is used to generate an operation
	 * @param operation donates the operation type such as "insert", "delete", and "search".
	 * @param e donates the element which will be operated.
	 */
	public Operation(Opt operation, Element e) {
		this.operation = operation;
		this.e = e;
	}
	/**
	 * This method is used to get the operation type of an operation action.
	 * @return
	 */
	public Opt getOperation() {
		return operation;
	}
	/**
	 * This method is used to get the element of an operation action.
	 * @return
	 */
	public Element getElement() {
		return e;
	}
	public String toString() {
		return this.operation +" " + this.e;
	}
}
