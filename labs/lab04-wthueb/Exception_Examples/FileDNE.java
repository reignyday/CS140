package lab04;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileDNE{
	public static void main(String[] args){
		try{
    		FileReader myFile = new FileReader("file_that_doesnt_exist.txt");
		} 
		catch(FileNotFoundException e) {
    		System.out.println("That file does not exist!");
		}
	}
}
