pipeline {
    agent any

    stages {

        stage('Build & Deploy using Docker Compose') {
            steps {
                echo 'Building images and deploying containers using Docker Compose'
                dir('pfms') {
                    sh '''
                      docker-compose down
                      docker-compose build
                      docker-compose up -d
                    '''
                }
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
            echo 'CI/CD SUCCESS 🚀 Application is running'
        }
        failure {
            echo 'CI/CD FAILED ❌ Check logs'
        }
    }
}
