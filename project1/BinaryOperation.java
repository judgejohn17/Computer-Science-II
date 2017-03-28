package perp.tree.stu;
import perp.SymbolTable;
import perp.tree.ExpressionNode;
import perp.machine.stu.Machine;
import java.util.*;
import perp.Errors;

/**
 * Created by johnjudge on 3/7/15.
 */
public class BinaryOperation implements ExpressionNode {

    final public static String ADD = "+"; //The operator symbol used for addition
    final public static String DIV = "//"; //The operator symbol used for the division
    final public static String MUL = "*"; //The operator symbol used for multiplication
    final public static String SUB = "-"; //The operator symbol used for subtraction
    final public static Collection<String> OPERATORS = Arrays.asList(ADD, SUB, MUL, DIV); //Container of all legal binary operators, for use by parsers
    private String operator;
    private ExpressionNode leftChild;
    private ExpressionNode rightChild;


    /*
     * Create a new BinaryOperation
     * @p: operator - the string representation of the operation
     * @p: leftChild - the left operand
     * @p: rightChild - the right operand
     * Precondition : OPERATORS.contains(operator), leftChild != null, rightChild != null
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;

    }

    /*
     * Compute the result off the evaluating both operands and appplying the operator to them
     * @s: evaluate in interface ExpressionNode
     * @p: symTab - symbol table, needed to evaluate the child trees
     * @r: the result of the computation
     */
    public int evaluate(SymbolTable symTab){
        String operator = this.operator;
        if (operator.equals(ADD)){
            return leftChild.evaluate(symTab) + rightChild.evaluate(symTab);
        }
        else if (operator.equals(SUB)){
            return leftChild.evaluate(symTab) - rightChild.evaluate(symTab);
        }
        else if (operator.equals(DIV)){
            if (rightChild.equals(0)){
                Errors.error("Invalid", OPERATORS);
                return 0;
            }
            else {
                return (int)leftChild.evaluate(symTab) / rightChild.evaluate(symTab);
            }
        }
        else if (operator.equals(MUL)){
            return leftChild.evaluate(symTab) * rightChild.evaluate(symTab);
        }
        else{
            Errors.error("Invalid", OPERATORS);
            return 0;
        }

    }

    /*
     * Print, on standard output, the infixDisplay of the two child nodes separated by the
     * operator and surrounded by parenthesis. Blanks are inserted throughout
     * @s: infixDisplay in interface PerpNode
     */
    public void infixDisplay(){
        System.out.print("(");
        this.leftChild.infixDisplay();
        System.out.print(" " + this.operator + " ");
        this.rightChild.infixDisplay();
        System.out.print(")");

    }

    /*
     * Emit the Machine instructions necessary to preform the computations of this BinaryOperation
     * The operator its self is realized by and instruction thats pops two values off the stack, applies
     * the operator, and pushes the answer
     * @s: emit in interface PerpNode
     * @r: a list containing instructions for the left operand, instructions for the right operand
     * and the instruction to preform the operation
     */
    public List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> lst = new ArrayList<Machine.Instruction>();
        lst.addAll(leftChild.emit());
        lst.addAll(rightChild.emit());
        if (operator.equals(ADD)){
            lst.add(new Machine.Add());
        }
        else if (operator.equals(SUB)){
            lst.add(new Machine.Subtract());
        }
        else if (operator.equals(DIV)){
            lst.add(new Machine.Divide());
        }
        else if (operator.equals(MUL)){
            lst.add(new Machine.Multiply());
        }
        return lst;
    }

}
