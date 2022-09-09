package cn.edu.njupt;

import junit.framework.TestCase;

public class BookTest extends TestCase {
	private Book book1;
	private Book book2;

	public BookTest(String arg0) {
		super(arg0);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		book1 = new Book("ES",12.99);
		book2 = new Book("The Gate",11.99);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		book1 = null;
		book2 = null;
	}
	
	public void testEqualsObject(){
		assertFalse(book2.equals(book1));
		assertTrue(book1.equals(book1));
	}

}
