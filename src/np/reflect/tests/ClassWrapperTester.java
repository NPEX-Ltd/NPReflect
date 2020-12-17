package np.reflect.tests;

import np.library.testing.Test;
import np.reflect.ClassWrapper;

public class ClassWrapperTester {
	
	public ClassWrapperTester() {
		System.out.println("Constructed Class");
	}
	
	@Test
	public void TestConstruct() {
			ClassWrapper<ClassWrapperTester> wrapper = new ClassWrapper<>(ClassWrapperTester.class);
			wrapper.Construct();
	}
}
