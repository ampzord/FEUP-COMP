/* Generated By:JJTree: Do not edit this line. ASTPattern.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTPattern extends SimpleNode {
  public ASTPattern(int id) {
    super(id);
  }

  public ASTPattern(PatternParser p, int id) {
    super(p, id);
  }

  @Override
	public String generateJava() {
		// TODO Auto-generated method stub
		String s= "\tvoid "+this.value+"{\n";
		for(int i = 0; i < jjtGetNumChildren(); i++){
			  SimpleNode n = (SimpleNode)jjtGetChild(i);
			  s+="\t\t"+n.generateJava();
		  }
		  s+="\t}\n";
		  return s;
	}
}
/* JavaCC - OriginalChecksum=bff851e3bedcd756fc6bba5920ef5d11 (do not edit this line) */
