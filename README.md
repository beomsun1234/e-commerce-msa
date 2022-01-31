# E-Coommerce with MSA + KAFKA + ELK + Spring Cloud Sleth + Zipkin + Kubernetes + Jenkins + Docker

카프카사용하여 order-service와 product-service의 비동기 처리 및 로그 수집 분산 모니터링 및 

Jenkins를 사용하여 CI/CD 구축  main baranch에서 작업이 완료되면 빌드 및 테스트 성공 후 Rolling Update

## 구성


![msa구성](https://user-images.githubusercontent.com/68090443/151831066-cce6235c-8d43-463c-9bee-eb577594138e.PNG)


![eureka](https://user-images.githubusercontent.com/68090443/151831364-365bb19b-c74c-4474-ae5c-abafa551a185.jpg)



## 쿠버네티스 모니터링(대시보드)


![msa이커머스쿠버네티스대시보드](https://user-images.githubusercontent.com/68090443/151829426-f4946363-5398-462e-99db-3790717b4b0f.PNG)



## ELK를 이용하여 로그 수집

![이커머스elk를통해 로그수집](https://user-images.githubusercontent.com/68090443/149524277-b2e0f27d-dcee-4b48-96dc-f07a558d8452.PNG)


## Spring Cloud Sleth + Zipkin이용한 분산 모니터링


![msa-zipkin](https://user-images.githubusercontent.com/68090443/151829255-3632efe7-f410-4f0a-8e9a-c2cefa843d35.jpg)



![msa집킨](https://user-images.githubusercontent.com/68090443/151829316-656a5303-a523-4db8-9fb2-3b0dbda4fb3e.jpg)



![집킨2](https://user-images.githubusercontent.com/68090443/149607350-66b00393-5881-4703-98ef-42c2ec1361d3.PNG)

## CI/CD


![cicd아키텍처](https://user-images.githubusercontent.com/68090443/151831132-f4d0e027-b5af-4b74-8946-65d13d94927b.PNG)


![잰킨스배포](https://user-images.githubusercontent.com/68090443/151829757-f50f0047-3879-4df8-bf33-c03b2ec78461.jpg)


### jenkins pipeline 

    pipeline {
            agent any
            environment{
                DOCKERHUB_CREDENTIALS=credentials('docker-hub')
            }
            stages {
                stage('github clone') {
                    steps {
                        git branch: 'main',
                            credentialsId: 'github',
                            url: 'https://github.com/beomsun1234/e-commerce-msa.git'
                    }
                }

                stage('build') {
                    steps {
                        sh """
                            cd /var/lib/jenkins/workspace/msa/order-service
                            chmod +x gradlew
                            ./gradlew clean build 
                            echo 'order-service build sucess'
                        """
                        sh """
                            cd /var/lib/jenkins/workspace/msa/product-service
                            chmod +x gradlew
                            ./gradlew clean build
                            echo 'product-service build sucess'
                        """
                    }
                }
                stage('docker build') {
                    steps {
                        sh """
                            cd /var/lib/jenkins/workspace/msa/order-service
                            sudo docker build -t beomsun22/order-service:${BUILD_NUMBER} .
                            echo 'docker jenkins-order build sucess'
                        """
                        sh """
                            cd /var/lib/jenkins/workspace/msa/product-service
                            sudo docker build -t beomsun22/product-service:${BUILD_NUMBER} .
                            echo 'docker jenkins-product build sucess'
                        """
                    }
                }
                stage('login'){
                    steps{
                        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    }
                }
                 stage('docker push') {
                    steps {
                        sh 'docker push beomsun22/order-service:${BUILD_NUMBER}'
                        echo 'docker order-service:v${BUILD_NUMBER} push sucess'
                        sh 'docker push beomsun22/product-service:${BUILD_NUMBER}'
                        echo 'docker product-service:v${BUILD_NUMBER} push sucess'
                    }
                }
                stage('deploy') {
                    steps {
                        sh """

                            sudo ssh root@[worker ip] 'export KUBECONFIG=/etc/kubernetes/admin.conf'
                            sudo ssh root@[worker ip] 'kubectl get pod'
                            sudo ssh root@[worker ip] '/home/beomsun159/./test.sh aaa bbb'
                            sudo ssh root@[worker ip] '/home/beomsun159/./rolling_update.sh order-service order-service beomsun22/order-service:${BUILD_NUMBER}'
                            sudo ssh root@[worker ip] '/home/beomsun159/./rolling_update.sh product-service product-service beomsun22/product-service:${BUILD_NUMBER}'
                        """
                    }
                }
            }
            post {
                always{
                    sh 'docker logout'
                }
            }
        }

### rolling update shell script 

    echo "deploy name = ${1}"
    echo "container name = ${2}"
    echo "image name = ${3}"
    kubectl set image deployment ${1} ${2}=${3}
    echo "run kubectl set image deployment ${1} ${2}=${3}"
    kubectl get pod



## Postman 통해 요청


![post맨](https://user-images.githubusercontent.com/68090443/151830984-213203ec-a873-4271-b3b3-6aaf566495ff.PNG)


POST - gcp공개IP:8000/order/products/{id}/order


## order service 

![orderservice](https://user-images.githubusercontent.com/68090443/149526116-0a9ff3c3-8eea-452c-9dec-2756fc16360f.PNG)


## product service

![product서비스](https://user-images.githubusercontent.com/68090443/149526146-976695f4-4ddd-41d4-92f7-3bada5bfceba.PNG)

