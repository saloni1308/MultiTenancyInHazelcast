package com.architecture.prod.module;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.architecture.prod.model.UserRegionContext;
import com.architecture.prod.model.RegionDBMap;
import com.google.inject.Inject;
import com.mongodb.MongoClient;

/**
 * Provide DataStore instance on the basis of User context
 * if there is no user context set then create a dataStore instance of default database
 */
public class DataStoreProvider {

  private final List<RegionDBMap> regionDBMap;

  @Inject
  DataStoreProvider(final List<RegionDBMap> regionDBMap) {
    this.regionDBMap = regionDBMap;
  }

  public Datastore get() {
    final Morphia morphia = new Morphia();
    final String dbName = regionDBMap.stream()
        .filter(regionMap1 -> regionMap1.getRegionId().equals(UserRegionContext.getRegionId()))
        .findFirst()
        .orElse(new RegionDBMap("default")).getDbName();
   return morphia.createDatastore(new MongoClient(), dbName);
  }
}
