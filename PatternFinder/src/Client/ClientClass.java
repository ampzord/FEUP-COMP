package Client;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Finders.PatComparator;
import Finders.PatFinder;
import parseObjects.pObject;
public class ClientClass {

	
	public static void main(String[] args) throws IOException{
		if(args.length != 2){
			System.err.println("Wrong number of arguments. Use is ClientClass <Path for Java File with patterns> <Path for Java file to search for patterns>");
			return;
		}
		String path1 = args[0];
		String path2 = args[1];
		
		File fileToParse = new File(path1);
		File fileToCompare = new File(path2);
		
		PatFinder pF = null;
		PatComparator pC = null;;
		
		if(fileToParse.exists()){
			
			pF = new PatFinder(fileToParse);
			pF.findPattern();
			
		}else{
			System.err.println("File to parse does not exist");
			
		}
		
		if(fileToCompare.exists()){
			
			pC = new PatComparator(fileToCompare,pF.getArray());
			pC.findOccasions();
			
		}else{
			System.err.println("File to compare does not exist");
			
		}
	}
}
