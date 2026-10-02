Las tres tecnologías elegidas son: Android nativo, Kotlin Multiplatform e Ionic.
1. Lenguaje y herramientas necesarias:

En el caso de Android, utilizamos Kotlin o Java en Android Studio. 
Para Kotlin Multiplatform, necesitaremos Kotlin en Android Studio y para PAW, necesitaremos HTML, CSS, 
un navegador y herramientas web.


2. Plataformas que soporta:

Android nativo solamente soporta Android, mientras PWA es soportada por cualquier dispotivo siempre 
que tenga un navegador.
Por su parte Kotlin Multiplatform, también soporta varias plataformas como Android e IOS, escritorio o web.


3. Rendimiento y acceso:

En lo que respecta al rendimiento, PWA sería la peor opción, puesto que el acceso al hardware es muy
limitado. En segundo lugar tendríamos Kotlin Platform, puesto que tiene un redimiento menor que una 
nativa, notable en operaciones gráficas.
Si lo que queremos es un rendimiento máximo, la mejor opción es Android nativo, puesto que no tiene 
capas ni traducciones que ralenticen la app.



4. Coste de desarrollo y mantenimiento:

En lo que respecta al coste, PWA es la opción más económica, puesto que solo requiere un navegador.
Por su parte, Android nativo, tendría un coste medio, si no necesitamos que el proyecto se lleve a cabo
también en IOS, lo que duplicaría el trabajo y el coste. En lo que respecta a Kotlin Multiplatform, 
su coste se reduce porque comparte lógica entre Android e IOS.


5. Caso real de uso:

Android nativo sería la mejor opción para apps que requieran de sensores, como medir la localización
de una persona o detectar movimiento a través del acelerómetro,  puesto que estas funciones se 
actualizan el propio día que salen nuevas APIs.
Kotlin Platform es buena opción para apps de atención al cliente, que comparten una misma lógica,
pero cada plataforma mantiene su propia interfaz. Esto permite la reutilización de la lógica y disminuir
costes.
PWA sería la mejor opción para aquellas apps sencillas que no necesitan un acceso profundo al hardware 
y que no necesitan Google Play, sino un navegador. Una app de compras cumpliría estos requisitos perfectamente, 
puesto que ofrece una cantidad de contenidos y puede abrirse en cualquier disposito con el navegador.
