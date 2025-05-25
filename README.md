# QAT.AI Workspace

This repository contains the QAT.AI project, which is designed to streamline test automation and SWE artefacts generation. The workspace is structured to support modular development, testing, and automated SWE artefacts generation.

---

## Table of Contents
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Testing](#testing)
- [Jenkins Integration](#jenkins-integration)
- [License](#license)

---

## Project Structure
JenkinsFile # Jenkins pipeline configuration mvnw, mvnw.cmd # Maven wrapper scripts pom.xml # Maven project configuration README.md # Project documentation .mvn/ # Maven wrapper configuration .vscode/ # VS Code workspace settings qGen-HUB/ # Core modules for input, prompt library, and output src/ # Source code for main and test Java files target/ # Compiled classes, reports, and test outputs


### Key Directories:
- qGen-HUB: Wrapper on top of GitHub Copilot: Reuse prompts, templates. This enables the user to scale up GitHub Copilot across use cases.
- **qGen-HUB/1.Input/**: Contains input templates for output.
- **qGen-HUB/2.Prompt_Library/**: Includes reusable prompt logic and context files.
- **qGen-HUB/3.Output/**: Stores generated outputs like user stories
- **src/main/java/**: Main Java source code.
- **src/test/java/**: Test Java source code.
- **target/**: Build artifacts, reports, and compiled classes.

-------------

## Prerequisites

- **Java 24**: Ensure Java 24 is installed and configured.
- **Maven**: Use the Maven wrapper (`mvnw`) included in the repository.
- **Jenkins**: For CI/CD integration.

---

## Setup Instructions

1. Clone the repository:
   ```sh
   git clone https://github.com/S-Saha-GenAI-Consulting/QAT.AI.git
   cd QAT.AI

2. Ensure Java 24 is installed: java -version

3. Build the project using Maven: ./mvnw clean install

4. Testing
Running Tests
Execute all tests using Maven: mvnw clean test

5. Jenkins Integration
This project includes a JenkinsFile for CI/CD automation. To set up Jenkins:

* Configure a Jenkins job to pull this repository.

* Ensure the JAVA_HOME environment variable is set to Java 24.

* Run command on terminal: brew services start jenkins-lts

* Trigger the pipeline to build and test the project.
