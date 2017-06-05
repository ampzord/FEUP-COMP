package Utils;

import com.github.javaparser.ast.Node;

public class Utilities {

	public static String getNodeType(Node node){
		return node.getClass().getName();
	}
	
}
