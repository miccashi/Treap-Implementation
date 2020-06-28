
import java.util.Random;

/**
 * This class is a Treap data structure which implements the interface DataStruc.
 * The Treap contains some basic operations like insertion, deletion and search.
 * @author shimh
 *
 */
public class Treap implements DataStruc{
	private Node root = null;
	// treapLen is used to record the length of the Treap.
	private int treapLen;
	// Random object is used to generate a random priority for each node in the Treap.
	private Random r = new Random();
	
	public Treap() {
	}
	
	/**
	 * This method is used to return the length of the Treap
	 */
	public int getLen() {
		return treapLen;
	}
	/**
	 * This method is used to insert an element with an id and a key to the Treap.
	 */
	public void insert(Element x) {
		// TODO Auto-generated method stub
		int priority = r.nextInt((int)Math.pow(10, 7));

		Node node = new Node(x,priority);
		treapLen += 1;

		if (root==null) {
			root = node;
			return;
		}
		Node insertPos = root;
		while(insertPos != null) {
			if(node.key<insertPos.key || 
					node.key==insertPos.key && node.id<insertPos.id) {
				if(insertPos.lChild==null) {
					insertPos.setL(node);
					break;
				}
				insertPos = insertPos.lChild;	
			}
			else {
				if(insertPos.rChild==null) {
					insertPos.setR(node);
					break;
				}
				insertPos = insertPos.rChild;
			}		
		}
		rotate(node.parent, node);	
	
	}
	/**
	 * This method is used to maintain the Treap to retain the properties of the Treap after the insertion operation.
	 * @param parent donates the initial parent node in the Treap with violation problem.
	 * @param child donate the initial child node in the Treap with violation problem
	 */
	private void rotate(Node parent, Node child) {
		Node p = parent;
		while(p!=null&&child!=null&&child.priority<p.priority) {
			if(child==p.lChild)
				rightRotate(p, child);
			else
				leftRotate(p, child);
			p = child.parent;
			if(p==null)
				root = child;
		}
	}
	
	/**
	 * This method is used to implement the right rotation
	 * @param parent donates the initial parent node in the Treap with violation problem.
	 * @param child donate the initial child node in the Treap with violation problem
	 */
	private void rightRotate(Node parent,Node child) {
		Node anotherC = child.rChild;
		Node[] pc = sameRotate(parent, child);
		Node newP = pc[0];
		Node newC = pc[1];
		newP.rChild = newC;
		newC.lChild = anotherC;
		if(anotherC!=null)
			anotherC.parent = newC;
	}
	/**
	 * This method is used to implement the left rotation
	 * @param parent donates the initial parent node in the Treap with violation problem.
	 * @param child donate the initial child node in the Treap with violation problem
	 */
	private void leftRotate(Node parent,Node child) {
		Node anotherC = child.lChild;
		Node[] pc = sameRotate(parent, child);
		Node newP = pc[0];
		Node newC = pc[1];
		newP.lChild = newC;
		newC.rChild = anotherC;
		if(anotherC!=null)
			anotherC.parent = newC;
	}
	/**
	 * This method is used to extract the same operation in the left and right rotation.
	 * @param parent donates the parent node in the Treap with violation problem.
	 * @param child donate the child node in the Treap with violation problem
	 */
	private Node[] sameRotate(Node parent, Node child) {
		Node grandma = parent.parent;
		Node new_parent = child;
        Node new_child = parent;
        new_parent.parent = grandma;
        if (grandma!=null) {
            if (parent == grandma.lChild)
                grandma.lChild = new_parent;
            else
                grandma.rChild = new_parent;
        }
        new_child.parent = new_parent;
        Node[] npc = {new_parent, new_child};
        
        return npc;
	}
	
	/**
	 * This method is used to maintain the Treap to retain the properties of the Treap after the deletion operation.
	 * @param parent donates the initial parent node in the Treap with violation problem.
	 * @param child donate the initial child node in the Treap with violation problem
	 */
	private void rotateAfterDel(Node parent) {
		Node p = parent;
		while(p.lChild!=null||p.rChild!=null) {
			Node smallNode = null;
			if(p.lChild!=null && p.rChild!=null) {
				if(p.lChild.priority<p.rChild.priority)
					smallNode = p.lChild;
				else
					smallNode = p.rChild;
			}
			else if(p.lChild != null)
				smallNode = p.lChild;
			else
				smallNode = p.rChild;
			
			if(smallNode.priority<p.priority) {
				if(smallNode==p.lChild)
					rightRotate(p,smallNode);
				else
					leftRotate(p,smallNode);
				if(smallNode.parent==null)
					root = smallNode;
			}
			
		}
		
	}
	
	/**
	 * This method is used to implement a deletion operation in the Treap when giving a key.
	 */
	public void delete(int key_del) {
		// TODO Auto-generated method stub
		Node n = searchNode(key_del);
		if (n!=null) {
			n.priority = 1000000000;
			Node searchNode = n;
			rotateAfterDel(searchNode);
			if (searchNode.parent!=null) {
				if(searchNode.parent.lChild==searchNode)
					searchNode.parent.lChild = null;
				else
					searchNode.parent.rChild = null;
			}
			searchNode.parent = null;
			treapLen -= 1;
			if (treapLen==0)
				root = null;
		}
	}
	
	/**
	 * This method is used to extract the search result of the searchNode method.
	 * It will return the element with id and key when the node is not null, or return null.
	 */
	public Element search(int q) {
		// TODO Auto-generated method stub
		Node node = searchNode(q);
		if (node!=null)
			return node.e;
		return null;
	}
	/**
	 * This method is used to implement a search operation in the Treap when giving a search key q.
	 * @param key donates the key of the element 
	 * @return the node with the same key with the searched key if it is found in the Treap, else null.
	 */
	public Node searchNode(int key) {
		
		Node sNode = root;
		while (sNode != null) {
			if (sNode.key==key)
				return sNode;
			else if(key<sNode.key)
				sNode = sNode.lChild;
			else
				sNode = sNode.rChild;
		}
		return null;
	}
	
	/**
	 * This class is used to discribe the properties of the node in the treap which contains the 
	 * attributes: id, key, priority, parent, left child and right child
	 * @author shimh
	 *
	 */
	public class Node{
		private Element e;
		private int key = 0;
		private int id = 0;
		private int priority;
		private Node parent;
		private Node lChild;
		private Node rChild;
		
		

		public Node(Element x, int priority) {
			this.e = x;
			this.priority = priority;
			this.key = x.getKey();
			this.id = x.getID();
		}
		
		/**
		 * This method is used to set a node as the left child of another node
		 * and at the meantime, makes its parent be that node.
		 * @param node
		 */
		public void setL(Node node) {
			node.parent = this;
			this.lChild = node;
		}
		/**
		 * This method is used to set a node as the right child of another node
		 * and at the meantime, makes its parent be that node.
		 * @param node
		 */
		public void setR (Node node) {
			node.parent = this;
			this.rChild = node;
		}
		
		
	}


		
}
