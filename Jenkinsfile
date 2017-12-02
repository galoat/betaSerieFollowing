node() {
  deleteDir()
  stage 'checkout'
  checkout scm

   stage 'Create Env'
  def buildEnv = docker.build 'androidproject:latest', 'dockerFile'
  buildEnv.inside {
        stage 'Build'
             sh '''mkdir -p ?/.android
             keytool -genkey -v -keystore ?/.android/debug.keystore -storepass android -alias androiddebugkey -keypass android -dname "CN=Android Debug,O=Android,C=US"
            '''
            sh './gradlew clean'
            sh './gradlew assembleDebug'
            archive 'app/build/outputs/**/app-debug.apk'
        
        stage 'Quality'
        sh './gradlew lint'
        stash includes: 'app/build/reports/lint-results*.xml', name: 'lint-reports'

        stage 'Test (unit)'
        try {
             sh './gradlew test'
        } catch (err) {
             currentBuild.result = 'UNSTABLE'
         }
        stash includes: '**/test-results/**/*.xml', name: 'junit-reports'

        stage 'Test (device)'
        sh '''./gradlew :app:assembleDebug
              ./gradlew :app:assembleDebugAndroidTest
         '''
    // Archive for downstream AWS job
    archive 'app/build/outputs/**/*androidTest*.apk'
  }
}
        

stage 'Report'
node() {
  deleteDir()

  unstash 'junit-reports'
  step([$class: 'JUnitResultArchiver', testResults: '**/test-results/**/*.xml'])

  unstash 'lint-reports'
  step([$class: 'LintPublisher', canComputeNew: false, canRunOnFailed: true, defaultEncoding: '', healthy: '', pattern: '*/build/reports/lint-results*.xml', unHealthy: ''])
}

