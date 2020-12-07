# Ebay Task - Sales Statistics
Rest Microservice which allows to add a new sales amount, and retreive statistics regarding the transactions of the last 60 seconds



#prerequisites

*Java 8 + jdk installed

*External Java pakages the service uses 

	[SpringFramework, google.common.guava, com.google.gson.Gson, Junit4, Lombock]



# How to run project : 

1. clone the project from the git repository : ""
2. make sure you are on "master" branch
3. make sure you configure the wanted properties in the : resources/EbayTaskConfig.json file
 (RelativePath : /src//main/resources/EbayTaskConfig.json)
4. open the app in any IDE (I used inteliJ)
5. run the SalesStatisticsAPplication main class 
6. the service now should be up and running


#How to make sure your application works on InteliJ:

1. open the project
2. open file -> project reference
3. Go to Project and set your sdk to java 8 (with Product Language -> SDK default)
4. Go to library add a new maven library -> 
	*"com.google.code.gson:gson:2.8.6"
	*"com.google.guava:guava:18.0"
	*"junit-4.10-extended:1.0.4"
5. Go to project settings/preferences -> plugins
	*install lombock.
6. Run Application from SalesStatisticsAPplication main class 

