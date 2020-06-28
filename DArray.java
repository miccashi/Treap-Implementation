/**
 * This class is a traditional dynamic array data structure which implements the interface DataStruc.
 * This data structure contains some basic operations like insertion, deletion and search.
 * @author shimh
 *
 */
public class DArray implements DataStruc {
	Element[] array;
	int n;
	public DArray() {
		n = 0;
		array = new Element[1];
	}
	/**
	 * This method is used to double the size of the array to accommodate more element in a new array.
	 */
	private void resize() {
		int newSize = 2 * array.length;
		Element[] newArray = new Element[newSize];
		
		for(int i=0; i<array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	/**
	 * This method is used to half the size of the array when the number of elements is less than a
	 * quarter of the length of the original array.
	 */
	private void shrink() {
		int newSize = (int)array.length/2;
		Element[] newArray = new Element[newSize];
		for(int i=0; i<array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	/**
	 * This method is used to insert an element with an id and a key to the dynamic array.
	 */
	public void insert(Element x) {
		if (n == array.length) {
			resize();
		}
		array[n] = x;
		n += 1;
	}
	/**
	 * This method is used to implement a deletion operation in the dynamic array when giving a key.
	 */
	public void delete(int key_del) {
		int index = search_node(key_del);
		if (index != -1) {
			array[index] = array[n-1];
			n -= 1;
			if (n<array.length/4)
				shrink();
		}
	}
	
	/**
	 * This method is used to implement a search operation in the dynamic array when giving a search key q.
	 * @param key donates the key of the element 
	 * @return the node with the same key with the searched key if it is found in the array, else null.
	 */
	public int search_node(int q) {
		for(int i=0;i<n;i++) {
			if (array[i].getKey() == q) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * This method is used to extract the search result of the searchNode method.
	 * It will return the element with id and key when the node is not null, or return null.
	 */
	public Element search(int q) {
		int index = search_node(q);
		if (index>=0) {
			return array[index];
		}
		return null;
	}
	/**
	 * This method is used to return the length of the Treap
	 */
	@Override
	public int getLen() {
		// TODO Auto-generated method stub
		return n;
	}
}
