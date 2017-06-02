package Finders;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import Iterators.NodeIterator;
import parseObjects.pObject;

public class PatComparator {

	private ArrayList<pObject> patternArray;
	private int nPatterns;
	private int currIndex;
	private File fileToCompare;	
	
	public PatComparator(File file, ArrayList<pObject> patternArray){
		this.fileToCompare = file;
		this.patternArray = patternArray;
		this.nPatterns = 0;
		this.currIndex = 0;
	}
	
	public int findOccasions(){
		
		try {
            new NodeIterator(new NodeIterator.NodeHandler() {
                @Override
                public boolean handle(Node node) {
                		
                		if (node.getClass().getName().equals(patternArray.get(currIndex).getClass().getName())) {
                			
                			if(node instanceof IfStmt || node instanceof WhileStmt){
                				//separateAnalysis();
                			}else{
                				//analyzeChildren();
                			}
                			
                            return false;
                        }
                		else{
                			return false;
                		}
                		
                	
                    
                }
            }).explore(JavaParser.parse(this.fileToCompare));
            System.out.println(); // empty line
        } catch (IOException e) {
            new RuntimeException(e);
        }
		
		return 0;
	}
	
	
	
	
}
