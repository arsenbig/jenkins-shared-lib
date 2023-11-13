def callCheckout() {
    checkout scm
}

def callBuild() {
    sh 'mvn clean install'
}

def callTest() {
    sh 'mvn test'
    callPublishTestResults()
}

def callPublishTestResults() {
    script {
        def junitReports = "**/target/surefire-reports/TEST-*.xml"

        def mavenHome = tool 'Maven'
        junit testResults: junitReports
        checks([ $class: 'JUnitChecksPublisher', testResults: junitReports ])
    }
}

def callBuildDockerImage() {
    sh 'docker build -t my-java-app .'
}
