package np.reflect;

import java.lang.reflect.Method;

public class MethodWrapper {
	
	private Method method;
	
	public MethodWrapper(Method method) {
		this.method = method;
	}
	
	public static Object InvokeMethod(String name, Object instance, Object... args) {
		try {
			MethodWrapper wrapper = GetMethod(instance.getClass(), name, args);
			return wrapper.Invoke(instance, args);
		} catch (MethodNotFoundException | MethodInvokationException ex) {
			return null;
		}
	}
	
	public static MethodWrapper GetMethod(Class<?> clazz, String name, Object... args)
	throws MethodNotFoundException {
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods) {
			if(
				method.getName().equals(name) && 
				isApplicable(method, args)
			  ) {
				return new MethodWrapper(method);
			}
		}
		throw new MethodNotFoundException();
	}
	
	public Object Invoke(Object instance, Object... args) {
		try {
			return method.invoke(instance, args);
		} catch (Exception ex) {
			throw new MethodInvokationException();
		}
	}
	
	private static boolean isApplicable(Method method, Object... args) {
	    Class<?>[] parameters = method.getParameterTypes();

	    // Length does not match
	    if (parameters.length != args.length) {
	        return false;
	    }

	    // Check classes against arguments
	    for (int i = 0; i < args.length; i++) {
	        Object argument = args[i];
	        Class<?> parameterType = parameters[i];

	        // Argument is not instance of parameter
	        if (!parameterType.isInstance(argument)) {
	            return false;
	        }
	    }

	    return true;
	}
}
