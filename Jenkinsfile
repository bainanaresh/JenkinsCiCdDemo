pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('SCM Checkout') {
            steps {
                // Get some code from a GitHub repository
               checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/bainanaresh/JenkinsCiCdDemo.git']])
            }
        }
        stage('Build Process') {
            steps {
                script {
                    bat 'mvn clean install'
                }

            }
        }

        stage('Deployment to Container') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat-pwd', path: '', url: 'http://localhost:9090/')], contextPath: 'JenkinsCiCdDemo', war: '**/*.war'
            }
        }

    }
    
     post {
                
                always {
                    emailext attachLog: true, 
                    body: '''<html>
                      <body>
                         <p>BUILD_STATUS : ${BUILD_STATUS}</p>
                         <p>BUILD_NUMBER : ${BUILD_NUMBER}</p>
                         <p>check the url : <a href="${BUILD_URL}">cosole_output</a></p>
                      </body>
                     </html>''', mimeType: 'text/html', subject: 'pipeline_status : ${BUILD_NUMBER}', to: 'bainanaresh@gmail.com'
                }
               
            }
        
}
