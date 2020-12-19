package np.reflect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.jar.Attributes.Name;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import np.library.exceptions.JuggledException;

public class Javac {
	private static JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); 
	
	public static boolean IsJDKInstalled() { return compiler != null; }
	
	public static <T> T CompileClass(String className, String[] source) {
		String buffer = "";
		for(String line : source) {
			buffer += line + "\n";
		}
		
		return CompileClass(className, buffer);
	}
	
	public static <T> T CompileClass(String className, String source)
	throws JuggledException {
		try {
			File file = new File(System.getProperty("java.io.tmpdir")+"/"+className+".java");
			file.createNewFile();
			file.deleteOnExit();
			SaveJavaSource(source, file);
			Path path = CompileSource(className, file);
			return LoadClass(className, path);
		} catch (Exception ioex) {
			throw new JuggledException(ioex);
		}
	}
	
	private static <T> T LoadClass(String name, Path path) 
	throws Exception {
		URL classURL = path.getParent().toFile().toURI().toURL();
		ClassLoaderWrapper loader = new ClassLoaderWrapper();
		loader.addURL(classURL);
		Class<?> clazz = Class.forName(name, true, loader);
		return (T) clazz.newInstance();
	}
	
	private static Path CompileSource(String className, File file) {
		compiler.run(null, System.out, System.err, file.getAbsolutePath());
		return Path.of(file.toURI()).resolve(className);
	}
	
	private static void SaveJavaSource(String source, File file)
	throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(source);
		writer.close();
	}
}
