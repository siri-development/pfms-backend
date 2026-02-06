pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                deleteDir()
                git branch: 'master',
                    url: 'https://github.com/siri-development/pfms-backend.git',
                    credentialsId: 'github-pat'
            }
        }

        stage('Build') {
            steps {
                echo 'Build stage reached'
            }
        }
    }
}
