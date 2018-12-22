package eg.edu.alexu.csd.oop.game.cs01.DynamicLinkage;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class GameObjectLoader {

	private static GameObjectLoader instance;
	private Map<String, Class<?>> classesMap;

	private GameObjectLoader() {
		classesMap = new HashMap<>();
	}

	public static GameObjectLoader getInstance() {
		if (instance == null) {
			instance = new GameObjectLoader();
		}
		return instance;
	}

	@SuppressWarnings("resource")
	private Class<?> loadClass(String jarPath) {
		try {
			JarFile jarFile = new JarFile(jarPath);
			Enumeration<JarEntry> e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + jarPath + "!/") };
			URLClassLoader classLoader = URLClassLoader.newInstance(urls);
			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				String className = je.getName().substring(0, je.getName().length() - 6);
				className = className.replace('/', '.');
				try {
					Class<?> c = classLoader.loadClass(className);
					return c;
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Class<?>> getClassesMap() {
		return classesMap;
	}

	public void setClassesMap(Map<String, Class<?>> classesMap) {
		this.classesMap = classesMap;
	}

	public void loadClasses() {
		addClass("", "");
		addClass("", "");
		addClass("", "");
		addClass("", "");

	}

	public void addClass(String name, String jarPath) {
		classesMap.put(name, loadClass(jarPath));
	}
}
