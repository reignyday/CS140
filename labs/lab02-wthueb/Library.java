package lab02;

public class Library
{
	public static void main(String[] argv)
	{
		var library = new Book[3];

		library[0] = new Book("titleA", 417);
		library[1] = new Book("titleB", 256);
		library[2] = new Book("titleC", 731);

		for (var i = 0; i < library.length; ++i)
		{
			System.out.println(library[i].getTitle());
		}

		for (var book : library)
		{
			System.out.println(book.getNumPages());
		}

		System.out.println(1404);
		System.out.println(numPagesInLibrary(library));

		System.out.println(731);
		System.out.println(mostPages(library));
	}

	public static int numPagesInLibrary(Book[] books)
	{
		int sum = 0;

		for (var book : books)
		{
			sum += book.getNumPages();
		}

		return sum;
	}

	public static int mostPages(Book[] books)
	{
		int max = -1;

		for (var book : books)
		{
			max = Math.max(max, book.getNumPages());
		}

		return max;
	}
}
