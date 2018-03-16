package cn.dttn.javac;

import com.sun.tools.javac.Main;

public class BootStrap {

    public static void main(String[] args) {
        Main.compile(new String[]{"Hello.java"});
    }
}
