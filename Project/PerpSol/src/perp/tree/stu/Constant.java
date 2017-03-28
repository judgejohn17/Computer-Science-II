package perp.tree.stu;

import perp.SymbolTable;
import perp.tree.ExpressionNode;
import perp.machine.stu.Machine;
import java.util.*;
/**
 * Created by johnjudge on 3/7/15.
 */
/*
 * public class Constant
 * extends Object
 * implements ExpressionNode
 * An expression node representing a constant, i.e., literal value
 */
public class Constant implements ExpressionNode{
    int value;
   /*
    * Store the integer value in this new Constant
    * @p: value - the integer it will hold
    */
    public Constant(int value){
     this.value = value;

    }

    /*
     * Print this Constant's value on standard output
     * @s: infixDisplay in interface PerpNode
     */
    public void infixDisplay(){
     System.out.print(this.value);

    }

    /*
     * Evaluate the constant
     * @s: evaluate in the interface ExpressionNode
     * @p: symTab- unused
     * @r: this Constant's value
     */
    public int evaluate (SymbolTable symTab){
     return value;

    }

    /*
     * Emit an instruction to push the value onto the stack.
     * @s: emit in interface PerpNode
     * @r: a list containing that one instruction
     */
    public List<Machine.Instruction> emit(){
     ArrayList<Machine.Instruction> lst = new ArrayList<Machine.Instruction>();
     lst.add(new Machine.PushConst(value));
     return lst;
    }


}
