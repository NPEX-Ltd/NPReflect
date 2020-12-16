package np.reflect;

import java.lang.reflect.*;
import java.util.function.Consumer;

import np.library.common.Logger;
import np.library.exceptions.JuggledException;

@SuppressWarnings({"unused", "deprecation"})
public final class ReflectUtils {

	private static Logger logger = Logger.CreateNew(ReflectUtils.class);
	
	private ReflectUtils() {}
	public static <T> T Create(Class<T> clazz)
	throws JuggledException {
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			throw new JuggledException(ex);
		}
	}
	public static int GetIntFieldFromObject(String name, Object object) {
		try {
			return GetNamedFieldFromClass(name, object.getClass()).getInt(object);
		} catch (JuggledException ex) {
			return 0;
		} catch (ReflectiveOperationException refex) {
			return 0;
		}
	}
	
	public static Field GetNamedFieldFromClass(String name, Class<?> clazz)
	throws JuggledException {
		try {
			return clazz.getField(name);
		} catch (ReflectiveOperationException ex) {
			throw new JuggledException(ex);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void ConsumeAllFields(Class<?> clazz, Consumer<Field> consumer) {
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields) {
				boolean isPublic = field.isAccessible();
				field.setAccessible(true);
				consumer.accept(field);
				field.setAccessible(isPublic);
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