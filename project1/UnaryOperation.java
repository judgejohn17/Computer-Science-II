package perp.tree.stu;

import perp.SymbolTable;
import perp.tree.ExpressionNode;
import perp.machine.stu.Machine;
import java.util.*;
import perp.Errors;

/**
 * Created by johnjudge on 3/7/15.
 */
/*
 * public class UnaryOperation
 * extends Object
 * implements ExpressionNode
 *
 * A calculation represented by a unary operator and its operand
 */
public class UnaryOperation implements ExpressionNode {

    String operator;
    private ExpressionNode expr;
    public static final String NEG = "_"; //arithmetic negation operator
    public static final String SQRT = "#"; //square root operator
    final public static Collection<String> OPERATORS = Arrays.asList(NEG, SQRT); //Container of all legal binary operators, for use by parsers

    /*
     * Create a new UnaryOperation node
     * @p: operator - the string rep. of the operation
     * @p: expr - the operand
     * Precondition: OPERATORS.contains(operator), expr != null
     */
    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator = operator;
        this.expr = expr;


    }

    /*
     * Compute the result of evaluating the expression and applying the operator to it.
     * @s: evaluate in interface ExpressionNode
     * @p: symTab - symbol table, needed to evaluate the child tree
     * @r: the result of the computation
     */
    public int evaluate(SymbolTable symTab){
        ExpressionNode expression = this.expr;
        String operator = this.operator;
        if (operator.equals(NEG)){
            return this.expr.evaluate(symTab) *-1;
        }
        else if (operator.equals(SQRT)){

            return (int)Math.sqrt(this.expr.evaluate(symTab));
        }
        else{
            Errors.error("Invalid", OPERATORS);
            return 0;
        }
    }

    /*
     * Print, opn standard output, the infixDisplay of the child nodes preceded by
     * by the operator and without an intervening blank.
     * @s: infixDisplay in interface PerpNode
     */
    public void infixDisplay(){
        System.out.print(this.operator);
        this.expr.infixDisplay();

    }

    /*
     * Emit the Machine instructions necessary to preform the computation of this UnaryOperation
     * The operator itself is realized by an instruction that pops a value off the stack, applies the
     * operator and pushes the answer
     * @s: emit in interface PerpNode
     * @r: a list containing instructions for the expression and the instruction to preform the operation
     */
    public List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> lst = new ArrayList<Machine.Instruction>();
        lst.addAll(expr.emit());
        if (operator.equals(NEG)){
            lst.add(new Machine.Negate());
        }
        else if (operator.equals(SQRT)){
            lst.add(new Machine.SquareRoot());
        }
        else{
            Errors.error("Invalid", OPERATORS);
        }

        return lst;
    }
}
