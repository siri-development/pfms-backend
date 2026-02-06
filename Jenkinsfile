pipeline {
    agent any

    options {
        skipDefaultCheckout(false)
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Source code checked out by Jenkins'
                sh 'git status'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Spring Boot project'
                sh 'mvn clean install -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully'
        }
        failure {
            echo 'Build failed'
        }
    }
}
