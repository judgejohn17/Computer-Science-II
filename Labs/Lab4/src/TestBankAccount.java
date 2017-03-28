/**
 * Created by johnjudge on 2/24/15.
 */
import java.util.ArrayList;
public class TestBankAccount{
    public static void main(String[] args){
        ArrayList<BankAccount> list= new ArrayList<>();
        BankAccount a= new CDAccount( 11150,  "Andy Anderson");
        BankAccount b= new CheckingAccount( 6466.75,  "Sharon Smith",  false);
        BankAccount c= new CDAccount( 10000,  "Phil Phillips");
        BankAccount d= new CheckingAccount( 450,  "Carol Carroll",  true);
        BankAccount e= new SavingsAccount( 5000,  "Bill Bailey");
        BankAccount f= new CheckingAccount( 5500,  "Lois Lane",  true);
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        for(int i=1; i<=4;i++){
            System.out.println("Interest Report-Month "+i + "\n");
            for(BankAccount X:list){
                X.calcInterest();
                X.printStatement();


            }
            System.out.println("\n");
        }
    }
}
