# Ktor-Middleware

## A dead-simple middleware for Kotlin/Ktor

### Installation

```java
<dependency>
	<groupId>io.kida</groupId>
        <artifactId>Ktor-Middleware</artifactId>
        <version>1.0-SNAPSHOT</version>
</dependency>
```

### Usage

Inside your route handler:

```java
post("upload") {
	Middleware.evaluate(this, listOf(BearerAuthentication("secret_token"))) {
		call.resondText("Successfully authenticated")
	}, {
		call.respondText(it.gsonify(), ContentType.Application.Json)
	}
}
```
