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

	// Account에 대한

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
				System.out.println("계좌번호 생성 성공");
			} else {
				System.out.println("중복된 계좌번호가 있습니다.");
			}
		} else {
			System.out.println("유저 한명당 3개의 계좌번호만 만들 수 있습니다.");
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
			System.out.println("다시 확인해주세요.");
		}
	}

	// Delete
	public static void deleteArr(String account) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAccount().equals(account)) {
				list.remove(i);
				System.out.println("삭제 성공");
			}
		}
	}
}
