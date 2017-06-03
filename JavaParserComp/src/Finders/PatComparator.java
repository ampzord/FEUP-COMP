package Finders;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
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
	private Hashtable<String,String> atReferencer;
	private Hashtable<String,String> savedReferencer;
	
	public PatComparator(File file, ArrayList<pObject> patternArray){
		this.fileToCompare = file;
		this.patternArray = patternArray;
		this.nPatterns = 0;
		this.currIndex = patternArray.size();
		atReferencer = new Hashtable<String,String>();
		savedReferencer = new Hashtable<String,String>();
	}
	
	public int findOccasions(){
		
		try {
            new NodeIterator(new NodeIterator.NodeHandler() {
                @Override
                public boolean handle(Node node) {
                	//System.out.println(node.getClass().getName() + " AND " + patternArray.get(getCIndex()).getNode().getClass().getName());
                		if (node.getClass().getName().equals(patternArray.get(getCIndex()).getNode().getClass().getName())) {
                			
                			if(node instanceof IfStmt || node instanceof WhileStmt){
                				pObject contObject = patternArray.get(getCIndex());
                				currIndex--;
                				separateAnalysis(node,contObject);
                				System.out.println("Found an If! " + node);
                			}else if(checkIfSame(node,patternArray.get(getCIndex()).getNode())){
                				currIndex--;
                				System.out.println("Found a match " + node);
                			}
                			if(currIndex < 1){
                				nPatterns++;
                				currIndex = patternArray.size(); 
                			}
                            return false;
                        }
                		else{
                			return true;
                		}
                		
                	
                    
                }
            }).explore(JavaParser.parse(this.fileToCompare));
            System.out.println(); // empty line
        } catch (IOException e) {
            new RuntimeException(e);
        }
		System.out.println("FOUND N PATTERNS : " + nPatterns);
		System.out.println("How many left for more : " + currIndex);
		return 0;
	}
	
	private void separateAnalysis(Node parentNode,pObject container){
		
		List<Node> lChildren = parentNode.getChildNodes();
		int bodyStatements = 0;
		int nToRemove = 0;
		for(int i = 0; i< patternArray.size(); i++){
			if(patternArray.get(i).getBodyOf() != null){
			if(patternArray.get(i).getBodyOf().equals(container) && patternArray.get(i).isInBody()){
			
				bodyStatements++;
			}}
		}
		if(patternArray.get(getCIndex()).isCond() && lChildren.get(0).getClass().getName().equals(patternArray.get(getCIndex()).getNode().getClass().getName())){
			
			if(checkIfSame(lChildren.get(0),patternArray.get(getCIndex()).getNode())){
			System.out.println("Found a Cond! " + lChildren.get(0));
			nToRemove += 1;}
			else{
				return;
			}
		}
		if(patternArray.get(getCIndex() + nToRemove).isInBody()){
			if(analyzeBody(lChildren.get(1),bodyStatements,nToRemove)){
				nToRemove += bodyStatements;
				System.out.println("Found a Bod! " + lChildren.get(1));
			}
			
		}
		currIndex -= nToRemove; 
	}
	
	private boolean analyzeBody(Node parentNode,int statements,int adder){
		List<Node> lChildren = parentNode.getChildNodes();
		//List<Node> patChildren = patternArray.get(currIndex).getNode().getChildNodes();
		for(int i = 0; i < lChildren.size(); i++){
			if(lChildren.get(i).getClass().getName().equals(patternArray.get(getCIndex()+adder).getNode().getClass().getName())){
				if(checkIfSame(lChildren.get(i),patternArray.get(getCIndex()+adder).getNode())){
					
					if(adder + 1 < patternArray.size() - 1){
						adder+=1;
					}
					
					if(statements > 0){
						statements--;
						System.out.println("Found a analyzed match! - " + lChildren.get(i) + " " + patternArray.get(getCIndex()+adder).getNode());
					}
				}
			}
		}
		if(statements > 0){
			return false;
		}
		return true;
	}
	
	private boolean checkIfSame(Node parentNode, Node patParNode){
		List<Node> lChildren = parentNode.getChildNodes();
		List<Node> patChildren = patParNode.getChildNodes();
		
		if(lChildren.size() == patChildren.size()){
			for(int i = 0; i < lChildren.size(); i++){
				if(!lChildren.get(i).getClass().getName().equals(patChildren.get(i).getClass().getName())){
					return false;
				}
				if(lChildren.get(i).getChildNodes().size() == 0){
					if(patChildren.get(i) instanceof SimpleName && patChildren.get(i).toString().contains("_at_")){
						if(!(atReferencer.containsKey(patChildren.get(i).toString()) || atReferencer.containsKey(lChildren.get(i).toString()))){
						atReferencer.put(patChildren.get(i).toString(), lChildren.get(i).toString());
						atReferencer.put(lChildren.get(i).toString(),patChildren.get(i).toString());
						}
						
						if(atReferencer.containsKey(patChildren.get(i).toString()) && atReferencer.containsKey(lChildren.get(i).toString())){
						if(atReferencer.get(patChildren.get(i).toString()).equals(lChildren.get(i).toString())){
							return true;
						}else{
							return false;
						}
				
						}else{
							return false;
						}
					}
					else if(patChildren.get(i).toString().equals(lChildren.get(i).toString())){
						return true;
					}
					else{
						return false;
					}
				}else if(!checkIfSame(lChildren.get(i),patChildren.get(i))){
					return false;
				}
					
					
				
			}
		}
		else{
			return false;
		}
		return true;
	}
	
 
	private int getCIndex(){
		int i = currIndex;
		i = i- (patternArray.size() - (patternArray.size() - i) *2);
		return i;
	}
	
	
	
}
