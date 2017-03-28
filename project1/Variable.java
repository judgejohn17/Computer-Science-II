package perp.tree.stu;

import perp.SymbolTable;
import perp.tree.ExpressionNode;
import perp.machine.stu.Machine;
import java.util.*;

/**
 * Created by johnjudge on 3/7/15.
 */
/*
 * public class Variable
 * extends object
 * implements ExpressionNode
 */
public class Variable implements ExpressionNode {
    String name;

    /*
     * Set name of this new Variable. Not that it is not wrong for more than one Variable node to refer
     * to the same variable. Its actual value is stored in a SymbolTable
     * @p: name - the name of this variable
     */
    public Variable(String name){
        this.name = name;

    }

    /*
     * Print on standard output the Variable's name
     * @s: infixDisplay in interface PerpNode
     */
    public void infixDisplay(){
        System.out.print(name);

    }

    /*
     * Emit a LOAD instructions that pushes the Variable's value onto the stack
     * @s: emit in interface PerpNode
     * @r: a list containing a single LOAD instruction
     */
    public List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> lst = new ArrayList<Machine.Instruction>();
        lst.add(new Machine.Load(name));
        return lst;

    }

    /*
     * Evaluate a variable by fetching its value
     * @s: evaluate in interface ExpressionNode
     * @p: symTab - the table containing all variables' values
     * @r: this variable's current value in the symbol table
     */
    public int evaluate(SymbolTable symTab){
        return symTab.get(name);

    }
}
