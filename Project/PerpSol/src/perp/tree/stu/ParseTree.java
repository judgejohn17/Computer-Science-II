package perp.tree.stu;

import perp.Errors;
import perp.SymbolTable;
import perp.machine.stu.Machine;
import perp.tree.ActionNode;
import perp.tree.ExpressionNode;
import perp.tree.stu.BinaryOperation;
import perp.tree.stu.UnaryOperation;

import java.util.List;

/**
 * Operations that are done on a Perp code parse tree.
 *
 * THIS CLASS IS UNIMPLEMENTED. All methods are stubbed out.
 *
 * @author John Judge
 */
public class ParseTree {
    ActionSequence head;

    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     */
    public ParseTree( List< String > program ) {
        head = new ActionSequence();
        while (!program.isEmpty()){
            head.addAction(parseAction(program));


        }
    }

    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {
        String token = program.remove(0);
        if (token.equals(":=")){

           return new Assignment(program.remove(0),parseExpr(program));

        }
        else if(token.equals("@")){
            return new Print(parseExpr(program));
        }
        else {
            Errors.error("No token to parse", token);
            return null;
        }
    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {
        String token = program.remove(0);
        if (BinaryOperation.OPERATORS.contains(token)){
            return new BinaryOperation(token, parseExpr(program), parseExpr(program));
        }
        else if (UnaryOperation.OPERATORS.contains(token)){
            return new UnaryOperation(token, parseExpr(program));

        }
        else if (token.matches("[a-zA-Z]+")){
            return new Variable(token);

        }
        else if (token.matches("^[0-9]+")){
            return new Constant(Integer.parseInt(token));

        }
        else {
            Errors.error("Unable to parse", token);
            return null;
        }
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see perp.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
        head.infixDisplay();

    }

    /**
     * Run the program represented by the tree directly
     * @see perp.tree.ActionNode#execute(perp.SymbolTable)
     */
    public void interpret() {
        SymbolTable symTab = new SymbolTable();
        System.out.println(" ");
        System.out.println("Interpeting the Parse Tree...");
        head.execute(symTab);
        System.out.println("Interpetation complete");
        System.out.println(" ");
        symTab.dump();
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see perp.machine.stu.Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
        return head.emit();
    }

}
