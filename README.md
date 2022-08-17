# Automated test cases with cucumber and selenium webdriver #

This project is an example of UI automated functional test for https://todomvc.com/ home page and search using Selenium and Cucumber.

Test scenarios are described in the feature files located here ./src/test/resources/com/automatedtest.

## Installation ##

You need to have [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed along with [maven](https://maven.apache.org/download.cgi).

To run the tests locally with Chrome, install ChromeDriver from [here](http://chromedriver.chromium.org), add its location should be in `/src/test/resources/executables/chromedriver.exe`.

To run the tests locally with Firefox, install GeckoDriver from [here](https://github.com/mozilla/geckodriver/releases) and add its location should be in add its location should be in `/src/test/resources/executables/`.

To install all dependencies, run 

```console
$ mvn clean install
```

## Running tests ##

```console
$ mvn test
```

By default, tests will run on Chrome. To change that, specify `-Dbrowser={browser}` where `{browser}` is either `chrome` or `firefox`. 
If you haven't added the chrome driver path to your local variables, 
1. you can add it directly in the run command with the option `-Dwebdriver.chrome.driver=path/to/the/driver`.
or 
2. you can add firefox driver in the run command with option `-Dwebdriver.gecko.driver=path/to/the/driver`.

You can also select specific scenarios to execute using `-Dcucumber.options="--tags @your_tag"`. More info about tags and how to combine them [here](https://github.com/cucumber/cucumber/tree/master/tag-expressions).
