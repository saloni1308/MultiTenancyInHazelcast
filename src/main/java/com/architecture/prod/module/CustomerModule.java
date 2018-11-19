package com.architecture.prod.module;

import java.util.List;

import com.architecture.prod.model.RegionDBMap;
import com.architecture.prod.repository.CustomerRepository;
import com.architecture.prod.service.CustomerService;
import com.architecture.prod.service.CustomerServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.ServletModule;

/**
 * This class is called before the application is initializes. All the guice
 * bindings are initialized here and all the modules are installed.
 * 
 * @CalledFrom AppInitilizer.java
 * @category Module
 *
 */
public class CustomerModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ServletModule());
		install(new CustomerCacheModule());
		bind(CustomerService.class).to(CustomerServiceImpl.class);
		bind(CustomerRepository.class);
		bind(DataStoreProvider.class);
		bind( new TypeLiteral<List<RegionDBMap>>(){}).toProvider(RegionDBMapProvider.class);
	}
}
