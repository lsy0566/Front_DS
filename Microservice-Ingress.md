## [Microservice-Ingress]

```
apiVersion: networking.k8s.io/v1beta1
kind: Ingress
metadata:
  name: service-loadbalancing
spec:
  rules:
  - http:
      paths:
        - path: /dei
          backend:
            serviceName: dei-app-service
            servicePort: 8081
        - path: /logins
          backend:
            serviceName: login-app-service
            servicePort: 8082
        - path: /django
          backend:
            serviceName: django-app-service
            servicePort: 8083
            
---
apiVersion: v1
kind: Service
metadata:
  name: ingress-nginx-externalip
  namespace: ingress-nginx
spec:
  ports:
    - name: service
      port: 80
      targetPort: 80
  selector:
    app.kubernetes.io/name: ingress-nginx
    app.kubernetes.io/part-of: ingress-nginx 
  externalIPs:
    - 192.168.56.11
    - 192.168.56.12
    - 192.168.56.13
```

```
인그레스 컨트롤러 : nginx
인그레스는 결국 설정일 뿐이고 이 설정내용대로 동작하는 실제 행위자가 인그레스 컨트롤러 입니다. 인그레스 컨트롤러는 여러가지가 있지만 쿠버네티스가 공식적으로 제공하고 있는건 gce용인 ingress-gce와 nginx용인 ingress-nginx 2가지가 있습니다. ingress-gce는 gce를 이용하면 자동으로 사용할 수 있고 여기서는 직접 설치해서 사용할 수 있는 ingress-nginx를 알아보도록 하겠습니다. ingress-nginx는 github https://github.com/kubernetes/ingress-nginx 에서 확인할 수 있습니다. 
다음 명령으로 ingress-nginx 를 실행할 수 있습니다.

 kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/mandatory.yaml

이제 이 인그레스 컨트롤러에 접근해보기위한 서비스를 만들어 보겠습니다.

1.$kubectl expose deploy nginx-ingress-controller --type=NodePort -n ingress-nginx

2.$kubectl get svc -n ingress-nginx

[root@master-server ~]# kubectl get svc -n ingress-nginx
NAME                       TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
ingress-nginx              NodePort   10.107.200.10   <none>        80:32290/TCP,443:31454/TCP   125m
nginx-ingress-controller   NodePort   10.98.36.165    <none>        80:31818/TCP,443:31592/TCP   2m5s

nginx-ingress-controller의 port로 접속.
=> localhost:31818/path명.
```



## [Microservice-Deployment]

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: login-app-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: login-app
  template:
    metadata:
      labels:
        app: login-app
    spec:
      containers:
      - name: login-app
        image: wlgns0719/login-app-service:latest
        ports:
        - containerPort: 8080
        imagePullPolicy: Always
```



```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: deidentified-app-deployment
spec:
  replicas: 2
  selector:
    matchLabels:
      app: dei-app
  template:
    metadata:
      labels:
        app: dei-app
    spec:
      containers:
      - name: dei-app
        image: wlgns0719/dei-service:latest
        ports:
        - containerPort: 8080
        imagePullPolicy: Always
```



```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: django-app-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: django-app
  template:
    metadata:
      labels:
        app: django-app
    spec:
      containers:
      - name: django-app
        image: colvet/djangotest:latest
        ports:
        - containerPort: 8080
        imagePullPolicy: Always
```



curl -X GET http://192.168.56.10:30203/dei/getMongoAll

kubectl get all -n ingress-nginx

kubectl delete -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.27.1/deploy/static/mandatory.yaml

kubectl delete -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.27.1/deploy/static/provider/baremetal/service-nodeport.yaml -n ingress-nginx

kubectl get all -n ingress-nginx

kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.27.1/deploy/static/mandatory.yaml

kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.27.1/deploy/static/provider/baremetal/service-nodeport.yaml

kubectl get all -n ingress-nginx

kubectl get svc

curl -X GET http://192.168.56.10:30203/deis/getMongoAll

curl -X GET http://192.168.56.10:30203/deis

curl -X GET http://192.168.56.10:32326/deis/getMongoAll

curl -X GET http://192.168.56.10:32326/getMongoAll

kubectl get svc

kubectl get ingress

kubectl get all -n ingress-nginx

curl -X GET http://192.168.56.10:30203/users/getMongoAll

kubectl get pods

curl -X GET http://192.168.56.10:32326/getMongoAll

curl -X GET http://192.168.56.10:30203/users/

kubectl get pods

curl -X GET http://192.168.56.10:30203/users/getMongoAll

## [Microservice-service]

```
apiVersion: v1
kind: Service
metadata:
  name: dei-app-service
spec:
  ports:
    - name: "8080"
      port: 8081
      targetPort: 8085
  selector:
    app: dei-app
  type: NodePort

```

kubectl expose deploy nginx-ingress-controller --type=NodePort -n ingress-nginx

```
apiVersion: v1
kind: Service
metadata:
  name: login-app-service
spec:
  ports:
    - name: "8080"
      port: 8082
      targetPort: 8087
  selector:
    app: login-app
  type: NodePort
```



```
apiVersion: v1
kind: Service
metadata:
  name: django-app-service
spec:
  ports:
    - name: "8080"
      port: 8083
      targetPort: 8083
  selector:
    app: django-app
  type: NodePort
```



## [Microservice-database]

1. ### mongo

````
kind: PersistentVolume
apiVersion: v1
metadata:
  name: mongo-volume
  labels:
    type: msa-mongo
spec:
  capacity:
    storage: 2Gi
  accessModes:
    - ReadWriteOnce
  storageClassName: manual2
  hostPath:
    path: "/data/pv-mongo"
````

```
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: mongo-volumeclaim
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
  storageClassName: manual2
  selector:
  	matchLables:
  	  type: msa-mongo
```

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongodb
  labels:
    app: mongodb
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mongodb
  template:
    metadata:
      labels:
        app: mongodb
    spec:
      containers:
        - name: mongo
          image: mongo:latest
          env:
          	- name: MONGO_ROOT_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: mongo-password
                  key: password
          ports:
            - containerPort: 27017
              name: mongo
          volumeMounts:
            - name: mongo-persistent-storage
              mountPath: /data/db
      volumes:
        - name: mongo-persistent-storage
          persistentVolumeClaim:
            claimName: mongo-volumeclaim
```

```
apiVersion: v1

kind: Service
metadata:
  name: mongo
  labels:
    app: mongodb-svc
spec:
  type: ClusterIP
  ports:
    - port: 27017
  selector:
    app: mongodb
```



4. ### mysql

   - **hostpath**

     hostpath는 호스트의 디렉터리를 Pod와 공유해 사용하는 방식이다. 도커 스웜 모드의 호스트 볼륨 마운트와 비슷한 방식이라고 생각하면 쉽다. Pod가 삭제되어도 hostpath에 저장된 파일들은 호스트에 저장되어 남아있게 된다. 그러나 당연하게도 컨테이너가 할당될 특정 호스트를 nodeSelector를 통해 지정해주지 않으면 매번 컨테이너가 다른 호스트에 할당되므로 이 방식은 persistent storage와는 거리가 있다고 볼 수 있다.

     그러나 각 호스트의 metrics를 수집해야 하는 경우 (cadvisor나 prom-exporter, telemetry 등)이 로컬에 데이터를 쌓아야 한다면, 또는 도커 엔진의 소켓에 직접 접근해야 한다면 유용하게 사용할 수 있는 선택지이기도 하다.

````
kind: PersistentVolume
apiVersion: v1
metadata:
  name: mysql-volume
  labels:
    type: msa-mysql
spec:
  capacity:
    storage: 2Gi
  accessModes:
    - ReadWriteOnce
  storageClassName: manual
  hostPath:
    path: "/data/pv-mysql"
````

```
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: mysql-volumeclaim
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
  storageClassName: manual
  selector:
  	matchLables:
  	  type: msa-mysql
```

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql
  labels:
    app: mysqldb
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mysqldb
  template:
    metadata:
      labels:
        app: mysqldb
    spec:
      containers:
        - image: mysql:5.6
          name: mysql
          env:
            - name: MYSQL_ROOT_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: mysql-password
                  key: password
            - name: MYSQL_DATABASE # 구성할 database명
              value: k8sdb
            - name: MYSQL_USER # database에 권한이 있는 user
              value: k8suser
            - name: MYSQL_ROOT_HOST # 접근 호스트
              value: '%'
            - name: MYSQL_PASSWORD # database에 권한이 있는 user의 패스워드
              value: k8suser
          ports:
            - containerPort: 3306
              name: mysql
          volumeMounts:
            - name: mysql-persistent-storage
              mountPath: /var/lib/mysql
      volumes:
        - name: mysql-persistent-storage
          persistentVolumeClaim:
            claimName: mysql-volumeclaim
```

```
apiVersion: v1

kind: Service
metadata:
  name: mysql
  labels:
    app: mysqldb-svc
spec:
  type: ClusterIP 
  ports:
    - port: 3306
  selector:
    app: mysqldb
```

![image-20200609121825763](C:\Users\HPE\AppData\Roaming\Typora\typora-user-images\image-20200609121825763.png)



## [Spring-application.yml]

```
server:
  port: 8085
spring:
  data:
    mongodb:
      uri: mongodb://mongo:27017/k8s_mongodb

  jpa:
    hibernate:
      ddl-auto: update

  datasource:
    url: jdbc:mysql://mysql:3306/k8sdb?serverTimezone=Asia/Seoul&allowMultiQueries=true
    username: k8suser
    password: k8suser
    driver-class-name: com.mysql.cj.jdbc.Driver

  devtools:
    restart:
      enabled: true

  application:
    name: deidentified-app-service

  main:
    allow-bean-definition-overriding: true

  cloud:
    kubernetes:
      discovery:
        all-namespaces: true
```

```


```

```

```

```

```

admin / test123



- 서비스 기능, 마이크로 서비스 어필.