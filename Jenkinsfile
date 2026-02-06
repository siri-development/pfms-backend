pipeline {
    agent any

    stages {

        stage('Build Backend (Maven)') {
            steps {
                echo 'Building PFMS backend using Maven Wrapper'
                dir('pfms') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean install -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                echo 'Building Docker images (backend & frontend)'
                sh 'docker-compose build'
            }
        }

        stage('Deploy Containers') {
            steps {
                echo 'Stopping old containers and starting new ones'
                sh '''
                  docker-compose down
                  docker-compose up -d
                '''
            }
        }

        stage('Cleanup Old Images') {
            steps {
                echo 'Cleaning dangling Docker images'
                sh 'docker image prune -f'
            }
        }
    }

    post {
        success {
            echo 'CI/CD SUCCESS 🚀 All services are running'
        }
        failure {
            echo 'CI/CD FAILED ❌ Check logs'
        }
    }
}
