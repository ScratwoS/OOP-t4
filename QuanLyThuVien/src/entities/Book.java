package entities;

public class Book {
	private int id,published_year, quantity,category,type,language;
	private double price;
	private String title,author, publisher,image,description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPublished_year() {
		return published_year;
	}
	public void setPublished_year(int published_year) {
		this.published_year = published_year;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLanguage() {
		return language;
	}
	public void setLanguage(int language) {
		this.language = language;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Book(int id, int published_year, int quantity, int category, int type, int language, double price,
			String title, String author, String publisher, String image, String description) {
		this.id = id;
		this.published_year = published_year;
		this.quantity = quantity;
		this.category = category;
		this.type = type;
		this.language = language;
		this.price = price;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.image = image;
		this.description = description;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	
}
