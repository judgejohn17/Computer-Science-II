package perp.tree.stu;

import perp.*;
import perp.SymbolTable;
import perp.tree.ActionNode;
import perp.tree.ExpressionNode;
import perp.machine.stu.Machine;

import java.beans.Expression;
import java.util.*;
import perp.Errors;
/**
 * Created by johnjudge on 3/7/15.
 */
/*
 * public class Print
 * extends Object
 * implements ActionNode
 */
public class Print implements ActionNode {

    private ExpressionNode printee;
    /*
     * Set up a Print node
     * @p: printee - the expression to be evaluated to be evaluated and printed
     */
    public Print(ExpressionNode printee){
        this.printee = printee;

    }

    /*
     * Evaluate the expression and display the result on the console. Precede it with three equal signs
     * so it stands out a little
     * @s: execute in interface ActionNode
     * @p: symTab - the table where variable values are stored
     */
    public void execute(SymbolTable symTab){
        int printed = this.printee.evaluate(symTab);

        System.out.println("=== " + printed);

    }

    /*
     * Show this statement on the standard output as the word "Print" followed by the infix form of the
     * expression
     * @s: infixDisplay in interface PerpNode
     */
    public void infixDisplay(){
        System.out.print("Print");
        this.printee.infixDisplay();
        System.out.println();

    }

    /*
     * This method returns the code emitted by the printee node that pushes the value of the printee expression
     * onto the stack, followed by a PRINT instruction
     * @s: emit in interface PerpNode
     * @r: a list of instructions ending in the ones that compute the value to be printed, and print it.
     */
    public List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> lst = new ArrayList<Machine.Instruction>();
        lst.addAll(printee.emit());
        lst.add(new Machine.Print());
        return lst;

    }

}
