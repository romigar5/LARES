**DISEÑO DE ALTA FIDELIDAD DE LA PÁGINA DE DETALLE**
![ESTADO_ENFERMO.png](res/ESTADO_ENFERMO.png)


**ANÁLISIS DE COMPONENTES**
Dentro de la aplicación, se realizaron los mockups de cinco pantallas. A continuación se presenta el
análisis de la PáginaDetalle, que pertenece al mockup de la pantalla_estado_enfermo:

En ella, desglosamos el análisis en cinco componentes distintos que se describen a continuación:
Utilizaremos:
- Rectángulo rojo: column
- Rectángulo verde: row
- Rectángulo rosa: box
- Línea amarilla: separaciones

### 1. Tarjeta Horizontal (Cabecera de Estado)
![Tarjeta Vertical Familiares](res/TarjetaHorizontal%28cabeceraEstado%29.png)

- Layout principal: Row (distribución horizontal).
- Componentes interiores:
    - Image: Ilustración a color del emoji.
    - Column: Apila verticalmente la insignia de estado y los textos correspondientes.
    - Box + Text: Contenedor marrón con el texto de estado.

### 2. Tarjeta Vertical de acción (Atención médica)
![Tarjeta Vertical atención médica](res/TarjetaVertical%28AtencionMedica%29.png)

- Layout principal: Column (distribución vertical).
- **Componentes interiores:**
    - Row: Presenta dos row con información.
    - Box + Icon: Contenedores redondeados para la cruz médica y el teléfono.
    - Button: Contenedor interactivo para la llamada directa al centro médico.
    - Icon flecha.

### 3. Tarjeta Vertical de acción (Red de apoyo)
![Tarjeta Vertical red de apoyo](res/TarjetaVertical%28RedApoyo%29.png)

- Layout principal: Column (distribución vertical)
- **Componentes interiores:**
    - Row: Presenta dos row con información.
    - Box + Icon: Contenedores redondeados para familia y el teléfono.
    - Button: Contenedor interactivo para la llamada directa a familiares.
    - Icon flecha.

### 4. Tarjeta horizontal de aviso de privacidad
![Tarjeta Horizontal aviso de privacidad](res/TarjetaHorizontal%28AvisoPrivacidad%29.png)

- Layout principal: Row (distribución horizontal)
- **Componentes interiores:**
    - Box + Icon: Cápsula verde con el icono de verificación
    - Text: Información acerca de la privacidad de los datos personales.


### 5. Navigation Bar
![NavigationBar](res/NavigationBar.png)

Barra global fijada en la parte inferior,  que distribuye horizontalmente las secciones principales.
-Layout principal: Row (distribución horizontal)
- Column (Layouts Secundarios): Aparecen cuatro.
- **Componentes Fundamentales:**
    - NavigationBarItem / Button: Tres accesos directos estándar (`Inicio`, `Familia`, `Ajustes`) que combinan Icon y Text.
    - FloatingActionButton / Box: Botón destacado central (`Ayuda / SOS`) con fondo rojo elevado, que
      combina Icon + Text.