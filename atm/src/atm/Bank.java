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

	// Banking 관련 메소드
	public Bank(String name) {
		this.name = name;
	}

	public String inputData(String memo) {
		System.out.print(memo + " : ");
		String data = scanner.next();

		return data;
	}

	public void printMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 로그인");
		System.out.println("5. 로그아웃");
		System.out.println("0. 종료");
	}

	public void printSubMenu() {
		System.out.println("1. 계좌 신청(최대 3개까지)");
		System.out.println("2. 계좌 삭제");
		System.out.println("3. 계좌 불러오기");
		System.out.println("4. 계좌 변경");
		System.out.println("5. 뒤로가기");
	}

	public void printUserData() {
		for (int i = 0; i < userM.getList().size(); i++) {
			System.out.println(userM.getList().get(i).getName());
		}
	}

	public void plusUser() {
		String id = inputData("id");
		String password = inputData("password");
		String name = inputData("이름");

		userM.addArr(id, password, name);
	}

	public void accsPart() {
		while (true) {
			printSubMenu();
			int menu = scanner.nextInt();

			if (menu == 1) {
				String account = inputData("추가할 계좌번호 입력");
				accountM.addArr(log, account);
			} else if (menu == 2) {
				String account = inputData("삭제할 계좌번호 입력");
				accountM.deleteArr(account);
			} else if (menu == 3) {
				accountM.getUserById(log);
			} else if (menu == 4) {
				String account = inputData("변경하고 싶은 계좌번호 입력");
				String changeAccs = inputData("수정할 계좌번호 입력");
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
				System.out.printf("로그인 성공 %s님 환영합니다.\n", this.log);
				break;
			}
		}
	}

	public void run() {
		init();
		while (true) {
			// 데이터 추가 확인용
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
				System.out.println("로그아웃 되었습니다.");
			} else if (sel == 0) {
				break;
			}
		}
	}
}
