package atm;

import java.util.ArrayList;

public class User {
	private String user, password, name;
	private ArrayList<Account> accs;

	public User(String user, String password, String name) {
		this.user = user;
		this.password = password;
		this.name = name;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return this.name;
	}
}
