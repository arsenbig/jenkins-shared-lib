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
            tools: [checkStyle(pattern: checkstyleReports, id: 'custom-checkstyle-test')],
            aggregatingResults: true
        )

        recordIssues tools: [findBugs(pattern: '**/findbugs.xml', id: 'custom-findbugs-test')],
            aggregatingResults: true
    }
}

def callCheckstyle() {
    script {
        def checkstyleReports = "**/checkstyle-result.xml"

        // Remove existing Checkstyle action
        currentBuild.rawBuild.getActions().removeAll { it instanceof io.jenkins.plugins.analysis.core.model.ResultAction && it.id == 'custom-checkstyle' }

        recordIssues(
            tools: [checkStyle(pattern: checkstyleReports, id: 'custom-checkstyle')],
            aggregatingResults: true
        )
    }
}

def callBuildDockerImage() {
    sh 'docker build -t my-java-app .'
}
