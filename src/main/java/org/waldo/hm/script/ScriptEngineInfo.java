package org.waldo.hm.script;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;

/**
 * User: wangyin
 * Date: 13-6-5
 * Time: 下午11:04
 */
public class ScriptEngineInfo {

    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        //遍历输出
        for (ScriptEngineFactory factory : factories) {
            System.out.println("EngineName      = " + factory.getEngineName());
            System.out.println("EngineVersion   = " + factory.getEngineVersion());
            System.out.println("LanguageName    = " + factory.getLanguageName());
            System.out.println("LanguageVersion = " + factory.getLanguageVersion());
            System.out.println("Extensions      = " + factory.getExtensions());

            List<String> names = factory.getNames();
            for (String name : names) {
                System.out.println("Engine Alias = " + name);
            }
        }
    }

}
