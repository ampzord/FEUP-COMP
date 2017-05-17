package Client;
import java.io.File;
import java.io.IOException;

import Finders.ifFind;
public class ClientClass {

	
	public static void main(String[] args) throws IOException{
		String path1 = args[0];
		String path2 = args[1];
		
		File fileToParse = new File(path1);
		File file = new File(path2);
		
		
		ifFind ifFinder = new ifFind(file,fileToParse);
		ifFinder.getAllIfs();
		
		
	}
}
