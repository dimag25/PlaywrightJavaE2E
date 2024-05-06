# 🎭 Playwright JAVA Project 🎭



## 📃 Web apps tests in this project

* [GraphQL and REST API for Testing and Prototyping](https://gorest.co.in/ )
* [EA Employee Web App for Web UI automation testing](http://eaapp.somee.com/)
* [Swag Labs Web App for Web UI automation testing](https://www.saucedemo.com//)

## 🛠️ Tech Stack

| Tool                                                                               | Description                                                                               |
|------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| [allure-testng](https://mvnrepository.com/artifact/io.qameta.allure/allure-testng) | Allure reporting with your Java TestNG tests for better reporting                         |
| [playwright](https://playwright.dev/java/docs/intro)                               | Java library to automate the Chromium, WebKit, and Firefox browsers through a single API. |
| [TestNG](https://testng.org/)                                                      | Popular testing framework for Java                                                        |
| [org.json](https://mvnrepository.com/artifact/org.json/json)                                     | Library for parse JSON objecets in Java                                                   |

## ⚙️ Setup Instructions

### Step 1: Clone the project

```bash
git clone https://github.com/dimag25/PlaywrightJavaE2E
cd PlaywrightJavaE2E
```

### Step 2: Install Maven
Installing Maven on both Windows and macOS involves similar steps, but there are slight differences in the process due to the variations in the operating systems. Here's a guide for both:

## For Windows:
## Download Maven:
Visit the Maven official website https://maven.apache.org/download.cgi and download the latest version of Maven.
Choose a binary zip archive and download it to your computer.
## Extract the Maven Archive:
Once the download is complete, extract the contents of the zip file to a directory on your computer. For example, C:\Program Files\apache-maven.
## Set Environment Variables:
Add Maven's bin directory to the system's PATH environment variable. This allows you to run Maven commands from any command prompt.
Right-click on "This PC" or "Computer" and select "Properties".
Click on "Advanced system settings".
Click on "Environment Variables".
Under "System variables", select "Path" and click "Edit".
Add a new entry with the path to Maven's bin directory, e.g., C:\Program Files\apache-maven\bin.
Click "OK" to save the changes.

## For Mac:
```bash
# Install Maven
brew install maven
# Set Environment Variables:
nano ~/.bash_profile
# Add maven to PATH
export PATH=/path/to/maven/bin:$PATH
```

# Verify Installation:
Open a new command prompt and type `mvn -v` to verify that Maven is installed correctly. You should see Maven's version and other information if the installation was successful.



### Step 3: Install Project Dependencies

```bash
mvn clean install
```

## 🏃‍♂️ Running Tests

```bash
mvn test
```

#### * When no browser was selected then chrome will be used.


## 📊 Viewing Test Results

### Install Allure Commandline To View Test results

#### For Windows:

Follow the instructions [here](https://scoop.sh/) to install Scoop.<br>
Run the following command to install Allure using Scoop:

```bash
scoop install allure
```

#### For Mac:

```bash
brew install allure
```

### View Results Locally:

```bash
allure serve allure-results
```

### View trace results:

1. Navigate to the [Playwright Trace Viewer](https://trace.playwright.dev/)
2. Locate the trace file stored under the test-results folder. This file is generated after running your tests. Click on the 'Upload' button in the Playwright Trace Viewer and select your trace file.
3. After uploading, the trace viewer will display a detailed timeline of events that occurred during your test. This includes network requests, JavaScript execution, and browser interactions. You can click on individual events for more details.

## ℹ️ View Help And Other CLI Options

```
https://playwright.dev/java/docs/intro
```

### Pre Commit

#### Run Pre Commit Checks Automatically

```bash
pre-commit install
```

#### Bump Pre Commit Hooks Version

```bash
pre-commit autoupdate
```

#### Run Pre Commit Checks Manually On The Entire Project

```bash
pre-commit run --all-files
```
