package cep;

public class Main {

    public static void main(String[] args) {
        pascal.taie.Main.main(
                "-pp",
                "--class-path", "CustomizeEntryPoints/analyzed-programs",
                "--output-dir", "CustomizeEntryPoints/output",
                "--input-classes", "CustomEntryPoints",
                "-a", "pta=implicit-entries:false;only-app:true;dump:true;"
                        + "plugins:[cep.CustomEntryPointPlugin];",
                "-a", "cg=dump:true;dump-methods:true;dump-call-edges:true;"
        );
    }

}
