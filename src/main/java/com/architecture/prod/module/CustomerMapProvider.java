package com.architecture.prod.module;

import com.architecture.prod.model.Customer;
import com.architecture.prod.model.CustomerRegionMapName;
import com.architecture.prod.model.UserRegionContext;
import com.google.inject.Inject;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Create Map config according to the tenant
 * Provide Hazelcast IMap according to the user context
 */

public class CustomerMapProvider {

	private final HazelcastInstance hazelcastInstance;
	private final MapStoreConfig storeConfig;
	private final CustomerRegionMapName customerRegionMapName;

	@Inject
	CustomerMapProvider(final MapStoreConfig storeConfig, final CustomerRegionMapName customerRegionMapName) {
		this.storeConfig = storeConfig;
		this.customerRegionMapName = customerRegionMapName;
		this.hazelcastInstance = Hazelcast.newHazelcastInstance(createMapConfig());
	}

	public IMap<String, Customer> get() {
		return hazelcastInstance.getMap(customerRegionMapName.getRegionCutomerMapName().get(UserRegionContext.getRegionId()));
	}

	public Config createMapConfig() {
		Config config = new Config();
		customerRegionMapName.getRegionCutomerMapName().entrySet().forEach(regionMapName -> {
			MapConfig mapConfig = new MapConfig(regionMapName.getValue());
			mapConfig.setMapStoreConfig(storeConfig);
			config.addMapConfig(mapConfig);
		});
		return config;
	}
}
