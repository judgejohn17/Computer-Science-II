package perp.tree.stu;
import perp.*;
import perp.SymbolTable;
import perp.tree.ExpressionNode;
import perp.machine.stu.Machine;
import java.util.*;
import perp.tree.ActionNode;
import perp.Errors;
/**
 * Created by johnjudge on 3/7/15.
 */
/*
 * extends Object
 * implements actionNode
 */
public class Assignment implements ActionNode{
    private String id;
    private ExpressionNode rhs;
    /*
     * Set up an Assignment node. Note that the identifier is not turned into a Variable node. The
     * reason is that the variable's value is not needed; instead it is a destination for a computation
     * This use is not compatible with Variable's mission
     * @p: indent - the name of the variable that is getting a new value
     * @p: rhs - the expression on the "right-hand side" (RHS) of the assignment statement
     */

    public Assignment(String indent, ExpressionNode rhs){
        this.id = indent;
        this.rhs = rhs;

    }

    /*
     * Evaluate the RHS expression and assign the result to the variable
     * @s: execute in interface ActionNode
     * @p: symTab - the table where variable values are stored
     */
    public void execute(SymbolTable symTab){
        symTab.put(id, rhs.evaluate(symTab));
    }

    /*
     * Show this assignment on the standard output as a variable followed by an assignment arrow (":=")
     * followed by the infix form of the RHS expression
     * @s: infixDisplay in interface PerpNode
     */
    public void infixDisplay(){
        System.out.print(this.id+" := ");
        this.rhs.infixDisplay();
        System.out.println();


    }

    /*
     * This method returns a STORE instruction for the variable in question preceded by the code
     * emitted by the RHS node that eventually pushes the value of the expression onto the stack
     * @s: emit in the interface PerpNode
     * @r: a list of instructions ending in one that stores the top value on the stack to this
     * node's variable
     */
    public List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> lst = new ArrayList<Machine.Instruction>();
        lst.addAll(rhs.emit());
        lst.add(new Machine.Store(id));
        return lst;
    }


}
