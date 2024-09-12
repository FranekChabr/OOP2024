## Spis tresci
- [Adnotacje Spring Boot używane do budowy API](#Adnotacje-Spring-Boot-używane-do-budowy-API)
- [Useful dependencys](#Useful-dependencys)

## link
- [Spring Initializr](https://start.spring.io/)



# Adnotacje Spring Boot używane do budowy API
```java
@RestController
//Oznacza klasę jako kontroler REST, łącząc funkcje @Controller i @ResponseBody.
```

```java
@GetMapping
//Obsługuje żądania HTTP GET do przypisanej ścieżki.
```

```java
@PostMapping
//Obsługuje żądania HTTP POST.
```
```java
@PutMapping
//Obsługuje żądania HTTP PUT.
```

```java
@DeleteMapping
//Obsługuje żądania HTTP DELETE.
```

```java
@PatchMapping
//Obsługuje żądania HTTP PATCH.
```

```java
@RequestMapping
//Służy do przypisania ścieżki dla różnych metod HTTP (GET, POST, PUT, DELETE).
```

```java
@RequestParam
//Mapuje parametry zapytania HTTP do parametrów metody.
```

```java
@PathVariable
//Mapuje zmienne ścieżki do parametrów metody.
```

```java
@RequestBody
//Mapuje treść żądania HTTP do obiektu Java.
```

```java
@ResponseBody
//Wskazuje, że wynik metody powinien być zapisany bezpośrednio w odpowiedzi HTTP.
```

```java
@ExceptionHandler
//Przechwytuje i obsługuje wyjątki w kontrolerze.
```

```java
@CrossOrigin
//Pozwala na żądania CORS.
```


# Useful dependencys
## *Data base*
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>

<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

## *Web Starter*
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## *Validator (xd)*
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
## Javafx (jesli springboot laczony z javafx)
```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>20</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>20</version>
    </dependency>
    <!-- Inne zależności Spring Boot -->
</dependencies>
```
