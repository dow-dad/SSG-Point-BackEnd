// pipeline{
//     agent any
//     stages{
//         stage('SSH Connection'){
//             steps {
//                 sh 'ssh root@3.38.123.173'
//                 sh 'ls -al'
//                 sh 'cd /home/ssgpoint/jenkins/workspace/POINT-APP_SSG-Point-BackEnd_main'
//                 sh 'docker-composer up -d'
//             }
//         }
//         stage('Directory Change'){
//             steps {
//                 sh 'cd /home/ssgpoint/jenkins/workspace/POINT-APP_SSG-Point-BackEnd_main'
//             }
//         }
//         stage('docker-compose up'){
//             steps {
//                 sh 'docker-composer up -d'
//             }
//         }
//     }
// }

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh '''
                    chmod +x gradlew
                    ./gradlew build -x test
                '''
            }
        }
        stage('DockerSize') {
            steps {
                sh '''
                    docker stop ssgpointapp || true
                    docker rm ssgpointapp || true
                    docker rmi ssgpoint-be || true
                    docker build -t ssgpoint-be .
                    echo ${DB_URL}
                    echo ${DB_USER}
                    echo ${DB_PASS}
                    echo ${JWT_SECRET_KEY}
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d --name ssgpointapp --network ssgpoint-network -p 80:80 -e DATASOURCE_URL=${DB_URL} -e DATASOURCE_USER=${DB_USER} -e DATASOURCE_PASS=${DB_PASS} -e JWT_SECRET_KEY=${JWT_SECRET_KEY} ssgpoint-be'
            }
        }
    }
}
