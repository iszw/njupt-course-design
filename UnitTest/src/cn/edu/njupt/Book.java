package cn.edu.njupt;

public class Book {
	private String title;
	private double price;

	/**
	*Constructor
	*@param title
	*@param price
	*/

	public Book(String title,Double price){
		this.title = title;
		this.price = price;
	}

	/**
	*Check if an object is an instance of book
	*and the values of title and price are equal
	*then return true,otherwise return false
	*/

	public boolean equals(Object object){
		if(object instanceof Book){
			Book book = (Book)object;
			return getTitle().equals(book.getTitle())
						&&getPrice() == book.getPrice();
		}
		return false;
	}

	public double getPrice(){
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}
}
