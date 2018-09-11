# some
Hi, this is automation for facebook UI automation.
For further work with it , please prepare your environment:
Java 8,mvn 3.0 or above, allure 2.0 or above
Framework support 2 browsers: chrome and firefox
commands:
'mvn clean install -DskipTests' to build framework
'mvn clean test site -Dtest.env=chrome' run tests, which are stored in Suite.xml file on Google Chrome browser.
and generate allure-report in target\site\allure-maven-plugin/index.html
Please, make sure that "<parameter name="browser" value="${test.env}"/>" doesn`t commented.
