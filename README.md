# 🏥 Sistema de Gestión Clínica - Clínica Salvador

Este proyecto es un sistema integral para la administración del personal médico, gestión de pacientes y agendamiento dinámico de citas médicas en tiempo real. Desarrollado bajo los principios rigurosos de **Arquitectura Limpia (Hexagonal)**, garantiza un software desacoplado, altamente mantenible y escalable.

---

**Link de despliegue**: https://clinica-salvador-parcial.onrender.com/medicos

---

## 👤 Información del Autor y Contexto Académico

| Campo | Detalle |
| :--- | :--- |
| **Estudiante** | Alvaro Jesus Perez Menendez |
| **Identificación Académica** | 4° Semestre |
| **Institución** | Universidad de Cartagena |
| **Programa Académico** | Carrera de Ingeniería de Software |
| **Asignatura** | Ingeniería de Software |
| **Tutor / Docente** | Carlos Jose Caceres Ochoa |

---

## 🏗️ Arquitectura del Proyecto (Estructura en Árbol)

El proyecto implementa **Arquitectura Hexagonal**, separando estrictamente las reglas de negocio (Dominio) de los frameworks y detalles de infraestructura (Base de datos, Controladores web, APIs).

```text
📁 parcial/
│
├── 📁 domain/                           # 🧩 CAPA DE DOMINIO (Reglas de Negocio Puras)
│   ├── 📁 models/                       # Entidades de negocio principales
│   │   ├── 📄 Cita.java
│   │   ├── 📄 Medico.java
│   │   └── 📄 Paciente.java
│   ├── 📁 ValueObjects/                 # Objetos de valor inmutables
│   │   └── 📄 Horario.java
│   ├── 📁 ports/                        # Interfaces de comunicación
│   │   ├── 📁 in/                       # Puertos de Entrada (Casos de Uso)
│   │   │   └── 📄 AgendarCitaUseCase.java
│   │   └── 📁 out/                      # Puertos de Salida (Persistencia/SPI)
│   │       ├── 📄 CitaRepositoryPort.java
│   │       ├── 📄 MedicoRepositoryPort.java
│   │       └── 📄 PacienteRepositoryPort.java
│   └── 📁 usecases/                     # Implementación de los Casos de Uso
│       └── 📄 AgendarCitaUseCaseImpl.java
│
└── 📁 infrastructure/                   # ⚙️ CAPA DE INFRAESTRUCTURA (Frameworks y Adaptadores)
    ├── 📁 adapters/                     # Adaptadores del sistema
    │   └── 📁 out/                      # Adaptadores de Salida (Hacia el mundo exterior)
    │       └── 📁 persistence/          # Persistencia Relacional y Mapeadores JPA
    │           ├── 📄 HorarioEntity.java
    │           ├── 📄 MedicoEntity.java
    │           ├── 📄 MedicoRepositoryAdapter.java
    │           └── 📄 SpringDataMedicoRepository.java
    ├── 📁 config/                       # Configuraciones del Sistema e Inyección de Beans
    │   └── 📄 CitaConfig.java
    ├── 📁 input/                        # Adaptadores de Entrada (Hacia nuestra App)
    │   └── 📁 rest/                     # Controladores REST API y Controladores de Vistas
    │       ├── 📄 CitaRestController.java
    │       ├── 📄 MedicoRestController.java
    │       ├── 📄 PacienteRestController.java
    │       └── 📄 ViewController.java   # Controlador ThymeLeaf para el Front-End
    └── 📁 resources/                    # Recursos Estáticos y Configuración Interna
        ├── 📁 templates/                # Plantillas HTML5 Dinámicas (Thymeleaf)
        │   ├── 📄 medicos-lista.html
        │   ├── 📄 pacientes-lista.html
        │   └── 📄 citas-agenda.html
        └── 📄 application.properties    # Variables de Entorno del Sistema
```

---

## 🛠️ Tecnologías Utilizadas

**Lenguaje de Programacion** : JAVA 21 

**Framework Principale**     : Spring Boot 3.x |

**Motor de Plantillas**      : Thymeleaf HTML5 |

**Estilos y UI**             : Universidad de Cartagena |

**ORM / Persistencia**       : Spring Data JPA & Hibernate 7 |

**Asistente IA**             : Gemini

**Asistente Grafico IA**     : Google Stich

---

## 📦 Dependencias (Maven pom.xml)

```text
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

---

## 🚀 Tecnologías de Despliegue y Base de Datos

**SUPABASE**: Instancia administrada de PostgreSQL 17 en la nube. Conexión establecida de forma segura mediante Poolers de conexión JDBC remotos sobre SSL

**Render**: Despliegue automatizado sincronizado con la rama main de GitHub. Escanea el puerto dinámico de Tomcat automáticamente y compila el binario ejecutable en vivo.

---

## 📬 Comandos Postman Utilizados (Pruebas del API Rest)

```text
Para validar la lógica y restricciones de la API en las fases tempranas del backend, se ejecutaron las siguientes peticiones HTTP orientadas a los endpoints JSON:

1. Gestión de Médicos
Crear Nuevo Médico

Método: POST

URL: http://localhost:8080/api/v1/medicos

Cuerpo (JSON):

JSON
{
  "id": "1047444333",
  "nombreCompleto": "Dr. Alvaro Gaviria",
  "specialty": "Pediatría"
}
2. Módulo de Pacientes
Registrar Admisión

Método: POST

URL: http://localhost:8080/api/v1/pacientes

Cuerpo (JSON):

JSON
{
  "id": "1098765432",
  "nombreCompleto": "Carlos Mendoza",
  "email": "carlos.mendoza@email.com",
  "telefono": "3159998877"
}
3. Agendamiento de Consultas (Con Reglas de Negocio)
Reservar Espacio

Método: POST

URL: http://localhost:8080/api/v1/citas

Cuerpo (JSON):

JSON
{
  "codigoCita": "APT-2026-001",
  "pacienteId": "1098765432",
  "medicoId": "1047444333",
  "fechaHoraInicio": "2026-06-25T09:00:00",
  "fechaHoraFin": "2026-06-25T10:00:00",
  "estado": "AGENDADA"
}
```



