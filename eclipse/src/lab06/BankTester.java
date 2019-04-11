package lab06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

class BankTester {
    List<BankAccount> accounts;
    List<Object> objects;
    //we can initialize some objects to be used in later tests
    //using the BeforeEach keyword ensures this method is called before 
    //the test methods are called

    //Please note that this method is rerun before the start of each test,
    //which means the BankAccounts will have their numId's increased 
    //each time the account is called.

    //we will fix this issue by adding an @AfterEach method, which is run 
    //after each test ends
    @BeforeEach
    void initialize(){
    	// what's the purpose of doing this here if we're only going to
    	// use it for one test?
    	accounts = new ArrayList<>();

    	accounts.add(new BankAccount(100.0));
    	accounts.add(new SavingsAccount(110.0, .05));
    	accounts.add(new CheckingAccount(50.0, 3));
    	accounts.add(new BankAccount(150.0));
    	accounts.add(new CheckingAccount(1000.0, 5));
    	accounts.add(new SavingsAccount(5000.0, .1));
    	
    	objects = new ArrayList<>();
    	
    	objects.add(3);
    	objects.add(4.0);
    	objects.add(new BankTester());
    	objects.add("asdf");
    	objects.add(new ArrayList<Object>());
    	objects.add(new BankAccount(30.0));
    	objects.add(new int[]{3, 4, 5});
    	objects.add(new SavingsAccount(150.0, .05));
    }

    @AfterEach
    void resetIdNum(){
    	BankAccount.reset();
    	accounts.clear();
    }


    //the @Test is necessary, without it the test will not be run
    @Test
    @DisplayName("Tests deposit method of bank account")
    void testBankAccDeposit() {
    	BankAccount bacc = new BankAccount(700.00);
    	bacc.deposit(100);
    	try {
    		//when comparing two doubles, we need to give it some small value for it to see 
    		//if the expected and actual value are close enough to equaling one another
    		assertEquals(800.00, bacc.getBalance(), 1e-6);
    	}
    	//here if there is an assertion error, we can catch it 
    	//and print the error message out, then throw the AssertionError
    	//so that the test still fails. 
    	catch(AssertionError e){
    		System.out.println("BankAccount's deposit() method failed: " + e.getMessage());
    		throw e;
    	}
    }
    @Test
    @DisplayName("Tests withdraw method of checking account")
    void testCheckingAccWithdraw(){
    	CheckingAccount checking = new CheckingAccount(1000.00, 10);
    	for(int i = 0; i < 3; i++) checking.withdraw(100);
    	try {
    		//when comparing two doubles, we need to give it some small value for it to see 
    		//if the expected and actual value are close enough to equaling one another
    		assertEquals(700.00, checking.getBalance(), 1e-6);
    	}
    	//here if there is an assertion error, we can catch it 
    	//and print the error message out, then throw the AssertionError
    	//so that the test still fails. 
    	catch(AssertionError e){
    		System.out.println("CheckingAccount's withdraw() method failed: " + e.getMessage());
    		throw e;
    	}
    }
    @Test
    @DisplayName("Tests getter for withdrawCount of a CheckingAccount")
    void testCheckingAccGetWithdrawCount(){
    	CheckingAccount checking = new CheckingAccount(1000.00, 10);
    	for(int i = 0; i < 3; i++) checking.withdraw(100);
    	try {
    		//when comparing two integers, we don't need a third argument 
    		//since ints can be checked for equality
    		assertEquals(3, checking.getWithdrawCount());
    	}
    	//here if there is an assertion error, we can catch it 
    	//and print the error message out, then throw the AssertionError
    	//so that the test still fails. 
    	catch(AssertionError e){
    		System.out.println("CheckingAccount's getWithdrawCount() method failed: " + e.getMessage());
    		System.out.println("Make sure you adjust the withdrawCount in your CheckingAccount's withdraw() method!");
    		throw e;
    	}
    }

    //TODO: Finish out these seven additional tests. Keep each test in a separate test function.
    //This isolates testing a single method or case to a single testing method. Otherwise, 
    //if you try to do too much testing in one tester method, you could throw an error
    //for a test and never find the errors of the other tests you put in that method.
    //Alternatively, you could use assertAll. 

    @Test
    @DisplayName("Tests deposit to a Savings Account")
    void testSavingsAccDeposit(){
    	SavingsAccount savings = new SavingsAccount(100.0, .1);

    	savings.deposit(100);

    	try
    	{
    		assertEquals(210.0, savings.getBalance(), 1e-6);
    	}
    	catch (AssertionError e)
    	{
    		System.out.println("SavingsAccount's desposit(double) failed");
    		throw e;
    	}
    }

    @Test
    @DisplayName("Exceeds the withdraw limit of a Checking Account")
    void testExceedingCheckingWithdraw() {
    	CheckingAccount checking = new CheckingAccount(1000.0, 3);

    	for (int i = 0; i < 4; ++i)
    		checking.withdraw(100.0);

    	try
    	{
    		assertAll("checking withdraw",
    				() -> assertEquals(700.0, checking.getBalance(), 1e-6),
    				() -> assertEquals(3, checking.getWithdrawCount()));
    	}
    	catch (AssertionError e)
    	{
    		System.out.println("CheckingAccount's exceeding withdrawal limit failed");
    		throw e;
    	}
    }

    @Test
    @DisplayName("Tests BankAccount's withdraw where amt <= balance")
    void testTypicalBankAccWithdraw() {
    	BankAccount acc = new BankAccount(10.0);

    	var withdrew = acc.withdraw(5.0);

    	try
    	{
    		assertAll("typical withdrawal",
    				() -> assertEquals(5.0, acc.getBalance(), 1e-6),
    				() -> assertEquals(5.0, withdrew, 1e-6));
    	}
    	catch (AssertionError e)
    	{
    		System.out.println("BankAccount typical withdrawal failed");
    		throw e;
    	}
    }

    @Test
    @DisplayName("Tests BankAccount's withdraw where amt > balance")
    void testEmptyOutBankAccViaWithdraw() {
    	BankAccount acc = new BankAccount(10.0);

    	var withdrew = acc.withdraw(12.0);

    	try
    	{
    		assertAll("empty withdrawal",
    				() -> assertEquals(0.0, acc.getBalance(), 1e-6),
    				() -> assertEquals(10.0, withdrew, 1e-6));
    	}
    	catch (AssertionError e)
    	{
    		System.out.println("BankAccount empty withdrawal failed");
    		throw e;
    	}
    }

    @Test
    @DisplayName("Tests BankAccount's toString()")
    void testBankAccToString() {
    	BankAccount acc = new BankAccount(100.0);

    	try
    	{
    		assertEquals("Account #8 has $100.0", acc.toString());
    	}
    	catch (AssertionError e)
    	{
    		System.out.println("BankAccount's toString() failed");
    		throw e;
    	}
    }

    @Test
    @DisplayName("Tests CheckingAccount's toString()")
    void testCheckingAccToString() {
    	CheckingAccount acc = new CheckingAccount(100.0, 3);

    	try
    	{
    		assertEquals("Account #8 has $100.0 0/3", acc.toString());
    	}
    	catch (AssertionError e)
    	{
    		System.out.println("CheckingAccount's toString() failed");
    		throw e;
    	}
    }

    @Test
    @DisplayName("Tests SavingsAccount's toString()")
    void testSavingsAccToString() {
    	SavingsAccount acc = new SavingsAccount(100.0, .5);

    	try
    	{
    		assertEquals("Account #8 has $100.0 @ 50.0%", acc.toString());
    	}
    	catch (AssertionError e)
    	{
    		System.out.println("SavingsAccount's toString() failed");
    		throw e;
    	}
    }

    //TODO: time to do some things showcasing Dynamic Dispatching
    @Test
    @DisplayName("prints based off dynamic dispatching")
    void dynamicDispatchPrinting(){
    	for (var b : accounts)
    	{
    		if (b instanceof SavingsAccount)
    			System.out.println("SavingsAccount: ");
    		else if (b instanceof CheckingAccount)
    			System.out.println("CheckingAccount: ");
    		else
    			System.out.println("BankAccount: ");

    		System.out.println(b);
    	}
    	
    	for (var b: objects)
    		System.out.println(b);
    }

}
