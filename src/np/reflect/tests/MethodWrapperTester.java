package np.reflect.tests;

import static np.library.testing.Tester.*;
import np.library.testing.Test;
import np.reflect.MethodWrapper;

public class MethodWrapperTester {
	@Test
	public void TestMethodInvokation() {
		MethodWrapper wrapper = MethodWrapper.GetMethod(this.getClass(), "TestMethod", "Hello");
		wrapper.Invoke(this, "Hello");
	}
	
	@Test
	public void TestStaticMethodInvoking() {
		MethodWrapper.InvokeMethod("TestMethod", this, "Konichiwa");
	}
	public void TestMethod() {
		System.out.println("Hello From Test Method");
	}
	
	public void TestMethod(String greeting) {
		System.out.println("Hello From Test Method, "+greeting);
	}
}
