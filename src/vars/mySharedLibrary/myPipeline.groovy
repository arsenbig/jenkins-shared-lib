// mySharedLibrary/vars/myPipeline.groovy

def call() {

    def checkoutCode() {
        checkout scm
    }

    def buildJavaApp() {
        // Maven build the Java application
        sh 'mvn clean install'
    }

    def runTests() {
        // Run unit tests
        sh 'mvn test'
    }

    def publishTestResults() {
        // Publish test results
        step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
    }

    def buildDockerImage() {
        // Build Docker image
        sh 'docker build -t my-java-app .'
    }

    return this
}
