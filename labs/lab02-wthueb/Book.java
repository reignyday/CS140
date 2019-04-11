package lab02;

public class Book
{
    private String title;
    private int numPages;

    public Book(String title, int numPages)
    {
        this.title = title;
        this.numPages = numPages;
    }

    public String getTitle()
    {
        return this.title;
    }

    public int getNumPages()
    {
        return this.numPages;
    }
}
