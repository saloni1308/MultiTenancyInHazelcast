package com.architecture.prod.model;

/**
 * Object to store regionId and its DBName
 */

public class RegionDBMap {

	private final String regionId;
	private final String dbName;

	public RegionDBMap() {
		this(null, null);
	}

	public RegionDBMap(final String regionId, final String dbName) {
		this.regionId = regionId;
		this.dbName = dbName;
	}

	public RegionDBMap(final String dbName) {
		this.dbName = dbName;
		this.regionId = null;
	}

	public String getRegionId() {
		return regionId;
	}

	public String getDbName() {
		return dbName;
	}
}

