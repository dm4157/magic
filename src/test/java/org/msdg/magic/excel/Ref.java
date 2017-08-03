package org.msdg.magic.excel;

import sun.reflect.generics.tree.ReturnType;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by mw4157 on 16/6/23.
 */
public class Ref {

    public static void main(String[] args) throws Throwable {
        EntityTest test = new EntityTest();
        test.setName("adsb");

        Field f = test.getClass().getDeclaredField("name");

        MethodType getMethodType = MethodType.methodType(String.class);
        MethodHandle handle = MethodHandles.lookup().findVirtual(test.getClass(), "getName", getMethodType).bindTo(test);
        System.out.println((String)handle.invokeExact());
        System.out.println(f.getType().cast(handle.invokeExact()));
    }
}
