# AngularOrdenes (FrontEnd)

- Para instalar Angular 4 hay que seguir las instrucciones de la pagina oficial: https://angular.io/guide/quickstart.
- Este ejercicio hace uso de Material Design, el cual fue instalado siguiendo las instrucciones de la pagina oficial: https://material.angular.io/guide/getting-started
- Para iniciar angular el comando usado en la respectiva ubicacion del proyecto es: ng serve --open
- En data.service.ts se encuentra la url del BackEnd: public url = 'http://localhost:8080/';


# SpringOrdenes (BackEnd)

- Para hacer uso efectivo de hibernate y la conexion a mysql se debe modificar la url, nombre de usuario, contraseña y driver en src/main/resources. Actualmente tiene la siguiente configuracion:

spring.datasource.url=jdbc:mysql://localhost:3307/tienda

spring.datasource.username=root

spring.datasource.password=root

spring.datasource.driver-class-name=com.mysql.jdbc.Driver


- El proyecto es un spring boot. Para ejecutarlo en Spring Tools Suite se debe correr como Spring Boot App.

## Algunas Vistas (Prueba del funcionamiento con Postman)
- Vista de getCustomers (localhost:8080/customers) - METODO GET	
![Vista de getCustomers](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/1.png)

- Vista de getCustomer (localhost:8080/customer/1) - METODO GET
![Vista de getCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/2.png)

- Vista de createCustomer (localhost:8080/customer/) - METODO POST
![Vista de createCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/3.png)

- Vista de updateCustomer (localhost:8080/customer/) - METODO PUT
![Vista de updateCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/4.png)

- Vista de deleteCustomer (localhost:8080/customer/6) - METODO DELETE
![Vista de deleteCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/5.png)

- Vista de getOrderCustomer (localhost:8080/order/customer/1) - METODO GET
![Vista de getOrderCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/6.png)

- Vista de getOrderCustomerDates (localhost:8080/order/customer/1/desde/2017-01-07/hasta/2018-01-02) - METODO GET
![Vista de getOrderCustomerDates](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/7.png)

- Vista de getOrderCustomerLastMonth (localhost:8080/order/customer/1/ultimoMes) - METODO GET
![Vista de getOrderCustomerLastMonth](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/8.png)


# Configuracion en jenkins

Pipeline nombre: SpringOrdenes (BackEnd)
Pipeline
 
- Definition: pipeline script
- script:
 
node('master') {

    stage('checkout') {
    
        git 'https://github.com/lmarcela/BackEnd-Ordenes.git'
        
    }
    
    stage('build and test') {
    
       bat 'mvn test' 
       
    }
    
    stage('generate report') {
    
        archive "target/**/*"
        
        junit 'target/surefire-reports/*.xml'
        
    }
    
}

Plugins Recomendados: Blue Ocean, HTML Publisher, JUnit, Test Results Analyzer.

Instalaciones adicionales recomendadas: git, maven.
