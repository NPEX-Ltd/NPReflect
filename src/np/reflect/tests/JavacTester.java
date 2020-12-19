package np.reflect.tests;

import np.library.testing.Test;
import np.reflect.Javac;

public class JavacTester {
	@Test
	public void TestJavac() {
		String[] source = { 
				"import np.reflect.tests.JavacTester.TestInterface;",
				"public class TestImpl implements TestInterface {",
				" public void Greet() { System.out.println(\"Hello From TestClass\"); }",
				"}"
		};
		
		TestInterface api = Javac.CompileClass("TestImpl", source);
		api.Greet();
	}
		
	public static interface TestInterface {
			public void Greet();
	}
}
