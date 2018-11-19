package com.architecture.prod.module;


import com.architecture.prod.cache.CustomerMapstore;
import com.google.inject.Inject;
import com.hazelcast.config.MapStoreConfig;
import javax.inject.Provider;

/**
 * Provides MapStoreConfig
 * @Called from CustomerCacheModule
 */
public class MapStoreConfigProvider implements Provider<MapStoreConfig> {

	private final CustomerMapstore customerMapstore;

	@Inject
	public MapStoreConfigProvider(final CustomerMapstore customerMapstore) {
		this.customerMapstore = customerMapstore;
	}
	
	@Override
	public MapStoreConfig get() {
		MapStoreConfig mapStoreConfig = new MapStoreConfig();
		mapStoreConfig.setImplementation(customerMapstore).setEnabled(true).setWriteDelaySeconds(0);
		return mapStoreConfig;
	}

}
