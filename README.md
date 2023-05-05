# EC (EasyCompiler)

EC es un compilador para el lenguaje de programación *ELL*,  creado con **JavaCC**.

## Pasos para compilar

- Abrir una **terminal**.
- Ejecutar los siguientes comandos:
```shell
$ javacc EC.jj
```

```shell
$ javac compilador/*.java
```

## Probar la fase léxica del compilador

- Abrir una **terminal**
- Ejecutar los siguientes comandos:

```shell
$ javac lexico/TestLexico.java
```

```shell
$ java lexico.TestLexico <archivo_codigo_fuente>
```

