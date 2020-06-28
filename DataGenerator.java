import java.util.HashMap;
import java.util.Random;
/**
 * This class is used to generate the data which is used to do experiment in data structures.
 * @author shimh
 *
 */
public class DataGenerator {

	private HashMap<Integer, Integer> keyMap = new HashMap<Integer, Integer>();
	private int idNext = 1;
	private Random r = new Random();
	public DataGenerator() {
	}
	public int getIDNext() {
		return idNext;
	}
	private Element gen_element(){
		int id = idNext;
		idNext += 1;
		int key = r.nextInt((int) Math.pow(10, 7));
		Element e = new Element(id, key);
		return e;
	}
	public Operation gen_insertion() {
		Element e = gen_element();
		Operation o = new Operation(Opt.INS, e);
		keyMap.put(e.getID(), e.getKey());
		return o;
	}
	public Operation gen_deletion() {
		int id_del = Math.min(r.nextInt(idNext) + 1, idNext-1);
		int key = r.nextInt((int)Math.pow(10, 7));
		if (keyMap.get(id_del) != null) {
			key = keyMap.get(id_del);
		}
		Element e = new Element(id_del, key);
		Operation o = new Operation(Opt.DEL, e);
		return o;
	}
	public Operation gen_search() {
		int q = r.nextInt((int)Math.pow(10, 7));
		Element e = new Element(q);
		Operation o = new Operation(Opt.SCH, e);
		return o;
	}
		
	
	
	
	
	
	
			
			
}
