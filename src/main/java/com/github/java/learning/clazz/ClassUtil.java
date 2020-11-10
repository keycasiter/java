package com.github.java.learning.clazz;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * created by guanjian on 2020/11/10 14:52
 */
public class ClassUtil {

    /**
     * 通过loader加载所有类
     *
     * @param
     * @return
     */
    public static List<Class> loadClassByLoader(ClassLoader load) throws Exception {
        Enumeration<URL> urls = load.getResources("");
        //放所有类型
        List<Class> classes = new ArrayList<Class>();
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            //文件类型（其实是文件夹）
            if (url.getProtocol().equals("file")) {
                loadClassByPath(null, url.getPath(), classes, load);
            }
        }
        return classes;
    }

    /**
     * 通过文件路径加载所有类 root 主要用来替换path中前缀（除包路径以外的路径）
     *
     * @param
     * @return
     */
    private static void loadClassByPath(String root, String path, List<Class> list, ClassLoader load) {
        File f = new File(path);
        if (root == null) root = f.getPath();
        //判断是否是class文件
        if (f.isFile() && f.getName().matches("^.*\\.class$")) {
            try {
                String classPath = f.getPath();
                //截取出className 将路径分割符替换为.（windows是\ linux、mac是/）
                String className = classPath.substring(root.length() + 1, classPath.length() - 6).replace('/', '.').replace('\\', '.');
                list.add(load.loadClass(className));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            File[] fs = f.listFiles();
            if (fs == null) return;
            for (File file : fs) {
                loadClassByPath(root, file.getPath(), list, load);
            }
        }
    }

    /*
     * 获取指定接口的所有实现实例
     */
    public static List<Object> getAllObjectByInterface(Class<?> c)
            throws InstantiationException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        List<Class<?>> classes = getAllClassByInterface(c);
        for (int i = 0; i < classes.size(); i++) {
            list.add(classes.get(i).newInstance());
        }
        return list;
    }

    /*
     * 获取指定接口的实例的Class对象
     */
    public static List<Class<?>> getAllClassByInterface(Class<?> c) {
        // 如果传入的参数不是接口直接结束
        if (!c.isInterface()) {
            return null;
        }

        // 获取当前包名
        String packageName = c.getPackage().getName();
        List<Class<?>> allClass = null;
        try {
            allClass = getAllClassFromPackage(packageName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        ArrayList<Class<?>> list = new ArrayList<Class<?>>();
        for (int i = 0; i < allClass.size(); i++) {
            if (c.isAssignableFrom(allClass.get(i))) {
                if (!c.equals(allClass.get(i))) {
                    list.add(allClass.get(i));
                }
            }
        }

        return list;
    }

    private static List<Class<?>> getAllClassFromPackage(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> enumeration = classLoader.getResources(path);
        List<String> classNames = getClassNames(enumeration, packageName);

        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (int i = 0; i < classNames.size(); i++) {
            classes.add(Class.forName(classNames.get(i)));
        }

        return classes;
    }

    public static List<String> getClassNames(Enumeration<URL> enumeration, String packageName) {
        List<String> classNames = null;
        while (enumeration.hasMoreElements()) {
            URL url = enumeration.nextElement();
            if (url != null) {
                String type = url.getProtocol();
                if (type.equals("file")) {
                    System.out.println("type : file");
                    String fileSearchPath = url.getPath();
                    if (fileSearchPath.contains("META-INF")) {
                        System.out.println("continue + " + fileSearchPath);
                        continue;
                    }
                    classNames = getClassNameByFile(fileSearchPath);
                } else if (type.equals("jar")) {
                    try {
                        System.out.println("type : jar");
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        classNames = getClassNameByJar(jarFile, packageName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("type : none");
                }
            }
        }

        return classNames;
    }

    /*
     * 获取项目某路径下的所有类
     */
    public static List<String> getClassNameByFile(String fileSearchPath) {
        List<String> classNames = new ArrayList<String>();

        File file = new File(fileSearchPath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                classNames.addAll(getClassNameByFile(childFile.getPath()));
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    String className = childFilePath.substring(childFilePath.lastIndexOf("\\bin\\") + 1,
                            childFilePath.length()).replaceAll("\\\\", ".");
                    className = className.substring(4, className.indexOf(".class"));
                    classNames.add(className);
                }
            }
        }

        return classNames;
    }

    /*
     * 从jar包中获取某路径下的所有类
     */
    public static List<String> getClassNameByJar(JarFile jarFile, String packageName) {
        List<String> classNames = new ArrayList<String>();
        Enumeration<JarEntry> entrys = jarFile.entries();
        while (entrys.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) entrys.nextElement();
            String entryName = jarEntry.getName();
            if (entryName.endsWith(".class")) {
                String className = entryName.replace("/", ".");
                className = className.substring(0, className.indexOf(".class"));
                classNames.add(className);
            }

        }
        return classNames;
    }

}
