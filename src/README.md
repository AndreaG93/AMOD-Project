![](https://img.shields.io/badge/Programming_Language-Java-blue.svg)
![](https://img.shields.io/badge/Release-1.0-blue.svg)
![](https://img.shields.io/badge/Status-Tested-green.svg)

# AMOD-Project
Repository used for AMOD ("Algoritmi e Modelli per l'Ottimizzazione Discreta") project. AA 2018/2019.

## Requirement

1. **Microsoft Windows 10** 
1. **JDK 12.0.2** 
1. **Gurobi 8.1.1**
1. **JAVA FX 12.0.2** 

In order to run our application, users have to modify `PATH` environment variable as follows:

2. add the directory `javafx-sdk-12.0.2\lib` to `PATH` environment variable (`javafx-sdk-12.0.2` folder is included into our repository).
2. add the directory `jdk-12.0.2\bin` to `PATH` environment variable (`jdk-12.0.2\bin` folder is into Windows default installation directory (`C:\Program Files\`)).
2. add the directory `%GUROBI_HOME%\bin` to `PATH` environment variable, where `%GUROBI_HOME%` is the environment variable containing default Gurobi installation directory.

## How to run

Open Powershell Terminal and execute following commands:

3. `git clone https://github.com/AndreaG93/AMOD-Project.git` 
3. `cd .\AMOD-Project`
3. `java --module-path ".\src\libraries\javafx-sdk-12.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar .\AMOD-Project.jar` 

Don't forget to set `PATH` environment variable!