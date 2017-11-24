package org.graylog.plugins.stringfn;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

public class StringfnMetaData implements PluginMetaData {

    @Override
    public String getUniqueId() {
        return "org.graylog.plugins.stringfn.StringfnPlugin";
    }

    @Override
    public String getName() {
        return "Stringfn Plugin";
    }

    @Override
    public String getAuthor() {
        return "jrvn";
    }

    @Override
    public URI getURL() {
        return URI.create("https://github.com/jrvn/graylog-plugin-stringfn/");
    }

    @Override
    public Version getVersion() {
        return Version.fromPluginProperties(
                getClass(),
                "org.graylog.plugins.graylog-plugin-stringfn-functions/graylog-plugin.properties",
                "version",
                Version.from(0, 1, 0)
        );
    }

    @Override
    public String getDescription() {
        return "Plugin includes various conversion pipeline functions";
    }

    @Override
    public Version getRequiredVersion() {
        return new Version(2, 3, 0);
    }

    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }
}
