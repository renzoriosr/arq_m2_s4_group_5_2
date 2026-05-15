# ADR-001 — Sistema de persistencia (H2 vs Mysql)

**Fecha:** 2026-05-14
**Estado:** ✅ Aceptado
---

## Contexto

El sistema de gestión bancaria tiene la funcionalidad principal de crear cuentas bancarias y transferir dinero entre cuentas. Para esta demo se necesita usar una base de datos ligera, de fácil implementación el cual permita realizar las operaciones descritas.

---

## Decisión

Se ha decidido usar el motor de base de datos H2.

---

## Alternativas consideradas

- **Opción A:** MySql

Es el sistema de gestión de bases de datos relacionales (RDBMS) de código abierto más popular del mundo, ampliamente usado en desarrollo web por su fiabilidad y facilidad. Permite almacenar y gestionar datos estructurados mediante SQL. Es ideal para principiantes y ofrece herramientas gráficas como MySQL Workbench.

- **Opción B:** H2 database - **Elegida**

Es un motor de base de datos relacional ligero, de código abierto y escrito en Java. Ideal para desarrollo y pruebas, funciona en memoria (rápida, volátil) o incrustado, ofreciendo compatibilidad JDBC, SQL estándar y una consola web. Es muy popular en Spring Boot por su fácil integración sin configuración externa.

---

## Consecuencias

### Positivas

- **Velocidad y Ligereza:** Al operar en memoria, H2 es extremadamente rápida, lo que la hace ideal para pruebas unitarias y de integración que requieren respuestas inmediatas.

- **Facilidad de Uso:** Es un archivo JAR simple que no requiere instalación compleja, lo que facilita su integración rápida en proyectos Java.

- **Modo Embebido y en Memoria:** Puede ejecutarse dentro de la propia aplicación (embebido) o desaparecer al cerrarse la aplicación (en memoria), garantizando que las pruebas comiencen con datos limpios.

### Negativas

- **Inconsistencia en Producción:** H2 se comporta diferente a las bases de datos de producción (como PostgreSQL, MySQL o Oracle). Probar con H2 y producir con otra base de datos puede ocultar errores de SQL o de tipos de datos.

- **Volatilidad de Datos:** En el modo "en memoria", si la aplicación se detiene, todos los datos se pierden instantáneamente.

- **No apta para Producción:** Aunque técnicamente posible, no se recomienda utilizar H2 para entornos de producción debido a sus limitaciones de concurrencia y persistencia comparado con motores robustos.



---