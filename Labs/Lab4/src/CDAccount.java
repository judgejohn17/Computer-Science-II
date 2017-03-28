/**
 * Created by johnjudge on 2/24/15.
 */
public class CDAccount extends BankAccount{

    //Fields
    static double MONTHLY_INTEREST_RATE = .006/12;
    //The anual interest rate for a standard savings account
    static double MINIMUM_BALANCE = 1000.0;
    public final java.lang.String CD_ACCOUNT = "CD";
    //The account type for this account

    public CDAccount(double newMoney,java.lang.String owner){
        /*
         *A constructor for a CD object.
         * It accepts the amount of money deposited when the account is created.
         * @p:newMoney - The amount of money deposited when the account is opened.
         * @p:owner - The owner of this account
         */
        super(newMoney,owner);
    }
    public void calcInterest(){
       /*
        *Calculate the interest and update the balance for this account.
        * Checking accounts earn interest only if a "bonus" account.
        * @s: calcInterest in class BankAccount
        */
        if(MINIMUM_BALANCE<= getCurrentBalance()) {
            addInterest(MONTHLY_INTEREST_RATE * getCurrentBalance());
        }
    }
    public java.lang.String getAccountType(){
        /*
         *The account type designation for this account.
         * @s:getAccountType in class BankAccount
         * @r:String representation of this account type.
         */
        return CD_ACCOUNT;

    }
    @Override
    public java.lang.String toString(){
        /*
         *return a printable version of this object
         * @o: toString in class bankAccount
         * @r: the owner name, current balance and literal "CD" to identify this account
         */
        return "Owner Name: "+ownerName+"  Current Balance: "+getCurrentBalance()+"  CD";

    }
}
