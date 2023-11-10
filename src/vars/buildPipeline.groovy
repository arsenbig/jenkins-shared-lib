// src/vars/buildPipeline.groovy

def call() {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }

            stage('Build') {
                steps {
                    script {
                        echo 'Building the Java application...'
                        sh 'mvn clean package'
                    }
                }
            }

            stage('Test') {
                steps {
                    script {
                        echo 'Running unit tests...'
                        sh 'mvn test'
                    }
                }
            }

            stage('Publish Test Results') {
                steps {
                    script {
                        echo 'Publishing test results...'
                        junit 'target/surefire-reports/*.xml'
                    }
                }
            }

            stage('Build Docker Image') {
                steps {
                    script {
                        echo 'Building Docker image...'
                        sh 'docker build -t my-java-app .'
                    }
                }
            }
        }
    }
}
