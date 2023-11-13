def callCheckout() {
    checkout scm
}

def callBuild() {
    sh 'mvn clean install'
}

def callTest() {
    sh 'mvn test'
    callPublishTestResults()
    callCheckstyle()
}

def callPublishTestResults() {
    script {
        def junitReports = "**/target/surefire-reports/TEST-*.xml"
        def checkstyleReports = "**/checkstyle-result.xml"

        junit testResults: junitReports

        recordIssues(
            tools: [checkStyle(pattern: checkstyleReports)],
            aggregatingResults: true
        )

        recordIssues tools: [findBugs(pattern: '**/findbugs.xml')], aggregatingResults: true
    }
}

def callCheckstyle() {
    script {
        def checkstyleReports = "**/checkstyle-result.xml"

        recordIssues(
            tools: [checkStyle(pattern: checkstyleReports)],
            aggregatingResults: true
        )
    }
}

def callBuildDockerImage() {
    sh 'docker build -t my-java-app .'
}
