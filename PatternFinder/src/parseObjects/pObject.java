package parseObjects;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;

public class pObject {

	private boolean container;
	private boolean isCond;
	private boolean inBody;
	private pObject bodyOf;
	private Node node;
	
	public pObject(Node node, boolean wSt,boolean cond, boolean body){
		this.node = node;
		container = wSt;
		isCond = cond;
		inBody = body;
		bodyOf = null;
	}
	
	public pObject(Node node, boolean wSt,boolean cond, boolean body, pObject st){
		this.node = node;
		container = wSt;
		isCond = cond;
		inBody = body;
		bodyOf = st;
	}



	public boolean isCond() {
		return isCond;
	}

	public boolean isInBody() {
		return inBody;
	}
	
	public boolean isContainer(){
		return container;
	}
	
	public String getObjectNodeType(){
		return this.node.getClass().getName();
	}
	

	public Node getNode() {
		return node;
	}

	public pObject getBodyOf() {
		return bodyOf;
	}
	
	
	
}
