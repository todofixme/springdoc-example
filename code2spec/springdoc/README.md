# Generating OpenAPI specs with springdoc-openapi

This example showcases the usage of [springdoc-openapi](https://springdoc.org/) in a Spring Boot / Kotlin application.

## Current state

- [x] simple model
- [x] simple path
- [ ] complex model
- [ ] enums
- [ ] inheritence
- [ ] complex paths
- [ ] header
- [ ] references
- [ ] security

## How-to run
```shell
./gradlew bootRun
```

## How-to access auto-generated specification
* [Swagger UI](http://localhost:7001/swagger-ui.html)
* [OpenAPI spec](http://localhost:7001/v3/api-docs)

## How-to test
* use the REST-API with [some example requests](src/test/http/authors.http)
* run all tests:
```shell
./gradlew test
```
