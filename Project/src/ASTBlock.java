/* Generated By:JJTree: Do not edit this line. ASTBlock.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTBlock extends SimpleNode {
	public ASTBlock(int id) {
		super(id);
	}

	public ASTBlock(PatternParser p, int id) {
		super(p, id);
	}

	@Override
	public String generateJava() {
		// TODO Auto-generated method stub
		//String s= " {\n";
		String s = "\n";
		for(int i = 0; i < jjtGetNumChildren(); i++){
			SimpleNode n = (SimpleNode) jjtGetChild(i);
			s += "\t\t" + n.generateJava();
		}
		//s += "\t\t}\n";
		return s;
	}
}
/* JavaCC - OriginalChecksum=b4fdadd457dd2667da05314a30f7d1eb (do not edit this line) */
