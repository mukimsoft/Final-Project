/*
 * Jenkins Pipeline for Spring Boot, Docker, and Kubernetes
 */

pipeline {
    agent any

    environment {
        // Define variables for reuse
        DOCKER_IMAGE = "mukimsoft/final-project:latest"
        K8S_NAMESPACE = "ba-cirt-infra"
    }

    stages {
        // Stage 1: Checkout the code from GitHub
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // Stage 2: Build JAR file using Maven Wrapper
        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        // Stage 3: Build Docker Image
        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        // Stage 4: Push Image to Docker Hub
        stage('Push to Registry') {
            steps {
                // Ensure the credential ID matches what you created in Jenkins
                withDockerRegistry([credentialsId: 'docker-hub-creds', url: 'https://index.docker.io/v1/']) {
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        // Stage 5: Deploy to Kubernetes
        stage('Kubernetes Deploy') {
            steps {
                script {
                    // Create namespace if it doesn't exist
                    sh "kubectl get namespace ${K8S_NAMESPACE} || kubectl create namespace ${K8S_NAMESPACE}"

                    // Apply Kubernetes manifests (Deployment and Service)
                    // Note: Ensure your deployment.yaml is in a folder named 'k8s'
                    sh "kubectl apply -f k8s/deployment.yaml -n ${K8S_NAMESPACE}"
                    sh "kubectl apply -f k8s/service.yaml -n ${K8S_NAMESPACE}"
                }
            }
        }
    }
}