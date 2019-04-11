package assignment01;

public class BookForClass
{
    private int numPages;
    private String title;

    public BookForClass(int numPages, String title)
    {
    	this.numPages = numPages;
    	this.title = title;
    }

    public double pagesNeededPerDayToRead(int days)
    {
    	return (double)this.numPages / days;
    }

    public int daysNeededToRead(int pagesPerDay)
    {
    	int days = this.numPages / pagesPerDay;

    	if (this.numPages % pagesPerDay > 0)
    		++days;

    	return days;
    }

    public int needToRead(int from, int to, int days)
    {
    	int totalPages = to - from + 1;

    	return (int)Math.ceil((double)totalPages / days);
    }
}
