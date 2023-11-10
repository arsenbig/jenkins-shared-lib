def call() {
    pipeline {
        agent {
            docker {
                label 'docker-agent'
                image 'maven:3.6.3-jdk-11'
            }
        }
        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }
            stage('Build') {
                steps {
                    script {
                        buildJavaApp()
                    }
                }
            }
            stage('Test') {
                steps {
                    script {
                        testJavaApp()
                    }
                }
            }
            stage('Publish Test Results') {
                steps {
                    script {
                        publishTestResults()
                    }
                }
            }
            stage('Build Docker Image') {
                steps {
                    script {
                        buildDockerImage()
                    }
                }
            }
        }
    }
}

def buildJavaApp() {
    sh 'mvn clean install'
}

def testJavaApp() {
    sh 'mvn test'
}

def publishTestResults() {
    junit '**/target/surefire-reports/*.xml'
}

def buildDockerImage() {
    sh 'docker build -t java-app .'
}
