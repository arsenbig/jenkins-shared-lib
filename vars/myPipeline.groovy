// vars/JavaPipeline.groovy
def callCheckout() {
    checkout scm
}

def callBuild() {
    sh 'mvn clean package'
}

def callTest() {
    sh 'mvn test'
}

def callPublishTestResults() {
    junit 'target/surefire-reports/*.xml'
}

def callBuildDockerImage() {
    sh 'docker build -t my-java-app .'
}
