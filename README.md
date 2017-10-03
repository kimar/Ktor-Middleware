# Ktor-Middleware

## A dead-simple middleware for Kotlin/Ktor

### Installation

```
<dependency>
	<groupId>io.kida</groupId>
        <artifactId>Ktor-Middleware</artifactId>
        <version>1.0-SNAPSHOT</version>
</dependency>
```

### Usage

Inside your route handler:

```
post("upload") {
	Middleware.evaluate(this, BearerAuthentication("secret_token")) {
		call.resondText("Successfully authenticated")
	}, {
		call.respondText(it.gsonify(), ContentType.Application.Json)
	}
}
```
