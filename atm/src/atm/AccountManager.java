package atm;

import java.util.ArrayList;

public class AccountManager {

	private static ArrayList<Account> list = new ArrayList<Account>();
	
	public ArrayList<Account> getList(){
		return this.list;
	}
	
	// Create
	public void addAccount(Account account) {
		this.list.add(account);
	}
	
	// Read
	public Account getAccount(int index) {
		Account account = this.list.get(index);
		String userId = account.getUserId(); 
		String accNum = account.getAccNum();
		int money = account.getMoney();
		Account reqAcc = new Account(userId, accNum, money);
		return reqAcc;
	}
	
	// Update
	public void setAccount(int index, Account account) {
		this.list.set(index, account);
	}
	
	// Delete
	public void deleteAccount(int index) {
		this.list.remove(index);
	}
	public void deleteAccountByNum(int account) {
		this.list.remove(account);
	}
}
