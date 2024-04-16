#!/usr/bin/env groovy

def call(String buildResult) {
  def emailSubject
  def emailBody
  def recipientEmail

  if (buildResult == "SUCCESS") {
      emailSubject = "Pipeline Success: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}"
      emailBody = "The pipeline run for ${env.JOB_NAME} - Build #${env.BUILD_NUMBER} was successful. You can view the details at ${env.BUILD_URL}"
      recipientEmail = '$DEFAULT_RECIPIENTS'
  }
  else {
      emailSubject = "Pipeline Failure: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}"
      emailBody = "The pipeline run for ${env.JOB_NAME} - Build #${env.BUILD_NUMBER} has failed. You can view the details at ${env.BUILD_URL}"
      recipientEmail = '$DEFAULT_RECIPIENTS'
  }

  emailext (
      subject: emailSubject,
      body: emailBody,
      to: recipientEmail
  )
}
