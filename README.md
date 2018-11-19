# Handling Multi Tenancy at DB and Caching Level.
This repository shows a small demo of handling multi tenancy at Cache and Database level.
 
## What is Multi Tenancy?
Multi Tenancy refers to single instance of application is running which servers multiple tenants. A tenant is a group of users who share a common access with specific privileges to the software instance. for example: users belong to one region can be a tenant.

## Pros of Multi-Tenant Application 
1. Single Deployment
2. Reduced Overall Costs
3. Increased Scalability
 
## Concerns of Multi-Tenant Application
1. The multi-tenant system should be intelligent enough to identify the client or source where the request is originated.
2. User of a region should be able to access data of that region.
3. Cache data should not be shared among clients. 
4. Log Identification for each client.
 
## Technology Stack
1. Java
2. Jersey (2.X)
3. [Hazelcast](https://docs.hazelcast.org/docs/3.3/manual/html-single/hazelcast-documentation.html#introduction "Hazelcast Introduction")
4. [MongoDB](https://docs.mongodb.com/ "MongoDB Docs")
5. [Morphia](http://morphiaorg.github.io/morphia/1.3/ "Getting Started Morphia") to fire Mongo DB queries.
6. [Guice](https://github.com/google/guice) for dependency injection
 
## Three strategies to handle multi tenancy
1. Using tenant-specific fields in the database and filter out data based on this field for each region.
2. Create Collection/Table for each region in the same database.
3. Create different database for each region.
 
## I have tried implementing third strategy
1. Size of data may differ for each region. Through this solution it will be easy to work according to the DB size.
2. Scaling out your application will be easier for each database separately.
3. Schema of data can be different for a region. By creating separate database for each region, it can be handled.
4. Maintaining versions/backup of data per tenant will be easier.
5. There can be a legal restriction for the separation of data for different region.
 
## Scenario
Many countries like US, EU, India etc. put a restriction on the organizations that they cannot share, move or transfer the data to an entity outside the country. So, the organizations need to isolate the requests, data of the customers depending on their region.
 
### Solution
1. USER login, logout feature is not implemented . So, to identify the user context or the region of the user, rest endpoint is being created to set the user context.
2. Create a Map where key will be region and value will be database name to store the DB Name for the corresponding region.
3. Create Hazelcast map for each region, give Hazelcast map Name like "CUSTOMER_REGION1" map to store data of customer.
4. According to the user context identify the DB Name and Hazelcast map name from the Map created above (i.e. Tenant -> DB Name and Tenant -> Hazelcast map name).
5. After identifying the DB Name and Hazelcast Map name send the request to the corresponding Hazelcast map and DB.
