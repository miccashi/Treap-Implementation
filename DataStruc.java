/**
 * This interface defines a data structure with some operations: insertion,
 * deletion and search. Also it has a method to return the length of element
 * in the data structure.
 * @author shimh
 *
 */
public interface DataStruc {
	public void insert(Element x);
	public void delete(int key_del);
	public Element search(int q);
	public int getLen();
}
