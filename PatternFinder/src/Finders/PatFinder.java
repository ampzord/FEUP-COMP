package Finders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import Iterators.NodeIterator;
import parseObjects.pObject;

public class PatFinder {

	private File fileToParse;
	private ArrayList<pObject> array;
	
	public PatFinder(File file){
		this.fileToParse = file;
		array = new ArrayList<pObject>();
	}
	
	public ArrayList<pObject> findPattern(){
		ArrayList<pObject> aList = this.array;
		try {
            new NodeIterator(new NodeIterator.NodeHandler() {
                @Override
                public boolean handle(Node node) {
                		
                		if ((node instanceof IfStmt || node instanceof WhileStmt || node instanceof DoStmt) && node.toString().contains("_at_")) {
                			pObject nObject = new pObject(node,true,false,false);
                			aList.add(nObject);
                			separateNodes(nObject);
                			return false;
                        }
                		else if (node instanceof ExpressionStmt && node.toString().contains("_at_")) {
                        	pObject nObject = new pObject(node,true,false,false);
                			aList.add(nObject);
                        	return false;
                        }  else {
                            return true;
                        }
                		
                	
                    
                }
            }).explore(JavaParser.parse(this.fileToParse));
            System.out.println(); // empty line
        } catch (IOException e) {
            new RuntimeException(e);
        }
		
		this.array = aList;
		
		System.out.println("All Patterns to find:");
		
		for(int i = 0; i < aList.size(); i++){
			Node thisNode = aList.get(i).getNode();
			
			String rtext = "Pattern number " + (i+1) +" [Lines " + thisNode.getBegin().get().line
                    + " - " + thisNode.getEnd().get().line + " ] " + thisNode;
			System.out.println(rtext);
				
		}
		return aList;
	}
	
	public void separateNodes(pObject parentPObject){
		Node parentNode = parentPObject.getNode();
		Node condNode = parentNode.getChildNodes().get(0);
		Node bodyNode = parentNode.getChildNodes().get(1);
		pObject condObject = new pObject(condNode,false,true,false,parentPObject);
		array.add(condObject);
		
		//This object does not require analysis since it will ALWAYS be either a method statement or a binary expression
		//ADD IF NEEDED TO ADD EVERY SINGLE THING FORM BODY -  pObject bodyObject = new pObject(bodyNode,0,false,true,parentPObject);
		analyzeChildren(bodyNode, parentPObject);
		
		
		
				
		
	}
	
	public void analyzeChildren(Node parentNode, pObject grandpaPObject){
		
		for(int i =0; i< parentNode.getChildNodes().size(); i++){
			
			Node newNode = parentNode.getChildNodes().get(i);
			if(newNode.toString().contains("_at_")){
				pObject newObject = new pObject(newNode,false,false,true,grandpaPObject);
				array.add(newObject);
			}
			
        }
		return;
	}
	
	public ArrayList<pObject> getArray(){
		return array;
	}
	
	
}




