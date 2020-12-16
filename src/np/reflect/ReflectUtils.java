package np.reflect;

import java.lang.reflect.Field;
import java.util.function.Consumer;

import np.library.common.Logger;
import np.library.exceptions.JuggledException;

public class ReflectUtils {
	private static Logger logger = Logger.CreateNew(ReflectUtils.class);
	@SuppressWarnings({ "deprecation", "finally" })
	public static <T> T Create(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (ClassCastException ccex) {
			logger.Warn(ccex);
		} catch (IllegalAccessException ilex) {
			logger.Warn(ilex);
		} catch (InstantiationException inex) {
			logger.Warn(inex);
		}
		
		return null; //Should Be Unreachable
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
}

