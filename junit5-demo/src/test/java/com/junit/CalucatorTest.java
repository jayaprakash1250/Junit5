package com.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Running tests on calucator")
class CalucatorTest {
	
	
	static Calucator c1;
	@BeforeAll  //@BeforeClass --older version
	public static void first() 
	{
	    System.out.println("Started Test");
	    c1 = new Calucator();
	    
	}
	
    @BeforeEach  //@Before
	public void beforeTest()
	{
	    System.out.println("Before Test");
	    
	}

	@Test
	@Disabled(value = "just demo disable example") //@Ignore --older version
	@DisplayName("default test case")
	void test() {
		System.out.println("from test() Demo");
	}
	
	@Test
	@Tag("Math")
	public void addTest()
	{
		
		int actual = c1.add(20, 30);
		int expected = 50;
		assertEquals(expected, actual, ()-> "Got Worng result :"+actual+"Expected is: "+expected);
		System.out.println("from addTest() Demo");
	}
	
	@Test
	@Tag("Math")
	public void subTest()
	{
		assertAll(()->assertEquals(30, new Calucator().sub(50, 20)),
		()->assertEquals(20, new Calucator().sub(50, 20)),
		()->assertEquals(120, new Calucator().sub(100, -20)),
		()->assertEquals(-10, new Calucator().sub(20, 30)),
		() ->assertEquals(60, new Calucator().sub(50, 20)));
		
	}
	
	@Test
	@Tag("Math")
	public void multest()
	{
		assertEquals(1000, new Calucator().mul(50, 20));
		System.out.println("from multest() Demo");
	}
	
	@Test
	@Tag("Math")
	public void divTest()
	{
		assertEquals(2, new Calucator().div(50, 20));
		assertThrows(ArithmeticException.class, () ->new Calucator().div(0, 0));
		assertThrows(NumberFormatException.class, () ->new Calucator().div(4, -1));
		System.out.println("from testdiv() Demo");
	}
	
	@AfterEach
	public void afterTest()
	{
	    System.out.println("After Test");
	    
	}
	@AfterAll
	public static void last()
	{
	    System.out.println("Last Test");
	    c1 = null;
	}
	
	@Test
	@EnabledOnOs(OS.WINDOWS)
	@Tag("OS")
	public void testWindows()
	{
		System.out.println("For Windows only..");
	}
	@Test
	@Tag("OS")
	@EnabledOnOs(OS.LINUX)
	public void testLinux()
	{
		System.out.println("For Linux only..");
	}
	
	@Test
	@Tags(value = {@Tag("Math"), @Tag("Demo")})
	public void testDynamically()
	{
		System.out.println("Test Dynamically");
		//assumeTrue(true);
		assertEquals(200, c1.mul(20, 10));
		
	}


}
