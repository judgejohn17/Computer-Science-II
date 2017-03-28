/**
 * Created by johnjudge on 2/24/15.
 */



public abstract class BankAccount {
    /*
     *Main class for all bank accounts
     */
    private double newMoney;
    public java.lang.String ownerName;
    private double balance;
    private double interest;
    private String accountType;

    public BankAccount(double newMoney, java.lang.String ownerName) {
        //Constructer for a new Bank Account
        balance = newMoney;
        this.ownerName = ownerName;
    }

    protected void addInterest(double newInterestEarned){
        /*
         *Add the current interest amount to the current balance
         *@p:newInterestEarned - the total amount of interest earned.
         */
        balance = newInterestEarned + balance;
        interest = newInterestEarned;

    }
    public abstract void calcInterest();
        /*
         *Calculate the interest and update the balance for this account.
         *The rules for earning interest are different for every kind of account.
         */

    public abstract java.lang.String getAccountType();
        /*
         *Return the two character String identifier for the account.
         *@r: The two character String identifier for the account
         */

    public double getCurrentBalance(){
        /*
         *Return the current balance
         *@r:the current balance
         */
        return balance;
    }

    public double getInterest(){
        /*
         *Return the current interest earned
         *@r:the current interest earned
         */
        return interest;


    }

    public void printStatement(){
        /*
         *Print a statement for this month.
         */
        System.out.printf(getAccountType()+" " + "%-20s Interest Earned: %-10.2f Current Balance %-15.2f \n",ownerName, interest, balance);
    }

    protected void setInterestEarned(double interest){
        /*
         *Store the interest earned
         */
        this.interest = interest + this.balance;

    }

    public java.lang.String toString(){
        /*
         *A printable version of this account
         * @0:toString in class java.lang.Object
         * @r:the owner name and the current balance
         */
        return ownerName + "  "+ getCurrentBalance();
    }
}
