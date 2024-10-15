package shoppingMall;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopProductDAO extends DAO{
	
	private static ShopProductDAO instance = new ShopProductDAO();
	private ShopProductDAO() {}
	public static ShopProductDAO getInstance() {
		return instance;
	}
	//판매 상품 추가
	public int addProduct(ShopMember member, ShopProduct product) {
		String sql = "INSERT INTO shop_product (product_id, "
				+ "                             seller_id, "
				+ "                             category_id, "
				+ "                             product_name,"
				+ "                             product_price, "
				+ "                             product_quantity"
				+ "                             ) "
				+ "   VALUES (seq_product_no.NEXTVAL, ?, ?, ?, ?, ?)";
		getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, member.getMemberId());
			psmt.setInt(2, product.getCategory());
			psmt.setString(3, product.getProductName());
			psmt.setDouble(4, product.getProductPrice());
			psmt.setInt(5, product.getProductQuantity());
			
			int row = psmt.executeUpdate();
			psmt.close();
			getClose();
			
			return row;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//판매 상품 삭제 
	public int deleteProduct(ShopMember member, ShopProduct product) {
		String sql = "DELETE "+
					 "FROM   shop_product "+
					 "WHERE  seller_id = ? "+
					 "AND    product_id = ?";
		getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, member.getMemberId());
			psmt.setInt(2, product.getProductId());
			
			int row = psmt.executeUpdate();
			psmt.close();
			getClose();
			
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	//회원 탈퇴 전 상품 전체 삭제
	public void deleteAllProduct(ShopMember member) {
		String sql = "DELETE "+
					 "FROM   shop_product "+
					 "WHERE  seller_id = ? ";
		getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, member.getMemberId());
			int rows = psmt.executeUpdate();
			psmt.close();
			if(rows > 0) {
				System.out.println("탈퇴 전 회원의 상품 리스트 삭제 처리");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		
	}
	
	
	
	//상품 전체 조회
	public List<ShopProduct> productList() {
		String sql = "SELECT   p.product_id, "
				+ "            p.category_id, "
				+ "            p.product_name, "
				+ "            p.product_price, "
				+ "            p.discount, "
				+ "            p.product_quantity, "
				+ "            m.member_name           AS seller_name, "
				+ "            c.category_name "
				+ "FROM    shop_product p "
				+ "JOIN    shop_member m           ON p.seller_id = m.member_id "
				+ "JOIN    categories c            ON p.category_id = c.category_id "
				+ "ORDER BY 1, 2, 3, 4, 5, 6, 7";
		getConn();
		List<ShopProduct> result = new ArrayList<>();
		try {
			psmt =conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ShopProduct product = new ShopProduct();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getInt("category_id"));
				product.setCategoryName(rs.getString("category_name"));
				product.setProductName(rs.getNString("product_name"));
				product.setProductPrice(rs.getDouble("product_price"));
				product.setDiscount(rs.getDouble("discount"));
				product.setProductQuantity(rs.getInt("product_quantity"));
				product.setSellerName(rs.getString("seller_name"));
				result.add(product);
			};
			psmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
		
	}
	
	
	//상품 검색(상품명)
	public List<ShopProduct> searchByProduct(ShopProduct product) {
		String sql = "SELECT   p.product_id, "
				+ "            p.category_id, "
				+ "            p.product_name, "
				+ "            p.product_price, "
				+ "            p.discount, "
				+ "            p.product_quantity, "
				+ "            m.member_name           AS seller_name, "
				+ "            c.category_name "
				+ "FROM    shop_product p "
				+ "JOIN    shop_member m           ON p.seller_id = m.member_id "
				+ "JOIN    categories c            ON p.category_id = c.category_id "
				+ "WHERE   product_name like ?"
				+ "ORDER BY 1, 2, 3, 4, 5, 6, 7";
		getConn();
		List<ShopProduct> result = new ArrayList<>();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setString(1, "%" + product.getProductName() +"%");
			rs = psmt.executeQuery();
			while(rs.next()) {
				product = new ShopProduct();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getInt("category_id"));
				product.setCategoryName(rs.getString("category_name"));
				product.setProductName(rs.getNString("product_name"));
				product.setProductPrice(rs.getDouble("product_price"));
				product.setDiscount(rs.getDouble("discount"));
				product.setProductQuantity(rs.getInt("product_quantity"));
				product.setSellerName(rs.getString("seller_name"));
				result.add(product);
			};
			psmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
		
	}
	//상품 검색(카테고리)
	public List<ShopProduct> searchByCategory(ShopProduct product) {
		String sql = "SELECT   p.product_id, "
				+ "            p.category_id, "
				+ "            p.product_name, "
				+ "            p.product_price, "
				+ "            p.discount, "
				+ "            p.product_quantity, "
				+ "            m.member_name           AS seller_name, "
				+ "            c.category_name "
				+ "FROM    shop_product p "
				+ "JOIN    shop_member m           ON p.seller_id = m.member_id "
				+ "JOIN    categories c            ON p.category_id = c.category_id "
				+ "WHERE   c.category_name like ?"
				+ "ORDER BY 1, 2, 3, 4, 5, 6, 7";
		getConn();
		List<ShopProduct> result = new ArrayList<>();
		
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setString(1, "%"+product.getCategoryName()+"%");
			rs = psmt.executeQuery();
			while(rs.next()) {
				product = new ShopProduct();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getInt("category_id"));
				product.setCategoryName(rs.getString("category_name"));
				product.setProductName(rs.getNString("product_name"));
				product.setProductPrice(rs.getDouble("product_price"));
				product.setDiscount(rs.getDouble("discount"));
				product.setProductQuantity(rs.getInt("product_quantity"));
				product.setSellerName(rs.getString("seller_name"));
				result.add(product);
			};
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
	}
	//상품 검색(판매자명)
	public List<ShopProduct> searchBySeller(ShopMember member) {
		String sql = "SELECT   p.product_id, "
				+ "            p.category_id, "
				+ "            p.product_name, "
				+ "            p.product_price, "
				+ "            p.discount, "
				+ "            p.product_quantity, "
				+ "            m.member_name           AS seller_name, "
				+ "            c.category_name "
				+ "FROM    shop_product p "
				+ "JOIN    shop_member m           ON p.seller_id = m.member_id "
				+ "JOIN    categories c            ON p.category_id = c.category_id "
				+ "WHERE   m.member_name LIKE ? "
				+ "ORDER BY 1, 2, 3, 4, 5, 6, 7 ";
		getConn();
		List<ShopProduct> result = new ArrayList<>();
		
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setString(1, "%"+member.getMemberName()+"%");
			rs = psmt.executeQuery();
			while(rs.next()) {
				ShopProduct product = new ShopProduct();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getInt("category_id"));
				product.setCategoryName(rs.getString("category_name"));
				product.setProductName(rs.getNString("product_name"));
				product.setProductPrice(rs.getDouble("product_price"));
				product.setDiscount(rs.getDouble("discount"));
				product.setProductQuantity(rs.getInt("product_quantity"));
				product.setSellerName(rs.getString("seller_name"));
				result.add(product);
			};
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
	}
	//판매자가 등록한 상품 전체 조회
	public List<ShopProduct> productsOfSeller(ShopMember member) {
			String sql = "SELECT   p.product_id, "
					+ "            p.category_id, "
					+ "            p.product_name, "
					+ "            p.product_price, "
					+ "            p.discount, "
					+ "            p.product_quantity, "
					+ "            m.member_name           AS seller_name, "
					+ "            c.category_name "
					+ "FROM    shop_product p "
					+ "JOIN    shop_member m           ON p.seller_id = m.member_id "
					+ "JOIN    categories c            ON p.category_id = c.category_id "
					+ "WHERE   seller_id = ? "
					+ "ORDER BY 1, 2, 3, 4, 5, 6, 7 ";
			getConn();
			List<ShopProduct> result = new ArrayList<>();
			try {
				psmt =conn.prepareStatement(sql);
				psmt.setInt(1, member.getMemberId());
				rs = psmt.executeQuery();
				while(rs.next()) {
					ShopProduct product = new ShopProduct();
					product.setProductId(rs.getInt("product_id"));
					product.setCategory(rs.getInt("category_id"));
					product.setCategoryName(rs.getString("category_name"));
					product.setProductName(rs.getNString("product_name"));
					product.setProductPrice(rs.getDouble("product_price"));
					product.setDiscount(rs.getDouble("discount"));
					product.setProductQuantity(rs.getInt("product_quantity"));
					product.setSellerName(rs.getString("seller_name"));
					result.add(product);
				};
				psmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getClose();
			return result;
			
	}
	//판매자가 수정할 상품 정보 불러오기
	public ShopProduct loadProductInfo(ShopMember member, ShopProduct product) {
				String sql = "SELECT       p.product_id, "
							+ "            p.category_id, "
							+ "            p.product_name, "
							+ "            p.product_price, "
							+ "            p.discount, "
							+ "            p.product_quantity, "
							+ "            m.member_name           AS seller_name, "
							+ "            c.category_name "
							+ "FROM        shop_product p "
							+ "JOIN        shop_member m           ON p.seller_id = m.member_id "
							+ "JOIN        categories c            ON p.category_id = c.category_id "
							+ "WHERE       seller_id = ? "
							+ "AND         p.product_id = ? "
							+ "ORDER BY 1, 2, 3, 4, 5, 6, 7 ";
					getConn();
					
					try {
						psmt =conn.prepareStatement(sql);
						psmt.setInt(1, member.getMemberId());
						psmt.setInt(2, product.getProductId());
						rs = psmt.executeQuery();
						if(rs.next()) {
							product = new ShopProduct();
							product.setProductId(rs.getInt("product_id"));
							product.setCategory(rs.getInt("category_id"));
							product.setCategoryName(rs.getString("category_name"));
							product.setProductName(rs.getNString("product_name"));
							product.setProductPrice(rs.getDouble("product_price"));
							product.setDiscount(rs.getDouble("discount"));
							product.setProductQuantity(rs.getInt("product_quantity"));
							product.setSellerName(rs.getString("seller_name"));
						};
						psmt.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					getClose();
					return product;
					
	}
	//판매자가 상품 정보 수정하기
	public int updateProductInfo(ShopProduct product) {
			String sql = "UPDATE shop_product "+
						 "SET    category_id = ?, "+
						 "       product_name = ?, "+
						 "       product_price = ?, "+
						 "       discount = ?, "+
						 "       product_quantity = ? "+
						 "WHERE  product_id = ?";
			getConn();
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, product.getCategory());
				psmt.setString(2, product.getProductName());
				psmt.setDouble(3, product.getProductPrice());
				psmt.setDouble(4, product.getDiscount());
				psmt.setInt(5, product.getProductQuantity());
				psmt.setInt(6, product.getProductId());
				int row = psmt.executeUpdate();
				psmt.close();
				getClose();
				
				return row;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getClose();
			return 0;
	}
		
		
	//장바구니 관련
	//장바구니 담기(등록번호체크-> 자기 상품은 제외, 장바구니에 이미 있는 상품인지 확인하고 있으면 수량만 더하고 없으면 추가. 구매 수량은 재고 수량을 넘으면 안됨)
	public ShopProduct checkIdAndQuantity(ShopProduct product, ShopMember member) {
		String sql = "SELECT   seller_id, "+
					 "         product_quantity "+
					 "FROM     shop_product "+
					 "WHERE    product_id = ? ";
		getConn();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, product.getProductId());
			rs = psmt.executeQuery();
			ShopProduct result = null;
			while(rs.next()) {
				result = new ShopProduct();
				result.setSellerId(rs.getInt("seller_id"));
				result.setProductQuantity(rs.getInt("product_quantity"));
			}
			psmt.close();
			getClose();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return null;
	}
	
	//장바구니에 추가하기
	public int addBuyingList(int buyerId, int productId, int quantity, int maxQuantity) {
		String sql1 = "SELECT * "+
					  "FROM buying_list "+
					  "WHERE product_id=? "+
					  "AND   buyer_id=?";
		
				
		String sql2 = "INSERT INTO buying_list("
				+ "   buying_list_id, "
				+ "   buyer_id, "
				+ "   product_id, "
				+ "   quantity )"
				+ "   VALUES "
				+ "   (seq_buying_list_no.NEXTVAL, "
				+ "   ?, "
				+ "   ?, "
				+ "   ?) ";
		
		String sql3 = "SELECT quantity "+
					  "FROM   buying_list "+
					  "WHERE  product_id=?";
		
		String sql4 = "UPDATE buying_list"
				+ "    SET    quantity = quantity + ?"
				+ "    WHERE  buyer_id = ?"
				+ "    AND    product_id = ? ";
		getConn();
		try {
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, productId);
			psmt.setInt(2, buyerId);
			int checkContain = psmt.executeUpdate();
			
			if(checkContain == 0) {
				psmt =conn.prepareStatement(sql2);
				psmt.setInt(1, buyerId);
				psmt.setInt(2, productId);
				psmt.setInt(3, quantity);
				int row = psmt.executeUpdate();
				
				psmt.close();
				getClose();
				return row;
			}else {
				psmt = conn.prepareStatement(sql3);
				psmt.setInt(1, productId);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					int buyingListQuantity = rs.getInt("quantity");
					if(maxQuantity < (buyingListQuantity + quantity)) {
						psmt.close();
						getClose();
						System.out.println("유효하지 않는 수량입니다.");
						return 0;
					}else {
						psmt =conn.prepareStatement(sql4);
						psmt.setInt(1, quantity);
						psmt.setInt(2, buyerId);
						psmt.setInt(3, productId);
						int row = psmt.executeUpdate();
						
						psmt.close();
						getClose();
						System.out.println("장바구니에 이미 존재하므로 수량을 합했습니다.");
						return row;
					}
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return 0;
	}
	
	//장바구니 내역 불러오기
	public List<ShopProduct> buyingList(ShopMember member){
		String sql ="SELECT bl.buying_list_id, "
				+ "         bl.buyer_id, "
				+ "         bl.product_id, "
				+ "         sp.category_id, "
				+ "         c.category_name, "
				+ "         m.member_name                  AS seller_name,"
				+ "         sp.product_name, "
				+ "         sp.discount, "
				+ "         bl.quantity, "
				+ "         sp.product_price,  "
				+ "         sp.product_price * bl.quantity * (1 -sp.discount) AS total_price "
				+ "  FROM   buying_list bl "
				+ "  JOIN   shop_product sp "
				+ "    ON   bl.product_id = sp.product_id "
				+ "  JOIN   categories c "
				+ "    ON   sp.category_id = c.category_id "
				+ "  JOIN   shop_member m "
				+ "    ON   sp.seller_id = m.member_id "
				+ "  WHERE  buyer_id =? "
				+ "  ORDER BY "
				+ "           product_id, "
				+ "           category_id, "
				+ "           total_price";
		getConn();
		List<ShopProduct> result = new ArrayList<>();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, member.getMemberId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				ShopProduct product = new ShopProduct();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getInt("category_id"));
				product.setCategoryName(rs.getString("category_name"));
				product.setProductName(rs.getNString("product_name"));
				product.setProductPrice(rs.getDouble("product_price"));
				product.setTotalPrice(rs.getDouble("total_price"));
				product.setDiscount(rs.getDouble("discount"));
				product.setProductQuantity(rs.getInt("quantity"));
				product.setSellerName(rs.getString("seller_name"));
				result.add(product);
			}
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
	}
	//장바구니 내역 상품 수정
	public int updateBuyingList(ShopMember member, ShopProduct product) {
		String sql = "UPDATE buying_list "
				+ "   SET    quantity=? "
				+ "   WHERE  buyer_id=? "
				+ "     AND  product_id=? "
				+ "     AND  ? > 0"
				+ "     AND  ? <= (SELECT   product_quantity "
				+ "                FROM     shop_product "
				+ "                WHERE    product_id=? )";
		getConn();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, product.getProductQuantity());
			psmt.setInt(2, member.getMemberId());
			psmt.setInt(3, product.getProductId());
			psmt.setInt(4, product.getProductQuantity());
			psmt.setInt(5, product.getProductQuantity());
			psmt.setInt(6, member.getMemberId());
			int row = psmt.executeUpdate();
			psmt.close();
			getClose();
			
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return 0;
	}
	//장바구니 내역 상품 삭제
	public int deleteBuyingList(ShopMember member, ShopProduct product) {
		String sql = "DELETE "
				+ "   FROM   buying_list "
				+ "   WHERE  buyer_id = ? "
				+ "    AND   product_id = ? ";
		getConn();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, member.getMemberId());
			psmt.setInt(2, product.getProductId());
			int row = psmt.executeUpdate();
			psmt.close();
			getClose();
			
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//장바구니 내역 전체 구매
	public boolean buyAllProduct(ShopMember member){
		//거래기록 추가
		String sql1 = "INSERT INTO   transaction_records("
				+ "                 transaction_id, "
				+ "                 buyer_id, "
				+ "                 buyer_name, "
				+ "                 product_id, "
				+ "                 product_name, "
				+ "                 category_id, "
				+ "                 seller_id, "
				+ "                 seller_name, "
				+ "                 quantity, "
				+ "                 product_price, "
				+ "                 discount, "
				+ "                 transaction_price, "
				+ "                 transaction_date)"
				+ "(  SELECT seq_transaction_no.NEXTVAL, "
				+ "          bl.buyer_id, "
				+ "          bm.member_name, "
				+ "          bl.product_id, "
				+ "          sp.product_name, "
				+ "          sp.category_id, "
				+ "          sp.seller_id, "
				+ "          sm.member_name, "
				+ "          bl.quantity, "
				+ "          sp.product_price, "
				+ "          sp.discount, "
				+ "          (sp.product_price *(1 - sp.discount) * bl.quantity), "
				+ "          sysdate "
				+ "   FROM   buying_list bl "
				+ "   JOIN   shop_product sp "
				+ "     ON   bl.product_id = sp.product_id "
				+ "   JOIN   shop_member bm "
				+ "     ON   bl.buyer_id = bm.member_id"
				+ "   JOIN   shop_member sm "
				+ "     ON   sp.seller_id = sm.member_id "
				+ "   WHERE  bl.buyer_id = ?"
				+ ")";
		
		//재고에서 거래한 구매수량 만큼 차감
		String sql2 = "UPDATE shop_product sp"
				+ "    SET    sp.product_quantity = "
				+ "                 sp.product_quantity - (SELECT bl.quantity "
				+ "                                        FROM   buying_list bl "
				+ "                                        WHERE  bl.product_id = sp.product_id "
				+ "                                          AND  bl.buyer_id = ? "
				+ "                                       ) "
				+ "   WHERE   sp.product_id IN ( SELECT bl.product_id "
		        + "                              FROM   buying_list bl  "
		        + "                              WHERE  bl.buyer_id = ? "
		        + "                            )";
		//제거 전에 구매 총액 알려주기
		String sql3 ="SELECT sp.product_price *(1 - sp.discount) * bl.quantity AS total_price"
				+ "   FROM   buying_list bl "
				+ "   JOIN   shop_product sp"
				+ "     ON   bl.product_id = sp.product_id "
				+ "   WHERE  bl.buyer_id = ?";
		//구매리스트에서 제거
		String sql4 = "DELETE "
				+ "    FROM buying_list "
				+ "    WHERE buyer_id = ? ";
		
		getConn();
		try {
			psmt =conn.prepareStatement(sql1);
			psmt.setInt(1, member.getMemberId());
			int addRecords = psmt.executeUpdate();
			if(addRecords != 0) {
				psmt = conn.prepareStatement(sql2);
				psmt.setInt(1, member.getMemberId());
				psmt.setInt(2, member.getMemberId());
				int purchases  = psmt.executeUpdate();
				if(purchases != 0) {
					psmt =conn.prepareStatement(sql3);
					psmt.setInt(1, member.getMemberId());
					rs = psmt.executeQuery();
					double totalPrice = 0;
					while(rs.next()) {
						totalPrice += rs.getDouble("total_price");
					}
					psmt =conn.prepareStatement(sql4);
					psmt.setInt(1, member.getMemberId());
					int checkPurchase = psmt.executeUpdate();
					if(checkPurchase != 0) {
						psmt.close();
						System.out.println("구매가 완료 되었습니다.");
						System.out.println("총 금액:" + totalPrice + "원");
						return true;
						
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getClose();
		return false;
	}
	//장바구니 내역 선택 구매
	public boolean buyProduct(ShopMember member, ShopProduct product) {
		//거래기록 추가
				String sql1 = "INSERT INTO   transaction_records("
						+ "                 transaction_id, "
						+ "                 buyer_id, "
						+ "                 buyer_name, "
						+ "                 product_id, "
						+ "                 product_name, "
						+ "                 category_id, "
						+ "                 seller_id, "
						+ "                 seller_name, "
						+ "                 quantity, "
						+ "                 product_price, "
						+ "                 discount, "
						+ "                 transaction_price, "
						+ "                 transaction_date)"
						+ "(  SELECT seq_transaction_no.NEXTVAL, "
						+ "          bl.buyer_id, "
						+ "          bm.member_name, "
						+ "          bl.product_id, "
						+ "          sp.product_name, "
						+ "          sp.category_id, "
						+ "          sp.seller_id, "
						+ "          sm.member_name, "
						+ "          bl.quantity, "
						+ "          sp.product_price, "
						+ "          sp.discount, "
						+ "          (sp.product_price *(1 - sp.discount) * bl.quantity), "
						+ "          sysdate "
						+ "   FROM   buying_list bl "
						+ "   JOIN   shop_product sp "
						+ "     ON   bl.product_id = sp.product_id "
						+ "   JOIN   shop_member bm "
						+ "     ON   bl.buyer_id = bm.member_id"
						+ "   JOIN   shop_member sm "
						+ "     ON   sp.seller_id = sm.member_id "
						+ "   WHERE  bl.buyer_id = ? "
						+ "     AND  bl.product_id = ? "
						+ ")";
				
				//재고에서 거래한 구매수량 만큼 차감
				String sql2 = "UPDATE shop_product sp"
						+ "    SET    sp.product_quantity = "
						+ "                 sp.product_quantity - (SELECT bl.quantity "
						+ "                                        FROM   buying_list bl "
						+ "                                        WHERE  bl.product_id = sp.product_id "
						+ "                                          AND  bl.buyer_id = ? "
						+ "                                       ) "
						+ "   WHERE   sp.product_id IN ( SELECT bl.product_id "
				        + "                              FROM   buying_list bl  "
				        + "                              WHERE  bl.buyer_id = ? "
				        + "                                AND  bl.product_id  = ?"
				        + "                            )";
				//제거 전에 구매 총액 알려주기
				String sql3 ="SELECT sp.product_price *(1 - sp.discount) * bl.quantity AS total_price"
						+ "   FROM   buying_list bl "
						+ "   JOIN   shop_product sp"
						+ "     ON   bl.product_id = sp.product_id "
						+ "   WHERE  bl.buyer_id = ? "
						+ "     AND  bl.product_id = ?";
				//구매리스트에서 제거
				String sql4 = "DELETE "
						+ "    FROM buying_list "
						+ "    WHERE buyer_id = ? "
						+ "    AND   product_id = ?";
				
				getConn();
				try {
					psmt =conn.prepareStatement(sql1);
					psmt.setInt(1, member.getMemberId());
					psmt.setInt(2, product.getProductId());
					int addRecords = psmt.executeUpdate();
					if(addRecords != 0) {
						psmt = conn.prepareStatement(sql2);
						psmt.setInt(1, member.getMemberId());
						psmt.setInt(2, member.getMemberId());
						psmt.setInt(3, product.getProductId());
						int purchases  = psmt.executeUpdate();
						if(purchases != 0) {
							psmt =conn.prepareStatement(sql3);
							psmt.setInt(1, member.getMemberId());
							psmt.setInt(2, product.getProductId());
							rs = psmt.executeQuery();
							double totalPrice = 0;
							while(rs.next()) {
								totalPrice += rs.getDouble("total_price");
							}
							psmt =conn.prepareStatement(sql4);
							psmt.setInt(1, member.getMemberId());
							psmt.setInt(2, product.getProductId());
							int checkPurchase = psmt.executeUpdate();
							if(checkPurchase != 0) {
								psmt.close();
								System.out.println("구매가 완료 되었습니다.");
								System.out.println("총 금액:" + totalPrice + "원");
								return true;
								
							}
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				getClose();
				return false;
	}
	//(로그아웃할때) 장바구니 전체 삭제
	public int deleteAllBuyingList(ShopMember member) {
		String sql = "DELETE "+
					 "FROM   buying_list "
				   + "WHERE  buyer_id=?";
		getConn();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, member.getMemberId());
			int row = psmt.executeUpdate();
			psmt.close();
			getClose();
			
			return row;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	//거래기록 관련
	//구매자 자신의 기록 보기
	public List<TransactionRecord> transactionRecords(ShopMember member){
		String sql = "SELECT tr.transaction_id,"
				+ "          tr.buyer_id, "
				+ "          tr.buyer_name, "
				+ "          tr.product_id, "
				+ "          tr.product_name, "
				+ "          tr.category_id, "
				+ "          c.category_name, "
				+ "          tr.seller_id, "
				+ "          tr.seller_name, "
				+ "          tr.quantity, "
				+ "          tr.product_price, "
				+ "          tr.discount, "
				+ "          tr.transaction_price, "
				+ "          TO_CHAR(transaction_date, 'YYYY-MM-DD') AS transaction_date"
				+ "  FROM    transaction_records tr "
				+ "  JOIN    categories c"
				+ "    ON    tr.category_id = c.category_id "
				+ "  WHERE   buyer_id=? "
				+ "    OR    seller_id=?"
				+ "  ORDER BY 1";
		getConn();
		List<TransactionRecord> result = new ArrayList<>();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, member.getMemberId());
			psmt.setInt(2, member.getMemberId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				TransactionRecord record = new TransactionRecord();
				record.setTransactionId(rs.getInt("transaction_id"));
				record.setBuyerId(rs.getInt("buyer_id"));
				record.setProductId(rs.getInt("product_id"));
				record.setProductName(rs.getString("product_name"));
				record.setCategory(rs.getInt("category_id"));
				record.setCategoryName(rs.getString("category_name"));
				record.setSellerId(rs.getInt("seller_id"));
				record.setProductQuantity(rs.getInt("quantity"));
				record.setProductPrice(rs.getDouble("product_price"));
				record.setDiscount(rs.getDouble("discount"));
				record.setTotalPrice(rs.getDouble("transaction_price"));
				record.setTransactionDate(rs.getString("transaction_date"));
				record.setSellerName(rs.getString("seller_name"));
				record.setBuyerName(rs.getString("buyer_name"));
				result.add(record);
			}
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
	}
	//관리자는 구매기록을 전체 조회 가능
	public List<TransactionRecord> transactionRecords(){
		String sql = "SELECT tr.transaction_id,"
				+ "          tr.buyer_id, "
				+ "          tr.buyer_name, "
				+ "          tr.product_id, "
				+ "          tr.product_name, "
				+ "          tr.category_id, "
				+ "          c.category_name, "
				+ "          tr.seller_id, "
				+ "          tr.seller_name, "
				+ "          tr.quantity, "
				+ "          tr.product_price, "
				+ "          tr.discount, "
				+ "          tr.transaction_price, "
				+ "          TO_CHAR(transaction_date, 'YYYY-MM-DD') AS transaction_date"
				+ "  FROM    transaction_records tr"
				+ "  JOIN    categories c"
				+ "    ON    tr.category_id = c.category_id "
				+ "  ORDER BY transaction_id";
		getConn();
		List<TransactionRecord> result = new ArrayList<>();
		try {
			psmt =conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				TransactionRecord record = new TransactionRecord();
				record.setTransactionId(rs.getInt("transaction_id"));
				record.setBuyerId(rs.getInt("buyer_id"));
				record.setProductId(rs.getInt("product_id"));
				record.setProductName(rs.getString("product_name"));
				record.setCategory(rs.getInt("category_id"));
				record.setCategoryName(rs.getString("category_name"));
				record.setSellerId(rs.getInt("seller_id"));
				record.setProductQuantity(rs.getInt("quantity"));
				record.setProductPrice(rs.getDouble("product_price"));
				record.setDiscount(rs.getDouble("discount"));
				record.setTotalPrice(rs.getDouble("transaction_price"));
				record.setTransactionDate(rs.getString("transaction_date"));
				record.setSellerName(rs.getString("seller_name"));
				record.setBuyerName(rs.getString("buyer_name"));
				result.add(record);
			}
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
	}
	
}

