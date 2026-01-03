Project Structure
AppleTest/
├── src/
│   ├── main/java/         # Core framework code
│   ├── main/resources/    # Config files, test data
│   ├── test/java/         # Test classes
│   ├── test/resources/    # Test-specific configs
├── allure-report/         # Generated Allure reports
├── allure-results/        # Allure result files
├── logs/                  # Execution logs
├── plugins/               # Custom plugins
├── screenshots/           # Captured screenshots
├── TestCases/             # Organized test cases
├── test-output/           # TestNG default reports
├── pom.xml                # Maven configuration
├── testng.xml             # Test suite configuration
├── allure.properties      # Allure config

Clone the repository:
git clone https://github.com/NagarajaGpoojari/AppleTest.git

Run tests: mvn clean test
Allure Report : allure serve allure-results

Running Tests : mvn test -Dsuite=ui

Name : Nagaraja
Role : Automation Test Engineer
Email : nag.poojari1@gmail.com
LinkedIn : www.linkedin.com/in/Nagaraja0827


