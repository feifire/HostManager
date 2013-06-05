package org.waldo.hm.script;

/**
 * Created with IntelliJ IDEA.
 * User: wangyin
 * Date: 13-6-6
 * Time: 上午12:22
 * To change this template use File | Settings | File Templates.
 */
public class Friend {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println(getName() + " say: Hello World!!!");
    }
}
