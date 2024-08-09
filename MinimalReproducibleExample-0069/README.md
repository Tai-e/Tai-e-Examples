# Minimal Reproducible Example - [Issue 69](https://github.com/pascal-lab/Tai-e/issues/69)

> This project provides a convenient minimal reproducible example for the Tai-e's Issue, specifically reproducing [Issue 69](https://github.com/pascal-lab/Tai-e/issues/69).

## Project Structure

- `analyzed-programs/`
  Contains the program to be analyzed, including both source (`.java`) and compiled (`.class`) files.
  
  - `Main.java` / `Main.class`
  - `SourceSink.java` / `SourceSink.class`
  
- `configs/`
  Contains configuration files for the Tai-e tool.
  
  - `options.yml` - Tai-e's arguments.
  - `taint-config.yml` - Configuration for Taint analysis.
  
- `src/main/java/issue/Main.java`
  Entry point for running the analysis.

  ```java
  public static void main(String[] args) {
      pascal.taie.Main.main(
          "--options-file",
          "MinimalReproducibleExample-0069/configs/options.yml"
      );
  }
  ```

- `pom.xml`
  Maven configuration file.
