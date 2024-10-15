package shoppingMall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingMallHome {
	static Scanner sc = new Scanner(System.in);
	static ShopMemberDAO  memberDAO = ShopMemberDAO.getInstance();
	static ShopProductDAO productDAO = ShopProductDAO.getInstance();
	
	
	public static void main(String[] args) {
		ShopMember member = null;
		boolean run = true;
			while(run) {
				
				String id;
				String pw;
				String name;
				String address;
				String phone;
				try {
				
				System.out.println("============================================");
				System.out.println("                 벼락장터");
				System.out.println("============================================");
				System.out.println("1.회원가입 2.로그인 3.상품조회(검색) 4.종료");
				System.out.println("============================================");
				System.out.print("선택(1 ~ 4)>");
				int selNum = Integer.parseInt(sc.nextLine());
				switch(selNum) {
				case 1:
					while(true) {
						System.out.println("아이디 입력(4~15자리)");
						System.out.print("아이디>");
						id = sc.nextLine();
						if(id.isBlank()) {
							System.out.println("아이디를 입력해주세요.");
						}
						else if(id.length() <4 || id.length() > 15) {
							System.out.println("아이디의 길이를 확인해주세요.");
						}
						else if(memberDAO.checkId(id)) {
							System.out.println("해당아이디는 사용할 수 없습니다. 다른 아이디를 사용해주세요.");
						}
						else break;
					}
					
					while(true) {
						System.out.println("비밀번호 입력(4~15자리)");
						System.out.print("비밀번호>");
						pw = sc.nextLine();
						if(pw.isBlank()) {
							System.out.println("비밀번호를 입력해주세요.");
						}
						else if(pw.length() <4 || pw.length() > 15) {
							System.out.println("비밀번호의 길이를 확인해주세요.");
						}
						else break;
					}
					while(true) {
						System.out.print("회원 이름>");
						name = sc.nextLine();
						if(name.isBlank()) {
							System.out.println("이름을 입력해주세요.");
						}
						else break;
					}
					while(true) {
						System.out.print("주소>");
						address = sc.nextLine();
						if(address.isBlank()) {
							System.out.println("주소를 입력해주세요.");
						}
						else break;
					}
					while(true) {
						System.out.print("연락처>");
						phone = sc.nextLine();
						if(phone.isBlank()) {
							System.out.println("이름을 입력해주세요.");
						}
						else break;
					}
					
					
					System.out.println();
					
					member = new ShopMember(id, pw, name, address, phone);
					int result = memberDAO.insert(member);
					if(result == 1) {
						System.out.println("회원 가입 완료");
					}else {
						System.out.println("회원 가입 실패.");
						
					}
					break;
				case 2://로그인
					login();
					break;
				case 3://상품 검색 및 조회
					shopProduct(null);
					break;
				case 4:	//종료
					run = false;
					System.out.println("접속 종료");
					sc.close();
					break;
				}
				}catch (NumberFormatException e) {
					System.out.println("숫자를 입력주세요.");
				}
			}
				
			sc.close();
	}//end of main
	
	static void	login() {
		ShopMember member = null;
		
		
		//로그인 체크(안되면 계속 반복)
		while(true) {
			System.out.print("아이디를 입력>");
			String id = sc.nextLine();
			System.out.print("비밀번호를 입력>");
			String pw = sc.nextLine();
			//정상적으로 로그인되면 "(회원 이름)" 환영메세지
			
			member = memberDAO.checkMember(id, pw);
			
			
			if(member != null) {
				System.out.println("환영합니다. " + member.getMemberName() + "님");
				
				//login한 상태의 메뉴(회원/ 관리자)
				if(member.getResponsibility().equals("User")) {
					loginMenu(member);
				}
				if(member.getResponsibility().equals("Admin")) {
					adminMenu(member);
				}
				break;
			}
			System.out.println("아이디와 비밀번호가 올바르지 않습니다.");
			System.out.println("다시 시도 하실거라면 \"로그인\", 아니라면 아무거나 입력하세요. ");
			System.out.print(">");
			String answer = sc.nextLine();
			
			if(!(answer.equals("로그인"))) {
				System.out.println("메뉴로 돌아갑니다.");
				break;
			}
		}
	}//end of login
	static void adminMenu(ShopMember member) {
		boolean menuRun = true;
		while(menuRun) {
			List<ShopMember> memberList = null;
			
			System.out.println("< 관리자 " + member.getMemberName() + "님>");
			System.out.println("=====================================================================================================");
			System.out.println("1.회원조회 2.상품조회(검색) 3.회원 거래기록 조회 4.로그아웃");
			System.out.println("=====================================================================================================");
			System.out.print(">>");
			int selNum = Integer.parseInt(sc.nextLine());
			switch(selNum) {
			case 1:
				System.out.println("=====================================================================================================");
				System.out.println("회원번호\t아이디\t\t비밀번호\t\t이름\t\t주소\t\t\t\t\t\t연락처\t\t권한\t가입일");
				memberList = memberDAO.members();
				for(ShopMember ele : memberList) {
					System.out.println(ele);
				}
				System.out.println("=====================================================================================================");
				break;
			case 2:
				shopProduct(member);
				break;
			case 3:
				transactionRecords(member);
				break;
			case 4:
				menuRun=false;
				System.out.println("로그아웃");
				break;
			}
		}
		
	}
	
	static void loginMenu(ShopMember member) {
		boolean menuRun = true;
		//ShopProduct product = null;
		
		while(menuRun) {
			String answer= "";
			
			int result = 0;
			
			System.out.println("<" + member.getMemberName() + "님>");
			System.out.println("=====================================================================================================");
			System.out.println("1.상품조회(검색) 2.장바구니 3.판매 상품 관리  4.회원 거래기록 조회"
					+ " 5.회원 정보 수정 6.회원 탈퇴 7.로그아웃");
			System.out.println("=====================================================================================================");
			System.out.print("선택(1 ~ 7)>");
			int selNum = Integer.parseInt(sc.nextLine());
			
			switch(selNum) {
			case 1:
				shopProduct(member);
				break;
			case 2:
				buyList(member);
				break;
			
			case 3:
				productsOfSeller(member);
				break;
				
			case 4:
				transactionRecords(member);
				break;
			case 5:
				boolean run = true;
				ShopMember currentInfo = new ShopMember();
				String currentId = member.getLoginId();
				
				currentInfo.setLoginId(member.getLoginId());	
				currentInfo.setPassword(member.getPassword());
				currentInfo.setMemberName(member.getMemberName());
				currentInfo.setMemberAddress(member.getMemberAddress());
				currentInfo.setPhone(member.getPhone());
				
				
				while(run) {
					System.out.println("===================================================================");
					System.out.println("정보 수정");
					System.out.println("<1>아이디 : " + member.getLoginId());
					System.out.println("<2>비밀번호 : " + member.getPassword());
					System.out.println("<3>이름 : " + member.getMemberName());
					System.out.println("<4>주소 : " + member.getMemberAddress());
					System.out.println("<5>전화번호 : " + member.getPhone());
					System.out.println("===================================================================");
					System.out.println("수정할 항목을 입력하세요[1, 2, 3, 4, 5]");
					System.out.println("현재 수정중인 정보를 확정 짓고 저장하고 싶다면 [6], 저장하지 않고 돌아가고 싶으면 [7]을 입력하세요.");
					System.out.print(">");
					answer = sc.nextLine();
					switch(answer) {
					
					case "1":
						System.out.print("아이디 : ");
						String id = sc.nextLine();
						if(id.isBlank()) {
							System.out.println("공백은 무효 처리 됩니다.");
						}
						else if(id.equals(member.getLoginId())) {
							System.out.println("현재 아이디와 같습니다.");
						}
						else if (memberDAO.checkId(id)) {
							System.out.println("이미 존재하는 아이디입니다.");
						}
						else {
							member.setLoginId(id);	
						}
						break;
					case "2":
						System.out.print("비밀번호 : ");
						String pw = sc.nextLine();
						if(pw.isBlank()) {
							System.out.println("공백은 무효 처리 됩니다.");
						}else {
							member.setPassword(pw);	
						}
						break;
					case "3":
						System.out.print("이름 : ");
						String name = sc.nextLine();
						if(name.isBlank()) {
							System.out.println("공백은 무효 처리 됩니다.");
						}else {
							member.setMemberName(name);	
						}
						break;
					case "4":
						System.out.print("주소 : ");
						String address = sc.nextLine();
						if(address.isBlank()) {
							System.out.println("공백은 무효 처리 됩니다.");
						}else {
							member.setMemberAddress(address);	
						}
						break;
					case "5":
						System.out.print("전화번호 : ");
						String phone = sc.nextLine();
						if(phone.isBlank()) {
							System.out.println("공백은 무효 처리 됩니다.");
						}else {
							member.setPhone(phone);	
						}
						break;
					case "6":
						result  = memberDAO.memberInfoUpdate(member, currentId);
						if(result == 1) {
							System.out.println("저장되었습니다.");
							
						} else {
							System.out.println("저장되지 않았습니다");
						}
						run = false;
						System.out.println("메뉴로 돌아갑니다.");
						break;
						
					case "7":
						member.setLoginId(currentInfo.getLoginId());
						member.setPassword(currentInfo.getPassword());
						member.setMemberName(currentInfo.getMemberName());
						member.setMemberAddress(currentInfo.getMemberAddress());
						member.setPhone(currentInfo.getPhone());
						run = false;
						System.out.println("메뉴로 돌아갑니다.");
						break;
					
					default :;
					}
					
				}
				break;
			case 6:
				System.out.println("정말로 탈퇴하실 건가요? 그렇다면 \"탈퇴하겠습니다\"라고 입력하세요.");
				System.out.println("(만약 철회 하신다면 \"탈퇴하겠습니다\" 외 아무 내용을 입력하세요.)");
				System.out.print(">");
				
				answer = sc.nextLine();
				
				if(answer.equals("탈퇴하겠습니다")) {
					if(productDAO.deleteAllBuyingList(member) > 0) {
						System.out.println("장바구니 초기화");
					}
					productDAO.deleteAllProduct(member);
					String id = member.getLoginId();
					result = memberDAO.delete(id);
					if(result == 1) {
						menuRun = false;
						System.out.println("회원 탈퇴가 완료되었습니다.");
						break;
					} else {
						System.out.println("연결에 문제가 생겨 실패했습니다.");
					}
					
				} else {
					System.out.println("탈퇴를 철회 하셨습니다.");
				}
				break;
				
				
			case 7:
				menuRun = false;
				if(productDAO.deleteAllBuyingList(member) > 0) {
					System.out.println("장바구니 초기화");
				}
				System.out.println("로그아웃");
				break;
			}
		}
	}//end of login menu
	
	static void shopProduct(ShopMember member) {
		boolean menuRun = true;
		ShopProduct product = null;
		while(menuRun) {
			
			System.out.println("=========================================================================================================================================");
			System.out.println("상품등록번호\t상품명\t\t\t\t\t\t카테고리\t\t\t가격\t\t\t할인율\t\t남은 재고\t판매자");
			List<ShopProduct> list = productDAO.productList();
			for(ShopProduct ele : list) {
				System.out.println(ele);
			}
			System.out.println("=========================================================================================================================================");
			System.out.println("1.상품명으로 검색 2.카테고리로 검색 3.판매자명으로 검색 4.장바구니담기 5.돌아가기");
			System.out.println("==================================================================");
			System.out.print(">>");
			String answer = sc.nextLine();
			switch(answer) {
			case "1":
				System.out.println("상품명을 입력해주세요.");
				System.out.print(">>");
				String productName = sc.nextLine();
				if(productName.isBlank()) {
					System.out.println("상품 명을 입력해서 검색해주세요");
					break;
				}
				product = new ShopProduct();
				product.setProductName(productName);
				
				System.out.println("=========================================================================================================================================");
				List<ShopProduct> seachByProduct = productDAO.searchByProduct(product);
				if(seachByProduct.isEmpty()) {
					System.out.println("검색 결과 없음");
					break;
				}
				System.out.println("상품등록번호\t상품명\t\t\t\t\t\t카테고리\t\t\t가격\t\t\t할인율\t\t남은 재고\t판매자");
				for(ShopProduct ele : seachByProduct) {
					System.out.println(ele);
				}
				System.out.println("=========================================================================================================================================");
				if(member!=null && member.getResponsibility().equals("User")) areYouAddBuyingList(member);
				break;
				
			case "2":
				System.out.println("[1] 패션의류/잡화\t\t [2] 뷰티\t\t\t [3] 출산/유아동");
				System.out.println("[4] 식품\t\t\t [5] 주방용품\t\t [6] 생활용품");
				System.out.println("[7] 홈인테리어\t\t [8] 가전디지털\t\t [9] 스포츠/레저");
				System.out.println("[10] 자동차용품\t\t [11] 도서/음반/DVD\t [12] 완구/취미");
				System.out.println("[13] 문구/오피스\t\t [14] 반려동물용품\t\t [15] 헬스/건강식품");
				System.out.println("카테고리 명을 입력해주세요.");
				System.out.print(">>");
				String categoryName = sc.nextLine();
				if(categoryName.isBlank()) {
					System.out.println("카테고리 이름을 입력해서 검색해주세요");
					break;
				}
				product = new ShopProduct();
				product.setCategoryName(categoryName);
				List<ShopProduct> searchByCategory = productDAO.searchByCategory(product);
				System.out.println("=========================================================================================================================================");
				if(searchByCategory.isEmpty()) {
					System.out.println("검색 결과 없음");
					break;
				}
				System.out.println("상품등록번호\t상품명\t\t\t\t\t\t카테고리\t\t\t가격\t\t\t할인율\t\t남은 재고\t판매자");
				System.out.println("=========================================================================================================================================");
				for(ShopProduct ele : searchByCategory) {
					System.out.println(ele);
				}
				System.out.println("=========================================================================================================================================");
				if(member!=null && member.getResponsibility().equals("User")) areYouAddBuyingList(member);
				break;
			case "3":
				System.out.println("판매자의 이름을 입력해주세요.");
				System.out.print(">>");
				String sellerName = sc.nextLine();
				if(sellerName.isBlank()) {
					System.out.println("판매자 이름을 입력해서 검색해주세요");
					break;
				}
				ShopMember seller = new ShopMember();
				seller.setMemberName(sellerName);
				List<ShopProduct> searchBySeller = productDAO.searchBySeller(seller);
				System.out.println("=========================================================================================================================================");
				if(searchBySeller.isEmpty()) {
					System.out.println("검색 결과 없음");
					break;
				}
				System.out.println("상품등록번호\t상품명\t\t\t\t\t\t카테고리\t\t\t가격\t\t\t할인율\t\t남은 재고\t판매자");
				
				for(ShopProduct ele : searchBySeller) {
					System.out.println(ele);
				}
				System.out.println("=========================================================================================================================================");
				if(member!=null && member.getResponsibility().equals("User")) areYouAddBuyingList(member);
				break;
			case "4":
				if(member == null) {
					System.out.println("구매는 회원만 가능합니다.");
					break;
				};
				if(member.getResponsibility().equals("Admin")) {
					System.out.println("관리자 계정은 구매가 비활성화됩니다.");
					break;
				}
				addBuyingList(member);
				break;
			case "5":
				menuRun = false;
				System.out.println("메뉴로 돌아갑니다.");
				break;
			}

		}
	}//end of shopProduct
	
	static void productsOfSeller(ShopMember member) {
		boolean menuRun = true;
		ShopProduct product = null;
		String answer = "";
		String pName ="";
		int category;
		double price;
		double discount;
		int quantity;
		
		while(menuRun) {
			System.out.println("======================================================================================================================");
			System.out.println("상품등록번호\t상품명\t\t\t\t\t\t카테고리\t\t\t가격\t\t\t할인율\t\t남은 재고\t판매자");
			List<ShopProduct> sellList = productDAO.productsOfSeller(member);
			for(ShopProduct sell : sellList) {
				System.out.println(sell);
			}
			System.out.println("======================================================================================================================");
			System.out.println("1.판매 상품 등록  2.판매 상품 정보 수정 3.판매 상품 삭제 4.메뉴로 돌아 가기");
			System.out.println("========================================================================");
			System.out.print(">>");
			String selNum = sc.nextLine();
			
			switch(selNum) {
			case "1":
				while(true) {
					System.out.println("[상품명 입력]");
					System.out.println("등록을 취소하고 싶으면 [취소]를 입력");
					System.out.print(">>");
					answer = sc.nextLine();
					if(answer.isEmpty()) {
						System.out.println("상품명을 입력하세요.");
					}else break;
				}
				if(answer.equals("취소")) {
					break;
				}
				pName = answer;
				
				Category:while(true) {
					System.out.println("카테고리 선택[1~15]");
					System.out.println("[1] 패션의류/잡화\t\t [2] 뷰티\t\t\t [3] 출산/유아동");
					System.out.println("[4] 식품\t\t\t [5] 주방용품\t\t [6] 생활용품");
					System.out.println("[7] 홈인테리어\t\t [8] 가전디지털\t\t [9] 스포츠/레저");
					System.out.println("[10] 자동차용품\t\t [11] 도서/음반/DVD\t [12] 완구/취미");
					System.out.println("[13] 문구/오피스\t\t [14] 반려동물용품\t\t [15] 헬스/건강식품");
					System.out.println("등록을 취소하고 싶으면 [취소]를 입력");
					System.out.print(">>");
					answer = sc.nextLine();
					if(answer.equals("취소")) {
						break;
					}
					String[] categories = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
							"12", "13", "14", "15"};
					for(String ele : categories) {
						if(answer.equals(ele)) {
							break Category;
						}
					}
					System.out.println("카테고리 번호를 제대로 입력하세요.");
				}
				if(answer.equals("취소")) {
					break;
				}
				category= Integer.parseInt(answer);
				
				while(true) {
					System.out.println("[상품 가격 입력]");
					System.out.println("등록을 취소하고 싶으면 [취소]를 입력");
					System.out.print(">>");
					answer = sc.nextLine();
					if(answer.isEmpty()) {
						System.out.println("가격을 입력해주세요.");
					}else break;
				}
				if(answer.equals("취소")) {
					break;
				}
				price = Double.parseDouble(answer);
				
				while(true) {
					System.out.println("[상품 재고 수 입력]");
					System.out.println("등록을 취소하고 싶으면 [취소]를 입력");
					System.out.print(">>");
					answer = sc.nextLine();
					if(answer.isEmpty()) {
						System.out.println("재고 수를 입력해주세요.");
					}else break;
				}
				if(answer.equals("취소")) {
					break;
				}
				quantity = Integer.parseInt(answer);
				
				product = new ShopProduct();
				product.setProductName(pName);
				product.setCategory(category);
				product.setProductPrice(price);
				product.setProductQuantity(quantity);
				int result = productDAO.addProduct(member, product);
				if(result==1) {
					System.out.println("판매 상품을 등록했습니다.");
				}else {
					System.out.println("상품이 등록되지 않았습니다.");
				}
				break;
			case "2":
				System.out.println("수정할 상품의 등록번호를 입력하세요");
				int updateProductId = Integer.parseInt(sc.nextLine());
				
				product = new ShopProduct();
				product.setProductId(updateProductId);
				
				ShopProduct productInfo = productDAO.loadProductInfo(member, product);
				if(productInfo.getProductName() == null) {
					System.out.println("유효하지 않는 번호입니다.");
					break;
				}
				
				boolean updateRun = true;
				while(updateRun) {
					System.out.println("===================================================================");
					System.out.println("상품 등록번호 : " + productInfo.getProductId());
					System.out.println("<1>상품명 : " + productInfo.getProductName());
					System.out.println("<2>카테고리 :" + "["+productInfo.getCategory() +"]" + productInfo.getCategoryName());
					System.out.println("<3>상품 가격 : " + productInfo.getProductPrice());
					System.out.println("<4>할인율 : " + productInfo.getDiscount()*100 + "%");
					System.out.println("<5>재고 수량 : " + productInfo.getProductQuantity());
					System.out.println("===================================================================");
					System.out.println("수정할 항목을 입력하세요[1, 2, 3, 4, 5]");
					System.out.println("현재 수정중인 정보를 확정 짓고 저장하고 싶다면 [6], 저장하지 않고 돌아가고 싶으면 [7]을 입력하세요.");
					System.out.print(">");
					answer = sc.nextLine();
					
					switch(answer) {
					case "1":
						System.out.print("상품명 : ");
						pName = sc.nextLine();
						if(pName.isBlank()) {
							System.out.println("상품명을 입력해주세요.");
						}else{
							productInfo.setProductName(pName);
						}
						break;
					case "2":
						System.out.println("===================================================================");
						System.out.println("[1] 패션의류/잡화\t\t [2] 뷰티\t\t\t [3] 출산/유아동");
						System.out.println("[4] 식품\t\t\t [5] 주방용품\t\t [6] 생활용품");
						System.out.println("[7] 홈인테리어\t\t [8] 가전디지털\t\t [9] 스포츠/레저");
						System.out.println("[10] 자동차용품\t\t [11] 도서/음반/DVD\t [12] 완구/취미");
						System.out.println("[13] 문구/오피스\t\t [14] 반려동물용품\t\t [15] 헬스/건강식품");
						System.out.println("===================================================================");
						System.out.print("카테고리[1~15] : ");
						String categoryS = sc.nextLine();
						String[] categories = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
								"12", "13", "14", "15"};
						int i=1;
						for(String ele : categories) {
							if(categoryS.equals(ele)) {
								category = Integer.parseInt(categoryS);
								productInfo.setCategory(category);
								productInfo.setCategoryName();
								break;
							}
							if(i == categories.length) {
								System.out.println("카테고리 번호를 제대로 입력해주세요.");
							}
							i++;
						}
						break;
					case "3":
						System.out.print("상품 가격 : ");
						price = Double.parseDouble(sc.nextLine());
						if(price>=0) {
							productInfo.setProductPrice(price);
						}else{
							System.out.println("금액을 제대로 입력해주세요.");
						}
						break;
					case "4":
						System.out.print("할인율(%) : ");
						discount = Double.parseDouble(sc.nextLine());
						if(discount>=0 && discount<=100) {
							productInfo.setDiscount(discount / 100);
						}else{
							System.out.println("할인율을 제대로 입력해주세요.");
						};
						break;
					case "5":System.out.print("재고 수량 : ");
					quantity = Integer.parseInt(sc.nextLine());
					if(quantity>=0) {
						productInfo.setProductQuantity(quantity);
					}else{
						System.out.println("재고 수량을 제대로 입력해주세요.");
					};
					break;
					case "6":
						int updateRs = productDAO.updateProductInfo(productInfo);
						if(updateRs == 1) {
							System.out.println("수정 완료");
						}else {
							System.out.println("수정 되지 않았습니다.");
						}
					case "7":
						System.out.println("돌아가기");
						updateRun = false;
						break;
					default:;
					}
				}
				
				
				
				break;
			case "3":;
				System.out.println("[삭제할 상품의 등록번호 입력]");
				int delProductId = Integer.parseInt(sc.nextLine()); 
				product = new ShopProduct();
				product.setProductId(delProductId);
				int delResult = productDAO.deleteProduct(member, product);
				if(delResult == 1) {
					System.out.println("해당 상품을 판매 리스트에서 삭제했습니다.");
				}else {
					System.out.println("유효하지 않는 번호입니다.");
				}
				break;
			case "4": 
				menuRun = false;
				break;
			}
		}
	}//end of productsOfSeller
	
	static void areYouAddBuyingList(ShopMember member) {
		boolean run = true;
		while(run) {
			System.out.println("1. 장바구니에 담기 2. 뒤로가기");
			System.out.print(">>");
			String answer = sc.nextLine();
			switch(answer) {
			case "1":
				addBuyingList(member);
			case "2":
				run=false;
				break;
			}
			
		}
	}
	
	static void addBuyingList(ShopMember member) {
		while(true) {
			System.out.print("장바구니에 담을 상품의 번호>");
			int productId = Integer.parseInt(sc.nextLine());
			ShopProduct product = new ShopProduct();
			product.setProductId(productId);
			ShopProduct result =  productDAO.checkIdAndQuantity(product, member);
			if(result == null) {
				System.out.println("존재하지 않는 상품 번호입니다.");
				break;
			}
			int memberIdOfSeller = result.getSellerId();
			int memberIdOfBuyer = member.getMemberId();
			if(memberIdOfSeller == memberIdOfBuyer) {
				System.out.println("경고 : "+member.getMemberName()+"의 판매 물품입니다.");
				break;
			}
			System.out.print("구매 수량>");
			int buyingQuantity =  Integer.parseInt(sc.nextLine());
			int quantityOfProduct = result.getProductQuantity();
			if(buyingQuantity <= 0 || buyingQuantity > quantityOfProduct) {
				System.out.println("유효하지 않는 수량입니다.");
				break;
			}
			int NumOfAddBuyingList = productDAO.addBuyingList(memberIdOfBuyer, productId, buyingQuantity, quantityOfProduct);
			if(NumOfAddBuyingList == 1) {
				System.out.println("장바구니가 갱신되었습니다.");
			}else {
				System.out.println("장바구니에 추가하지 못했습니다.");
			}
			break;
		}
	}
	
	static void buyList(ShopMember member) {
		int result = 0;
		boolean run = true;
		while(run) {
			System.out.println("===========================================================================================================================================================================");
			System.out.println("상품등록번호\t상품명\t\t\t\t\t\t카테고리\t\t\t가격\t\t\t할인율\t\t구매 수량\t판매자\t총액");
			List<ShopProduct> list = productDAO.buyingList(member);
			for(ShopProduct ele : list) {
				System.out.println(ele);
			}
			System.out.println("===========================================================================================================================================================================");
			System.out.println("1. 전체 구매 2. 선택 구매 3. 상품 삭제 4. 상품 구매 수량 수정 5. 돌아가기");
			System.out.print(">>");
			String answer = sc.nextLine();
			switch(answer) {
			case "1":
				if(!(productDAO.buyAllProduct(member))) {
					System.out.println("구매 실패");
				};
				break;
			case "2":
				System.out.println("[장바구니에서 구매할 상품등록번호 입력]");
				System.out.print(">>");
				int proIdToBuy = Integer.parseInt(sc.nextLine());
				ShopProduct buyingProduct = new ShopProduct();
				buyingProduct.setProductId(proIdToBuy);
				if(!(productDAO.buyProduct(member, buyingProduct))) {
					System.out.println("구매 실패");
				};
				break;
			case "3":
				System.out.println("[장바구니에서 삭제할 상품등록번호 입력]");
				System.out.print(">>");
				int delProId = Integer.parseInt(sc.nextLine());
				ShopProduct deleteProduct = new ShopProduct();
				deleteProduct.setProductId(delProId);
				result = productDAO.deleteBuyingList(member, deleteProduct);
				if(result == 1) {
					System.out.println("장바구니에서 상품을 삭제 했습니다.");
				}else {
					System.out.println("삭제 실패.");
					System.out.println("등록번호가 유효한 지 확인해주세요.");
				}
				break;
				
			case "4":
				System.out.println("[수정할 상품등록번호 입력]");
				System.out.print(">>");
				int upProId = Integer.parseInt(sc.nextLine());
				System.out.println("[구매 수량 입력(최소 1부터)]");
				System.out.print(">>");
				int quantity = Integer.parseInt(sc.nextLine());
				ShopProduct updateProduct = new ShopProduct();
				updateProduct.setProductId(upProId);
				updateProduct.setProductQuantity(quantity);
				result = productDAO.updateBuyingList(member, updateProduct);
				if(result == 1) {
					System.out.println("구매 수량 수정 완료");
				}else {
					System.out.println("구매 수량 수정 실패.");
					System.out.println("등록번호와 구매수량이 유효한 지 확인해주세요.");
				}
				break;
			case "5":
				run=false;
				break;
			}
			
		}
	}

	static void transactionRecords(ShopMember member) {
		while(true) {
			if(member.getResponsibility().equals("Admin")) {
				List<TransactionRecord> list = new ArrayList<TransactionRecord>();
				list = productDAO.transactionRecords();
				System.out.println("============================================================================================"
						+ "=====================================================================================");
				System.out.println("거래번호\t\t상품번호\t상품명\t\t\t\t\t\t카테고리\t\t\t구매수량\t상품가격\t\t\t할인율\t총 가격\t\t\t거래일자"
						+ "\t\t판매자/구매자");
				for(TransactionRecord ele : list) {
					System.out.println(ele.toString());
				}
				System.out.println("============================================================================================"
						+ "=====================================================================================");
			}else if (member.getResponsibility().equals("User")) {
				List<TransactionRecord> list = new ArrayList<TransactionRecord>();
				list = productDAO.transactionRecords(member);
				System.out.println("============================================================================================"
						+ "=====================================================================================");
				System.out.println("거래번호\t\t상품번호\t상품명\t\t\t\t\t\t카테고리\t\t\t구매수량\t상품가격\t\t\t할인율\t총 가격\t\t\t거래일자"
						+ "\t\t판매자/구매자");
				for(TransactionRecord ele : list) {
					System.out.println(ele.toString());
				}
				System.out.println("============================================================================================="
						+ "=====================================================================================");
			}else {
				System.out.println("권한 없음");
			}
			break;
		}
	}
	
}//end of class
