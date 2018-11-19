package com.architecture.prod.model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * It stores the HashMap of (regionId, Hazelcast Map name)
 */

@Singleton
public final class CustomerRegionMapName {

	private HashMap<String, String> regionCutomerMapName; /** HashMap of (regionId, Hazelcast Map name) */
	private final List<RegionDBMap> listRegionDBMap; /** List of Object { regionId, DBName} */
	private final static String MAP_NAME_PREFIX = "CUSTOMER_";

	@Inject
	public CustomerRegionMapName(final List<RegionDBMap> listRegionDBMap) {
		this.listRegionDBMap = listRegionDBMap;
		initializeHashMap();
	}

	private void initializeHashMap() {
		/** Create HashMap from the list of Object{regionId, DBName} */
		regionCutomerMapName = (HashMap<String, String>) this.listRegionDBMap.stream()
		.collect(Collectors.toMap(RegionDBMap::getRegionId, region ->  MAP_NAME_PREFIX + region.getRegionId()));
	}

	public final HashMap<String, String> getRegionCutomerMapName() {
		return regionCutomerMapName;
	}
}
