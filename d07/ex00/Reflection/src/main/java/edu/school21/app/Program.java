package edu.school21.app;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    private static final String packagePrefix = "edu.school21.classes.";
    private static final String[] supportedClasses = {"User", "Car"};

    public static void main(String[] args) throws
            InstantiationException,
            IllegalAccessException,
            ClassNotFoundException,
            NoSuchFieldException,
            InvocationTargetException,
            NoSuchMethodException {
        listClasses();
        hr();
        Scanner scanner = new Scanner(System.in);
        Class<?> inputClass = inputClass(scanner);
        hr();
        listFieldsAndMethods(inputClass);
        hr();
        Object o = createObject(inputClass, scanner);
        hr();
        changeField(o, scanner);
        hr();
        callMethod(o, scanner);
    }

    private static void hr() {
        System.out.println("----------------------");
    }

    private static void listClasses() {
        System.out.println("Classes:");
        for (String s : supportedClasses)
            System.out.println(s);
    }

    private static Class<?> inputClass(Scanner scanner) throws ClassNotFoundException {
        System.out.println("Enter class name:");
        String className = scanner.nextLine();
        return Class.forName(packagePrefix + className);
    }

    private static void listFieldsAndMethods(Class<?> c) {
        System.out.println("Fields:");
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields)
            System.out.println("\t" + f.getType().getSimpleName() + " " + f.getName());
        System.out.println("Methods:");
        Method[] methods = c.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals("toString"))
                continue;
            System.out.println("\t" + m.getReturnType().getSimpleName() + " " + methodToString(m));
        }
    }

    private static String methodToString(Method m) {

        StringBuilder s = new StringBuilder(m.getName());
        s.append("(");
        Class<?>[] parameters = m.getParameterTypes();
        int count = m.getParameterCount();
        for (int i = 0; i < count; ++i) {
            s.append(parameters[i].getSimpleName());
            if (i < count - 1)
                s.append(", ");
        }
        s.append(")");
        return s.toString();
    }

    private static Object createObject(Class<?> c, Scanner scanner) throws
            InstantiationException, IllegalAccessException {
        System.out.println("Let's create an object.");
        Object o = c.newInstance();
        for (Field f : c.getDeclaredFields()) {
            System.out.println(f.getName() + ":");
            f.setAccessible(true);
            f.set(o, scanForType(scanner, f.getType().getSimpleName()));
        }
        System.out.println("Object created: " + o.toString());
        return o;
    }

    private static void changeField(Object o, Scanner scanner) throws
            NoSuchFieldException, IllegalAccessException {
        System.out.println("Enter name of the field for changing:");
        String name = scanner.nextLine();
        Field f = o.getClass().getDeclaredField(name);
        System.out.println("Enter " + f.getType().getSimpleName() + " value:");
        f.setAccessible(true);
        f.set(o, scanForType(scanner, f.getType().getSimpleName()));
        System.out.println("Object updated: " + o);
    }

    private static void callMethod(Object o, Scanner scanner) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of the method to call:");
        String inputName = scanner.nextLine();
        Method[] methods = o.getClass().getDeclaredMethods();
        Method method = null;
        for (Method m : methods) {
            String methodName = methodToString(m);
            if (inputName.equals(methodName)) {
                method = m;
                break ;
            }
        }
        if (method == null) {
            throw new NoSuchMethodException();
        }
        int count = method.getParameterCount();
        Parameter[] parameters = method.getParameters();
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            Parameter p = parameters[i];
            System.out.println("Enter " + p.getType().getSimpleName() + " value:");
            list.add(scanForType(scanner, p.getType().getSimpleName()));
        }
        Object r = method.invoke(o, list.toArray());
        if (r != null) {
            System.out.println("Method returned:");
            System.out.println(r);
        }
    }

    private static Object scanForType(Scanner s, String type) {
        String in = s.nextLine();
        if (type.equals("String"))
            return in;
        else if (type.equals("int") || type.equals("Integer"))
            return Integer.parseInt(in);
        else if (type.equalsIgnoreCase("double"))
            return Double.parseDouble(in);
        else if (type.equalsIgnoreCase("boolean"))
            return Boolean.parseBoolean(in);
        else if (type.equalsIgnoreCase("long"))
            return Long.parseLong(in);
        System.err.println("Unsupported type");
        return null;
    }
}
