package com.architecture.prod.listeners;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.architecture.prod.module.CustomerModule;
import com.google.inject.Guice;
import javax.inject.Inject;
import com.google.inject.Injector;

public class AppInitilizer extends ResourceConfig {

	/**
	 * 
	 * @param serviceLocator
	 * @see if the code throws "Jersey should specify what constitutes a "suitable
	 *      constructor" - It is a common mistake to import com.google.Inject
	 *      instead of javax.inject.Inject. Currently,
	 *      org.jvnet.hk2.internal.Utilities.hasInjectAnnotation() checks for
	 *      javax.inject.Inject and throws an error if two constructors are
	 *      annotated with @Inject. I suggest throwing an error if a constructor is
	 *      annotated with @Inject that does not resolve to javax.inject.Inject in
	 *      order to help users catch errors. As far as I know, each class only uses
	 *      one type of @Inject at a time so we are unlikely to run into false
	 *      positives. https://github.com/jersey/jersey/issues/2390
	 */
	@Inject
	public AppInitilizer(final ServiceLocator serviceLocator) {
		// Instantiate Guice Bridge
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		Injector injector = Guice.createInjector(new CustomerModule());
		guiceBridge.bridgeGuiceInjector(injector);

	}

}
