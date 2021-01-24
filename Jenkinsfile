pipeline {
    agent any
    environment {
        CI = 'true'
    }
    stages {
        stage('Build & Test') {
            steps {
                sh './jenkins/scripts/test.sh spring'
            }
        }
        stage('Deploy dev') {
            when {
                branch 'develop'
            }
            steps {
                withCredentials([string(credentialsId: 'POSTGRES-URL-DEV', variable: 'POSTGRES_URL')]) {
                    sh './jenkins/scripts/deploy.sh spring-dev 8080'
                }
            }
        }
        stage('Deploy pre') {
            when {
                branch 'master'
            }
            steps {
                withCredentials([string(credentialsId: 'POSTGRES-URL-PRE', variable: 'POSTGRES_URL')]) {
                    sh './jenkins/scripts/deploy.sh spring-pre 8081'
                }
            }
        }
        stage('Deploy pro') {
            when {
                branch 'master'
            }
            steps {
                withCredentials([string(credentialsId: 'POSTGRES-URL-PRO', variable: 'POSTGRES_URL')]) {
                    sh ''
                }
            }
        }
    }
}
