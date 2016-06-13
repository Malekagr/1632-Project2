
public class Node {
	private String loc="-";
	private Node left=null;
	private Node right=null;
	private Node up=null;
	private Node down=null;
	public Node(String location){
		this(location,null,null,null,null);
	}
	public Node(String location, Node l, Node r, Node u, Node d){
		loc=location;
		left=l;
		right=r;
		up=u;
		down=d;
	}
	public void setLoc(String location){
		loc=location;
	}
	public void setRight(Node r){
		right=r;
	}
	public void setLeft(Node l){
		left=l;
	}
	public void setUp(Node u){
		up=u;
	}
	public void setDown(Node d){
		down=d;
	}
	public boolean hasLeft(){
		return left!=null;
	}
	public boolean hasRight(){
		return right !=null;
	}
	public boolean hasUp(){
		return up !=null;
	}
	public boolean hasDown(){
		return down != null;
	}
	public String getLoc(){
		return loc;
	}
	public Node getLeft(){
		return left;
	}
	public Node getRight(){
		return right;
	}
	public Node getUp(){
		return up;
	}
	public Node getDown(){
		return down;
	}
}
