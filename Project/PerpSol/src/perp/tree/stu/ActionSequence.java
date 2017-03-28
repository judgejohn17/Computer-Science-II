package perp.tree.stu;
import perp.*;
import perp.SymbolTable;
import perp.tree.ExpressionNode;
import perp.machine.stu.Machine;
import java.util.*;
import perp.Errors;
import perp.tree.ActionNode;

import javax.swing.*;

/**
 * Created by johnjudge on 3/7/15.
 */

/*
 * extends: Object
 * implements ActionNode
 */
public class ActionSequence implements ActionNode {

    ArrayList<ActionNode> Actions = new ArrayList<ActionNode>();
    /*
     *Initialize this instance as an empty sequence of ActionNode Children
     */
    public ActionSequence() {

    }

    /*
     * Add a child of this ActionSequence node
     * @p: newNode - the node representing the action that will execute last
     */
    public void addAction(ActionNode newNode) {
        Actions.add(newNode);

    }

    /*
     * Execute each ActionNode in this object, from first-added to last-added
     * @s: execute in interface ActionNode
     * @p: symTab - the table of variable values
     */
    public void execute(SymbolTable symTab) {
        for (ActionNode Action:Actions){
            Action.execute(symTab);
        }
    }

    /*
     * show the infix displays of all the children on the standard output. The order is first-added to last-added
     * @s: infixDisplay in interface PerpNode
     */
    public void infixDisplay() {
        for (ActionNode Action: Actions){
            Action.infixDisplay();
        }

    }

    /*
     * Create a list of the instructions emitted by each child, from the first-added child to the last-added
     * @s: emit in interface PerpNode
     * @r: the concatenated instructions lists from all children
     */
    public List<Machine.Instruction> emit() {
        ArrayList<Machine.Instruction> lst = new ArrayList<Machine.Instruction>();
        for(int i=0; i<Actions.size(); i++) {
            lst.addAll(Actions.get(i).emit());
        }
        return lst;
    }

}
