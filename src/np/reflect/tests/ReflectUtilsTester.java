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
	
	private static class TestClass {
		public TestClass() {}
	}
}
