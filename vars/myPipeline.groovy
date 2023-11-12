// vars/myPipeline.groovy

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

def call() {
    echo 'Running CI/CD pipeline...'

    // Stages...
}
