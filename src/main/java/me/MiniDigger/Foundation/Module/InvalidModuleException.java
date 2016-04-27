package me.MiniDigger.Foundation.Module;

import me.MiniDigger.Foundation.Lang.Lang;
import me.MiniDigger.Foundation.Lang.LangKey;
import org.apache.commons.lang.ArrayUtils;

public class InvalidModuleException extends Exception {

    private static final long serialVersionUID = -5545845044454915809L;

    public InvalidModuleException(final LangKey key, final Exception ex, final String... args) {
        super(Lang.translate(key, (String[]) ArrayUtils.add(args, ": " + (ex != null ? (ex.getClass().getName() + ": " + ex.getMessage()) : ""))));
    }
}