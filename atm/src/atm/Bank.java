package atm;

import java.util.Scanner;

public class Bank {
	private UserManager userM;
	private AccountManager accountM;
	private Scanner scanner;
	private String name;
	private String log;

	public void init() {
		userM = new UserManager();
		accountM = new AccountManager();
		scanner = new Scanner(System.in);
		log = "";
	}

	// Banking ���� �޼ҵ�
	public Bank(String name) {
		this.name = name;
	}

	public String inputData(String memo) {
		System.out.print(memo + " : ");
		String data = scanner.next();

		return data;
	}

	public void printMenu() {
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ��Ż��");
		System.out.println("3. ���½�û");
		System.out.println("4. �α���");
		System.out.println("5. �α׾ƿ�");
		System.out.println("0. ����");
	}

	public void printSubMenu() {
		System.out.println("1. ���� ��û(�ִ� 3������)");
		System.out.println("2. ���� ����");
		System.out.println("3. ���� �ҷ�����");
		System.out.println("4. ���� ����");
		System.out.println("5. �ڷΰ���");
	}

	public void printUserData() {
		for (int i = 0; i < userM.getList().size(); i++) {
			System.out.println(userM.getList().get(i).getName());
		}
	}

	public void plusUser() {
		String id = inputData("id");
		String password = inputData("password");
		String name = inputData("�̸�");

		userM.addArr(id, password, name);
	}

	public void accsPart() {
		while (true) {
			printSubMenu();
			int menu = scanner.nextInt();

			if (menu == 1) {
				String account = inputData("�߰��� ���¹�ȣ �Է�");
				accountM.addArr(log, account);
			} else if (menu == 2) {
				String account = inputData("������ ���¹�ȣ �Է�");
				accountM.deleteArr(account);
			} else if (menu == 3) {
				accountM.getUserById(log);
			} else if (menu == 4) {
				String account = inputData("�����ϰ� ���� ���¹�ȣ �Է�");
				String changeAccs = inputData("������ ���¹�ȣ �Է�");
				accountM.updateArr(account, changeAccs);
			} else if (menu == 5) {
				break;
			}
		}
	}

	public void logIn() {
		String id = inputData("id");
		String password = inputData("password");
		for (int i = 0; i < this.userM.getList().size(); i++) {
			if (this.userM.getList().get(i).getName().equals(id)
					&& this.userM.getList().get(i).getPassword().equals(password)) {
				this.log = id;
				System.out.printf("�α��� ���� %s�� ȯ���մϴ�.\n", this.log);
				break;
			}
		}
	}

	public void run() {
		init();
		while (true) {
			// ������ �߰� Ȯ�ο�
			printUserData();

			printMenu();
			int sel = scanner.nextInt();

			if (sel == 1 && log == "") {
				plusUser();
			} else if (sel == 2 && log != "") {

			} else if (sel == 3 && log != "") {
				accsPart();
			} else if (sel == 4) {
				logIn();
			} else if (sel == 5) {
				log = "";
				System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
			} else if (sel == 0) {
				break;
			}
		}
	}
}
