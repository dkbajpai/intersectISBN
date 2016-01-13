package com.snapdeal.sps.intersectISBN.enums;

public enum RejectedFileHeaders {
	
	SKU("SKU"), ProductName("Product Name"), Author("Author"), Publisher("Publisher"), ISBN13("ISBN13"),
	ProductDescription("Product Description"), Binding("Binding"), Pages("Pages"), DateOfPublishing("Date of Publishing"),
	Language("Language"), ParentCategory("Parent Category"), ChildCategory1("Child Category 1"), Subcategory("Subcategory"),
	ISBN10("ISBN10"), MRP("MRP"), Inventory("Inventory"), Weight("Weight (g)"), Length("Length (cm)"),
	Height("Height (cm)"), 	Width("Width (cm)"), CourierType("COURIER TYPE"), WoodenPackaging("Wooden Packaging"),
	ShippingTimeInDays("Shipping Time in Days"), BrandId("BrandId"), BookNo("Book No."), WebPageNumber("Web page number"),
	ErrorCheck("Error Check"), VolumetricWeight("Volumetric Weight"), MerchantCut("Merchant Cut"), Margin("Margin %"),
	FixedCommission("Fixed Commission"), LogisticsCost("LOGISTICS COST"), FcCost("FC COST"), WoodenPackagingCharges("Wooden Packaging Charges"),
	PaymentCollectionCharges("Payment Collection Charges"), CustomerDiscount("Customer Discount %"), 
	ShippingMode("Shipping Mode"), RejectedReason("Rejectedd Reason");
	
	private final String header;
	
	private RejectedFileHeaders(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return header;
	}
	;
}
