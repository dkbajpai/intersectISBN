package com.snapdeal.sps.intersectISBN.dto;

public class NavigationCategoryDTO {

	String productCategory;
	String navigationCategory;
	
	
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getNavigationCategory() {
		return navigationCategory;
	}
	public void setNavigationCategory(String navigationCategory) {
		this.navigationCategory = navigationCategory;
	}
	
	public NavigationCategoryDTO(String productCategory,
			String navigationCategory) {
		super();
		this.productCategory = productCategory;
		this.navigationCategory = navigationCategory;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((navigationCategory == null) ? 0 : navigationCategory
						.hashCode());
		result = prime * result
				+ ((productCategory == null) ? 0 : productCategory.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NavigationCategoryDTO other = (NavigationCategoryDTO) obj;
		if (navigationCategory == null) {
			if (other.navigationCategory != null)
				return false;
		} else if (!navigationCategory.equals(other.navigationCategory))
			return false;
		if (productCategory == null) {
			if (other.productCategory != null)
				return false;
		} else if (!productCategory.equals(other.productCategory))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NavigationCategoryDTO [productCategory=" + productCategory
				+ ", navigationCategory=" + navigationCategory + "]";
	}
	
	
	
}
