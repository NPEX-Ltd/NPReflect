package np.reflect;

import np.library.common.Logger;

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
		} finally {
			return null;
		}
	}
}

