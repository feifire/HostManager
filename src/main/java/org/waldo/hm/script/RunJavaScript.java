package org.waldo.hm.script;

import javax.script.*;

/**
 * User: wangyin
 * Date: 13-6-6
 * Time: 上午12:17
 */
public class RunJavaScript {

    public static void main(String[] args) {
        // 获取脚本管理器
        ScriptEngineManager manager = new ScriptEngineManager();

        // create engine by name
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        for (int i = 0; i < 3; i++) {
            System.out.println(manager.getEngineByName("JavaScript"));
        }
        // create engine by name
        // ScriptEngine engine = manager.getEngineByExtension("js");
        // create engine by name
        // ScriptEngine engine = manager.getEngineByMimeType("application/javascript");
        try {
            engine.eval("if (true) { println('Hello World'); }");
            String script = "println('friend.name: ' + friend.name); friend.sayHello();";
            Friend friend = new Friend();
            friend.setName("WangYin");
            engine.put("friend", friend);
            engine.eval(script);

            Friend newFriend = new Friend();
            newFriend.setName("HuiHui");
            ScriptContext context = new SimpleScriptContext();
            Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
            bindings.put("friend", newFriend);
            engine.eval(script, context);

            engine.eval("function say(first, second) { " +
                    "println(first + ' ' + second);" +
                    "}");

            engine.eval(script);

            Invocable inv = (Invocable) engine;
            inv.invokeFunction("say", "Hello", "WangYin");

            /*String script = "var email=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]" + "+(\\.[a-zA-Z0-9_-]+)+$/;";
            script += "var ip = /^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])" + "(\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}$/;";
            script += "if(email.test(str)){println('it is an email')}" + "else if(ip.test(str)){println('it is an ip address')}" + "else{println('I don\\'t know')}";
            engine.put("str", "email@address.tony");
            Compilable compilable = (Compilable) engine;
            CompiledScript compiled = compilable.compile(script);
            compiled.eval();*/

//            ScriptContext context = new SimpleScriptContext();
//            manager.put();

        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
