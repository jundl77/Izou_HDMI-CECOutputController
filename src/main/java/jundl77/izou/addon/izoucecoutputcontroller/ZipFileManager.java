package jundl77.izou.addon.izoucecoutputcontroller;

import ro.fortsoft.pf4j.PluginWrapper;

/**
 * This class MUST be included in any plugin to work.
 * It shouldn't contain logic, the logic belongs in the AddOn classes
 */
public class ZipFileManager extends org.intellimate.izou.sdk.addon.ZipFileManager{
    public ZipFileManager(PluginWrapper wrapper) {
        super(wrapper);
    }
}
