package np.reflect;

import np.library.common.Logger;
import np.library.exceptions.JuggledException;
import java.lang.reflect.*;
@SuppressWarnings({"unused", "deprecation"})
public final class ReflectUtils {
	private static Logger logger = Logger.CreateNew(ReflectUtils.class);
	
	private ReflectUtils() {}
	
	public static int GetIntField(Class<?> clazz, String name, Object instance) {
		try {
			Field field = GetFieldByName(clazz, name);
			return field.getInt(instance);
		} catch (Exception ex) {
			logger.Debug(ex);
			return 0;
		}
	}
	
	public static <T> T Create(Class<T> clazz)
	throws JuggledException {
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			throw new JuggledException(ex);
		}
	}
	
	public static Field GetFieldByName(Class<?> clazz, String name)
	throws JuggledException {
		try {
			return clazz.getField(name);
		} catch (Exception ex) {
			throw new JuggledException(ex);
		}
	}
}