public class Book implements Comparable<Book> {
	private String title;
	private int pages;

	public Book(String title, int pages) {
		this.title = title;
		this.pages = pages;
	}
	
	public int compareTo(Book other) {
		if(pages < other.pages) {
			return -1;
		}
		else if(pages > other.pages) {
			return +1;
		}
		else {
			return 0;
		}
	}
	
	public String getTitle() {
		return title;
	}

	public int getPages() {
		return pages;
	}
	
	public String toString() {
		return "title: " + title + ", pages: " + pages;
	}
}
