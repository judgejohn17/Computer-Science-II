/**
 * Created by johnjudge on 2/24/15.
 */
public class SavingsAccount extends BankAccount{

    //Feilds
    static double SAVINGS_MONTHLY_INTEREST_RATE = .003/12;
    //The anual interest rate for a standard savings account
    public final java.lang.String SAVINGS_ACCOUNT = "SA";
    //The account type for this account

    public SavingsAccount(double newMoney,java.lang.String owner){
        /*
         *A constructor for a SavingsAccount object.
         * It accepts the amount of money deposited when the account is created.
         * @p:newMoney - The amount of money deposited when the account is opened.
         * @p:owner - The owner of this account
         */
        super( newMoney, owner);
    }
    public void calcInterest(){
       /*
        *Calculate the interest and update the balance for this account.
        * Savings accounts earn interest on the entire current balance.
        * Interest earned is calculated monthly, and added to the current balance.
        * @s: calcInterest in class BankAccount
        */
        addInterest(SAVINGS_MONTHLY_INTEREST_RATE * getCurrentBalance());
    }
    public java.lang.String getAccountType(){
        /*
         *The account type designation for this account.
         * @s:getAccountType in class BankAccount
         * @r:String representation of this account type.
         */
        return SAVINGS_ACCOUNT;
    }
    @Override
    public java.lang.String toString(){
        /*
         *return a printable version of this object
         * @o: toString in class bankAccount
         * @r: the owner name, current balance and literal "Savings" to identify this account
         */
        return "Account Owner: "+ownerName + ", Account balance: "+getCurrentBalance() + ", Savings";
    }



}

