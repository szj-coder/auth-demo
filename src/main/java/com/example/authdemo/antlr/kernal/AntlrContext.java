package com.example.authdemo.antlr.kernal;

import java.util.HashMap;
import java.util.Map;

/**
 * antlr 计算上下文
 */
public class AntlrContext {
    private final Map<String, Object> context;
    private AntlrContext parent;

    public AntlrContext() {
        this.context = new HashMap<>();
    }

    public AntlrContext(Map<String, Object> context) {
        this.context = context;
    }

    public AntlrContext pop() {
        if (parent != null) {
            return parent;
        }
        throw new RuntimeException("已经是最顶层了，无法回退");
    }

    public AntlrContext push() {
        final AntlrContext stop = new AntlrContext();
        stop.setParent(this);
        return stop;
    }

    public Object getValue(String key) {
        final Object value = context.get(key);
        if (value != null) {
            return value;
        }
        if (parent != null) {
            return parent.getValue(key);
        }
        throw new RuntimeException("变量不存在:" + key);
    }

    public boolean containsKey(String key) {
        final boolean result = context.containsKey(key);
        if (result || parent == null) {
            return true;
        }
        return parent.containsKey(key);
    }

    public void setValue(String key, Object value) {
        if (context.containsKey(key)) {
            context.put(key, value);
        } else if (containsKey(key) && parent != null) {
            parent.setValue(key, value);
        } else {
            context.put(key, value);
        }
    }

    void setParent(AntlrContext parent) {
        this.parent = parent;
    }
}
