#!/usr/bin/env groovy

// Définition de la fonction 'call' prenant en paramètre le résultat de la construction
def call(String buildResult) {
  def emailSubject
  def emailBody
  def recipientEmail

  // Si la construction est un succès
  if (buildResult == "SUCCESS") {
      emailSubject = "Pipeline Success: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}" // Sujet de l'email en cas de succès

      emailBody = "The pipeline run for ${env.JOB_NAME} - Build #${env.BUILD_NUMBER} was successful. You can view the details at ${env.BUILD_URL}" // Corps de l'email en cas de succès
      recipientEmail = '$DEFAULT_RECIPIENTS' // Email du destinataire
  }
  else {
      emailSubject = "Pipeline Failure: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}" // Sujet de l'email en cas d'échec
      emailBody = "The pipeline run for ${env.JOB_NAME} - Build #${env.BUILD_NUMBER} has failed. You can view the details at ${env.BUILD_URL}" // Corps de l'email en cas d'échec
      recipientEmail = '$DEFAULT_RECIPIENTS' // Email du destinataire
  }

  // Appel de la fonction 'emailext' pour envoyer l'email
  emailext (
      subject: emailSubject,
      body: emailBody,
      to: recipientEmail
  )
}
