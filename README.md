# Practice 03

## Intro
Several goals for this homework:
* You will become a master of JPA annotations
* JPA for Spring won't have secrets for you anymore
* Spring Data JPA magic
* Handle a layered conception
* Build a multi-module Maven application

We will print, in a web page, the content of a DB. This DB contains students, each student have several courses and several grades.

## DB Initialization
* Create a new schema named `yncrea_pw03`
* We assume that your DB credentials are `root:root`

## pw03-core
### Dependencies
For this module, you have to declare, in the `pom.xml` file, the following dependencies.

|groupId   |artifactId      |  version |
|----------|-------------|------|
| org.springframework.data | spring-data-jpa | 1.10.6.RELEASE | 
| org.hibernate | hibernate-entitymanager | 5.1.3.Final | 
| mysql | mysql-connector-java | 5.1.40 | 
| com.jolbox | bonecp | 0.8.0.RELEASE | 
| javax.inject | javax.inject | 1 | 

### Entities
#### Student
In the `yncrea.pw03.config.entity` package, create a `Student` class.

This new class has the following attributes:
* a string `lastname`
* a string `firstname`
* a list of `Course` named `courses`

Declare 2 constructors, one with no argument and another with the name arguments.

#### Course
In the `yncrea.pw03.config.entity` package, create a `Course` class.

This new class has the following attributes:
* a boolean `validated`
* a string `name`
* a `Student` named `student`
* a list of `Work` named `works`

Declare 2 constructors, one with no argument and another a `Student` argument and a name.

#### Work
In the `yncrea.pw03.config.entity` package, create a `Work` class.

This new class has the following attributes:
* a string `name`
* a `Course` named `course`
* a primitive integer `grade`

Declare 2 constructors, one with no argument and another  with all arguments.

### First annotations
* For each yncrea.pw03.config.entity, just add an attribute `id` of type `long`. This attribute will have a setter but no getter.
* Add the JPA annotations in your 3 entities, joints and cascades should be declared!

### DAOs
Create the `StudentDAO` interface in the `yncrea.pw03.dao` package. this new interface should extend `JpaRepository<Student,Long>`.

In this interface, add a `findByLastname` method which returns a list of students and takes a string parameter. 

You now understood how to write `CourseDAO` and `WorkDAO` ;)

### Services
Create the `StudentService` interface in the `yncrea.pw03.service` package.
Declare the following methods:
* `findByLastname` which returns a list of students and takes a string parameter.
* `saveStudent`  which returns nothing but takes a student parameter.
* `findAll` which returns a list of students 

Create the corresponding implementation `StudentServiceImpl` in the `yncrea.pw03.service.impl` package.
You will have to add the `@Named` and `@Transactional` annotations on this class.

Inject the StudentDAO like this :
```
 @Inject
 private StudentDAO studentDAO;
```

When you implement the methods of this service, just call the corresponding methods of the DAO.

### Wiring
* In the `yncrea.pw03.config` package, create a `AppConfig` class.
* Annotate it with `@Configuration`
* Annotate it with `@ComponentScan`
  * its basePackage value is `yncrea.pw03.service`
* Declare a bean of type `java.util.Properties` named `dbProperties`.
  * add the following properties inside :
    * driverClass = com.mysql.jdbc.Driver
    * jdbcUrl = jdbc:mysql://localhost:3306/yncrea_pw03
    * username = root
    * password = root
    
### Fill your DB
* Execute the `main` method inside the "pw03-app" module and check if your database is filled with data.

## pw03-web
Complete the Maven module called `pw03-web`, its packaging should be `war`.

### Dependencies
|groupId   |artifactId      |  version | scope |
|----------|-------------|------|------|
| ${project.groupId} | pw03-core | ${project.version} | |
| javax.servlet | javax.servlet-api | 3.1.0 | provided|

### Servlet
* Create a `StudentServlet` class inside the `yncrea.pw03.controller`package.
* It should respond to the "/students" urlPattern.
* Override the `init`, `doGet` and `destroy` methods.
* Add a `StudentService` attribute inside the servlet
* Add a `ConfigurableApplicationContext` attribute inside the servlet
* The `init` method initializes the Spring context, retrieves the `StudentService` bean then stores the context and the bean in the attributes declared above 
* The `destroy` method call the `close()` method of the context.
* The `doGet` method calls the `findAll()` method of the service, sets the `students` attribute in the request then displays `Students.jsp`

## That's it!
You can now deploy pw03-web in a Tomcat and see the list of students, fetched from the DB!

## Bonus for superstars
Another JSP is provided : `Students2.jsp`

Try to display this new template. You should get a `LazyInitException` because this template, which displays the students with their courses full of works, tries to consume the courses of the students outside the transaction!

A few tips:
* do not forget that the default fetch type of `@ManyToOne` is EAGER
* do not forget that the default fetch type of `@OneToMany` is LAZY
* Try to customize the fetch type of your annotations in courses to satisfy this query in a transaction
  * `SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses`
* Modify your `StudentDAO` in order to take into account the query above.



