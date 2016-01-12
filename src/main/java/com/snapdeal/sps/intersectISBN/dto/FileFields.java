package com.snapdeal.sps.intersectISBN.dto;

public class FileFields {

	String title;
	String authors;
	String publisher;
	String isbn10;
	String isbn13;
	String description;
	String numberOfPages;
	String publicationDate;
	String categoryCode;
	String language;
	String binding;

	@Override
	public String toString() {
		return "FileFields [title=" + title + ", authors=" + authors
				+ ", publisher=" + publisher + ", isbn10=" + isbn10
				+ ", isbn13=" + isbn13 + ", description=" + description
				+ ", numberOfPages=" + numberOfPages + ", publicationDate="
				+ publicationDate + ", categoryCode=" + categoryCode
				+ ", language=" + language + ", binding=" + binding + "]";
	}

	public FileFields() {
	}

	public FileFields(String title, String authors, String publisher,
			String isbn10, String isbn13, String description,
			String numberOfPages, String publicationDate, String categoryCode,
			String language, String binding) {
		super();
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.description = description;
		this.numberOfPages = numberOfPages;
		this.publicationDate = publicationDate;
		this.categoryCode = categoryCode;
		this.language = language;
		this.binding = binding;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

}
