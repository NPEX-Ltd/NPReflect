package np.reflect.tests;

import np.library.testing.Test;
import np.reflect.ReflectUtils;
import static np.library.testing.Tester.*;

public class ReflectUtilsTester {
	@Test
	public void TestConstruction() {
		TestClass test = ReflectUtils.Create(TestClass.class);
		FailIfNull(test);
	}
	
	@Test
	public void TestFields() {
		TestClass test = new TestClass();
		int dogCount = ReflectUtils.GetIntField(TestClass.class, "dogCount", test);
		System.out.println("Dog Count: "+dogCount);
	}
	
	public static class TestClass {
		public TestClass() {}
		
		public int dogCount = 10;
	}
}
