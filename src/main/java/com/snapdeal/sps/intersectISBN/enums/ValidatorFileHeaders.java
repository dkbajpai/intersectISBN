package com.snapdeal.sps.intersectISBN.enums;

public enum ValidatorFileHeaders {

	OFFER("Offer"), VENDOR_CODE("Vendor Code"), PRODUCT_NAME("Product Name"), SKU(
			"SKU"), HIGHLIGHTS("Highlights"), SUB_TITLE("Sub-Title"), PRODUCT_DESCRIPTION(
			"Product Description"), TECH_SPECS("Tech specs"), BRANDID("BrandId"), SIZE_CHART_ID(
			"SizeChartId"), LENGTH("Length (cm)"), HEIGHT("Height (cm)"), WIDTH(
			"Width (cm)"), SHIPPPING_WEIGHT("Shipping Weight"), SHIPPING_LENGTH(
			"Shipping Length"), SHIPPING_WIDTH("Shipping Width"), SHIPPING_HEIGHT(
			"Shipping Height"), START_DATE("Start Date (DD/MM/YYYY)"), END_DATE(
			"End Date (DD/MM/YYYY)"), BD_EMAIL("BD Email"), ATTRIBUTE_1_NAME(
			"Attribute1-Name"), ATTRIBUTE_1_VALUE("Attribute1-Value"), ATTRIBUTE_2_NAME(
			"Attribute2-Name"), ATTRIBUTE_2_VALUE("Attribute2-Value"), ATTRIBUTE_3_NAME(
			"Attribute3-Name"), ATTRIBUTE_3_VALUE("Attribute3-Value"), SELECT_CATEGORY(
			"Select Category"), PRODUCT_CATEGORY("Product Category"), FILTER_1_NAME(
			"Filter1-Name"), FILTER_1_VALUE("Filter1-Value"), FILTER_2_NAME(
			"Filter2-Name"), FILTER_2_VALUE("Filter2-Value"), FILTER_3_NAME(
			"Filter3-Name"), FILTER_3_VALUE("Filter3-Value"), FILTER_4_NAME(
			"Filter4-Name"), FILTER_4_VALUE("Filter4-Value"), FILTER_5_NAME(
			"Filter5-Name"), FILTER_5_VALUE("Filter5-Value"), FILTER_6_NAME(
			"Filter6-Name"), FILTER_6_VALUE("Filter6-Value"), FILTER_7_NAME(
			"Filter7-Name"), FILTER_7_VALUE("Filter7-Value"), FILTER_8_NAME(
			"Filter8-Name"), FILTER_8_VALUE("Filter8-Value"), FILTER_9_NAME(
			"Filter9-Name"), FILTER_9_VALUE("Filter9-Value"), FILTER_10_NAME(
			"Filter10-Name"), FILTER_10_VALUE("Filter10-Value"), IMAGES1(
			"Images1"), IMAGES2("Images2"), IMAGES3("Images3"), IMAGES4(
			"Images4"), IMAGES5("Images5"), IMAGES6("Images6"), IMAGES7(
			"Images7"), IMAGES8("Images8"), IMAGES9("Images9"), IMAGES10(
			"Images10"), IMAGES11("Images11"), IMAGES12("Images12"), INVENTORY(
			"Inventory"), PROCUREMENT_SLA("Procurement SLA"), WAREHOUSE_PROCESSING_SLA(
			"Warehouse Processing SLA"), MRP("MRP"), SELLING_PRICE(
			"Selling Price"), VENDOR_PRICE("Vendor Price"), SERVICE_TAX(
			"Service Tax"), SD_COMMISSION("Sd Commission"), COURIER_COST(
			"Courier Cost"), FULFILLMENT_BY("fulfillmentBy"), WEIGHT(
			"Weight (g)"), COURIER_COST_BOURNE_BY("Courier Cost Bourne By"), VENDOR_ENABLED(
			"Vendor Enabled"), SHIPPING_GROUP("Shipping Group"), SERVICIBILITY_INDEX(
			"Servicibility Index"), FREEBIE_ID("Freebie Id"), UPC("UPC"), EAN(
			"EAN"), MPN("MPN"), MODEL_NUMBER("Model Number"), NAVIGATION_CATEGORY(
			"Navigation Category");

	private final String header;

	private ValidatorFileHeaders(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

}
