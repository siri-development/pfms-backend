pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend (Maven)') {
            steps {
                dir('pfms') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Deploy Backend + Infra') {
            steps {
                dir('pfms') {
                    sh '''
                      docker-compose down || true

                      docker-compose build mysql redis backend

                      docker-compose up -d mysql redis backend
                    '''
                }
            }
        }
    }

    post {
        success {
            echo '✅ Backend deployed successfully'
        }
        failure {
            echo '❌ Backend deployment failed'
        }
    }
}
