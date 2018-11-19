package com.architecture.prod.model;

/**
 * To Store currently accessing user context
 */

public final class UserRegionContext {
	
	private static String regionId = "default";

	public static final String getRegionId() {
		return regionId;
	}

	public static final void setRegionId(String regionId) {
		UserRegionContext.regionId = regionId;
	}
}
