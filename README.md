tag
====

Objectives
----------

The aim of this project is to provide a simple HTTP API to expose information about a tag (a string).

Features
--------

* GET information about a tag
* GET a list of tags matching a search query
* POST/PUT information about a tag

Quick installation and running
------------------------------

Requires Java (version 6+) and Maven (version 2+).

Run `mvn clean install` at the root of the project. This will build a "fat" JAR (shaded JAR containing all its dependencies).

You can run the application by `java -jar target/tag-1.0-SNAPSHOT.jar server src/main/resources/configuration.yml` at the root of the project.

Tests
-----

Run `mvn test` at the root of the project.

Examples
--------

Data model
----------

Tag:

* slug
* title
* plain text description
* list of sameAs URIs
* list of broader tags
