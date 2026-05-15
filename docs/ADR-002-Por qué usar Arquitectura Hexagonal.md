# ADR-001 — Sistema de persistencia (H2 vs Mysql)

**Fecha:** 2026-05-14
**Estado:** ✅ Aceptado
---

## Contexto

El sistema de gestión bancaria tiene la funcionalidad principal de crear cuentas bancarias y transferir dinero entre cuentas. Para esta demo se necesita usar una base de datos ligera, de fácil implementación el cual permita realizar las operaciones descritas.

---

## Decisión

Se ha decidido usar la arquitectura hexagonal por las caracteristicas que se van de acorde a la necesidad.

---

## Alternativas consideradas

- **Opción A:** Arquitectura hexagonal - **Elegida**

Aísla el núcleo funcional de una aplicación (dominio y casos de uso) de los detalles externos como bases de datos, APIs o frameworks. Se basa en tres capas (dominio, aplicación, infraestructura) para lograr código escalable, testeable y mantenible.

- **Opción B:** Arquitectura MVC

Es un patrón de arquitectura de software que divide una aplicación en tres componentes interconectados. Su objetivo principal es separar la lógica de negocio y los datos de la interfaz de usuario, lo que permite un código más organizado, mantenible y escalable.

---

## Consecuencias

### Positivas

- **Alta Mantenibilidad y Evolución:** Al separar la lógica de negocio del exterior, los cambios en tecnologías externas (como cambiar de base de datos) no afectan al núcleo, facilitando la evolución del sistema.

- **Testeabilidad superior:** Permite realizar pruebas unitarias del núcleo de negocio sin necesidad de depender de bases de datos o servicios externos, lo que resulta en pruebas más rápidas y fiables.

- **Desacoplamiento Tecnológico:** La infraestructura depende de la aplicación, y no al revés. Esto facilita el intercambio de componentes (por ejemplo, cambiar Kafka por RabbitMQ) sin alterar el comportamiento de la aplicación.


### Negativas
- **Complejidad Inicial Elevada:** Requiere un mayor esfuerzo inicial para configurar la estructura de puertos y adaptadores, lo que puede ser excesivo para proyectos sencillos.

- **Curva de Aprendizaje Pronunciada:** Para equipos no familiarizados con principios SOLID o Domain-Driven Design (DDD), puede resultar difícil comprender dónde ubicar cada elemento.

- **No apta para MVPs o Proyectos Pequeños:** En prototipos o aplicaciones con tiempo limitado, la rigidez estructural puede reducir la velocidad de desarrollo, lo que la hace desaconsejable si no se busca una larga vida útil.

---