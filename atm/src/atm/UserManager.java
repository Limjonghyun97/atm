package atm;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();

	public ArrayList<User> getList() {
		return this.list;
	}
	// User�� ����

	// Create
	public static void addArr(String id, String password, String name) {
		boolean isRun = true;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUser().equals(id)) {
				isRun = false;
			}
		}
		if (isRun) {
			User data = new User(id, password, name);
			list.add(data);
			System.out.println("���� ����");
		} else {
			System.out.println("�ߺ��Ǵ� ���̵� �ֽ��ϴ�.");
		}
	}

	// Read
	public static void readArr() {
		
	}

	// Update
	public static void updateArr() {

	}
	// Delete
	public static void deleteArr() {

	}
}
