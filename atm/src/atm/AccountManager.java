package atm;

import java.util.ArrayList;

public class AccountManager {

	private static ArrayList<Account> list = new ArrayList<Account>();

	public AccountManager() {
		list = new ArrayList<Account>();
	}

	public ArrayList<Account> getList() {
		return this.list;
	}

	// Account�� ����

	// Create
	public static void addArr(String id, String account) {

		int count = 0;
		boolean isRun = true;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				count++;
			}
			if (list.get(i).getAccount().equals(account)) {
				isRun = false;
			}
		}
		if (count < 3) {
			if (isRun) {
				Account data = new Account(id, account, 0);
				list.add(data);
				System.out.println("���¹�ȣ ���� ����");
			} else {
				System.out.println("�ߺ��� ���¹�ȣ�� �ֽ��ϴ�.");
			}
		} else {
			System.out.println("���� �Ѹ�� 3���� ���¹�ȣ�� ���� �� �ֽ��ϴ�.");
		}
	}

	// Read
	public void getUserById(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				System.out.println(i + ") " + list.get(i).getAccount());
			}
		}
	}

	// Update
	public static void updateArr(String account, String changeAccs) {
		int index = -1;
		boolean isRun = true;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAccount().equals(account)) {
				index = i;
			}
			if (list.get(i).getAccount().equals(changeAccs)) {
				isRun = false;
			}
		}
		if (isRun && index != -1) {
			Account data = new Account(list.get(index).getId(), changeAccs, list.get(index).getMoney());
			list.set(index, data);
		} else {
			System.out.println("�ٽ� Ȯ�����ּ���.");
		}
	}

	// Delete
	public static void deleteArr(String account) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAccount().equals(account)) {
				list.remove(i);
				System.out.println("���� ����");
			}
		}
	}
}
