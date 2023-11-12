// File: src/mySharedLibrary/MyPipeline.groovy
class MyPipeline {
    
    def checkoutCode() {
        echo 'Checking out code...'
    }

    def buildJavaApp() {
        echo 'Building Java application...'
    }

    def runTests() {
        echo 'Running tests...'
    }

    def publishTestResults() {
        echo 'Publishing test results...'
    }

    def buildDockerImage() {
        echo 'Building Docker image...'
    }
}
