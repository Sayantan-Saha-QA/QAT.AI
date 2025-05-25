# QAT.AI – Intelligent Test Automation & SWE Artefact Generation Framework

QAT.AI is a modern, modular automation framework designed to accelerate software quality assurance and automate the generation of software engineering artefacts. Built with Java, Selenium, Cucumber (TestNG), and integrated with CI/CD pipelines, QAT.AI empowers teams to deliver robust, maintainable, and scalable test solutions for web applications and beyond.

---

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Key Features](#key-features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Testing](#testing)
- [Reporting](#reporting)
- [Jenkins Integration](#jenkins-integration)
- [License](#license)

---

## Overview

QAT.AI streamlines the process of test automation and artefact generation by providing:
- Modular, reusable components for rapid test development
- Integration with GitHub Copilot for prompt reuse and template management
- Automated reporting with ExtentReports and Cucumber HTML reports
- Seamless CI/CD integration using Jenkins
- Support for data-driven and behavior-driven development (BDD)

---

## Project Structure

```
.
├── JenkinsFile                # Jenkins pipeline configuration
├── build.gradle               # Gradle build configuration
├── README.md                  # Project documentation
├── product_names.json         # Sample data file
├── src/
│   ├── main/                  # Main Java source code
│   └── test/                  # Test Java source code
├── qGen-HUB/                  # Copilot prompt library and artefact generation modules
│   ├── 1.Input/               # Input templates
│   ├── 2.Prompt_Library/      # Reusable prompt logic
│   └── 3.Output/              # Generated outputs (e.g., user stories)
├── target/                    # Build artifacts, reports, and test outputs
├── screenshots/               # Screenshots captured during test runs
└── ...                        # Other supporting files and directories
```

### Key Directories

- **qGen-HUB/**: Wrapper on top of GitHub Copilot for prompt and template reuse.
- **src/main/java/**: Main application source code.
- **src/test/java/**: Test code (Cucumber, TestNG).
- **target/**: Compiled classes, reports, and test outputs.
- **screenshots/**: Screenshots from automated test executions.

---

## Key Features

- **Java 17+**: Modern language features and performance.
- **Selenium WebDriver**: Cross-browser web automation.
- **Cucumber + TestNG**: BDD and data-driven testing.
- **ExtentReports & Cucumber HTML Reports**: Rich, interactive test reporting.
- **Jenkins Integration**: Automated CI/CD pipelines.
- **Reusable Prompt Library**: Scale up Copilot usage for SWE artefact generation.
- **Database Integration**: MySQL connector for data-driven tests.
- **Utilities**: Logging, JSON handling, and more.

---

## Prerequisites

- **Java 24** (or Java 17+): Ensure Java is installed and configured.
- **Gradle**: Use the included Gradle wrapper (`./gradlew`).
- **Jenkins**: For CI/CD integration (optional).

---

## Setup Instructions

1. **Clone the repository:**
   ```sh
   git clone https://github.com/S-Saha-GenAI-Consulting/QAT.AI.git
   cd QAT.AI
   ```

2. **Ensure Java is installed:**
   ```sh
   java -version
   ```

3. **Build the project using Gradle:**
   ```sh
   ./gradlew clean build
   ```

---

## Usage

- **Run tests locally:**
  ```sh
  ./gradlew test
  ```

- **View reports:**
  - Open `target/ExtentReport.html` for ExtentReports.
  - Open `target/reports/html.html` for Cucumber HTML reports.

- **Customize configuration:**
  - Edit `src/config.properties` and `src/data.properties` as needed.

---

## Testing

- Tests are written using Cucumber and TestNG.
- Feature files and step definitions are located in `src/test/java/`.
- Test results and logs are available in the `target/` and `build/` directories.

---

## Reporting

- **ExtentReports**: Rich HTML reports at `target/ExtentReport.html`
- **Cucumber HTML**: BDD-style reports at `target/reports/html.html`
- **Screenshots**: Captured during test failures in the `screenshots/` directory.

---

## Jenkins Integration

- The included `JenkinsFile` enables CI/CD automation.
- To set up Jenkins:
  1. Configure a Jenkins job to pull this repository.
  2. Ensure `JAVA_HOME` is set to Java 24 (or compatible version).
  3. Start Jenkins (e.g., `brew services start jenkins-lts` on macOS).
  4. Trigger the pipeline to build and test the project.

---

## License

This project is licensed under the MIT License.

---