package shoppingMall;

public class ShopProduct {
	protected int    productId;
	protected String productName;
	protected int    category;
	protected String categoryName;
	protected double productPrice;
	protected double totalPrice;
	protected double discount;
	protected int    productQuantity;
	protected int    sellerId;
	protected String sellerName;
	
	public ShopProduct() {}
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setCategoryName() {
		switch(this.category) {
			case 1:
				this.categoryName = "패션의류/잡화";
				break;
			case 2:
				this.categoryName = "뷰티";
				break;
			case 3:
				this.categoryName = "출산/유아동";
				break;
			case 4:
				this.categoryName = "식품";
				break;
			case 5:
				this.categoryName = "주방용품";
				break;
			case 6:
				this.categoryName = "생활용품";
				break;
			case 7:
				this.categoryName = "홈인테리어";
				break;
			case 8:
				this.categoryName = "가전디지털";
				break;
			case 9:
				this.categoryName = "스포츠/레저";
				break;
			case 10:
				this.categoryName = "자동차용품";
				break;
			case 11:
				this.categoryName = "도서/음반/DVD";
				break;
			case 12:
				this.categoryName = "완구/취미";
				break;
			case 13:
				this.categoryName = "문구/오피스";
				break;
			case 14:
				this.categoryName = "반려동물용품";
				break;
			case 15:
				this.categoryName = "헬스/건강식품";
				break;
			}
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		if(productPrice < 0) {
			this.productPrice = 0;
		}
		this.productPrice = productPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		if(discount < 0 || discount > 100) {
			discount = 0;
		}
		this.discount = discount;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		if(productQuantity<0) {
			this.productQuantity = 1;
		}else {
			this.productQuantity = productQuantity;
		}
	}
	public int getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellertName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	@Override
	public String toString() {
		String  pName = String.format("%-40s", productName);
		String  cName = String.format("%-15s", categoryName);
		String  price = String.format("%-20s", Double.toString(productPrice));
		String  totalPriceS = String.format("%-20s", Double.toString(totalPrice));
		
		String result = "["+productId +"]"+"\t\t" + pName + " \t"+ "["+category+"]"+cName+ "\t" + price + "\t" + discount*100 +"% " 
				+"\t\t"+ productQuantity + "\t";
		
		if(totalPrice != 0) {
			result += sellerName + "\t" + totalPriceS;
		}else {
			result += sellerName;
		}
		return result;

		
	}

}
