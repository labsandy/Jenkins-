pipeline {
    agent { label 'php' }
    stages {
        stage('build') {
            steps {
                ...
            }
        }
        stage('test') {
            parallel {
                stage('unit tests') {
                    steps {
                        ...
                    }
                }
                stage('integration tests') {
                    steps {
                        ...
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                ...
            }
        }
    }
}





=================

### CM parallel pipeline ex:-




pipeline{
  agent any
  stages{
     stage('Checkout'){
         steps{
              sh 'cd /home/jenkins/projects/cmol-api && git checkout master && git pull origin master && git checkout -b Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && git push origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER'
        }
     }
     stage('Deployment'){
         parallel{
             stage('CMOL-API1'){
                 steps{
                   sh'ssh -o "stricthostkeychecking no" -i "/home/jenkins/pem/cmprod.pem" ubuntu@172.31.48.236 "cd /home/ubuntu/cmol-api.creditmantri.com/current && sudo git fetch origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git checkout Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git pull origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo service supervisor restart && sudo service supervisor status"'
                 }
             }
             stage('CMOL-API2'){
                 steps{
                   sh'ssh -o "stricthostkeychecking no" -i "/home/jenkins/pem/cmprod.pem" ubuntu@172.31.48.135 "cd /home/ubuntu/cmol-api.creditmantri.com/current && sudo git fetch origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git checkout Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git pull origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo service supervisor restart && sudo service supervisor status"'
                 }
             }
             stage('CMOL-API3'){
                 steps{
                   sh'ssh -o "stricthostkeychecking no" -i "/home/jenkins/pem/cmprod.pem" ubuntu@172.31.48.103 "cd /home/ubuntu/cmol-api.creditmantri.com/current && sudo git fetch origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git checkout Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git pull origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo service supervisor restart && sudo service supervisor status"'
                 }
             }
             stage('CMOL-API4'){
                 steps{
                   sh'ssh -o "stricthostkeychecking no" -i "/home/jenkins/pem/cmprod.pem" ubuntu@172.31.48.157 "cd /home/ubuntu/cmol-api.creditmantri.com/current && sudo git fetch origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git checkout Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git pull origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo service supervisor restart && sudo service supervisor status"'
                 }
             }
             stage('CMOL-API5'){
                 steps{
                   sh'ssh -o "stricthostkeychecking no" -i "/home/jenkins/pem/cmprod.pem" ubuntu@172.31.48.210 "cd /home/ubuntu/cmol-api.creditmantri.com/current && sudo git fetch origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git checkout Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git pull origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo service supervisor restart && sudo service supervisor status"'
                 }
             }
             stage('Dhuvekam'){
              steps{
                    sh'ssh -o "stricthostkeychecking no" -i "/home/jenkins/pem/cmprod.pem" ubuntu@172.31.48.239 "cd /home/ubuntu/cmol-api.creditmantri.com/current && sudo git fetch origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git checkout Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git pull origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo service supervisor restart && sudo service supervisor status"'
              }
          }
          stage('Anekam'){
                 steps{
                   sh'ssh -o "stricthostkeychecking no" -i "/home/jenkins/pem/cmprod.pem" ubuntu@172.31.48.92 "cd /home/ubuntu/cmol-api.creditmantri.com/current && sudo git fetch origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git checkout Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo git pull origin Release-$(date +"%Y-%m-%d")-$BUILD_NUMBER && sudo service supervisor restart && sudo service supervisor status"'
                 }
             }
             
         }
     }
  }
  post {
        always {
            echo 'One way or another, I have finished'
        }
        success {
             emailext body: """
                <!doctype html>
		<html>
		  <head>
		    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		    <title>Simple Transactional Email</title>
		    <style>      
		      body {
			background-color: #f6f6f6;
			font-family: sans-serif;
			-webkit-font-smoothing: antialiased;
			font-size: 14px;
			line-height: 1.4;
			margin: 0;
			padding: 0;
			-ms-text-size-adjust: 100%;
			-webkit-text-size-adjust: 100%; 
		      }

		      table {
			border-collapse: separate;
			mso-table-lspace: 0pt;
			mso-table-rspace: 0pt;
			width: 100%; }
			table td {
			  font-family: sans-serif;
			  font-size: 14px;
			  vertical-align: top; 
		      }

		      .body {
			background-color: #f6f6f6;
			width: 100%; 
		      }
		      .container {
			display: block;
			margin: 0 auto !important;
			/* makes it centered */
			max-width: 580px;
			padding: 10px;
			width: 580px; 
		      }
		      .content {
			box-sizing: border-box;
			display: block;
			margin: 0 auto;
			max-width: 580px;
			padding: 10px; 
		      }
		      .main {
			background: #ffffff;
			border-radius: 3px;
			width: 100%; 
		      }

		      .wrapper {
			box-sizing: border-box;
			padding: 20px; 
		      }

		      .content-block {
			padding-bottom: 10px;
			padding-top: 10px;
		      }

		      p {
			font-family: sans-serif;
			font-size: 14px;
			font-weight: normal;
			margin: 0;
			margin-bottom: 15px; 
		      }
		      p {
			  list-style-position: inside;
			  margin-left: 5px; 
		      }

		      a {
			color: #3498db;
			text-decoration: underline; 
		      }

		      .btn {
			box-sizing: border-box;
			width: 100%; }
			.btn > tbody > tr > td {
			  padding-bottom: 15px; }
			.btn table {
			  width: auto; 
		      }
			.btn table td {
			  background-color: #ffffff;
			  border-radius: 5px;
			  text-align: center; 
		      }
			.btn a {
			  background-color: #ffffff;
			  border: solid 1px #3498db;
			  border-radius: 5px;
			  box-sizing: border-box;
			  color: #3498db;
			  cursor: pointer;
			  display: inline-block;
			  font-size: 14px;
			  font-weight: bold;
			  margin: 0;
			  padding: 12px 25px;
			  text-decoration: none;
			  text-transform: capitalize; 
		      }

		      .btn-primary table td {
			background-color: #3498db; 
		      }

		      .btn-primary a {
			background-color: #5c9eca;
			border-color: #3498db;
			color: #ffffff; 
		      }

		      @media only screen and (max-width: 620px) {
			table.body h1 {
			  font-size: 28px !important;
			  margin-bottom: 10px !important; 
			}
			table.body p,
			table.body ul,
			table.body ol,
			table.body td,
			table.body span,
			table.body a {
			  font-size: 16px !important; 
			}
			table.body .wrapper,
			table.body .article {
			  padding: 10px !important; 
			}
			table.body .content {
			  padding: 0 !important; 
			}
			table.body .container {
			  padding: 0 !important;
			  width: 100% !important; 
			}
			table.body .main {
			  border-left-width: 0 !important;
			  border-radius: 0 !important;
			  border-right-width: 0 !important; 
			}
			table.body .btn table {
			  width: 100% !important; 
			}
			table.body .btn a {
			  width: 100% !important; 
			}
			table.body .img-responsive {
			  height: auto !important;
			  max-width: 100% !important;
			  width: auto !important; 
			}
		      }
		      @media all {
			.ExternalClass {
			  width: 100%; 
			}
			.btn-primary table td:hover {
			  background-color: #34495e !important; 
			}
			.btn-primary a:hover {
			  background-color: #34495e !important;
			  border-color: #34495e !important; 
			} 
		      }

		    </style>
		  </head>
		  <body>
		    <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="body">
		      <tr>
			<td>&nbsp;</td>
			<td class="container">
			  <div class="content">
			    <table role="presentation" class="main">
			      <th style="background-color: #46a346; height: 59px; width: fit-content; border-radius:  8px 8px 0px 0px; color: white; font-size: 21px; font-weight: lighter;"> Successfully Deployed !!  &#128522;</th>
			      <tr>
				<td class="wrapper">
				  <table role="presentation" border="0" cellpadding="0" cellspacing="0">
				    <tr>
				      <td>
				        <p> Hi Team,</p>
				        <p> ${env.JOB_NAME} has been successfully deployed. You can check the log by clicking the button below.</p>
				        <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary">
				          <tbody>
				            <tr>
				              <td align="center">
				                <table role="presentation" border="0" cellpadding="0" cellspacing="0">
				                  <tbody>
				                    <tr>
				                      <td style="text-align: center;"> <a href="${BUILD_URL}" target="_blank" >Click Here</a> </td>
				                    </tr>
				                  </tbody>
				                </table>
				              </td>
				            </tr>
				          </tbody>
				        </table>
				        <p>Thanks, <br>
				        <span>Happy Automation</span></p>
				      </td>
				    </tr>
				  </table>
				</td>
			      </tr>
			    </table>
			    </div>
			</td>
			<td>&nbsp;</td>
		      </tr>
		    </table>
		  </body>
		</html>
		""",    
                    mimeType: 'text/html',
                    subject: "Deployment Success !! - ${env.JOB_NAME}",
                    from: "${emailFrom}",
                    to: "${emailApprover}"
			}
        failure {
            emailext body: """
		<!doctype html>
		<html>
		  <head>
		    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		    <title>Simple Transactional Email</title>
		    <style>      
		      body {
			background-color: #f6f6f6;
			font-family: sans-serif;
			-webkit-font-smoothing: antialiased;
			font-size: 14px;
			line-height: 1.4;
			margin: 0;
			padding: 0;
			-ms-text-size-adjust: 100%;
			-webkit-text-size-adjust: 100%; 
		      }

		      table {
			border-collapse: separate;
			mso-table-lspace: 0pt;
			mso-table-rspace: 0pt;
			width: 100%; }
			table td {
			  font-family: sans-serif;
			  font-size: 14px;
			  vertical-align: top; 
		      }

		      .body {
			background-color: #f6f6f6;
			width: 100%; 
		      }
		      .container {
			display: block;
			margin: 0 auto !important;
			/* makes it centered */
			max-width: 580px;
			padding: 10px;
			width: 580px; 
		      }
		      .content {
			box-sizing: border-box;
			display: block;
			margin: 0 auto;
			max-width: 580px;
			padding: 10px; 
		      }
		      .main {
			background: #ffffff;
			border-radius: 3px;
			width: 100%; 
		      }

		      .wrapper {
			box-sizing: border-box;
			padding: 20px; 
		      }

		      .content-block {
			padding-bottom: 10px;
			padding-top: 10px;
		      }

		      p {
			font-family: sans-serif;
			font-size: 14px;
			font-weight: normal;
			margin: 0;
			margin-bottom: 15px; 
		      }
		      p {
			  list-style-position: inside;
			  margin-left: 5px; 
		      }

		      a {
			color: #3498db;
			text-decoration: underline; 
		      }

		      .btn {
			box-sizing: border-box;
			width: 100%; }
			.btn > tbody > tr > td {
			  padding-bottom: 15px; }
			.btn table {
			  width: auto; 
		      }
			.btn table td {
			  background-color: #ffffff;
			  border-radius: 5px;
			  text-align: center; 
		      }
			.btn a {
			  background-color: #ffffff;
			  border: solid 1px #3498db;
			  border-radius: 5px;
			  box-sizing: border-box;
			  color: #3498db;
			  cursor: pointer;
			  display: inline-block;
			  font-size: 14px;
			  font-weight: bold;
			  margin: 0;
			  padding: 12px 25px;
			  text-decoration: none;
			  text-transform: capitalize; 
		      }

		      .btn-primary table td {
			background-color: #3498db; 
		      }

		      .btn-primary a {
			background-color: #5c9eca;
			border-color: #3498db;
			color: #ffffff; 
		      }

		      @media only screen and (max-width: 620px) {
			table.body h1 {
			  font-size: 28px !important;
			  margin-bottom: 10px !important; 
			}
			table.body p,
			table.body ul,
			table.body ol,
			table.body td,
			table.body span,
			table.body a {
			  font-size: 16px !important; 
			}
			table.body .wrapper,
			table.body .article {
			  padding: 10px !important; 
			}
			table.body .content {
			  padding: 0 !important; 
			}
			table.body .container {
			  padding: 0 !important;
			  width: 100% !important; 
			}
			table.body .main {
			  border-left-width: 0 !important;
			  border-radius: 0 !important;
			  border-right-width: 0 !important; 
			}
			table.body .btn table {
			  width: 100% !important; 
			}
			table.body .btn a {
			  width: 100% !important; 
			}
			table.body .img-responsive {
			  height: auto !important;
			  max-width: 100% !important;
			  width: auto !important; 
			}
		      }
		      @media all {
			.ExternalClass {
			  width: 100%; 
			}
			.btn-primary table td:hover {
			  background-color: #34495e !important; 
			}
			.btn-primary a:hover {
			  background-color: #34495e !important;
			  border-color: #34495e !important; 
			} 
		      }

		    </style>
		  </head>
		  <body>
		    <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="body">
		      <tr>
			<td>&nbsp;</td>
			<td class="container">
			  <div class="content">
			    <table role="presentation" class="main">
			      <th style="background-color: #903a24; height: 59px; width: fit-content; border-radius:  8px 8px 0px 0px; color: white; font-size: 21px; font-weight: lighter;">Oops ! Deployment Failed &#128542;</th>
			      <tr>
				<td class="wrapper">
				  <table role="presentation" border="0" cellpadding="0" cellspacing="0">
				    <tr>
				      <td>
				        <p> Hi Team,</p>
				        <p> ${env.JOB_NAME} was not deployed properly. You can check the log by clicking the button below.</p>
				        <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary">
				          <tbody>
				            <tr>
				              <td align="center">
				                <table role="presentation" border="0" cellpadding="0" cellspacing="0">
				                  <tbody>
				                    <tr>
				                      <td style="text-align: center;"> <a href="${BUILD_URL}" target="_blank" >Click Here</a> </td>
				                    </tr>
				                  </tbody>
				                </table>
				              </td>
				            </tr>
				          </tbody>
				        </table>
				        <p>Thanks, <br>
				        <span>Happy Automation</span></p>
				      </td>
				    </tr>
				  </table>
				</td>
			      </tr>
			    </table>
			    </div>
			</td>
			<td>&nbsp;</td>
		      </tr>
		    </table>
		  </body>
		</html>
                   """,    
                    mimeType: 'text/html',
                    subject: "Deployment Failiure - ${env.JOB_NAME}",
                    from: "${emailFrom}",
                    to: "${emailApprover}"
        }
    }
}
