/* Generated By:JJTree: Do not edit this line. ASTVariable.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTVariable extends SimpleNode {
  public ASTVariable(int id) {
    super(id);
  }

  public ASTVariable(PatternParser p, int id) {
    super(p, id);
  }

  @Override
	public String generateJava() {
	return value.toString().replace("@","_at_");
	}
}
/* JavaCC - OriginalChecksum=1295d2313ed57dcf9596685179de4356 (do not edit this line) */
