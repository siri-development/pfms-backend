pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building PFMS multi-module project using Maven Wrapper'
                dir('pfms') {             
                    sh 'chmod +x mvnw'
                    sh './mvnw clean install -DskipTests'
                }
            }
        }
    }

    post {
        success {
            echo 'BUILD SUCCESS'
        }
        failure {
            echo 'BUILD FAILED'
        }
    }
}
