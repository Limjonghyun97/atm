package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int NEW_ACC = 3;
	private final int DELETE_ACC = 4;
	private final int LOG_IN = 5;
	private final int LOG_OUT = 6;
	private final int BANKING = 7;
	private final int QUIT = 0;

	private UserManager um;
	private AccountManager am;

	private String name;
	private Scanner scan;
	private int log;

	private ArrayList<User> userList;
	private ArrayList<Account> accountList;

	private File file;
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	// ATM ������Ʈ
	// ȸ������/Ż��
	// ���½�û/öȸ (1�� 3���±���)
	// �α���

	// Banking ���� �޼ҵ�

	public Bank(String name) {
		this.name = name;
		init();
		getFileReader();
	}

	private void init() {
		this.um = new UserManager();
		this.am = new AccountManager();
		this.scan = new Scanner(System.in);
		this.userList = um.getList();
		this.accountList = am.getList();
		this.log = -1;
		this.file = new File("atm.txt");
	}

	private void getFileReader() {
		try {
			this.fileReader = new FileReader(this.file);
			this.bufferedReader = new BufferedReader(this.fileReader);

			int count = 0;
			while (this.bufferedReader.ready()) {
				String[] dataArr = bufferedReader.readLine().split("/");
				User userData = new User(dataArr[0], dataArr[1], dataArr[2]);
				userList.add(userData);
				if (dataArr.length > 3) {
					Account accountData = new Account(dataArr[0], dataArr[3], Integer.parseInt(dataArr[4]));
					userList.get(count++).getAccs().add(accountData);
				}
			}

			this.fileReader.close();
			this.bufferedReader.close();
			System.out.println("���Ϸε� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("���Ϸε� ����");
		}
	}

	private void getFileWriter() {
		try {
			this.fileWriter = new FileWriter(this.file);

			for (int i = 0; i < userList.size(); i++) {
				String data = "";
				User x = userList.get(i);
				if (userList.get(i).getAccs().size() > 0) {
					for (int j = 0; j < userList.get(i).getAccs().size(); j++) {
						Account y = x.getAccs().get(j);
						data += x.getId() + "/" + x.getPassword() + "/" + x.getName() + "/";
						data += y.getAccNum() + "/" + y.getMoney() + "\n";
					}
				} else
					data += x.getId() + "/" + x.getPassword() + "/" + x.getName() + "\n";
				fileWriter.write(data);
			}

			this.fileWriter.close();
			System.out.println("���Ͼ��� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("���Ͼ��� ����");
		}
	}

	private void printMenu() {
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ��Ż��");
		System.out.println("3. ���½�û");
		System.out.println("4. ����öȸ");
		System.out.println("5. �α���");
		System.out.println("6. �α׾ƿ�");
		System.out.println("7. ��ŷ");
		System.out.println("0. ����");
	}

	public int selectMenu() {
		System.out.println("�޴� ���� : ");
		return this.scan.nextInt();
	}

	public String inputUserInfo(String memo) {
		System.out.print(memo + " : ");
		String user = scan.next();
		return user;
	}

	private void join() {
		String id = inputUserInfo("id");
		String pw = inputUserInfo("pw");
		String name = inputUserInfo("name");
		User user = new User(id, pw, name);
		int index = -1;
		for (int i = 0; i < this.userList.size(); i++) {
			if (this.userList.get(i).getId().equals(id)) {
				index = i;
			}
		}
		if (index == -1) {
			System.out.println("ȸ�����ԿϷ�");
			this.userList.add(user);
		} else {
			System.out.println("�ߺ��� ���̵� �Դϴ�.");
		}
	}

	private void leave() {
		String id = inputUserInfo("id");
		String pw = inputUserInfo("pw");
		int index = -1;
		for (int i = 0; i < this.userList.size(); i++) {
			if (this.userList.get(i).getId().equals(id) && this.userList.get(i).getPassword().equals(pw))
				index = i;
		}
		if (index != -1) {
			System.out.println("ȸ��Ż��Ϸ�");
			this.userList.remove(index);
		} else
			System.out.println("��ġ���� �ʴ� �����Դϴ�.");
	}

	private void newAcc() {
		this.accountList = this.userList.get(log).getAccs();
		if (this.accountList.size() < Account.LIMIT) {
			String accNum = inputUserInfo("���¹�ȣ");
			Account account = new Account(this.userList.get(log).getId(), accNum, 0);
			this.accountList.add(account);
		} else {
			System.out.println("���´� �ִ� 3�������� ������ �� �ֽ��ϴ�.");
		}
	}

	private void deleteAcc() {
		this.accountList = this.userList.get(log).getAccs();
		if (this.accountList.size() == 0) {
			System.out.println("������ ���°� �����ϴ�.");
			return;
		}

		for (int i = 0; i < this.accountList.size(); i++) {
			System.out.printf("%d) %s / %d��\n", i + 1, this.accountList.get(i).getAccNum(),
					this.accountList.get(i).getMoney());
		}
		System.out.println("������ ���¹�ȣ�� index�� �����ÿ�");
		int select = this.scan.nextInt() - 1;
		this.accountList.remove(select);
	}

	private void logIn() {
		String id = inputUserInfo("id");
		String pw = inputUserInfo("pw");
		int index = -1;
		for (int i = 0; i < this.userList.size(); i++) {
			if (this.userList.get(i).getId().equals(id) && this.userList.get(i).getPassword().equals(pw))
				index = i;
		}
		if (index != -1) {
			System.out.println("�α��μ���");
			log = index;
		}
	}

	private void logOut() {
		System.out.println("�α׾ƿ��մϴ�.");
		this.log = -1;
	}

	private void printAll() {
		for (int i = 0; i < this.userList.size(); i++) {
			User user = userList.get(i);
			System.out.printf("%s/%s/%s\n", user.getId(), user.getPassword(), user.getName());
			ArrayList<Account> accs = user.getAccs();
			for (int j = 0; j < accs.size(); j++) {
				System.out.printf("%s���� %d��° ����\n ���¹�ȣ : %s / �ܾ� : %d��\n", user.getId(), j + 1, accs.get(j).getAccNum(),
						accs.get(j).getMoney());
			}
		}
	}

	private void printBackingMenu() {
		System.out.println("1. �Ա�");
		System.out.println("2. ���");
		System.out.println("3. ��ȸ");
		System.out.println("4. ��ü");
		System.out.println("0. �ڷΰ���");
	}

	private void plusMoney() {
		this.accountList = this.userList.get(log).getAccs();
		for (int i = 0; i < this.accountList.size(); i++) {
			System.out.printf("%d) %s / %d��\n", i + 1, this.accountList.get(i).getAccNum(),
					this.accountList.get(i).getMoney());
		}
		System.out.println("�Ա��� ���¹�ȣ�� index�� �����ÿ�");
		int select = this.scan.nextInt() - 1;

		if (select >= 0 && select < this.accountList.size()) {
			int inputM = Integer.parseInt(inputUserInfo("�Ա��� �ݾ�"));

			if (inputM > 0) {
				User user = userList.get(log);
				ArrayList<Account> accs = user.getAccs();
				inputM += accs.get(select).getMoney();
				Account acc = new Account(accs.get(select).getUserId(), accs.get(select).getAccNum(), inputM);
				this.accountList.set(select, acc);
			}
		}
	}

	private void outMoney() {
		this.accountList = this.userList.get(log).getAccs();
		for (int i = 0; i < this.accountList.size(); i++) {
			System.out.printf("%d) %s / %d��\n", i + 1, this.accountList.get(i).getAccNum(),
					this.accountList.get(i).getMoney());
		}
		System.out.println("����� ���¹�ȣ�� index�� �����ÿ�");
		int select = this.scan.nextInt() - 1;

		if (select >= 0 && select < this.accountList.size()) {
			User user = userList.get(log);
			ArrayList<Account> accs = user.getAccs();
			int outM = Integer.parseInt(inputUserInfo("����� �ݾ�"));

			if (outM > 0 && outM <= accs.get(select).getMoney()) {
				outM = accs.get(select).getMoney() - outM;
				Account acc = new Account(accs.get(select).getUserId(), accs.get(select).getAccNum(), outM);
				this.accountList.set(select, acc);
			}
		}
	}

	private void scanMoney() {
		this.accountList = this.userList.get(log).getAccs();
		for (int i = 0; i < this.accountList.size(); i++) {
			System.out.printf("%d) %s / %d��\n", i + 1, this.accountList.get(i).getAccNum(),
					this.accountList.get(i).getMoney());
		}
	}

	private void sendMoney() {
		this.accountList = this.userList.get(log).getAccs();
		for (int i = 0; i < this.accountList.size(); i++) {
			System.out.printf("%d) %s / %d��\n", i + 1, this.accountList.get(i).getAccNum(),
					this.accountList.get(i).getMoney());
		}
		System.out.println("��ü�� ���¹�ȣ�� index�� �����ÿ�");
		int select = this.scan.nextInt() - 1;

		if (select >= 0 && select < this.accountList.size()) {
			String sendM = inputUserInfo("�Ա��� ���¹�ȣ�� �Է��ϼ���.");

			boolean isRun = false;
			for (int i = 0; i < this.userList.size(); i++) {
				if (i != log) {
					User user = userList.get(i);
					System.out.printf("%s/%s/%s\n", user.getId(), user.getPassword(), user.getName());
					ArrayList<Account> accs = user.getAccs();
					for (int j = 0; j < accs.size(); j++) {
						if (accs.get(j).getAccNum().equals(sendM)) {
							isRun = true;
						}
					}
				}
			}
			if (isRun) {
				int inputM = Integer.parseInt(inputUserInfo("�Ա��� �ݾ�"));

				User user = userList.get(log);
				ArrayList<Account> accs = user.getAccs();
				if (inputM > 0 && inputM <= accs.get(select).getMoney()) {
					inputM += accs.get(select).getMoney();
					Account acc = new Account(accs.get(select).getUserId(), accs.get(select).getAccNum(), inputM);
					this.accountList.set(select, acc);
				}
			} else {
				System.out.println("��ġ�ϴ� ���°� �����ϴ�.");
			}
		}
	}

	private void Baking() {
		while (true) {
			printBackingMenu();
			int sel = selectMenu();
			if (sel == 1)
				plusMoney();
			else if (sel == 2)
				outMoney();
			else if (sel == 3)
				scanMoney();
			else if (sel == 4)
				sendMoney();
			else if (sel == 0)
				break;
		}
	}

	private void printLogInUser() {
		System.out.printf("%s�� ȯ���մϴ�!!\n", this.userList.get(log).getId());
	}

	public void run() {
		while (true) {
			printAll();
			printMenu();
			if (this.log != -1) {
				printLogInUser();
			}
			int select = selectMenu();
			if (select == JOIN)
				join();
			else if (select == LEAVE)
				leave();
			else if (select == NEW_ACC && this.log != -1)
				newAcc();
			else if (select == DELETE_ACC && this.log != -1)
				deleteAcc();
			else if (select == LOG_IN && this.log == -1)
				logIn();
			else if (select == LOG_OUT && this.log != -1)
				logOut();
			else if (select == BANKING)
				Baking();
			else if (select == QUIT) {
				getFileWriter();
				break;
			}
		}
	}
}