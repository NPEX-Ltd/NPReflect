package np.reflect;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import np.library.exceptions.JuggledException;

public class ClassWrapper<T> {
	private Class<T> clazz;
	
	public ClassWrapper(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	public ClassWrapper(String fullName, ClassLoader loader) throws ClassNotFoundException {
		this((Class<T>)loader.loadClass(fullName));
	}
	
	public static <T> ClassWrapper<T> Load(String name)
	throws JuggledException {
		try {
			ClassLoader loader = ClassWrapper.class.getClassLoader();
			return new ClassWrapper<>(name, loader);
		} catch (ClassNotFoundException cnfex) {
			throw new JuggledException(cnfex);
		}
	}
	
	public static <T> ClassWrapper<T> LoadExternal(String path, String name)
	throws JuggledException {
		try {
			ClassLoaderWrapper loader = new ClassLoaderWrapper();
			loader.addURL(new File(path).toURI().toURL());
			return new ClassWrapper<T>(name, loader);
		} catch (Exception cnfex) {
			throw new JuggledException(cnfex);
		}
	}

	public T Construct(Object... args) {
		try {
			Class<?>[] types = ReflectUtils.GetTypes(args);
			Constructor<T> constructor = clazz.getDeclaredConstructor(types);
			return constructor.newInstance(args);
		} catch (ReflectiveOperationException refex) {
			throw new JuggledException(refex);
		}
		
	}
}
