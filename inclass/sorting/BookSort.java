import java.util.*;

public class BookSort {
	public static void main(String[] args) {
		String[] bookTitles = {"Catcher in the Rye", "Adventures of Huck Finn", 
				"Lord of the Flies", "Sun Also Rises", "Memórias Póstumas de Brás Cubas"};

		int[] bookPages = {253, 312, 198, 253, 238};

		List<Book> list = new LinkedList<Book>();

		for(int i = 0; i < bookTitles.length; i++) {
			list.add(new Book(bookTitles[i], bookPages[i]));
		}

		Collections.sort(list);

		System.out.println(list);
	}
}
