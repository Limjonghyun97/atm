package atm;

public class Account {
	private String id, account;
	private int money;

	public Account(String id, String account, int money) {
		this.id = id;
		this.account = account;
		this.money = money;
	}

	public String getId() {
		return this.id;
	}

	public String getAccount() {
		return this.account;
	}

	public int getMoney() {
		return this.money;
	}
}
