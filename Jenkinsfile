pipeline {
    agent any

    stages {
        stage('Build & Deploy using Docker Compose') {
            steps {
                echo 'Building images and deploying containers using Docker Compose'
                sh '''
                  docker-compose down
                  docker-compose build
                  docker-compose up -d
                '''
            }
        }

        stage('Cleanup Old Images') {
            steps {
                sh 'docker image prune -f'
            }
        }
    }

    post {
        success {
            echo 'CI/CD SUCCESS 🚀'
        }
        failure {
            echo 'CI/CD FAILED ❌'
        }
    }
}
