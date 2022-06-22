/** 
 * Client code for ArrayIntList.
 * 
 * @author Raghuram Ramanujan
 * @author Tabitha Peck
 */
public class MyArrayTester {
	public static void main(String[] args) {
		MyArrayList list1 = new MyArrayList();

		list1.add(25);
		list1.add(30);
		list1.add(35);
		list1.add(40);
		list1.add(45);
		list1.add(50);
		list1.add(55);
		list1.add(60);
		list1.add(65);
		list1.add(70);
		list1.add(75);
		list1.add(80);
		list1.add(85);
		list1.add(90);
		list1.add(95);
		list1.add(100);


		list1.remove(0);
		System.out.println(list1.toString());

		MyArrayList list2 = new MyArrayList();
		list1.add(25);
		list1.remove(0);
		list1.add(30);
		list1.add(35);
		System.out.println(list2.toString());
	}
}