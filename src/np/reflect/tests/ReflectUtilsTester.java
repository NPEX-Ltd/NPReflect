package np.reflect.tests;

import np.library.testing.Test;
import np.reflect.ReflectUtils;
import static np.library.testing.Tester.*;

import java.lang.reflect.Field;

public class ReflectUtilsTester {
	
	TestClass clazz = new TestClass();
	
	@Test
	public void TestConstruction() {
		TestClass test = ReflectUtils.Create(TestClass.class);
		FailIfNull(test);
	}
	
	@Test
	public void TestFieldInput() {
		int numberOfDogs = ReflectUtils.GetIntFieldFromObject("dogCount", clazz);
		System.out.println("Number Of Dogs: "+numberOfDogs);
		FailIfNotEqual(numberOfDogs, clazz.dogCount);
	}
	
	@Test
	public void TestFieldConsumer() {
		ReflectUtils.ConsumeAllFields(TestClass.class, this::FieldConsumer);
	} 
	
	public void FieldConsumer(Field field) {
		System.out.println(field);
	}
	public void TestFields() {
		TestClass test = new TestClass();
		int dogCount = ReflectUtils.GetIntFieldFromObject("dogCount", test);
		System.out.println("Dog Count: "+dogCount);
	}
	
	public static class TestClass {
		public TestClass() {}
		
		public int dogCount = 10;
		private byte catCount = 127;
	}
}
