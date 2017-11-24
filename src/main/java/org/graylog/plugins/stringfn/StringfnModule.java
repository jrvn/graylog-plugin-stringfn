package org.graylog.plugins.stringfn;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import org.graylog.plugins.pipelineprocessor.ast.functions.Function;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

/**
 * Extend the PluginModule abstract class here to add you plugin to the system.
 */
public class StringfnModule extends PluginModule {
	/**
	 * Returns all configuration beans required by this plugin.
	 *
	 * Implementing this method is optional. The default method returns an empty
	 * {@link Set}.
	 */
	@Override
	public Set<? extends PluginConfigBean> getConfigBeans() {
		return Collections.emptySet();
	}

	/*
	 * Register your plugin types here.
	 *
	 * Examples:
	 *
	 * addMessageInput(Class<? extends MessageInput>); addMessageFilter(Class<?
	 * extends MessageFilter>); addMessageOutput(Class<? extends
	 * MessageOutput>); addPeriodical(Class<? extends Periodical>);
	 * addAlarmCallback(Class<? extends AlarmCallback>); addInitializer(Class<?
	 * extends Service>); addRestResource(Class<? extends PluginRestResource>);
	 *
	 *
	 * Add all configuration beans returned by getConfigBeans():
	 *
	 * addConfigBeans();
	 */
	@Override
	protected void configure() {
		addMsgProcFunc(DecToHexFunction.NAME, DecToHexFunction.class);
		addMsgProcFunc(HexToDecFunction.NAME, HexToDecFunction.class);
		addMsgProcFunc(StrLenFunction.NAME, StrLenFunction.class);
	}

	protected void addMsgProcFunc(String name, Class<? extends Function<?>> functionClass) {
		addMsgProcFunc(binder(), name, functionClass);
	}

	public static MapBinder<String, Function<?>> processorFunctionBinder(Binder binder) {
		return MapBinder.newMapBinder(binder, TypeLiteral.get(String.class), new TypeLiteral<Function<?>>() {
		});
	}

	public static void addMsgProcFunc(Binder binder, String name, Class<? extends Function<?>> functionClass) {
		processorFunctionBinder(binder).addBinding(name).to(functionClass);
	}
}
