/**
 * Created by johnjudge on 2/11/15.
 */
import java.util.Scanner;



public class Gatekeeper {
    public static void main(java.lang.String[] args) {
        int response; //What does the user want to do?
        String n; //Name of new patron
        boolean r; //Is the new patron a regular (Yes/No)(Converted to boolean)
        int c; //New patron's coolness rating (0(Erkel)-10(Fonzie))
        int count = 0;
        while (count < 100) {
            Scanner one = new Scanner(System.in);
            System.out.println("Enter the number of the command:" +
                    "1: Add a patron to the queue       " +
                    "2: Admit the highest priority patron       " +
                    "3: Close for the night (Quit)");
            response = one.nextInt();
            if (response == 1) {//Lets make a new person!

                //Whats their name?
                Scanner two = new Scanner(System.in);
                System.out.println("Enter patron's name:");
                n = one.next();

                //On a scale from Steve Erkel to Fonzie, how cool are they?
                Scanner three = new Scanner(System.in);
                System.out.println("Enter patron's coolness (0-10):");
                c = two.nextInt();

                //Hey babe, you come here often?
                Scanner four = new Scanner(System.in);
                System.out.println("Is the patron a regular? (yes/no):");
                String strBoolean = one.next();
                //Let me make that a boolean for you. (women love booleans)
                //
                if (strBoolean == "yes") {
                    strBoolean = "true";
                    r = Boolean.parseBoolean(strBoolean);
                    Patron patron = new Patron(n, c, r);
                } else {
                    r = false;
                    Patron patron = new Patron(n, c, r);
                }
            }
                HeapQueue heap = new HeapQueue();
                if (response == 2) {
                if (heap.isEmpty()== true) {
                        System.out.println("The queue is empty");
                    } else {
                        System.out.println(heap.toString());
                        heap.dequeue();
                        System.out.println("Let 'em in!");
                    }
                    if (response == 3) {
                        System.out.println("Good Night!");
                        System.exit(0);
                    }
                }
        }
    }
}
