# Ktor-Middleware

## A dead-simple middleware for Kotlin/Ktor

### Installation

### pom.xml

```java
<dependency>
	<groupId>com.github.kimar</groupId>
        <artifactId>Ktor-Middleware</artifactId>
        <version>0.1.0</version>
</dependency>
```

### build.gradle

```java
compile 'com.github.kimar:Ktor-Middleware:0.1.0'
```

### Usage

#### Inside your route handler

Let's assume you've got an `upload` route you'd like to secure using `BearerAuthentication`, one way would be to install this routing using `Ktor-Middleware` which will then, in turn, automatically filter all failed middlewares and report and error accordingly, e.g.:

```java

fun Application.module() {
	...
	install(Routing) {
		upload(listOf(BearerAuthentication("secret_token"))
	}
}

fun Routing.upload(middlewares: List<Middleware<Unit, HttpStatus>>): Route {
    return post("upload") {

        val result = Middlewares.evaluate(this, middlewares)
        when(result.success) {
            true -> success(call)
            else -> call.respondText(result.result!!.gsonify(), ContentType.Application.Json)
        }
    }
}

private fun success(call: ApplicationCall) {
	...
}
```
