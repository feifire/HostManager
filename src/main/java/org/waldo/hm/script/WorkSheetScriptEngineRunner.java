package org.waldo.hm.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.*;
import java.io.*;
import java.nio.charset.Charset;

/**
 * User: wangyin
 * Date: 13-6-6
 * Time: 上午1:13
 */
public class WorkSheetScriptEngineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WorkSheetScriptEngineRunner.class);

    private Reader scriptReader;

    private ScriptEngine engine;

    private CompiledScript compiledScript;

    public WorkSheetScriptEngineRunner() {

    }

    public void init() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        System.out.println("init jsEngine...");
        manager.put("log", logger);
        engine = manager.getEngineByName("JavaScript");
        System.out.println("init done!");

        System.out.println("load script...");
        scriptReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("script/ws-group-rule.js"), Charset.forName("utf-8"));
        System.out.println("load done!");

        if (Compilable.class.isInstance(engine)) {
            System.out.println("compile js...");
            Compilable compilable = (Compilable) engine;
            compiledScript = compilable.compile(scriptReader);
            System.out.println("compile done...");
        }
    }

    public void destroy() {
        if (scriptReader != null) {
            try {
                scriptReader.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    public static void main(String[] args) {
        WorkSheetScriptEngineRunner runner = new WorkSheetScriptEngineRunner();
        try {
            runner.init();
            System.out.println("GroupName: " + runner.runWorkSheetScript());
            System.out.println("GroupName: " + runner.runOtherWorkSheetScript());
            runner.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String runWorkSheetScript() {
        WorkSheet worksheet = new WorkSheet();
        worksheet.setType("TradeComplaint");

        ScriptContext context = new SimpleScriptContext();
        context.setBindings(engine.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
        context.setAttribute("worksheet", worksheet, ScriptContext.ENGINE_SCOPE);
        context.setAttribute("wsType", worksheet.getClass().getSimpleName(), ScriptContext.ENGINE_SCOPE);

        String groupName = null;
        try {
            compiledScript.eval(context);
            groupName = (String) context.getAttribute("result");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return groupName;
    }

    public String runOtherWorkSheetScript() {
        OtherWorkSheet worksheet = new OtherWorkSheet();
        worksheet.setRefundId("TQ2013060612349527");

        ScriptContext context = engine.getContext();
        context.setBindings(engine.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
        context.setAttribute("worksheet", worksheet, ScriptContext.ENGINE_SCOPE);
        context.setAttribute("wsType", worksheet.getClass().getSimpleName(), ScriptContext.ENGINE_SCOPE);

        String groupName = null;
        try {
            compiledScript.eval(context);
            groupName = (String) context.getAttribute("result");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return groupName;
    }

}
