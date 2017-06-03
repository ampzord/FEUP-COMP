package Finders;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

import Iterators.NodeIterator;

public class ifFind {
	
	private File fileToParse;
	private File file;
	
	
	public ifFind(File file, File fileToParse){
		this.file = file;
		this.fileToParse = fileToParse;
		
	}
	
	public ArrayList<String> getAllIfs() throws IOException{
		ArrayList<String> allReturnText = new ArrayList<String>();
		
		try {
            new NodeIterator(new NodeIterator.NodeHandler() {
                @Override
                public boolean handle(Node node) {
                    if (node instanceof IfStmt) {
                    	//System.out.println(node.getMetaModel());
                    	String rtext = " [Lines " + node.getBegin().get().line
                                + " - " + node.getEnd().get().line + " ] " + node;
                        System.out.println(rtext);
                        allReturnText.add(rtext);
                        getAllChildren(node,allReturnText,2);
                        return false;
                    } else {
                    	String rtext = " [Lines " + node.getBegin().get().line
                                + " - " + node.getEnd().get().line + " ] " + " nope " + node;
                        System.out.println(rtext);  
                    	
                        return true;
                    }
                }
            }).explore(JavaParser.parse(this.fileToParse));
            System.out.println(); // empty line
        } catch (IOException e) {
            new RuntimeException(e);
        }
		PrintWriter out = new PrintWriter(new FileWriter(file, true));
		for(int i =0; i < allReturnText.size(); i++){
			out.append(allReturnText.get(i) + '\n');
		}
		out.close();
		
		
		
		return allReturnText;
	}
	
	
	public ArrayList<String> getNestedIfs(Node node,ArrayList<String> aList ){
		
		
			for(int i =0; i< node.getChildNodes().size(); i++){
				if(node.getChildNodes().get(i) instanceof IfStmt){
					Node newNode = node.getChildNodes().get(i);
					String rtext = " [Lines " + newNode.getBegin().get().line
                            + " - " + newNode.getEnd().get().line + " ] " + newNode;
					System.out.println(rtext);
					aList.add(rtext);
					getNestedIfs(newNode,aList);
				}
				else{
					Node newNode = node.getChildNodes().get(i);
					getNestedIfs(newNode,aList);
				}
			}
		
		
		return aList;
	}
	
	public ArrayList<String> getAllChildren(Node node,ArrayList<String> aList, int level ){
	
		int newlevel = level + 1;
		for(int i =0; i< node.getChildNodes().size(); i++){
			
			Node newNode = node.getChildNodes().get(i);
				
				
				
				if(newNode instanceof ExpressionStmt ){
					ExpressionStmt Bexpr = (ExpressionStmt) newNode;
					String rtext2 = " [Lines " + newNode.getBegin().get().line
	                        + " - " + newNode.getEnd().get().line + " ] " + "level " +level + " opr " + Bexpr;
					System.out.println(rtext2);
					
				}
				else if(newNode instanceof BinaryExpr){
					BinaryExpr Bexpr = (BinaryExpr) newNode;
					String rtext2 = " [Lines " + newNode.getBegin().get().line
	                        + " - " + newNode.getEnd().get().line + " ] " + "level " +level + " binExpr " + Bexpr;
					System.out.println(rtext2);
				}else if(newNode instanceof AssignExpr){
					AssignExpr Bexpr = (AssignExpr) newNode;
					String rtext2 = " [Lines " + newNode.getBegin().get().line
	                        + " - " + newNode.getEnd().get().line + " ] " + "level " +level + " " + newNode.getClass().getSimpleName()  + " " + Bexpr;
					System.out.println(rtext2);
					
				}else if(newNode instanceof SimpleName){
					SimpleName Bexpr = (SimpleName) newNode;
					String rtext2 = " [Lines " + newNode.getBegin().get().line
	                        + " - " + newNode.getEnd().get().line + " ] " + "level " +level + " " + newNode.getClass().getSimpleName()  + " " + Bexpr;
					System.out.println(rtext2);
					
				}else{
				
					String rtext = " [Lines " + newNode.getBegin().get().line
	                        + " - " + newNode.getEnd().get().line + " ] " + "level " +level + " " + newNode.getClass().getSimpleName()  + " " + newNode;
					System.out.println(rtext);
					aList.add(rtext);
				}
				getAllChildren(newNode,aList,newlevel);
			
			}
		return aList;
	}
	
	
	
	
	
	public File getFile(){
		return this.file;
	}
	
}
