package eg.edu.alexu.csd.oop.game.cs01.DynamicLinkage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import eg.edu.alexu.csd.oop.game.cs01.objects.AbstractFallenObject;

public class GameObjectLoader {

	private static GameObjectLoader instance;
	// map of classes that were loaded from jarfile .... classes's name was
	// lowerCase.
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
	public void loadClass(String jarPath) {
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
					System.out.println(className);
					Class<?> c = classLoader.loadClass(className);
					classesMap.put(className.substring(className.lastIndexOf(".") + 1).toLowerCase(), c);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Class<?>> getClassesMap() {
		return classesMap;
	}

	public void setClassesMap(Map<String, Class<?>> classesMap) {
		this.classesMap = classesMap;
	}

	public Class<?> getClass(String className) {
		return classesMap.get(className);
	}

	public AbstractFallenObject newInstance(String className) {
		return newInstance(className, new Object[] {});
	}

	public AbstractFallenObject newInstance(String className, Object[] parameters) {
		Constructor<?> constrcutor = null;
		try {
			Class<?>[] paramTypes = new Class[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				paramTypes[i] = parameters[i].getClass();
			}
			constrcutor = classesMap.get(className).getConstructor(paramTypes);
			return (AbstractFallenObject) constrcutor.newInstance(parameters);
		} catch (Exception e) {
		}
		return null;
	}

	public void loadClasses() {
		if (classesMap.isEmpty()) {
			File fileJars = new File("platesJars");
			for (File jar : fileJars.listFiles()) {
				loadClass(jar.getAbsolutePath());
			}
		}
	}
}
