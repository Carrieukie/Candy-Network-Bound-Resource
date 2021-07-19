# Network-Bound-Resource

## Description

* This function provides an easy interface to fetch resource from both the database and the network. Depending on your needs, you can easiy tweek it to: 
    * Either always fetch data fromying the network whilst displaying data from the cache as placeholder data. 
        - It is normally a good idea to prevent a user from looking at a blank loading screen.
    *Just display data from the cache and not make a network request if there's any data in the cache.
        - Say data from your rest api is static, you only wanna load it once, if and only if there's no data in database.
You can easily achieve the above use cases using this algorithm, by making just a few adjustments. It works well with the Android architecture component. 

Enough talking, let's jump into the code.

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


