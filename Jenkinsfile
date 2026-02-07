pipeline {
    agent any

    environment {
        BACKEND_REPO  = 'https://github.com/siri-development/pfms-backend.git'
        FRONTEND_REPO = 'https://github.com/siri-development/pfms-frontend.git'
    }

    stages {

        stage('Checkout Backend') {
            steps {
                echo 'Cloning backend repository'
                checkout scm
            }
        }

        stage('Checkout Frontend') {
            steps {
                echo 'Cloning frontend repository'
                dir('..') {
                    sh '''
                        rm -rf pfms-frontend
                        git clone ${FRONTEND_REPO}
                    '''
                }
            }
        }

        stage('Build Backend (Maven)') {
            steps {
                echo 'Building Spring Boot backend'
                dir('pfms') {
                    sh '''
                        chmod +x mvnw
                        ./mvnw clean install -DskipTests
                    '''
                }
            }
        }

        stage('Build & Deploy (Docker Compose)') {
            steps {
                echo 'Building images and starting containers'
                dir('pfms') {
                    sh '''
                        docker-compose down
                        docker-compose build
                        docker-compose up -d
                    '''
                }
            }
        }

        stage('Cleanup Dangling Images') {
            steps {
                echo 'Cleaning unused Docker images'
                sh 'docker image prune -f'
            }
        }
    }

    post {
        success {
            echo 'CI/CD PIPELINE SUCCESS ✅'
        }
        failure {
            echo 'CI/CD PIPELINE FAILED ❌'
        }
    }
}
