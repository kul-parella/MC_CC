MC_CC
Run the springboot application and access the following URLs 

http://localhost:8080/connected?origin=Boston&destination=Newark

will return yes

http://localhost:8080/connected?origin=Boston&destination=Philadelphia

will return yes

http://localhost:8080/connected?origin=Philadelphia&destination=Albany

will return no

for any other orgin and destination other than xyz and xyz (which is an exception case)

will return no

To raise an exception from postman or other RESTFUL testing tool, plz hit the url

http://localhost:8080/connected?origin=xyz&destination=xyz

This will, raise the exception, and the exception will be handled by controller advice method and to the response headers an error code 0011 and error message are added

id →1100 message →citiesException

Junit test cases are handled using mokitorunner and springrunner, which makes a decent code coverage
