# modular-boot

This demo project shows how to create a spring boot project that is made up of multiple maven
modules. The modules are 100% independent of each other including having their own database schemas.
The idea is to be able to pull out individual modules into separate microservices should the need
arise. 

## The C4 Model
 
The [C4 model](https://c4model.com/) is a system for visualising software architecture by 
modeling 4 levels of abstractions: Context, Containers, Components and Classes. 

In the [C4 model](https://c4model.com/) "a component is simply a grouping of related functionality 
encapsulated behind a well-defined interface". Components are defined as Java packages with 
a small set of public interfaces,classes,enums that are the interface and a collection of 
package protected implementations. Spring dependency injection is used to autowire implementations
of the interfaces where required. Read Simon Brown blog post 
[Package by component and architecturally-aligned testing](http://www.codingthearchitecture.com/2015/03/08/package_by_component_and_architecturally_aligned_testing.html)

In the [C4 model](https://c4model.com/) "a container represents something that hosts code or data. 
A container is something that needs to be running in order for the overall software system 
to work." In the context of this app a container is a collection of related components that might
be run together in a single process. Typically containers correspond to .jar files produced by
maven modules, the next section source code directory layout and organization into modules.

Below are a set of suggested resources for learning more about the C4Model. You don't need 
to understand C4 model to understand how this project is layed out from a SpringBoot prespective
but if you are looking an overall architectural approach you can follow with a modular boot 
application then C4 Model is a great starting point.

* [C4 Model WebSite](https://c4model.com/)
* [Visualising software architecture with the C4 model](https://www.youtube.com/watch?v=x2-rSnhpw0g)
* [Software architecture as code](https://www.youtube.com/watch?v=oDpdaXt0HQI)

## Code Repo Organization  

The code is organized into a collection of maven modules.  

### Configuration and Packaging Maven Modules 

The following maven modules are used to configure and bootstrap the application. They should have 
no components with business logic. 

* __parent__ location of the parent pom that defines a consistent classpath for all the maven
  modules 
* __core__ contains a collection of common classes that don't contain any business logic, all
  other modules depend on core.
* __config__ contains all the configuration files required to configure the spring application 
 context. It hosts the `application.yml` files and other cross cutting configuration objects. 
 Maven modules containing business logic depend on config so that they can bootstrap an 
 application context with autowired dependencies.
* __app__ contains the `@SpringBootApplication` class and pulls in all the required modules via
  maven dependencies. it should not have any business logic components but it contains end-to-end
  integration acceptance tests.
  
### Business Logic Containers and Maven Modules

Business logic for the application is located in the directories below. Each directory is a 
maven module that contains a collection of components. Maven modules below map to containers
in the [C4 Model](https://c4model.com)

* __email__ module contains a component for sending emails asynchronously.
* __security__ module contains a collection of components for authentication and authorization. 
 
All the components in a module share a database schema that is managed using Flyway. 
A module's components should only read write tables from the modules schema only. If a module 
needs to read/write data from another module it should go through the components of the module
it is collaborating with so that it is possible to extract a module into a microservice 
with a private database if the need arises. 

Components in different modules can call each other using simple java method calls. A database
transaction can span calls between components. Should a module be turned into a microservice 
the callers of the module will likely need to be refactored to work with eventual consistency and 
compensating transactions across multiple transactions in two different databases. So the effort
to split a module into a microservices is not trivial but also not super hard. 

# Maven Configuration

This project uses Apache Maven as the build system. The `pom.xml` explicitly sets all maven plugin 
versions so that the build is reproducible without modification across operating systems on 
a developer machine or a build server. The maven wrapper is used so that the host machine
does not require maven to be installed for compiles to work. 

Running `./mvnw clean verify` will build the frontend backend and produce a single SpringBoot
deployable .jar file.

JDK 11 is required to build and compile the application, see instructions below on how to 
configure maven with the location of JDK 11.

## Maven Toolchains

The maven [toolchains plugin](https://maven.apache.org/plugins/maven-toolchains-plugin) is used
to select the version of the JDK required. Below an is an example `toolchains.xml' file.

```xml
<toolchains>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>11</version>
            <vendor>openjdk</vendor>
            <id>openjk11</id>
        </provides>
        <configuration>
            <jdkHome>/opt/java/openjdk</jdkHome>
        </configuration>
    </toolchain>
</toolchains>
```

 **You will need at add a `toolchains.xml` file to your `.m2/` folder that sets the path 
  to JDK 11 installation on your machine**. 
