package Client;
import java.io.File;
import java.io.IOException;

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
		String requestType = args[2];
		
		File fileToParse = new File(path1);
		File file = new File(path2);
		if(!file.exists()){
			file.createNewFile();
		}
		
		if(fileToParse.exists()){
			switch(requestType){
			case("If"):
				ifFind ifFinder = new ifFind(file,fileToParse);
				ifFinder.getAllIfs();
				break;
			case("While"):
				whileFind whileFinder = new whileFind(file,fileToParse);
				whileFinder.getAllWhiles();
				break;
			case("For"):
				forFind forFinder = new forFind(file,fileToParse);
				forFinder.getAllFors();
				break;
			}
		
		}else{
			System.err.println("File to parse does not exist");
			
		}
		
		
		
		
	}
}
