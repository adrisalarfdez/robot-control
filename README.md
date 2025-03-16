# WV DIGITAL:HUB - Cleaning Robot Control Challenge
## Adrián Salar Fernández

<details>
  <summary>English</summary>

  # 🤖 App short description

  This **Cleaning Robot Control** application runs via console to manage the movement of cleaning robots within a 
  rectangular area of a factory. 

  Besides the area size, it is required to provide the initial state and the instructions for each robot to follow.

  The system allows multiple robots in the same area, preventing them from colliding or going out the established area.

  ---

  ## 🧠 Decisions and assumptions

  * Functionality: 
    * Execution and input:

      The challenge description did not specify a strict execution mode and how the input would be provided. 
  
      Therefore, I decided to execute it as a command and use input text files. 
    
      However, future adjustments could be made to support other execution ways, such as API,
      and other input formats, such as an interactive console input or a database with all configuration stored.
    * Unsupported instructions:
    
      Instead of causing a failure, unsupported instructions are simply ignored in the code.
    
      This decision ensures we don't stop cleaning robots in a factory due to a mistaken instruction.
    * Wrong starting positions:
    
      Even though unsupported instructions are ignored, the initial position and direction of each robot must be valid.

      For instance, if the position is out of grid (1,9 in 6x6 grid, for example), an IndexOutOfBoundsException is fired.
    
      Similarly, if the starting direction is not supported (for instance `T`), it will rely on IllegalArgumentException 
      that Java itself fires when trying to find an invalid enum value.

      These limitations were made to ensure the robots are properly allocated at the beginning of the execution.
    * Preventing valid movement instructions leading to issues:

      When the instructions are valid (`L`, `R` or `M`), there could be situations that would make the robots either go 
      out of the grid or collide with other robots in the area. 
      To prevent this to happen, the system will ignore the `M` instruction and wait until next rotation.

      Alternative approaches to avoid such issues could include forcing a rotation before the `M` instruction, but 
      it would mean that the system needs to decide a movement against the inputted instructions.
    * Visualization (`--show-grid` option argument):
      By using the `--show-grid` option argument, the console shows a grid representing all movements. 
      Even though this adds more execution time, it provides an easy way to visualize every step all robots follow.
  * Tech aspect:
    The structure of this project has been kept as simple as possible, but ensuring each layer has its own purpose, 
    ensuring business logic is on the corresponding domain object. Clarity helps onboarding new developers if needed.
    
    The chosen model allows easier implementation of future improvements such as the ones specified above regarding 
    execution or input formats, and other movement patterns such as skip/jump one cell, use specific cleaning technique 
    or detect obstacles using robot's sensors (other OI device to be considered). Additionally, the application logic 
    can perform its responsibilities without knowing the specifications of domain objects.

    Having Unit tests, even though we could add more cases, allows us to ensure the basic functionality remains working,
    after applying any code changes. During the development, it has been useful to detect mistakes.

  ---

  ## 🚀 Installation and Requirements

  ### ☝️ **Prerequisites**
  - [Java](https://www.oracle.com/java/technologies/downloads/) *(Developed and tested with Java 23)*
  - [Apache Maven](https://maven.apache.org/)

  ### ⬇️ **Clone the Project**
  ```sh
  git clone https://github.com/YOUR-USERNAME/robot-control.git
  cd robot-control
  ```

  ### 💼 **Build**
  By using Maven we ensure we can build the executable package and install all required dependencies:
  ```sh
  mvn clean install
  ```

  Those dependencies are specified in the `pom.xml` file.

  ---

  ## ▶️ Execution

  ### ✏️ Command arguments

  The command supports two optional arguments:
  * Option `--show-grid` to show in Console the grid representing all robot movements.
    By default, the application won't show the grid but jump into the output directly.
  * Input file path. If specified, it'll look for a specific file in your system. 
    By default, the application uses a sample file in the repository.

  ### Command
  To run the application you don't necessarily need to add any arguments. Example:
  ```sh
  java -jar target/robot-control-0.0.1-SNAPSHOT.jar
  ```

  To show the grid, you need to add the `--show-grid` option:
  ```sh
  java -jar target/robot-control-0.0.1-SNAPSHOT.jar --show-grid
  ```

  To use a file from your file system, you can add the full absolute path:
  ```sh
  java -jar target/robot-control-0.0.1-SNAPSHOT.jar {file_path}
  ```
  
  You can also run the application using both arguments, the order does not matter.

  ---
  ## 📖 Input file

  ### 📄 File format
  The **input file** requires a specific format:
  * First row is always the grid size, specifying the coordinates of top-right corner. 
    Example: `5 5` for a grid of 6x6 cells, considering each axis starts with index 0.
  * From second row onwards, every two rows are definitions for each robot to be managed in the area:
    * First row is the initial position of the robot and the direction it is facing. 
      Example: `1 2 N` which would locate the robot in the cell 1,2 and face North.
    * Second row is a set of instructions the robot will follow, where each character represents one instruction.
      Example: `LMRMLM`, where:
      * `L` and `R` will make the robot to rotate to the Left or Right, respectively. 
        In other words, it will change the direction it faces but staying in the same cell).
      * `M` will make the robot to move one cell forward.
      * Any unsupported character will be ignored.

  **Example `input.txt`**, with a grid of 6x6 cells and two robots, starting in 1,1 facing north and 3,3 facing east.
    Each of them with its specific set of instructions.
  ```
  5 5
  1 2 N
  LMLMLMLMM
  3 3 E
  MMRMMRMRRM
  ```
  This example will end up moving the robots to the following final positions and display them in the console:
  ```
  1 3 N
  3 3 E
  ```

  ### ⓘ Important
  If the initial position for any robot is out of the grid bounds, the application will end up with an exception.

  Each robot moves sequentially, and only once one robot has finished all its instructions, the next one will initiate.

  Instructions that would make a robot to go out of the established area or collide with another robot will be ignored.

  ---

  ## 🏗️️ Project structure

  The project ensures a clean separation of responsibilities:
  - `domain/` → Business logic (robot behavior, grid constraints).
  - `application/` → Use cases and coordination.
  - `infrastructure/` → Input/output handling (reading files, displaying results).

  This structure makes it easy to modify or extend the system without affecting core business logic.
  Since every layer is expected to be independent, it should be easy to, for instance, replace the file reader with 
  another kind of IO approach to input the commands, or modify the visualization of the grid.

  ---

  ## 🧪 Running Tests

  This repository contains a set of basic unit tests that can be executed with Maven:
  ```sh
  mvn test
  ```

  Output (end):
  ```
[INFO] Tests run: 18, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
  ```

  Note: following the instructions mentioned above to install the project will also execute the tests.

  Note 2: more tests can be added, such as out of bounds initialization for a robot.

  ---

  ## 🔮 Future Improvements
  - Migrate the project to **Kotlin**.
  - Implement a **Logger** instead of `System.out.println()`.
  - Extend the system with other execution methods, such as **API**, and other inputs, such as direct console input.

</details>

---

<details>
  <summary>Español</summary>

# 🤖 Descripción corta de la aplicación

Esta aplicación de **Control de Robots de Limpieza** se ejecuta mediante consola para gestionar los movimientos de 
robots de limpieza en un área rectangular en una fábrica.

Además del tamaño del área, se necesita conocer el estado inicial y las instrucciones que cada robot debe seguir.

El sistema permite múltiples robots en el mismo perímetro, con prevención de colisiones o salirse del área establecida.

  ---

## 🧠 Decisiones y presunciones

* Functionalidad:
    * Ejecución y entrada de datos:

      La descripción del reto no especificaba un modo de ejecución estricto ni cómo se proveerían los datos de entrada.
  
      Así pues, he decidido ejecutarlo como comando y utilizar ficheros de texto. 
    
      Sin embargo, se podrían hacer futuros ajustes para añadir otros modos de ejecución, como API, y otros formatos de
      entrada, como mediante interacción con la consola o un almacén de configuración mediante base de datos.
    * Instrucciones no soportadas:

      En lugar de causar un fallo, las instrucciones no soportadas son simplemente ignoradas en el código.

      Esta decisión asegura que no se paren los robots de limpieza en una fábrica solo por una instrucción equivocada.
    * Posiciones iniciales erróneas:

      A pesar de que las instrucciones no soportadas son ignoradas, la posición y la dirección iniciales de cada robot 
      deben ser válidas.

      Por ejemplo, una posición fuera del área (1,9 en una cuadrícula de 6x6), provocará una IndexOutOfBoundsException.

      De igual modo, si la dirección inicial no está soportada (por ejemplo, `T`), provoca una IllegalArgumentException
      que Java lanza al intentar encontrar un valor inválido en un enum.

      Esta limitaciones aseguran que los robots están correctamente ubicados al inicio de la ejecución.
    * Evitando instrucciones de movimiento válidas que provocarían problemas:

      Cuando las instrucciones son válidas (`L`, `R` o `M`), podríamos encontrarnos con situaciones que harían que los 
      robots acabaran fuera de la cuadrícula o en la misma celda ocupada por otro robot.
      Para evitar  que esto ocurra, el sistema ignorará esas instrucciones `M` y esperará a la siguiente rotación.

      Otras alternativas para evitar esos problemas podrían incluir la rotación forzosa antes de la instrucción `M`,
      pero significaría que el sistema tendría decidir un movimiento en contra de lo especificado en las instrucciones.
    * Visualización (argumento de opción `--show-grid`):
      Mediante el uso de la opción `--show-grid`, la consola muestra una cuadrícula representando todos los movimientos.
      Aunque esto añade más tiempo de ejecución, proporciona una forma sencilla de ver cada paso que dan los robots.
* Aspectos técnicos:
  La estructura de este proyecto se ha mantenido lo más sencilla posible, pero asegurando que cada capa tiene su propio 
  cometido, dedicando la lógica de negocio a su correspondiente objeto de dominio. 
  Dicha claridad es de ayuda para incorporar nuevos programadores al proyecto si es necesario.

  El modelo escogido permite la implementación sencilla de futuras mejoras como las mencionadas anteriormente sobre
  modos de ejecución y formatos de entrada de datos, así como otros patrones de movimiento, como saltar una celda, 
  usar técnicas de limpieza específicas, o detectar obstáculos usando los sensores del robot (entrada de información a 
  considerar). Además, la lógica de negocio puede llevar a cabo sus responsabilidades sin conocer las especificaciones
  de los objetos de dominio.

  Teniendo tests unitarios, aunque podríamos añadir más casos, podemos asegurarnos de que la funcionalidad básica
  sigue funcionando tras aplicar cambios de código. Durante el desarrollo ha sido útil para detectar errores.

  ---

## 🚀 Instalación y Requisitos

### ☝️ **Prerrequisitos**
- [Java](https://www.oracle.com/java/technologies/downloads/) *(Desarrollado y probado con Java 23)*
- [Apache Maven](https://maven.apache.org/)

### ⬇️ **Clonar el Proyecto**
  ```sh
  git clone https://github.com/YOUR-USERNAME/robot-control.git
  cd robot-control
  ```

### 💼 **Preparativos**
Mediante Maven nos aseguramos de montar el paquete ejecutable e instalar todas las dependencias necesarias:
  ```sh
  mvn clean install
  ```

Dichas dependencias están especificadas en el fichero `pom.xml`.

  ---

## ▶️ Ejecuciónn

### ✏️ Argumentos del comando

El comando permite dos argumentos opcionales:
* La opción `--show-grid` para mostrar en la consola la cuadrícula representando todos los movimientos de los robots.
  Por defecto, la aplicación no mostrará la cuadrícula, sino que mostrará directamente el resultado.
* Ruta del fichero de entrada. Si se especifica, se usará un fichero del sistema de archivos del usuario.
  Por defecto, la aplicación utiliza un fichero de muestra en el repositorio.

### Comando
Para ejecutar la aplicación no es necesario añadir ningún argumento. Ejemplo:
  ```sh
  java -jar target/robot-control-0.0.1-SNAPSHOT.jar
  ```

Para mostrar la cuadrícula, necesitas añadir la opción `--show-grid`:
  ```sh
  java -jar target/robot-control-0.0.1-SNAPSHOT.jar --show-grid
  ```

Para usar un fichero de tu sistema de archivos, puedes añadir la ruta absoluta completa:
  ```sh
  java -jar target/robot-control-0.0.1-SNAPSHOT.jar {file_path}
  ```

También se pueden utilizar ambos argumentos a la vez, sin importar el orden en que se proporcionan.

  ---
## 📖 Fichero de entrada

### 📄 Formato
El **fichero de entrada** debe seguir un formato específico:
* La primera fila siempre indica el tamaño de la cuadrícula, indicando las coordenadas de la esquina superior derecha.
  Ejemplo: `5 5` para una cuadrícula de 6x6 celdas, considerando que cada eje comienza con el índice 0.
* De la segunda fila en adelante, cada dos filas tenemos las definiciones de cada robot que tendremos en el área:
    * La primera fila es la posición inicial del robot y la dirección a la que mira.
      Ejemplo: `1 2 N` ubicará el robot en la celda 1,2 mirando hacia el norte.
    * La segunda fila es el conjunto de instrucciones que el robot debe seguir, donde cada caracter es una instrucción.
      Ejemplo: `LMRMLM`, donde:
        * `L` y `R` harán que el robot rote hacia la izquierda o derecha, respectivamente.
          En otras palabras, cambiará la dirección a la que mira pero permaneciendo en la misma celda.
        * `M` hará que el robot avance una celda en la dirección a la que mira.
        * Cualquier caracter no soportado se ignorará.

**Ejemplo de fichero `input.txt`**, con una cuadrícula de 6x6 celdas y dos robots, empezando en 1,1 mirando al norte y 
3,3 mirando al este. Cada uno con su conjunto específico de instrucciones.
  ```
  5 5
  1 2 N
  LMLMLMLMM
  3 3 E
  MMRMMRMRRM
  ```
Este ejemplo acabará moviendo los robots a las siguientes posiciones finales y las mostrará en la consola:
  ```
  1 3 N
  3 3 E
  ```

### ⓘ Importante
Si la posición inicial de cualqueir robot está fuera de la cuadrícula, la aplicación acabará con una excepción.

Cada robot se mueve secuencialmente, solo cuando uno haya acabado todos sus movimientos, comenzará el siguiente.

Las instrucciones que lleven a un robot fuera de la cuadrícula o a chocarse con otro robot serán ignoradas.

  ---

## 🏗️️ Estructura del proyecto

El proyecto asegura una separación clara de responabilidades:
- `domain/` → lógica de negocio (comportamiento de los robots, restricciones de la cuadrícula).
- `application/` → Coordinación y casos de uso concretos.
- `infrastructure/` → Manejo de las entradas y salidas (leer ficheros, mostrar resultados).

Esta estructura facilita la modificación o extensión del sistema sin afectar la lógica de negocio principal. 
Como se espera que cada capa sea independiente, debería ser fácil de, por ejemplo, reemplazar el lector de ficheros por
otro tipo de entrada de comandos, o modificar la visualización de la cuadrícula.

  ---

## 🧪 Ejecutar Tests

Este repositorio contiene un conjunto de tests unitarios básicos que pueden ejecutarse con Maven:
  ```sh
  mvn test
  ```

Salida (fin):
  ```
[INFO] Tests run: 18, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
  ```

Nota: siguiendo las instrucciones mencionadas arriba para instalar el proyecto también se ejecutarán los tests.

Nota 2: se pueden añadir más tests, como la inicialización fuera de cuadrícula de los robots.

  ---

## 🔮 Mejoras futuras
- Migrar el proyecto a **Kotlin**.
- Implementar un **Logger** en lugar de `System.out.println()`.
- Extender el sistema con otros métodos de ejecución, como una **API**, u otros modos de entrada de datos, como hacerlo 
  directamente mediante consola.

</details>