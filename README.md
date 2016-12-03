# JCAJ
Java criterion API for Jason file based Data Base query operations.

### parent project
###### _see the pom.xml in project root_

01. JCAP-API - _criterion API for json queries_
02. jcaj-southbank-impl - _southbank implementation of JCAJ-API_

#### 01. JCAP-API
Contains java API clases to create criterias to perform SQL queries on json db.

#### 02. jcaj-southbank-impl
Contains implementation of JCAJ-API.

###### _depends on_
- jcaj-api
- gson-simple-1.1.1
- jackson-core-2.8.1
- jackson-databind-2.8.1
- Junit-4.11

## build 
mvn clean install

## test jcaj-southbank-impl
mvn test

###### see the test class src/test _southbank.org.json.task.QueryTest_
standard out will print the main query result.
result.json file wille be created in target/ 



