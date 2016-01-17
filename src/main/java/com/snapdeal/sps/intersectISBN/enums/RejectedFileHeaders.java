package com.snapdeal.sps.intersectISBN.enums;

public enum RejectedFileHeaders {
	
	
	SKU("SKU"), ProductName("Product Name"), Author("Author"), Publisher("Publisher"), ISBN13("ISBN13"),
	ProductDescription("Product Description"), Binding("Binding"), Pages("Pages"), DateOfPublishing("Date of Publishing"),
	Language("Language"), ParentCategory("Parent Category"), ChildCategory1("Child Category 1"), Subcategory("Subcategory"),
	ISBN10("ISBN10"), MRP("MRP"),SellingPrice("SELLING PRICE"), Inventory("Inventory"), Weight("Weight (g)"), Length("Length (cm)"),
	Height("Height (cm)"), 	Width("Width (cm)"),FulfillmentMode("FULFILLMENT MODE"), CourierType("COURIER TYPE"), WoodenPackaging("Wooden Packaging"),
	ShippingTimeInDays("Shipping Time in Days"), BrandId("BrandId"), BookNo("Book No."), WebPageNumber("Web page number"), RejectionReason("Rejection Reason"),
	ErrorCheck("Error Check"), VolumetricWeight("Volumetric Weight"), MerchantCut("Merchant Cut"), Margin("Margin %"),
	FixedCommission("Fixed Commission"), LogisticsCost("LOGISTICS COST"), FcCost("FC COST"), WoodenPackagingCharges("Wooden Packaging Charges"),
	PaymentCollectionCharges("Payment Collection Charges"), CustomerDiscount("Customer Discount %"), 
	ShippingMode("Shipping Mode");
	
	private final String header;
	
	private RejectedFileHeaders(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return header;
	}
}
