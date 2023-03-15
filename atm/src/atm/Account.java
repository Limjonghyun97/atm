package atm;

public class Account {
	
	public static int LIMIT = 3;
	
	private String userId;
	private String accNum;
	private int money;

	public Account(String userId, String accNum, int money) {
		this.userId = userId;
		this.accNum = accNum;
		this.money = money;
	}

	public String getUserId() {
		return this.userId;
	}
	public String getAccNum() {
		return this.accNum;
	}
	public int getMoney() {
		return this.money;
	}
}