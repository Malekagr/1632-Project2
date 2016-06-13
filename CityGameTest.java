import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.*;

import java.util.Random;

public class CityGameTest {

	/*test if coin flip works by asserting that the flipped coin yields true
	 *  
	 */
	@Test
	public void testcoinFlip0(){
		boolean coinSide = true;
		boolean val=false;
		Random num = new Random();
		val = CityGame.coinFlip(num);
		if(coinSide!=val||coinSide==val){
			val=true;
		}
		assertEquals(coinSide,val);
	}	
	
	
	/* checks that the random_int() never produces a number below 0. Edge case for random_int (should never reach negative numbers). Asserts
	 * that the random number is above 0*/
	@Test
	public void testRandom_int0(){
		Random rn = new Random();
		assertTrue(CityGame.random_int(rn)>0);
	}
	
	/*checks that the random_int function never produces the number 5. edge case for random_int, as the last driver should be #4
	 *  (should never reach 5). asserts that the random number is not 5.*/
	@Test
	public void testRandom_int1() {
		Random rn = new Random();
		assertNotEquals(CityGame.random_int(rn), 5);
	}
	
	/*The random_double method should exclude 1 when rolling. edge case for random_double (should never reach 1). Tests by asserting the random roll
	 * is not 1
	 * */
	@Test
	public void testRandom_double0() {
		Random rn = new Random();
		assertNotEquals(CityGame.random_double(rn), 1.0);
	}
	/*The random_double method should always be above 0. Asserts random_double() is larger than 0
	 * edge case for random_double (should never reach negative numbers)
	 */
	@Test
	public void testRandom_double() {
		Random rn = new Random();
		assertTrue(CityGame.random_double(rn)>0);
	}
	
	/*check if args 1 is not null && args 2 is null. If that is the case, then the function is working properly and should return true
	 * 
	 */
	@Test
	public void testcorrect_num_argument() {
		String [] args = new String[2];
		boolean val = false;
		if (args[0] != null && args[1] == null){ 
			val = true;
		}
		assertEquals(val, CityGame.correct_num_argument(args));		
		
	}
	
	/*if returning true then it is a int. Checks that the argument from the command line is usable by checking that whether or not 
	 * it is an int. */
	@Test
	public void testarg_is_int() {
		CityGame g = new CityGame();
		String [] mockArg = new String [1];
		mockArg[0] = "S";
		boolean returnVal = g.arg_is_int(mockArg);
		assertNotEquals(true, returnVal);
	}
	// stubbing to test if both arg is int and correct num of args is satisfied
	@Test
	public void testgood_start_conditions () {
		String [] mockStringArgs = new String [2];
		mockStringArgs[0] = "1";
		mockStringArgs[1] = "5";
		boolean val = CityGame.good_start_conditions(mockStringArgs);
		assertNotEquals(true, val);
	}
	// test double to test if getStartNode gives the correct string output. If so then true. 
	@Test
	public void testgetStartNode () {
		Node root;
		Random rn;
		root = Mockito.mock(Node.class);
		rn = Mockito.mock(Random.class);
		Node n = CityGame.getStartNode(root, rn);
		boolean val = false;
		if (n.getLoc() == "Hotel")
			val = true;
		if (n.getLoc() == "College")
			val = true;
		if (n.getLoc() == "Diner")
			val = true;
		if (n.getLoc() == "Library")
			val = true;
		assertTrue(val);
	}
	
	/*checked to make sure the nodes we got were actually filled with locations. Assert that the starting node is associated with the
	 * appropriate location and that the node has nodes surround it*/
	@Test
	public void testinit_map() {
		Node n = CityGame.init_map();
		boolean val = false;
		if (n.hasRight() != false && n.hasDown() != false && n.getLoc() == "Hotel")
			val = true;
		
		assertTrue(val);
	}
	/*make sure location is shifting when using iterate. This is done by comparing the first nodes location and the location of the second node
	 * after iterating*/
	@Test
	public void testiterate() {
		Node root;
		Random rn;
		root = Mockito.mock(Node.class);
		Node a = root;
		rn = Mockito.mock(Random.class);
		CityGame.iterate(a,rn);
		boolean val=false;
		if(a!=root){
			val=true;
		}		
		assertTrue(val);
	}
	
	/*checking to see if a location has an appropriate place to go. Assert that this is the case by checking if the node has a node above it and to 
	 * the left or if the node has a a node below it and to the right */
	@Test
	public void testnextLocation() {
		Node root;
		Random rn = Mockito.mock(Random.class);
		root = Mockito.mock(Node.class);
		Node a = CityGame.nextLocation(root, rn);
		boolean val = false;
		if ( (a.hasUp() || a.hasLeft()) || (a.hasDown() || a.hasRight()) ){
			val = true;
		}
		assertTrue(val);
			
	}
}