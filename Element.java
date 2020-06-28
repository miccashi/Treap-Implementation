/**
 * This class is used to describe some attributes and some method that element in the data structure
 * contains.
 * @author shimh
 *
 */
public class Element {
	
	private int id;
	private int key;
	public Element() {}
	public Element(int key) {
		this.key = key;
	}
	public Element(int id, int key) {
		this.id = id;
		this.key = key;
	}
	public int getID() {
		return id;
	}
	public int getKey() {
		return key;
	}
	public String toString() {
		return this.id + " "+this.key;
	}
}
