/**
 * Created by johnjudge on 2/24/15.
 */

public class CheckingAccount extends BankAccount{

    //Feilds
    public static final double BONUS_MONTHLY_RATE = .001/12;
    //The interest rate for a bonus account - currently 0.1% per year
    public static final double PREMIUM_CHECKING_MINIMUM_BALANCE	= 500.0;
    //The minimum balance to earn interest.
    // If the minimum balance is met, interest is earned on the total balance.
    public static boolean bonus;
    public java.lang.String CHECKING_ACCOUNT;

    public CheckingAccount(double newMoney,java.lang.String owner, boolean bonus){
        /*
         *A constructor for a CheckingAccount object.
         * It accepts the amount of money deposited when the account is created.
         * @p:newMoney - The amount of money deposited when the account is opened.
         * @p:owner - The owner of this account
         * @p:bonus - A boolean indicating if this account is paid interest
         */
        super( newMoney, owner);

    }
    public void calcInterest(){
       /*
        *Calculate the interest and update the balance for this account.
        * Checking accounts earn interest only if a "bonus" account.
        * @s: calcInterest in class BankAccount
        */
        if(bonus == true){
            addInterest(BONUS_MONTHLY_RATE * getCurrentBalance());
        }

    }
    public java.lang.String getAccountType(){
        /*
         *The account type designation for this account.
         * @s:getAccountType in class BankAccount
         * @r:String representation of this account type.
         */
        if(bonus == true){
            CHECKING_ACCOUNT = "CI";
        }
        else{
            CHECKING_ACCOUNT = "CN";
        }
        return CHECKING_ACCOUNT;
    }
    @Override
    public java.lang.String toString(){
        /*
         *return a printable version of this object
         * @o: toString in class bankAccount
         * @r: the owner name, current balance and literal "Checking" to identify this account
         */
        return "Account Owner: "+ownerName + ", Account balance: "+getCurrentBalance() + ", Checking";
    }



}
