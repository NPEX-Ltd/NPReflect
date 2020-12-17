 package np.reflect;

import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderWrapper extends URLClassLoader {

	public ClassLoaderWrapper() {
		super(new URL[0]);
	}
	
	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}

}
