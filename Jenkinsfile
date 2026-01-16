/*
 * Copyright (c) 2026 MukimSoft. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pull latest code from Git
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Grant execute permission and build the JAR
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                // Build docker image with English comments
                sh 'docker build -t mukimsoft/final-project:latest .'
            }
        }

        stage('Push to Registry') {
            steps {
                // Push image using Jenkins Credentials ID
                withDockerRegistry([credentialsId: 'docker-hub-creds', url: 'https://index.docker.io/v1/']) {
                    sh 'docker push mukimsoft/final-project:latest'
                }
            }
        }
    }
}