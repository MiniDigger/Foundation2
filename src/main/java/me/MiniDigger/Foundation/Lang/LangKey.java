package me.MiniDigger.Foundation.Lang;

import java.util.ArrayList;
import java.util.List;

public class LangKey {
    private final String parentName;
    private final String name;
    private final String defaultValue;

    public LangKey(final String parentName, final String name, final String defaultValue) {
        this.parentName = parentName;
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getFullName() {
        return getParentName() + "." + getName();
    }

    public String getParentName() {
        return parentName;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public static class Lang extends LangKeyProvider {
        public static final String NAME = "lang";
        public static final LangKey COULD_NOT_LOAD_FILE = new LangKey(NAME, "could_not_load_file", "Could not load langfile %0%!");
        public static final LangKey LOAD = new LangKey(NAME, "load", "Loaded %0% langs");
        public static final LangKey LOAD_STORAGE = new LangKey(NAME, "load_storage", "Loaded %0% keys in lang %1%");
        public static final LangKey SAVE_DEFAULT = new LangKey(NAME, "save_default", "Saving default lang to %0%");

        @Override
        public List<LangKey> values() {
            final List<LangKey> result = new ArrayList<>();

            result.add(LOAD);
            result.add(COULD_NOT_LOAD_FILE);
            result.add(LOAD_STORAGE);
            result.add(SAVE_DEFAULT);

            return result;
        }
    }

    public static class Module extends LangKeyProvider {
        public static final String NAME = "module";

        public static final LangKey ERROR_NO_MAIN = new LangKey(NAME, "error_no_main", "Cannot find main class %0%");
        public static final LangKey ERROR_NO_EXTEND = new LangKey(NAME, "error_no_extend", "Main class %0% does not extend Module");
        public static final LangKey ERROR_NO_CONSTRUCTOR = new LangKey(NAME, "error_no_constructor", "No public constructor in main class %0%");
        public static final LangKey ERROR_ABNORMAL_TYPE = new LangKey(NAME, "error_abnormal_type", "Abnormal plugin type for main class %0%");

        @Override
        public List<LangKey> values() {
            final List<LangKey> result = new ArrayList<>();

            result.add(ERROR_NO_MAIN);
            result.add(ERROR_NO_EXTEND);
            result.add(ERROR_NO_CONSTRUCTOR);
            result.add(ERROR_ABNORMAL_TYPE);

            return result;
        }
    }

    public static class TEST extends LangKeyProvider {
        public static final String NAME = "test";

        public static final LangKey TEST = new LangKey(NAME, "test", "Just a jUnit test %0%");
        public static final LangKey TEST_VARS = new LangKey(NAME, "test_vars", "Just %0% a %1% bunch %2% of %3% vars %4%");

        @Override
        public List<LangKey> values() {
            final List<LangKey> result = new ArrayList<>();

            result.add(TEST);
            result.add(TEST_VARS);

            return result;
        }
    }

    public static List<LangKey> values() {
        final List<LangKey> result = new ArrayList<>();

        result.addAll(new Lang().values());
        result.addAll(new TEST().values());
        result.addAll(new Module().values());
        result.addAll(LangHandler.getInstance().getAdditionalLangKeys());

        return result;
    }

    public static LangKey valueOf(final String key) {
        for (final LangKey k : values()) {
            if (k.getFullName().equals(key)) {
                return k;
            }
        }
        return null;
    }
}