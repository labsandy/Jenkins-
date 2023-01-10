

pipeline {
    agent any
    stages {
        stage('deploy') {
            parallel {
                stage('server1') {
                    steps {
                        echo "server1"
                    }
                }
                stage('server2') {
                    steps {
                        echo "server2"
                    }
                }
            }
        }
        
    }
}




==================

pipeline{
   agent any
   stages{
      
      stage('Deploy'){
          steps{
                sh 'ssh -i "/home/jenkins/pem/cgprod.pem" ubuntu@172.31.48.72 "cd /home/ubuntu/creditmantri && git checkout @{-1} && sudo service supervisor restart && sudo service supervisor status"'
              }
          }
    
   }
}
