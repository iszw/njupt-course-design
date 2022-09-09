package cn.edu.njupt;

import junit.framework.TestCase;

public class TriangleTest extends TestCase {
	private Triangle triangle1;
	private Triangle triangle2;
	private Triangle triangle3;
	private Triangle triangle4;
	
	public TriangleTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		triangle1 = new Triangle(3, 4, 5);
		triangle2 = new Triangle(5, 5, 6);
		triangle3 = new Triangle(1, 2, 4);
		triangle4 = new Triangle(3, 3, 3);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		triangle1 = null;
		triangle2 = null;
		triangle3 = null;
		triangle4 = null;
	}

	public void testGetType() {
		assertTrue(Triangle.getType(triangle1).equals("不等边三角形"));
		assertTrue(Triangle.getType(triangle2).equals("等腰三角形"));
		assertTrue(Triangle.getType(triangle3).equals("不是三角形"));
		assertTrue(Triangle.getType(triangle4).equals("等边三角形"));
	}

}
