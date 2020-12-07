# Ebay Task - Sales Statistics
Rest Microservice which allows to add a new sales amount, and retreive statistics regarding the transactions of the last 60 seconds.



# prerequisites

*Java 8 (Or later version)

*External Java pakages the service uses :

	[SpringFramework, google.common.guava, com.google.gson.Gson, Junit4, Lombock]



# How to run project : 

1. Clone the project from the git repository : "https://github.com/Nirch10/salesstats_nirc"
2. Make sure you are on "master" branch
3. Make sure you configure the wanted properties in the : resources/EbayTaskConfig.json file
 	(RelativePath : /src//main/resources/EbayTaskConfig.json)
4. Open the app in any IDE (I used inteliJ)
5. Run the SalesStatisticsAPplication main class 
6. The service now is up and running


# How to make sure your application works on InteliJ:

1. open the project
2. open file -> project reference

	2.1. Go to Project and set your sdk to java 8 (with Product Language -> SDK default)
	
	2.2. Go to library add a new maven library -> 
		*"com.google.code.gson:gson:2.8.6"
		*"com.google.guava:guava:18.0"
		*"junit-4.10-extended:1.0.4"
		
3. Go to project settings/preferences ->

	3.1 Go to plugins and install if not already install lombock (Requires the IDE to restart)
	
4. Run Application from SalesStatisticsAPplication main class 

