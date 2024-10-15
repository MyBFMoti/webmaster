package shoppingMall;

public class TransactionRecord extends ShopProduct{
	private int transactionId;
	private int buyerId;
	private String buyerName;
	private String transactionDate;
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		String pName = String.format("%-40s", productName);
		String  cName = String.format("%-15s", categoryName);
		String  price = String.format("%-20s", Double.toString(productPrice));
		String  totalPriceS = String.format("%-20s", Double.toString(totalPrice));
		//String  sellerNameS = String.format("%-10s", sellerName);
		//String  buyerNameS = String.format("%-10s", buyerName);
		
		String result = "[" + transactionId + "]\t\t" + productId + "\t" + pName + "\t" +"["+category+"]" +
		                      cName + "\t" + productQuantity +"\t" + price + "\t" + (discount*100) + "%\t" +
		                      totalPriceS + "\t" + transactionDate + "\t" + sellerName + "/" + buyerName;
		return result;
		
	}


	
}
