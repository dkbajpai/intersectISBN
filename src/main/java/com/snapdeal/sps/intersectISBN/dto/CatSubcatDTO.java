package com.snapdeal.sps.intersectISBN.dto;

public class CatSubcatDTO {

	String subCatLevel1;
	String subCatLevel2;
		
	public CatSubcatDTO() {
		super();
	}
	
	public CatSubcatDTO(String subCat_level1, String subCat_level2) {
		super();
		this.subCatLevel1 = subCat_level1;
		this.subCatLevel2 = subCat_level2;
	}
	
	@Override
	public String toString() {
		return "CatSubcatDTO [subCat_level1=" + subCatLevel1
				+ ", subCat_level2=" + subCatLevel2 + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subCatLevel1 == null) ? 0 : subCatLevel1.hashCode());
		result = prime * result
				+ ((subCatLevel2 == null) ? 0 : subCatLevel2.hashCode());
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
		CatSubcatDTO other = (CatSubcatDTO) obj;
		if (subCatLevel1 == null) {
			if (other.subCatLevel1 != null)
				return false;
		} else if (!subCatLevel1.equals(other.subCatLevel1))
			return false;
		if (subCatLevel2 == null) {
			if (other.subCatLevel2 != null)
				return false;
		} else if (!subCatLevel2.equals(other.subCatLevel2))
			return false;
		return true;
	}

	public String getSubCatLevel1() {
		return subCatLevel1;
	}

	public void setSubCatLevel1(String subCatLevel1) {
		this.subCatLevel1 = subCatLevel1;
	}

	public String getSubCatLevel2() {
		return subCatLevel2;
	}

	public void setSubCatLevel2(String subCatLevel2) {
		this.subCatLevel2 = subCatLevel2;
	}
	
}
