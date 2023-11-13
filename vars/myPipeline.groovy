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

        junit testResults: junitReports
        recordIssues tools: [checkStyle(), findBugs()], aggregatingResults: true
    }
}

def callBuildDockerImage() {
    sh 'docker build -t my-java-app .'
}
