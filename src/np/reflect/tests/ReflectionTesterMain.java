package np.reflect.tests;

import np.library.testing.Tester;

public class ReflectionTesterMain {
	public static void main(String[] args) {
		Tester.Test(ReflectUtilsTester.class);
		Tester.Test(MethodWrapperTester.class);
		Tester.Test(ClassWrapperTester.class);
		Tester.Test(JavacTester.class);
	} 
}
