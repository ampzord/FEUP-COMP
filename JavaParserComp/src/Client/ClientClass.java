package Client;
import java.io.File;
import java.io.IOException;

import Finders.PatFinder;
import Finders.forFind;
import Finders.ifFind;
import Finders.whileFind;
public class ClientClass {

	
	public static void main(String[] args) throws IOException{
		if(args.length != 3){
			System.err.println("Wrong number of arguments. Use is ClientClass <File to parse path> <File with resuslts path> <Type Of Request>");
			return;
		}
		String path1 = args[0];
		String path2 = args[1];
		String path3 = args[2];
		
		File fileToParse = new File(path1);
		File fileToCompare = new File(path3);
		File file = new File(path2);
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		if(fileToParse.exists()){
			
			//ifFind ifFinder = new ifFind(file,fileToParse);
			//ifFinder.getAllIfs();
			PatFinder pF = new PatFinder(fileToParse);
			pF.findPattern();
			
			
		
		}else{
			System.err.println("File to parse does not exist");
			
		}
		
		
		
		
	}
}
