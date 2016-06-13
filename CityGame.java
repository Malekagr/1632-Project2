import java.util.Random;

public class CityGame {

	public static void main(String[] args) {
		
		if(!good_start_conditions(args)){
			System.out.println("invalid number of arguments or invalid argument type");
			System.exit(0);
		}
		
		Random rn = new Random();
		int seed=Integer.parseInt(args[0]);
		rn.setSeed(seed);
		
		Node root = init_map();		
		startGame(root, rn);		
		
	}
	/*gets the starting node with getStartNode() followed by a for() loop which loops 4 times (once for each driver) running the iterate function each time
	 * and printing out which driver is currently going as well as a line to separate each run*/
	public static void startGame(Node root, Random rn){
		Node startingNode=getStartNode(root,rn);
		for(int i=0;i<5;i++){
			System.out.println("Driver "+i);
			iterate(startingNode,rn);
			System.out.println("\n__________________________________________________________________\n");
		}
	}
	/* determines which location the driver is currently at, and outputs a message associated with it. Then iterates to the next location using iterate()
	 * this is repeated until the driver leaves for Philly or Cleveland, at which point the iterating stops.
	 */
	public static void iterate(Node location, Random rn){
		if (location.getLoc()=="Philly"){
			System.out.println("Left for Philadelphia");
		}
		if (location.getLoc()=="Cleveland"){
			System.out.println("Left for Cleveland");
		}
		
		if(location.getLoc()=="Hotel"){
			System.out.println("Went to the hotel");
			iterate(nextLocation(location,rn),rn);
		}
		
		if(location.getLoc()=="Diner"){
			System.out.println("Went to the diner");
			iterate(nextLocation(location,rn),rn);
		}
		if(location.getLoc()=="College"){
			System.out.println("Went to the college");
			iterate(nextLocation(location,rn),rn);
		}
		if(location.getLoc()=="Library"){
			System.out.println("Went to the library");
			iterate(nextLocation(location,rn),rn);
		}
		
	}
	
	/* determines what locations can be reached from the current location by checking if specific directions are null. If those directions are not null
	 * the driver has a 50/50 chance of picking one of the two available directions.
	 */
	public static Node nextLocation(Node location, Random rn){
		Node newLoc=null;
		if(location.hasUp()){
			if(coinFlip(rn)){
				newLoc=location.getUp();
				System.out.println("Went on "+ newLoc.getLoc()+" street");
				newLoc=newLoc.getUp();
			}
			else{
				newLoc=location.getLeft();
			}
		}
		
		if(location.hasDown()){
			if(coinFlip(rn)){
				newLoc=location.getRight();
			}
			else{
				newLoc=location.getDown();
				System.out.println("Went on "+ newLoc.getLoc()+" street");
				newLoc=newLoc.getDown();
			}
		}
		return newLoc;
	}
	public static boolean coinFlip(Random rn){
		int check=rn.nextInt(2);
		if (check==1){
			return true;
		}
		return false;
	}
	
	/* picks a random starting point by having each of the 4 areas mapped to an int between 0-3. If it is 4, another roll is done to give a 50/50 
	 * chance of the driver starting at either the College or the Hotel.
	 */
	public static Node getStartNode(Node root, Random rn){
		int start_num=random_int(rn);
		Node starting=null;
		if (start_num==0){
			starting=root;
		}
		if (start_num==1){
			starting=root.getRight();
		}
		if (start_num==2){
			starting=root.getRight().getDown().getDown();
		}
		if(start_num==3){
			starting=root.getRight().getDown().getDown().getLeft();
		}
		if(start_num==4){
			double tie_breaker=random_double(rn);
			if(tie_breaker<=.5){
				starting=root;
			}
			else{
				starting=root.getRight().getDown().getDown();
			}
		}
		return starting;
	}
	
	/* checks two other methods to see if the appropriate data for running the program have been met from the command line*/
	public static boolean good_start_conditions(String[] args){
		return (correct_num_argument(args)&&arg_is_int(args));
	}
	/*returns a random double between 0 and 1 (exclusive)*/
	public static double random_double(Random rn){
		return rn.nextDouble();
	}
	
	/*returns a random int between 0-4 (inclusive)*/
	public static int random_int(Random rn){
		return rn.nextInt(5);
	}
	
	
	/* manually creates a map of the city using nodes that have 4 references to other nodes*/
	public static Node init_map(){
		
		
		Node hotel = new Node("Hotel");
		Node diner= new Node("Diner");
		Node philly=new Node("Philly");
		Node phil=new Node("Phil");
		Node bill=new Node("Bill");
		Node college=new Node("College");
		Node library=new Node("Library");
		Node cleveland=new Node("Cleveland");
		
		hotel.setRight(diner);
		hotel.setDown(bill);
		diner.setRight(philly);
		diner.setDown(phil);
		bill.setUp(hotel);
		bill.setDown(library);
		phil.setUp(diner);
		phil.setDown(college);
		college.setUp(phil);
		college.setLeft(library);
		library.setUp(bill);
		library.setLeft(cleveland);
		return hotel;
	}
	/*checks the number of args from the command line. If there are less than 1 (by the arg[0] being null) or more than 1 (by the arg[1]!=null
	 * return false, else return true.
	 */
	public static boolean correct_num_argument(String[] args){
		
		return (args.length==1); 
	}
	
	/*Checks if the first argument entered from the command line is an int by parsing it. If the parse yields an exception return false. 
	 * else return true.*/
	
	public static boolean arg_is_int(String[] args){
		try{
			int a =Integer.parseInt(args[0]);
		}
		catch(Exception e){
			return false;
		};
		return true;
	}
}
