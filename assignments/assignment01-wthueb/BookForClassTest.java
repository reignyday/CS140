package assignment01;

public class BookForClassTest
{
    public static void main(String[] args)
    {
    	var book = new BookForClass(243, "Creative Title Here");

    	System.out.printf("testing BookForClass.pagesNeededPerDayToRead: expecting %f got %f%n",
    			24.3, book.pagesNeededPerDayToRead(10));

    	System.out.printf("testing BookForClass.daysNeededToRead: expecting %d got %d%n",
    			3, book.daysNeededToRead(100));

    	System.out.printf("testing BookForClass.needToRead: expecting %d got %d%n",
    			10, book.needToRead(100, 199, 10));
    }
}
