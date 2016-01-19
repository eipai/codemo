package com.github.eipai.codemo.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExtProperties {
    private static final String LF = "\r\n";
    private String CHARSET = "unicode";
    private String PROPERTY_SEPARATOR_CHAR = ".";
    private String COMMENT_CHAR = "#";
    private String EVALUATE_CHAR = "=";
    private String GROUP_START_CHAR = "[";
    private String GROUP_END_CHAR = "]";
    private String file = "";
    private boolean loaded = false;
    private Map<String, ValueProperty> propMap = new HashMap<String, ValueProperty>();

    public ExtProperties(String file, String charset) {
        super();
        this.file = file;
        CHARSET = charset;
    }

    public ExtProperties(String file) {
        super();
        this.file = file;
    }

    public void load() {
        initProperties();
        loaded = true;
    }

    /**
     * Update a property to a new value.
     * 
     * @param key
     * @param value
     * @return Return true if update successfully.
     */
    public boolean update(String key, String value) {
        try {
            BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(this.file)), CHARSET));
            List<String> list = new ArrayList<String>();
            String line = "";
            while ((line = fr.readLine()) != null) {
                if (line.startsWith(COMMENT_CHAR)) list.add(line);
                else if (null == line || "".equals(line) || 0 == line.replaceAll("\\s+", "").length()) list.add(line);
                else if (line.startsWith(key + EVALUATE_CHAR)) {
                    list.add(line.substring(0, (key + EVALUATE_CHAR).length()) + value);
                } else list.add(line);
            }
            fr.close();
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(this.file), CHARSET);
            for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
                out.append(iter.next() + LF);
            }
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            //log.error(new StringBuffer("\n").append(e.getClass()).append(" : ").append(e.getMessage()).append("\n").append(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n")));
            return false;
        } finally {
            load();
        }
    }

    private void initProperties() {
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(this.file)), CHARSET));
            String line = null;
            String group = null;
            int lineCount = 0;
            propMap = new HashMap<String, ValueProperty>();
            while ((line = bufReader.readLine()) != null) {
                lineCount++;
                if (line.startsWith(COMMENT_CHAR)) continue;
                if (null == line || "".equals(line) || 0 == line.replaceAll("\\s+", "").length()) continue;
                if (isGroupLine(line)) {
                    group = getGroupName(line);
                    continue;
                }
                final String[] vals = line.split(EVALUATE_CHAR);
                //for line like this "=value"
                if (1 < vals.length && (null == vals[0] || "".equals(vals[0]))) continue;
                switch (vals.length) {
                case 2://for line like this "key=value"
                    propMap.put(((null != group) ? group + PROPERTY_SEPARATOR_CHAR : "") + vals[0].replaceAll("\\s+", ""), new ValueProperty(lineCount, vals[1]));
                    break;
                case 1://for line like this "key="
                    propMap.put(((null != group) ? group + PROPERTY_SEPARATOR_CHAR : "") + vals[0].replaceAll("\\s+", ""), new ValueProperty(lineCount, ""));
                    break;
                case 0://for line like this "key"
                    propMap.put(((null != group) ? group + PROPERTY_SEPARATOR_CHAR : "") + line, new ValueProperty(lineCount, ""));
                    break;
                default://for line like this "formula = z=x+y"
                    StringBuffer temp = new StringBuffer();
                    for (int i = 1; i < vals.length; i++) {
                        temp.append(vals[i]);
                    }
                    propMap.put(vals[0].replaceAll("\\s+", ""), new ValueProperty(lineCount, temp.toString()));
                    break;
                }
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            //log.error(new StringBuffer("\n").append(e.getClass()).append(" : ").append(e.getMessage()).append("\n").append(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n")));
        }
    }

    private String getGroupName(String line) {
        if (null == line) return "";
        if (!isGroupLine(line)) return "";
        return line.substring(GROUP_START_CHAR.length(), line.length() - GROUP_END_CHAR.length());
    }

    private boolean isGroupLine(String line) {
        if (null == line) return false;
        if (line.startsWith(GROUP_START_CHAR) && line.endsWith(GROUP_END_CHAR)) return true;
        return false;
    }

    public String get(String key) {
        if (!loaded) load();
        if (null == propMap.get(key)) return null;
        return propMap.get(key).getValue();
    }

    public String get(String group, String key) {
        return get(group + PROPERTY_SEPARATOR_CHAR + key);
    }

    public Map<String, String> getGroup(String group) {
        if (!loaded) load();
        Map<String, String> ps = new HashMap<String, String>();
        StringBuffer s = new StringBuffer(group + PROPERTY_SEPARATOR_CHAR);
        for (Iterator<Map.Entry<String, ValueProperty>> iter = propMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, ValueProperty> entry = iter.next();
            String key = entry.getKey();
            ValueProperty value = entry.getValue();
            if (key.startsWith(s.toString())) {
                ps.put(key.substring(s.toString().length(), key.length()), (null == value) ? null : value.getValue());
            }
        }
        return ps;
    }

    public int getLine(String key) {
        if (null == key) return -1;
        if (!loaded) load();
        for (Iterator<Map.Entry<String, ValueProperty>> iter = propMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, ValueProperty> entry = iter.next();
            String k = entry.getKey();
            ValueProperty v = entry.getValue();
            if (key.equals(k)) return (null == v) ? -1 : v.getLine();
        }
        return -1;
    }

    public int getLine(String group, String key) {
        if (null == key) return -1;
        if (!loaded) load();
        StringBuffer s = new StringBuffer(group + PROPERTY_SEPARATOR_CHAR + key);
        for (Iterator<Map.Entry<String, ValueProperty>> iter = propMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, ValueProperty> entry = iter.next();
            String k = entry.getKey();
            ValueProperty v = entry.getValue();
            if (s.toString().equals(k)) return (null == v) ? -1 : v.getLine();
        }
        return -1;
    }

    class ValueProperty {
        public ValueProperty(int line, String value) {
            super();
            this.line = line;
            this.value = value;
        }

        int line;
        String value;

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ValueProperty [line=" + line + ", value=" + value + "]";
        }
    }

}
