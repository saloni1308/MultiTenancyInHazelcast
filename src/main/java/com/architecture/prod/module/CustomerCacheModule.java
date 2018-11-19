package com.architecture.prod.module;

import com.architecture.prod.cache.CustomerCacheOperation;
import com.architecture.prod.cache.CustomerMapstore;
import com.architecture.prod.model.CustomerRegionMapName;
import com.google.inject.AbstractModule;
import com.hazelcast.config.MapStoreConfig;

/**
 * Guice Bindings related to Hazelcast IMap are here.
 * @CalledFrom CustomerModule.java
 *
 */
public class CustomerCacheModule extends AbstractModule {

  @Override
  protected void configure() {
	  bind(CustomerRegionMapName.class).asEagerSingleton();
	  bind(CustomerMapProvider.class);
    bind(CustomerCacheOperation.class);
    bind(CustomerMapstore.class);
    bind(MapStoreConfig.class).toProvider(MapStoreConfigProvider.class);
    
  }
}
