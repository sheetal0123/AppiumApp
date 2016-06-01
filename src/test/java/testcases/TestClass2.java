package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestClass2 {


	
	@Test
	public void one(){
		Assert.assertTrue(10>2);
	}
	
	@Test
	public void two(){
		Assert.assertTrue(10>20);
	}

	@Test
	public void Three(){
		Assert.assertTrue(1000>200);
	}

	
	
}
