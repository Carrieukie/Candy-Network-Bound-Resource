# Candy

A relatively small and simple application that consumes this [api](https://vast-brushlands-23089.herokuapp.com/main/api/) which contains a list of candies and their prices. I built this demo following the MVVM architecture, Kotlin Flows, (Uni-directional data flow), dagger hilt, viewmodel, Room and Kotlin coroutines.

## Network-Bound-Resource algorithm

* The network bound resource is an algorithm that provides an easy function to fetch resource from both the database and the network. Depending on your needs, you can either :
    * Always fetch data from your api but display data from your cache as placeholder data as the network operation is going on. 
        - It is normally a good idea to prevent a user from looking at a blank loading screen.
    * Just display data from the cache and not make a network request if there's any data in the cache.
        - You only wanna load data from your api once, if and only if there's no data in the cache.
    
You can easily achieve the above use cases using this algorithm, by making just a few adjustments. It works well with the Android architecture component. 

## Description
* This simple application 

### Tech-stack

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a cross-platform, statically typed, general-purpose programming language with type inference.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - handle the stream of data asynchronously that executes sequentially.
    * [Dagger hilt](https://dagger.dev/hilt/) - a pragmatic lightweight dependency injection framework.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Room](https://developer.android.com/topic/libraries/architecture/room) - a persistence library provides an abstraction layer over SQLite.
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - is an observable data holder.
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way.

* Architecture
    * Clean Architecture
    * MVVM - Model View View Model
`
* Gradle
    * Plugins
        * [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - creates convenient tasks in your Gradle project that run ktlint checks or do code auto format.
        * [Detekt](https://github.com/detekt/detekt) - a static code analysis tool for the Kotlin programming language.
        * [Spotless](https://github.com/diffplug/spotless) - format java, groovy, markdown and license headers using gradle.
        * [Dokka](https://github.com/Kotlin/dokka) - a documentation engine for Kotlin, performing the same function as javadoc for Java.
        * [jacoco](https://github.com/jacoco/jacoco) - a Code Coverage Library

## Code

### Network Bound Resource

```kotlin
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    //First step, fetch data from the local cache
    val data = query().first()

    //If shouldFetch returns true,
    val resource = if (shouldFetch(data)) {

        //Dispatch a message to the UI that you're doing some background work
        emit(Resource.Loading(data))

        try {

            //make a networking call
            val resultType = fetch()

            //save it to the database
            saveFetchResult(resultType)

            //Now fetch data again from the database and Dispatch it to the UI
            query().map { Resource.Success(it) }

        } catch (throwable: Throwable) {

            //Dispatch any error emitted to the UI, plus data emmited from the Database
            query().map { Resource.Error(throwable, it) }

        }

        //If should fetch returned false
    } else {
        //Make a query to the database and Dispatch it to the UI.
        query().map { Resource.Success(it) }
    }

    //Emit the resource variable
    emitAll(resource)
}
```
## Explanation 

  ### This is a generic
  * This is a generic function and that means it can work with any type of data,
  * ResultType is the data type loaded the local cache. Can be any thing, a list or any object.
  * RequestType is the data type loaded from the network. Can be any thing, a list or any object.
 
  ### ARGUMENT PARAMETERS
   * This function takes in four argument parameters which are functions.
 
  ### query
   * pass in a function that loads data from your local cache and returns a flow of your specified data type <ResultType>
   * This function returns a Flow<ResultType>
   
  ### fetch
   * This is a suspend function, that loads data from your rest api and returns an object of <RequestType>
   * This function returns returns <RequestType>

 
  ### saveFetchResult
   * THis is a function that just takes in <RequestType> (The data type got from the network) and saves it in the local cache.
   * This function returns returns Unit

 
  ### shouldFetch 
   * This is a function returns a Boolean.
   * pass in a function that has the logic to whether the algorithm should make a networking call or not.
   * In this case, this function takes in data loaded from @param query and determines whether to make a networking call or not. This can vary with your implementation however, say fetch depending on the last time you made a networking call....e.t.c.
 

### The repository

``` Kotlin


class MainRepository @Inject constructor(
    private val database: Appdatabase,
    private val apiService : ApiService,
) {

    private val weatherDao = database.charactersDao()

    fun getCandys() = networkBoundResource(
   
        // pass in the logic to query data from the database
        query = {
            weatherDao.getCandy()
        },
        // pass in the logic to fetch data from the api
        fetch = {
   
            //This is to show a progress bar
            delay(2000)
            apiService.getCandy()
        },

        //pass in the logic to save the result to the local cache
        saveFetchResult = { candys ->
            database.withTransaction {
                weatherDao.deleteAllCandy()
                weatherDao.insertCandy(candys)
            }
        },

        //pass in the logic to determine if the networking call should be made
        shouldFetch = {candys ->
            candys.isEmpty()
        }
    )
    
}
    
 ```
 


