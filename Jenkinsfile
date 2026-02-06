pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building PFMS using Maven Wrapper (no Docker)'
                dir('pfms') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean install -DskipTests'
                }
            }
        }
    }

    post {
        success {
            echo 'BUILD SUCCESS 🎉'
        }
        failure {
            echo 'BUILD FAILED ❌'
        }
    }
}
