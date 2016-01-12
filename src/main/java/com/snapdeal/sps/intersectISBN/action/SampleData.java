package com.snapdeal.sps.intersectISBN.action;


public class SampleData {
	
	String title;
	String author;
	String publisher;
	String isbn10RecordReference;
	String isbn13IdValue;
	String text;
	String numberOfPages;
	String publicationDate;
	String bicMainSubject;
	String language;
	String binding;
	@Override
	public String toString() {
		return "SampleData [title=" + title + ", author=" + author
				+ ", publisher=" + publisher + ", isbn10RecordReference="
				+ isbn10RecordReference + ", isbn13IdValue=" + isbn13IdValue
				+ ", text=" + text + ", numberOfPages=" + numberOfPages
				+ ", publicationDate=" + publicationDate + ", bicMainSubject="
				+ bicMainSubject + ", language=" + language + ", binding="
				+ binding + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getIsbn10RecordReference() {
		return isbn10RecordReference;
	}
	public void setIsbn10RecordReference(String isbn10RecordReference) {
		this.isbn10RecordReference = isbn10RecordReference;
	}
	public String getIsbn13IdValue() {
		return isbn13IdValue;
	}
	public void setIsbn13IdValue(String isbn13IdValue) {
		this.isbn13IdValue = isbn13IdValue;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String getBicMainSubject() {
		return bicMainSubject;
	}
	public void setBicMainSubject(String bicMainSubject) {
		this.bicMainSubject = bicMainSubject;
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
	public SampleData(String title, String author, String publisher,
			String isbn10RecordReference, String isbn13IdValue, String text,
			String numberOfPages, String publicationDate,
			String bicMainSubject, String language, String binding) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn10RecordReference = isbn10RecordReference;
		this.isbn13IdValue = isbn13IdValue;
		this.text = text;
		this.numberOfPages = numberOfPages;
		this.publicationDate = publicationDate;
		this.bicMainSubject = bicMainSubject;
		this.language = language;
		this.binding = binding;
	}
	
	public SampleData(){}
}