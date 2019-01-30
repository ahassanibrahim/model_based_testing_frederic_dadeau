package modelBasedTesting;

import fr.ufc.m2info.svam.ATM;
import fr.ufc.m2info.svam.Account;
import fr.ufc.m2info.svam.Card;

public class ATMAdapter {
 
    ATM sut = new ATM();
    Account account = new Account(100);
    Card c = new Card(1234,account );
    public void reset() {
        sut = new ATM();
        account = new Account(100);
        c = new Card(1234,account );
    }
    
    public int insertCard_Ok() {
         return sut.insertCard(c);
    }
    public int insertCard_KO() {
        // create a card with pin code 1234 and associated account of 100 euros in balance
    	sut.inputPin(1235);
    	sut.inputPin(1235);
    	sut.inputPin(1235);
    	if(c.isBlocked() == true)
    	System.out.println("card blocked");
    	else  System.out.println("card not blocked");
         return sut.insertCard(c);
    }
    public void cancel() {
        System.out.println("Pressed cancel");
        sut.cancel();
    }
    public int inputPin()
    {
       // System.out.println("Input PIN");
        return sut.inputPin(1234);
    }
    public Card takeCard()
    {
    	System.out.println("takeCard");
    	return sut.takeCard();
    }
    public int chooseValid()
    {
    	return sut.chooseAmount(120);
    }
    public int chooseNotPossibleValue()
    {
    	return sut.chooseAmount(120);
    }
    
    public int chooseMoreBalence()
    {
    	return sut.chooseAmount(110);
    }
    
    public boolean debitAccount()
    {
    	return account.debit(80);
    }
    
    
    
}