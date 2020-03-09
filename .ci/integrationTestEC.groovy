#!/usr/bin/env groovy

pipeline {
  agent { label 'linux' }
  stages {
    stage('Tests'){
      matrix {
        agent { label 'linux' }
        environment {
          TMPDIR = "/tmp"
          HOME = "/tmp"
          CONFIG_HOME = "/tmp"
          EC_WS ="/tmp/aaaa"
          VENV = "/tmp/.venv"
          PATH = "/tmp/aaaa/.ci/scripts:/tmp/bin:aaaa/bin:aaaa/.ci/scripts:${env.PATH}"
          CLUSTER_CONFIG_FILE="aaaaa/tests/environments/elastic_cloud.yml"
          ENABLE_ES_DUMP = "true"
        }
        axes {
          axis {
              name 'TEST'
              values 'all', 'dotnet', 'go', 'java', 'nodejs', 'python', 'ruby', 'rum'
          }
          axis {
              name 'ELASTIC_STACK_VERSION'
              values '8.0.0-SNAPSHOT', '7.7.0-SNAPSHOT', '7.6.1-SNAPSHOT', '6.8.7-SNAPSHOT'
          }
        }
        stages {
          stage('Prepare Test'){
            steps {
              echo "Running tests - ${ELASTIC_STACK_VERSION} x ${TEST}"
            }
          }
        }
      }
    }
  }
}
