// mySharedLibrary/vars/myPipeline.groovy

class MyPipeline {
    def call() {
        return this
    }

    def checkoutCode() {
        script {
            checkout scm
        }
    }

    def buildJavaApp() {
        script {
            sh 'mvn clean install'
        }
    }

    def runTests() {
        script {
            sh 'mvn test'
        }
    }

    def publishTestResults() {
        script {
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
        }
    }

    def buildDockerImage() {
        script {
            sh 'docker build -t my-java-app .'
        }
    }
}

return new MyPipeline()
