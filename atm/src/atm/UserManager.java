package atm;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();

	public ArrayList<User> getList() {
		return this.list;
	}

	// Create
	public void addUser(User user) {
		this.list.add(user);
	}

	// Read
	public User getUser(int index) {
		User user = this.list.get(index);
		String id = user.getId();
		String pw = user.getPassword();
		String name = user.getName();
		User reqObj = new User(id, pw, name);
		return reqObj;
	}

	// Update
	public void setUser(int index, User user) {
		this.list.set(index, user);
	}

	// Delete
	public void deleteUser(int index) {
		this.list.remove(index);
	}

	public void deleteUserById(String id) {
		this.list.remove(id);
	}
}
